package admin.Listener;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import admin.Admin;

public class TextListener implements FocusListener {
	public Admin ad;
	public TextListener (Admin ad)  {
		this.ad = ad;
	}
	@Override
	public void focusGained(FocusEvent e) {
		
	}
	@Override
	public void focusLost(FocusEvent e) {
		ad.mapName = ad.text.getText().trim();
	}
}
