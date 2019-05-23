package client.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonListen implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		GameUI.chatPanel.speak();
	}

}
