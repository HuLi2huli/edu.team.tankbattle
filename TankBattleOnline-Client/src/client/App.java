package client;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import client.view.*;
public class App {
	public static void main(String[] args) {
		/*
		 * AOP容器
		 * */
		ApplicationContext ac = new ClassPathXmlApplicationContext("ApplicationContext.xml");
		/*
		 * 工厂中获取对象
		 * */
		GameUI gu = (GameUI) ac.getBean("gameUI");
		gu.getLoginFrame().setVisible(true);
		
		/*
		 *  GameUI本身是静态类,等价于
		 * GameUI.loginFrame.setVisible(true);
		 */
	}
}
