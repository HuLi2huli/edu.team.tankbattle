package client.view;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextArea;

import client.control.net.NetClient;
import client.control.net.protocol.MessageMsg;

public class ChatPanel extends JPanel {
	private NetClient nc;
	private JTextArea textArea_1;
	private JTextArea textArea;
	ChatPanel() {
		setLayout(null);

		textArea_1 = new JTextArea();
		textArea_1.setBounds(26, 13, 394, 454);
		add(textArea_1);

		textArea = new JTextArea();
		textArea.setBounds(26, 480, 315, 62);
		add(textArea);

		JButton btnSend = new JButton("send");
		btnSend.setBounds(355, 496, 65, 34);
		btnSend.addActionListener(new ButtonListen());
		add(btnSend);
	}
	public void speak(){
		/*
		 * 发送消息
		 * */
		String str = "123456";
		System.out.println("发送的消息："+str);
		MessageMsg msg = new MessageMsg(str);
		this.nc.send(msg);
	}
	public void listen(String str){
		/*
		 * 收到消息  写到窗口
		 * */
		textArea_1.append(str);
	}
	
	public NetClient getNc() {
		return nc;
	}
	public void setNc(NetClient nc) {
		this.nc = nc;
		System.out.println("chantPanel设置nc:" + this.nc.getRoomId());
	}
}
