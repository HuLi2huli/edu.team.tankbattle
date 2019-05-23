package admin.Listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import admin.Admin;

public class XYListener implements MouseListener  {
	public Admin ad;
	public XYListener(Admin ad)  {
		this.ad = ad;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		//这里获得四个方块的类型，然后在这里改矩阵
		int x_index = e.getX()/20;
		int y_index = e.getY()/20;
		ad.map[y_index][x_index] = ad.current_type;
		ad.repaint();
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
