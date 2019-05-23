package admin;

import java.net.DatagramSocket;

public class NetAdmin {
	//这个类只用来向服务器发送UDP数据包而已
	private Admin ad;
	private int UDP_PORT;  //管理端的UDP端口
	private final String serverIP = "127.0.0.1";  //服务器IP地址
	private final int serverUDPPort = 55558;  //服务器接收管理端UDP包的UDP端口
	private DatagramSocket ds = null;
	public NetAdmin(Admin admin) {
		this.ad = admin;
		try {
			this.UDP_PORT = getRandomUDPPort();
		} catch (Exception e) {
		}
	}
	private int getRandomUDPPort() {
		return 55558 + (int)(Math.random() * 9000);
	}
	//向服务器发送UDP数据包
	public void send(String str) {
		
	}
}
