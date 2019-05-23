package client.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

import client.jdbc.po.User;
import client.jdbc.service.UserService;
import client.parameter.Images;
import client.parameter.Musics;

public class LoginFrame extends JFrame {
	LoginPanel lp = new LoginPanel();
	JLabel gameName;
	int width = 400;
	int height = 200;
	JButton loginButton;
	JButton exitButton;
	JLabel userName;
	JLabel Pwd;
	JTextField userField;
	JTextField pwdField;
	// 图片
	Image img = Images.image2;
	ImageIcon img2 = Images.icon;

	// 登录界面布局
	public LoginFrame() {
		setTitle("登录窗口");// 标题

		// 设置字体类型
		Font font = new Font("楷体", Font.PLAIN, 30);
		Font font_english = new Font("Arial", Font.BOLD, 20);

		// 标题图片
		img2.setImage(img2.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
		gameName = new JLabel(img2);
		gameName.setBounds(250, 0, 400, 200);
		this.add(gameName);

		// 用户名
		userName = new JLabel("用户名");
		userName.setBounds(300, 200, 100, 50);
		userName.setFont(font);
		userName.setForeground(Color.red);
		this.add(userName);

		// 密码
		Pwd = new JLabel("密码");
		Pwd.setBounds(300, 300, 100, 50);
		Pwd.setFont(font);
		Pwd.setForeground(Color.red);
		this.add(Pwd);

		// 用户名输入框
		userField = new JTextField("");
		userField.setBounds(400, 200, 200, 50);
		userField.setFont(font_english);
		this.add(userField);

		// 密码输入框
		pwdField = new JPasswordField();
		pwdField.setBounds(400, 300, 200, 50);
		pwdField.setFont(font_english);
		this.add(pwdField);

		// 登录按钮
		loginButton = new JButton("登录");
		loginButton.setBounds(275, 400, 100, 50);
		this.add(loginButton);

		// 登录按钮事件
		loginButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				super.mouseClicked(arg0);
				String name = userField.getText();
				String password = pwdField.getText().trim();

				User user = UserService.login(name, password); // 验证登录
				if (user != null) {
					GameUI.user = user;
					JOptionPane.showMessageDialog(null, "登录成功");
					System.out.println("登录成功: " + user.getPhone());
					GameUI.loginFrame.hide();
					GameUI.readyFrame.show();
				} else {
					JOptionPane.showMessageDialog(null, "登录失败，用户名或密码错误！");
				}

			}
		});

		// 退出按钮
		exitButton = new JButton("退出");
		exitButton.setBounds(525, 400, 100, 50);
		this.add(exitButton);
		// 退出按钮事件
		exitButton.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent g) {
				super.mouseClicked(g);
				if (JOptionPane.showConfirmDialog(null, "确定退出游戏？", "退出游戏", JOptionPane.YES_NO_OPTION) <= 0) {
					System.exit(0);
				} else
					return;
			}
		});

		// 添加背景图
		this.add(lp);

		this.setResizable(false);// 不可缩放
		setBounds(300, 100, 900, 650);
		setVisible(false);
		setLocationRelativeTo(null);// 居中显示
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Musics.musicBKM.play(0);
	}

	// 重写窗口的setVisible方法,窗口可视时开启音乐
	@Override
	public void setVisible(boolean b) {
		super.setVisible(b);
		if (b) {
			Musics.musicBKM.play(0);
		} else {
			Musics.musicBKM.stop();
		}

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		super.hide();
		Musics.musicBKM.stop();
	}

	@Override
	public void show() {
		super.show();
		Musics.musicBKM.play(0);
	}

	// 绘制背景
	class LoginPanel extends JPanel {
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), null);
		}
	}

}
