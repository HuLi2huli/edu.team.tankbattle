package server;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 服务器端
 */
public class TankServerWithOutView {

	public static int ID = 100;// id号的初始序列
	public static final int TCP_PORT = 55555;// TCP端口号
	public static final int UDP_PORT = 55556;// 转发客户端数据的UDP端口号
	public static final int TANK_DEAD_UDP_PORT = 55557;// 接收客户端坦克死亡的端口号
	public static final int UDP_ADMIN_PORT = 55558;// 管理端专用UDP端口
	private List<Client> clients = new ArrayList<>();// 客户端集合
	private Image offScreenImage = null;// 服务器画布
	private static final int SERVER_HEIGHT = 500;
	private static final int SERVER_WIDTH = 300;

	public static void main(String[] args) {
		TankServerWithOutView ts = new TankServerWithOutView();
		ts.start();
	}

	public void start() {
		new Thread(new UDPThread()).start();
		new Thread(new TankDeadUDPThread()).start();
		ServerSocket ss = null;
		try {
			ss = new ServerSocket(TCP_PORT);// 在TCP欢迎套接字上监听客户端连接
			System.out.println("TankServer has started...");
		} catch (IOException e) {
			e.printStackTrace();
		}

		while (true) {
			Socket s = null;
			try {
				s = ss.accept();// 给客户但分配专属TCP套接字
				System.out.println("A client has connected...");
				DataInputStream dis = new DataInputStream(s.getInputStream());
				int UDP_PORT = dis.readInt();// 记录客户端UDP端口
				int RoomID = dis.readInt();// 记录客户端所选择的房间号
				System.out.println("player into room " + RoomID);
				int pid = 1;
				for (Client c : clients) {
					if (c.roomid == RoomID) {
						pid++;
					}
				}
				Client client = new Client(s.getInetAddress().getHostAddress(), UDP_PORT, ID, RoomID, pid);// 创建Client对象
				clients.add(client);// 添加进客户端容器

				DataOutputStream dos = new DataOutputStream(s.getOutputStream());
				dos.writeInt(ID++);// 向客户端分配id号
				dos.writeInt(pid);
				dos.writeInt(TankServerWithOutView.UDP_PORT);// 告诉客户端自己的UDP端口号
				dos.writeInt(TankServerWithOutView.TANK_DEAD_UDP_PORT);

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
		}
	}

	/*
	 * 管理员的udp线程
	 */
	private class UDPAdminThread implements Runnable {
		byte[] buf = new byte[1024];

		@Override
		public void run() {
			DatagramSocket ds = null;
			try {
				ds = new DatagramSocket(UDP_ADMIN_PORT);
			} catch (SocketException e) {
				e.printStackTrace();
			}

			while (null != ds) {
				DatagramPacket dp = new DatagramPacket(buf, buf.length);
				ByteArrayInputStream bais;
				DataInputStream dis;
				try {
					ds.receive(dp);
					bais = new ByteArrayInputStream(buf, 0, dp.getLength());
					dis = new DataInputStream(bais);
					String mapupload = dis.readUTF();
					System.out.println(mapupload);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private class UDPThread implements Runnable {
		byte[] buf = new byte[1024];

		@Override
		public void run() {
			DatagramSocket ds = null;
			try {
				ds = new DatagramSocket(UDP_PORT);
			} catch (SocketException e) {
				e.printStackTrace();
			}

			while (null != ds) {
				DatagramPacket dp = new DatagramPacket(buf, buf.length);
				try {
					ds.receive(dp);
					for (Client c : clients) {
						dp.setSocketAddress(new InetSocketAddress(c.IP, c.UDP_PORT));
						ds.send(dp);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 监听坦克死亡的UDP线程
	 */
	private class TankDeadUDPThread implements Runnable {
		byte[] buf = new byte[300];

		@Override
		public void run() {
			DatagramSocket ds = null;
			try {
				ds = new DatagramSocket(TANK_DEAD_UDP_PORT);
			} catch (SocketException e) {
				e.printStackTrace();
			}
			while (null != ds) {
				DatagramPacket dp = new DatagramPacket(buf, buf.length);
				ByteArrayInputStream bais = null;
				DataInputStream dis = null;
				try {
					ds.receive(dp);
					bais = new ByteArrayInputStream(buf, 0, dp.getLength());
					dis = new DataInputStream(bais);
					int deadTankUDPPort = dis.readInt();
					for (int i = 0; i < clients.size(); i++) {
						Client c = clients.get(i);
						if (c.UDP_PORT == deadTankUDPPort) {
							clients.remove(c);
						}
					}

				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					if (null != dis) {
						try {
							dis.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					if (null != bais) {
						try {
							bais.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
	}

	public class Client {
		String IP; // ip地址
		int UDP_PORT; // 接收upd广播的端口号
		int id; // 客户端id
		int roomid; // 所在房间号
		int pid; // 在房间中代号(身份:可表示p1、p2或其他状态)

		public Client(String ipAddr, int UDP_PORT, int id, int roomid, int pid) {
			this.IP = ipAddr;
			this.UDP_PORT = UDP_PORT;
			this.id = id;
			this.roomid = roomid;
			this.pid = pid;
		}
	}
}
