package client.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import client.control.command.Explode;
import client.parameter.Images;

public class HelpFrame extends JFrame {
	HelpPanel help;// 帮助面板
	JButton back;// 返回按钮
	private Image image = Images.image1; // 背景图

	public HelpFrame() {
		this.setTitle("帮助");// 标题
		this.setBounds(300, 100, 600, 500);// 位置大小

		// 字体类型
		Font font = new Font("楷体", Font.PLAIN, 20);

		// 设置面板焦点
		this.setFocusable(true);

		// 请求焦点
		this.requestFocus();

		// 添加返回按钮
		back = new JButton("返回");
		back.setBounds(220, 400, 100, 50);
		add(back);

		// 添加帮助面板
		help = new HelpPanel();
		help.setFont(font);
		help.setForeground(Color.yellow);// 字体颜色
		this.add(help);

		// 返回按钮事件
		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				HelpFrame.this.setVisible(false);// 隐藏窗口
			}
		});

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);// 窗口关闭不执行
	}

	// 帮助面板内容
	class HelpPanel extends JPanel {
		@Override
		protected void paintComponent(Graphics g) {
			// TODO Auto-generated method stub
			super.paintComponent(g);
			g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), null);
			g.drawString("坦克大战游戏介绍", 200, 50);
			g.drawString("玩家失去所有生命或家园爆炸则游戏失败", 60, 120);
			g.drawString("w a s d 键控制坦克移动， j 键命令开火", 60, 170);
			g.drawString("游地图元素：铁墙不可销毁，土墙可销毁，水池不可进入", 60, 220);
		}
	}
}
