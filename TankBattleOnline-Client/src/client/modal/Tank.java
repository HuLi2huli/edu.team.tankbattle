package client.modal;

import client.control.command.Explode;
import client.control.event.TankHitEvent;
import client.control.event.TankHitListener;
import client.control.net.protocol.TankDeadMsg;
import client.control.net.protocol.TankMoveMsg;
import client.control.net.protocol.TankReduceBloodMsg;
import client.control.strategy.FireAction;
import client.control.strategy.NormalFireAction;
import client.parameter.Dir;
import client.parameter.Images;
import client.parameter.ItemsType;
import client.parameter.Musics;
import client.view.TankClient;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;

public class Tank implements TankHitListener { // 坦克 //实现坦克被子弹击中的监听
	private int id;

	public static final int XSPEED = 5;
	public static final int YSPEED = 5;
	public static final int Width = 35;
	public static final int Height = 35;

	private String name;
	private boolean good;
	private int x, y;
	private boolean live = true;
	private TankClient tc;
	// private boolean bL, bU, bR, bD;
	private Dir dir = Dir.STOP;
	private Dir ptDir = Dir.U;
	private int blood = 100;
	private BloodBar bb = new BloodBar();
	private FireAction fireAction = new NormalFireAction();// 可以开火

	private static Image[] imgs = Images.imagesTank;
	private static Map<String, Image> map = new HashMap<>();
	static {
		map.put("tD", imgs[0]);
		map.put("tL", imgs[1]);
		map.put("tLD", imgs[2]);
		map.put("tLU", imgs[3]);
		map.put("tR", imgs[4]);
		map.put("tRD", imgs[5]);
		map.put("tRU", imgs[6]);
		map.put("tU", imgs[7]);
	}

	public Tank(int x, int y, boolean good, String name) {
		this.x = x;
		this.y = y;
		this.good = good;
		this.name = name;
	}

	public Tank(String name, int x, int y, boolean good, Dir dir, TankClient tc) {
		this(x, y, good, name);
		this.dir = dir;
		this.tc = tc;
	}

	/**
	 * 根据坦克阵营画出图片
	 * 
	 * @param g
	 */
	public void draw(Graphics g) {
		if (!live) {
			if (!good) {
				tc.getTanks().remove(this);
			}
			return;
		}
		// System.out.println(x + "," + y);
		switch (ptDir) {
		case L:
			g.drawImage(map.get("tL"), x, y, Width, Height, null);
			break;
		case LU:
			g.drawImage(map.get("tLU"), x, y, Width, Height, null);
			break;
		case U:
			g.drawImage(map.get("tU"), x, y, Width, Height, null);
			break;
		case RU:
			g.drawImage(map.get("tRU"), x, y, Width, Height, null);
			break;
		case R:
			g.drawImage(map.get("tR"), x, y, Width, Height, null);
			break;
		case RD:
			g.drawImage(map.get("tRD"), x, y, Width, Height, null);
			break;
		case D:
			g.drawImage(map.get("tD"), x, y, Width, Height, null);
			break;
		case LD:
			g.drawImage(map.get("tLD"), x, y, Width, Height, null);
			break;
		}
		g.drawString(name, x, y - 20);
		bb.draw(g);// 画出血条
		move();
	}

	/**
	 * 根据坦克的方向进行移动
	 */
	private void move() {
		int oldx = x;
		int oldy = y;
		switch (dir) {
		case L:
			x -= XSPEED;
			break;
		case LU:
			x -= XSPEED;
			y -= YSPEED;
			break;
		case U:
			y -= YSPEED;
			break;
		case RU:
			x += XSPEED;
			y -= YSPEED;
			break;
		case R:
			x += XSPEED;
			break;
		case RD:
			x += XSPEED;
			y += YSPEED;
			break;
		case D:
			y += YSPEED;
			break;
		case LD:
			x -= XSPEED;
			y += YSPEED;
			break;
		case STOP:
			break;
		}

		if (dir != Dir.STOP) {
			ptDir = dir;
			Musics.musicMove.play(1);
		}
		if (isCollisionMap(BattleMap.map)) {
			x = oldx;
			y = oldy;
		}
		if (x < 0)
			x = 0;
		if (y < Height)
			y = Height;
		if (x + Width > TankClient.GAME_Width) {
			x = TankClient.GAME_Width - Width;
		}
		if (y + Height > TankClient.GAME_Height) {
			y = TankClient.GAME_Height - Height;
		}
	}

	/**
	 * 监听键盘按下, 上下左右移动分别对应WSAD
	 * 
	 * @param e
	 */
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		// switch (key) {
		// case KeyEvent.VK_A:
		// bL = true;
		// break;
		// case KeyEvent.VK_W:
		// bU = true;
		// break;
		// case KeyEvent.VK_D:
		// bR = true;
		// break;
		// case KeyEvent.VK_S:
		// bD = true;
		// break;
		// }
		// locateDirection();
		switch (key) {
		case KeyEvent.VK_A:
			dir = Dir.L;
			break;
		case KeyEvent.VK_W:
			dir = Dir.U;
			break;
		case KeyEvent.VK_D:
			dir = Dir.R;
			break;
		case KeyEvent.VK_S:
			dir = Dir.D;
			break;
		}
		locateDirection();

	}

	/**
	 * 根据4个方向的布尔值判断坦克的方向
	 */
	private void locateDirection() {
		// Dir oldDir = this.dir;
		// if (bL && !bU && !bR && !bD)
		// dir = Dir.L;
		// else if (bL && bU && !bR && !bD)
		// dir = Dir.LU;
		// else if (!bL && bU && !bR && !bD)
		// dir = Dir.U;
		// else if (!bL && bU && bR && !bD)
		// dir = Dir.RU;
		// else if (!bL && !bU && bR && !bD)
		// dir = Dir.R;
		// else if (!bL && !bU && bR && bD)
		// dir = Dir.RD;
		// else if (!bL && !bU && !bR && bD)
		// dir = Dir.D;
		// else if (bL && !bU && !bR && bD)
		// dir = Dir.LD;
		// else if (!bL && !bU && !bR && !bD)
		// dir = Dir.STOP;
		//
		// if (dir != oldDir) {
		TankMoveMsg msg = new TankMoveMsg(id, x, y, dir, ptDir);
		tc.getNc().send(msg);

		// }
	}

	/**
	 * 监听键盘释放
	 * 
	 * @param e
	 */
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		// switch (key) {
		// case KeyEvent.VK_J:// 监听到J键按下则开火
		// fire();
		// break;
		// case KeyEvent.VK_A:
		// bL = false;
		// break;
		// case KeyEvent.VK_W:
		// bU = false;
		// break;
		// case KeyEvent.VK_D:
		// bR = false;
		// break;
		// case KeyEvent.VK_S:
		// bD = false;
		// break;
		// }
		switch (key) {
		case KeyEvent.VK_J:// 监听到J键按下则开火
			fire();
			break;
		case KeyEvent.VK_A:
			dir = Dir.STOP;
			break;
		case KeyEvent.VK_W:
			dir = Dir.STOP;
			break;
		case KeyEvent.VK_D:
			dir = Dir.STOP;
			break;
		case KeyEvent.VK_S:
			dir = Dir.STOP;
			break;
		}
		locateDirection();
	}

	public void fire() {// 发出一颗炮弹的方法
		fireAction.fireAction(this);
	}

	@Override
	public void actionToTankHitEvent(TankHitEvent tankHitEvent) {
		this.tc.getExplodes()
				.add(new Explode(tankHitEvent.getSource().getX() - 20, tankHitEvent.getSource().getY() - 20, this.tc));// 坦克自身产生一个爆炸
		if (this.blood == 20) {// 坦克每次扣20滴血, 如果只剩下20滴了, 那么就标记为死亡.
			this.live = false;
			TankDeadMsg msg = new TankDeadMsg(this.id);// 向其他客户端转发坦克死亡的消息
			this.tc.getNc().send(msg);
			this.tc.getNc().sendClientDisconnectMsg();// 和服务器断开连接
			this.tc.gameOver();
			return;
		}
		this.blood -= 20;// 血量减少20并通知其他客户端本坦克血量减少20.
		TankReduceBloodMsg msg = new TankReduceBloodMsg(this.id, tankHitEvent.getSource());
		this.tc.getNc().send(msg);
	}

	/**
	 * 血条
	 */
	private class BloodBar {
		public void draw(Graphics g) {
			Color c = g.getColor();
			g.setColor(Color.BLACK);
			g.drawRect(x, y - 15, Width, 8);
			int w = (Width * blood) / 100;
			g.setColor(Color.RED);
			g.fillRect(x, y - 15, w, 8);
			g.setColor(c);
		}
	}

	public Rectangle getRect() {
		// return new Rectangle(x, y, imgs[0].getWidth(null),
		// imgs[0].getHeight(null));
		return new Rectangle(x, y, Width, Height);
	}

	public boolean isCollisionMap(int[][] map) {// 坦克和地图碰撞检测
		Rectangle a = new Rectangle(x, y, Width, Height);
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				Rectangle b = new Rectangle(j * BattleMap.Width, i * BattleMap.Height, BattleMap.Width,
						BattleMap.Height);
				if (a.intersects(b)) {
					if (!ItemsType.isCollisionEnable(map[i][j])) {
						// System.out.println("碰撞地图");
						return true;
					}
				}
			}
		}
		return false;
	}

	public boolean isLive() {
		return live;
	}

	public void setLive(boolean live) {
		this.live = live;
	}

	public boolean isGood() {
		return good;
	}

	public void setGood(boolean good) {
		this.good = good;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Dir getDir() {
		return dir;
	}

	public void setDir(Dir dir) {
		this.dir = dir;
	}

	public Dir getPtDir() {
		return ptDir;
	}

	public void setPtDir(Dir ptDir) {
		this.ptDir = ptDir;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBlood() {
		return blood;
	}

	public void setBlood(int blood) {
		this.blood = blood;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public TankClient getTc() {
		return tc;
	}

	public void setTc(TankClient tc) {
		this.tc = tc;
	}
}
