package client.view;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

public class ReadyFrame extends JFrame {
	private final int Widht = 750;
	private final int Height = 650;
	private MenuPanel menuPanel;
	private ChatPanel chatPanel;

	public ReadyFrame() {
		menuPanel = new MenuPanel();

		setBackground(Color.LIGHT_GRAY);
		setTitle("坦克大战-在线版"); // 标题
		setJMenuBar(menuPanel.mBar);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		// setLocationRelativeTo(null);// 居中显示
		// this.setResizable(false);// 不可缩放
		setBounds(600, 250, Widht, Height);
		setVisible(false);// 可视性
		getContentPane().setLayout(null);
		getContentPane().setBackground(Color.GRAY);

		chatPanel = new ChatPanel();
		chatPanel.setLocation(10, 10);
		chatPanel.setSize(444, 560);
		getContentPane().add(chatPanel);
	}

	public ChatPanel getChatPanel() {
		return chatPanel;
	}

	public void setChatPanel(ChatPanel chatPanel) {
		this.chatPanel = chatPanel;
	}

	public MenuPanel getMenuPanel() {
		return menuPanel;
	}

	public void setMenuPanel(MenuPanel menuPanel) {
		this.menuPanel = menuPanel;
	}
}
