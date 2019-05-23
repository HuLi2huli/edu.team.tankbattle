package client.view;

import client.control.command.Explode;
import client.control.net.NetClient;
import client.control.net.protocol.MissileDeadMsg;
import client.jdbc.po.User;
import client.modal.BattleMap;
import client.modal.Missile;
import client.modal.Tank;
import client.parameter.Dir;
import client.parameter.Musics;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class TankClient extends Frame {
	
	public TankClient()  {
		
	}
	
	public static final int GAME_Width = 800;

	public static final int GAME_Height = 800;
	private Image offScreenImage = null;
	private Tank myTank;// 客户端的坦克
	private static int RoomId;	//客户端所处的房间号
	private NetClient nc;
	private ConDialog dialog = new ConDialog();
	private GameOverDialog gameOverDialog = new GameOverDialog();
	private UDPPortWrongDialog udpPortWrongDialog = new UDPPortWrongDialog();
	private ServerNotStartDialog serverNotStartDialog = new ServerNotStartDialog();

	private List<Missile> missiles = new ArrayList<>();// 存储游戏中的子弹集合
	private List<Explode> explodes = new ArrayList<>();// 爆炸集合
	private List<Tank> tanks = new ArrayList<>();// 坦克集合

	@Override
	public void paint(Graphics g) {
		BattleMap.drawItems(g);
		for (int i = 0; i < missiles.size(); i++) {
			Missile m = missiles.get(i);
			if (m.hitTank(myTank)) {
				MissileDeadMsg mmsg = new MissileDeadMsg(m.getTankId(), m.getId());
				nc.send(mmsg);
			}
			m.draw(g);
		}
		for (int i = 0; i < explodes.size(); i++) {
			Explode e = explodes.get(i);
			e.draw(g);
		}
		for (int i = 0; i < tanks.size(); i++) {
			Tank t = tanks.get(i);
			t.draw(g);
		}
		if (null != myTank) {
			myTank.draw(g);
		}
		BattleMap.drawItems2(g);
		// for (int i = 0; i < 1; i++) {
		// for (int j = 0; j < 20; j++) {
		// System.out.print(BattleMap.map[i][j]);
		// }
		// }
		// System.out.println("\n===============================================");
	}

	@Override
	public void update(Graphics g) {
		if (offScreenImage == null) {
			offScreenImage = this.createImage(GAME_Width, GAME_Height);
		}
		Graphics gOffScreen = offScreenImage.getGraphics();
		Color c = gOffScreen.getColor();
		gOffScreen.setColor(Color.BLACK);
		gOffScreen.fillRect(0, 0, GAME_Width, GAME_Height);
		gOffScreen.setColor(c);
		paint(gOffScreen);
		g.drawImage(offScreenImage, 0, 0, null);
	}

	public void launchFrame() {
		this.setBounds(500, 100, GAME_Width, GAME_Height);
		this.setTitle("TankClient");
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				nc.sendClientDisconnectMsg();// 关闭窗口前要向服务器发出注销消息.
				TankClient.this.dispose();
			}
		});
		this.setResizable(false);
		this.setBackground(Color.LIGHT_GRAY);

		this.addKeyListener(new KeyMonitor());

		this.setVisible(true);

		new Thread(new PaintThread()).start();

		dialog.setVisible(true);
	}

	/**
	 * 重画线程
	 */
	class PaintThread implements Runnable {
		public void run() {
			while (true) {
				repaint();
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	class KeyMonitor extends KeyAdapter {

		@Override
		public void keyReleased(KeyEvent e) {
			myTank.keyReleased(e);
		}

		@Override
		public void keyPressed(KeyEvent e) {
			myTank.keyPressed(e);
		}
	}

	/**
	 * 游戏开始前连接到服务器的对话框
	 */
	class ConDialog extends Dialog {
		Button b = new Button("connect to server");
//		TextField tfIP = new TextField("119.29.139.239", 15);// 服务器的IP地址
		TextField tfIP = new TextField("127.0.0.1", 15);// 服务器的IP地址
		TextField RoomIdTF = new TextField("", 8);

		public ConDialog() {
			super(TankClient.this, true);
			this.setLayout(new FlowLayout());
			this.add(new Label("server IP:"));
			this.add(tfIP);
			this.add(new Label("Room Id:"));
			this.add(RoomIdTF);
			this.add(b);
			this.setLocation(500, 400);
			this.pack();
			this.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					setVisible(false);
					TankClient.this.dispose();
				}
			});
			b.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					String IP = tfIP.getText().trim();
					if(!"".equals(IP) && !"".equals(RoomIdTF.getText().trim())){
						int ri = Integer.parseInt(RoomIdTF.getText().trim());	//获取输入的房间号
						TankClient.setRoomId(ri);
						System.out.println(TankClient.RoomId);
						String tankName = GameUI.user.getName();
						myTank = new Tank(tankName, 500, 700, true, Dir.STOP, TankClient.this);
						nc = new NetClient(TankClient.this);
						nc.setRoomId(ri);
						nc.connect(IP);
						GameUI.chatPanel.setNc(nc);
						TankClient.this.setTitle("TankClient   Room ID: " + getRoomId() + "  Player Name:" + GameUI.getUser().getName());
						setVisible(false);
						Musics.musicGetHp.play(1);
					}
				}
			});

			Musics.musicStart.play(1);
		}
	}

	/**
	 * 坦克死亡后退出的对话框
	 */
	class GameOverDialog extends Dialog {
		Button b = new Button("exit");

		public GameOverDialog() {
			super(TankClient.this, true);
			this.setLayout(new FlowLayout());
			this.add(new Label("Game Over~"));
			this.add(b);
			this.setLocation(500, 400);
			this.pack();
			this.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					TankClient.this.dispose();
				}
			});
			b.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					TankClient.this.dispose();
				}
			});
		}
	}

	/**
	 * UDP端口分配失败后的对话框
	 */
	public class UDPPortWrongDialog extends Dialog {
		Button b = new Button("ok");

		public UDPPortWrongDialog() {
			super(TankClient.this, true);
			this.setLayout(new FlowLayout());
			this.add(new Label("something wrong, please connect again"));
			this.add(b);
			this.setLocation(500, 400);
			this.pack();
			this.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					TankClient.this.dispose();
				}
			});
			b.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					TankClient.this.dispose();
				}
			});
		}
	}

	/**
	 * 连接服务器失败后的对话框
	 */
	public class ServerNotStartDialog extends Dialog {
		Button b = new Button("ok");

		public ServerNotStartDialog() {
			super(TankClient.this, true);
			this.setLayout(new FlowLayout());
			this.add(new Label("The server has not been opened yet..."));
			this.add(b);
			this.setLocation(500, 400);
			this.pack();
			this.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					TankClient.this.dispose();
				}
			});
			b.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					TankClient.this.dispose();
				}
			});
		}
	}

	public void gameOver() {
		Musics.musicGameOver.play(1);
		this.gameOverDialog.setVisible(true);
	}

	public List<Missile> getMissiles() {
		return missiles;
	}

	public void setMissiles(List<Missile> missiles) {
		this.missiles = missiles;
	}

	public List<Explode> getExplodes() {
		return explodes;
	}

	public void setExplodes(List<Explode> explodes) {
		this.explodes = explodes;
	}

	public List<Tank> getTanks() {
		return tanks;
	}

	public void setTanks(List<Tank> tanks) {
		this.tanks = tanks;
	}

	public Tank getMyTank() {
		return myTank;
	}

	public void setMyTank(Tank myTank) {
		this.myTank = myTank;
	}

	public NetClient getNc() {
		return nc;
	}

	public void setNc(NetClient nc) {
		this.nc = nc;
	}

	public UDPPortWrongDialog getUdpPortWrongDialog() {
		return udpPortWrongDialog;
	}

	public ServerNotStartDialog getServerNotStartDialog() {
		return serverNotStartDialog;
	}

	public static int getGameWidth() {
		return GAME_Width;
	}

	public static int getGameHeight() {
		return GAME_Height;
	}

	public static int getRoomId() {
		return RoomId;
	}

	public static void setRoomId(int roomId) {
		RoomId = roomId;
	}
}