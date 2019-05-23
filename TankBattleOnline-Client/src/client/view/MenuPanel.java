package client.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;


//菜单类
public class MenuPanel extends JMenu {
	JMenuBar mBar = new JMenuBar();
	JMenu fMenu1;
	JMenu fMenu2;
	JMenu fMenu3;
	JMenuItem begin;
	JMenuItem logout;
	JMenuItem exit;
	JMenuItem help;
	JMenuItem about;
	ButtonGroup group = new ButtonGroup();// 分组

	public MenuPanel() {
		// 一级菜单
		fMenu1 = new JMenu("游戏");
		fMenu2 = new JMenu("自定义");
		fMenu3 = new JMenu("帮助");
		mBar.add(fMenu1);
		mBar.add(fMenu2);
		mBar.add(fMenu3);

		// 游戏菜单的下一级菜单
		begin = new JMenuItem("开始");
		logout = new JMenuItem("注销");
		exit = new JMenuItem("退出");
		
		help = new JMenuItem("帮助");
		
		about = new JMenuItem("关于");
		
		fMenu1.add(begin);
		fMenu1.add(logout);
		fMenu1.add(exit);
		
		fMenu3.add(help);
		fMenu3.add(about);


		// 开始事件
		begin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new TankClient().launchFrame();
			}
		});

		// 注销按钮事件
		logout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (JOptionPane.showConfirmDialog(null, "确定注销登录？", "注销", JOptionPane.YES_NO_OPTION) <= 0) {
					GameUI.readyFrame.dispose();
					GameUI.loginFrame.setVisible(true);
				} else {
					return;
				}
			}
		});
		
		// 退出按钮事件
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (JOptionPane.showConfirmDialog(null, "确定退出？", "退出", JOptionPane.YES_NO_OPTION) <= 0) {
					System.exit(0);
				} else {
					return;
				}
			}
		});

		// 帮助事件
		help.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				GameUI.helpFrame.setVisible(true);// 显示帮助窗口
			}
		});

		// "关于"事件
		about.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				GameUI.aboutFrame.setVisible(true);// 显示"关于"窗口
			}
		});

	}

}