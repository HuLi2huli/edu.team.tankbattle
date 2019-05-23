package client.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import client.jdbc.po.User;

@Component
public class GameUI {
	/*
	 * 在登录后设置的属性
	 */
	public static User user;
	/*
	 * spring注入
	 */
	public static LoginFrame loginFrame;
	public static MenuPanel menuPanel;
	public static HelpFrame helpFrame;
	public static AboutFrame aboutFrame;
	public static ChatPanel chatPanel;
	public static ReadyFrame readyFrame;

	public static User getUser() {
		return user;
	}

	public static void setUser(User user) {
		GameUI.user = user;
	}

	@Autowired
	public LoginFrame getLoginFrame() {
		return loginFrame;
	}

	@Autowired
	public void setLoginFrame(LoginFrame loginFrame) {
		GameUI.loginFrame = loginFrame;
	}

	@Autowired
	public MenuPanel getMenuPanel() {
		return menuPanel;
	}

	@Autowired
	public void setMenuPanel(MenuPanel menuPanel) {
		GameUI.menuPanel = menuPanel;
	}

	@Autowired
	public HelpFrame getHelpFrame() {
		return helpFrame;
	}

	@Autowired
	public void setHelpFrame(HelpFrame helpFrame) {
		GameUI.helpFrame = helpFrame;
	}

	@Autowired
	public AboutFrame getAboutFrame() {
		return aboutFrame;
	}

	@Autowired
	public void setAboutFrame(AboutFrame aboutFrame) {
		GameUI.aboutFrame = aboutFrame;
	}

	@Autowired
	public ChatPanel getChatPanel() {
		return chatPanel;
	}

	@Autowired
	public void setChatPanel(ChatPanel chatPanel) {
		GameUI.chatPanel = chatPanel;
	}

	@Autowired
	public ReadyFrame getReadyFrame() {
		return readyFrame;
	}

	@Autowired
	public void setReadyFrame(ReadyFrame readyFrame) {
		GameUI.readyFrame = readyFrame;
	}

}
