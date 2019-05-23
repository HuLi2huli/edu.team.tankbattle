package client.control.net;

import client.control.net.protocol.MessageMsg;
import client.control.net.protocol.MissileDeadMsg;
import client.control.net.protocol.MissileNewMsg;
import client.control.net.protocol.Msg;
import client.control.net.protocol.TankAlreadyExistMsg;
import client.control.net.protocol.TankDeadMsg;
import client.control.net.protocol.TankMoveMsg;
import client.control.net.protocol.TankNewMsg;
import client.control.net.protocol.TankReduceBloodMsg;
import client.jdbc.po.Map;
import client.jdbc.service.MapService;
import client.modal.BattleMap;
import client.modal.Tank;
import client.view.GameUI;
import client.view.TankClient;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * 网络方法接口
 */
public class NetClient {

	private TankClient tc;
	private int UDP_PORT;// 客户端的UDP端口号
	private int TCP_PORT = 55555;
	private String serverIP;// 服务器IP地址
	private int serverUDPPort;// 服务器转发客户但UDP包的UDP端口
	private int TANK_DEAD_UDP_PORT;// 服务器监听坦克死亡的UDP端口
	private int RoomId;
	private DatagramSocket ds = null;// 客户端的UDP套接字
	public void setUDP_PORT(int UDP_PORT) {
		this.UDP_PORT = UDP_PORT;
	}

	public NetClient(TankClient tc) {
		this.tc = tc;
		try {
			this.UDP_PORT = getRandomUDPPort();
		} catch (Exception e) {
			tc.getUdpPortWrongDialog().setVisible(true);// 弹窗提示
			System.exit(0);// 如果选择到了重复的UDP端口号就退出客户端重新选择.
		}
	}

	/**
	 * 与服务器进行TCP连接
	 * 
	 * @param ip
	 *            server IP
	 */
	public void connect(String ip) {
		serverIP = ip;
		Socket s = null;
		try {
			ds = new DatagramSocket(UDP_PORT);// 创建UDP套接字
			try {
				s = new Socket(ip, TCP_PORT);// 创建TCP套接字
			} catch (Exception e1) {
				tc.getServerNotStartDialog().setVisible(true);
			}
			DataOutputStream dos = new DataOutputStream(s.getOutputStream());
			dos.writeInt(UDP_PORT);// 向服务器发送自己的UDP端口号
			dos.writeInt(RoomId);// 向服务器发送自己选择的房间号
			DataInputStream dis = new DataInputStream(s.getInputStream());
			int id = dis.readInt();// 获得自己的id号
			int pid = dis.readInt(); // 在本房间内的角色代号,p1 p2 p3等
			this.serverUDPPort = dis.readInt();// 获得服务器转发客户端消息的UDP端口号
			this.TANK_DEAD_UDP_PORT = dis.readInt();// 获得服务器监听坦克死亡的UDP端口

			/*
			 * 加载地图 此处地图id应由玩家指定，通过网络广播 因为未实现开房间和地图的模块 所以把房间号和地图id绑定
			 */
			Map map = MapService.findMap(RoomId);
			BattleMap.parseMap(map);

			tc.getMyTank().setId(id);// 设置坦克的id号
			int w = TankClient.GAME_Width;
			int h = TankClient.GAME_Height;
			int tw = Tank.Width;
			int th = Tank.Height;
			switch (pid % 4 == 0 ? 4 : pid % 4) { //根据pid 表示坦克在本房间中的编号, 设置坦克出生位置，避免玩家在重合位置出生
			case 1:
				tc.getMyTank().setX(0);
				tc.getMyTank().setY(h - th);
				break;
			case 2:
				tc.getMyTank().setX(w - tw);
				tc.getMyTank().setY(h - th);
				break;
			case 3:
				tc.getMyTank().setX(w - tw);
				tc.getMyTank().setY(0);
				break;
			case 4:
				tc.getMyTank().setX(0);
				tc.getMyTank().setY(0);
				break;
			}
			tc.getMyTank().setGood(  (id & 1) == 0 ? true : false   );// 根据坦克的id号分配阵营
			System.out.println("connect to server successfully...");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (s != null)
					s.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		new Thread(new UDPThread()).start();// 开启客户端UDP线程, 向服务器发送或接收游戏数据

		TankNewMsg msg = new TankNewMsg(tc.getMyTank());// 创建坦克出生的消息
		send(msg);
	}

	/**
	 * 客户端随机获取UDP端口号
	 * 
	 * @return
	 */
	private int getRandomUDPPort() {
		return 55558 + (int) (Math.random() * 9000);
	}

	public void send(Msg msg) {
		//System.out.println(serverIP+serverUDPPort);
		msg.send(ds, serverIP, serverUDPPort, TankClient.getRoomId());
	}
	
	public class UDPThread implements Runnable {

		byte[] buf = new byte[1024];

		@Override
		public void run() {
			while (null != ds) {
				DatagramPacket dp = new DatagramPacket(buf, buf.length);
				try {
					ds.receive(dp);
					parse(dp);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		private void parse(DatagramPacket dp) {
			ByteArrayInputStream bais = new ByteArrayInputStream(buf, 0, dp.getLength());
			DataInputStream dis = new DataInputStream(bais);
			int roomid = 0;
			int msgType = 0;
			try {
				roomid = dis.readInt(); // 获取房间号
				if (RoomId != roomid) { // 不是自己的房间,放弃消息
					return;
				}
				msgType = dis.readInt();// 获得消息类型
			} catch (IOException e) {
				e.printStackTrace();
			}
			//System.out.println(msgType);
			Msg msg = null;
			switch (msgType) {// 根据消息的类型调用对应消息的解析方法
			case Msg.TANK_NEW_MSG:
				msg = new TankNewMsg(tc);
				msg.parse(dis);
				break;
			case Msg.TANK_MOVE_MSG:
				msg = new TankMoveMsg(tc);
				msg.parse(dis);
				break;
			case Msg.MISSILE_NEW_MSG:
				msg = new MissileNewMsg(tc);
				msg.parse(dis);
				break;
			case Msg.TANK_DEAD_MSG:
				msg = new TankDeadMsg(tc);
				msg.parse(dis);
				break;
			case Msg.MISSILE_DEAD_MSG:
				msg = new MissileDeadMsg(tc);
				msg.parse(dis);
				break;
			case Msg.TANK_ALREADY_EXIST_MSG:
				msg = new TankAlreadyExistMsg(tc);
				msg.parse(dis);
				break;
			case Msg.TANK_REDUCE_BLOOD_MSG:
				msg = new TankReduceBloodMsg(tc);
				msg.parse(dis);
				break;
			case Msg.MESSAGE:
				msg = new MessageMsg(GameUI.chatPanel);
				msg.parse(dis);
				break;
			}
		}
	}

	public void sendClientDisconnectMsg() {
		ByteArrayOutputStream baos = new ByteArrayOutputStream(88);
		DataOutputStream dos = new DataOutputStream(baos);
		try {
			dos.writeInt(UDP_PORT);// 发送客户端的UDP端口号, 从服务器Client集合中注销
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != dos) {
				try {
					dos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (null != baos) {
				try {
					baos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		byte[] buf = baos.toByteArray();
		try {
			DatagramPacket dp = new DatagramPacket(buf, buf.length,
					new InetSocketAddress(serverIP, TANK_DEAD_UDP_PORT));
			ds.send(dp);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int getRoomId() {
		return RoomId;
	}

	public void setRoomId(int roomId) {
		RoomId = roomId;
	}
}
