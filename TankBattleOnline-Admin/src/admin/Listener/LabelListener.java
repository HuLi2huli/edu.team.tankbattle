package admin.Listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import admin.Admin;

public class LabelListener implements MouseListener {
	private int type;
	Admin ad;
	public LabelListener(int type, Admin ad) {
		this.type = type;
		this.ad = ad;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		ad.current_type = type;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
