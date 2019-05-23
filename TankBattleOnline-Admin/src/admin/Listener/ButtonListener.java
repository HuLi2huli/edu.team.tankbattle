package admin.Listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import admin.Admin;

public class ButtonListener implements ActionListener {
	Admin ad;
	public ButtonListener(Admin ad) {
		this.ad = ad;
	}
	@Override
	public void actionPerformed(ActionEvent me)
	{
		ad.current_type = 0;
	}
}
