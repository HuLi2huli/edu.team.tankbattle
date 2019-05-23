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

public class AboutFrame extends JFrame {
	AboutPanel about;// 帮助面板
	JButton back;// 返回按钮
	private Image image = Images.image1;// 背景图

	public AboutFrame() {
		this.setTitle("帮助");// 标题
		this.setBounds(300, 100, 700, 750);// 位置大小

		// 字体类型
		Font font = new Font("楷体", Font.PLAIN, 20);

		// 设置面板焦点
		this.setFocusable(true);

		// 请求焦点
		this.requestFocus();

		// 添加返回按钮
		back = new JButton("返回");
		back.setBounds(300, 600, 100, 50);
		add(back);

		// 添加帮助面板
		about = new AboutPanel();
		about.setFont(font);
		about.setForeground(Color.yellow);// 字体颜色
		this.add(about);

		// 返回按钮事件
		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				AboutFrame.this.setVisible(false);// 隐藏窗口
			}
		});

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);// 窗口关闭不执行
	}

	// "关于"面板内容
	class AboutPanel extends JPanel {
		@Override
		protected void paintComponent(Graphics g) {
			// TODO Auto-generated method stub
			super.paintComponent(g);
			g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), null);
			g.drawString("坦克大战制作介绍", 200, 50);
			g.drawString("作者：钟海啸、冼文锋", 60, 120);
			g.drawString("本项目由TankOnline-master联机游戏项目为蓝本", 60, 170);
			g.drawString("保留了TankOnline-master网络通信模式", 60, 220);
			g.drawString("修改和新增了 部分游戏指令、消息协议", 60, 220);
			g.drawString("增加了 游戏控制面板", 60, 270);
			g.drawString("增加了 地图系统和物品碰撞机制", 60, 320);
			g.drawString("增加了 管理端的地图上传系统", 60, 370);
			g.drawString("新增和修改了 部分动作的动画和音乐", 60, 420);
			g.drawString("游戏素材来源互联网，仅作学习使用。", 60, 470);
			g.drawString("TankOnline-master项目主页:", 60, 520);
			g.drawString("   https://www.cnblogs.com/tanshaoshenghao/p/10708586.html", 60, 570);
		}
	}
	// public static void main(String[] args) {
	// new AboutFrame().setVisible(true);
	// }
}
