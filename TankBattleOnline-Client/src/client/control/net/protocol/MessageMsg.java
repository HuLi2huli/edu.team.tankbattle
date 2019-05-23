package client.control.net.protocol;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

import client.control.net.NetClient;
import client.modal.Tank;
import client.parameter.Dir;
import client.view.ChatPanel;
import client.view.TankClient;

public class MessageMsg implements Msg {
	String str;
	public final int msgType = 8;
	private int id;
	ChatPanel cp;
	public MessageMsg(ChatPanel cp) {
		this.cp = cp;
	}
	public MessageMsg(String str) {
		this.str = str;
	}
	@Override
	public void send(DatagramSocket ds, String IP, int UDP_Port, int roomid) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream(200);//指定大小, 免得字节数组扩容占用时间
        DataOutputStream dos = new DataOutputStream(baos);
        try {
        	dos.writeInt(roomid);
            dos.writeInt(msgType);
            dos.writeUTF(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] buf = baos.toByteArray();
        try{
            DatagramPacket dp = new DatagramPacket(buf, buf.length, new InetSocketAddress(IP, UDP_Port));
            ds.send(dp);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	@Override
	public void parse(DataInputStream dis) {
		try{
            String str = dis.readUTF();
            System.out.print("收到消息："+str);
            cp.listen(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

}
