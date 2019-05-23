package admin.Listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import admin.Admin;
import jdbc.MapService;

public class SubmitListener implements ActionListener {

	public Admin ad;

	public SubmitListener(Admin ad) {
		this.ad = ad;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String str = "";
		for (int i = 0; i < ad.map.length; i++) {
			for (int j = 0; j < ad.map[i].length; j++) {
				str += ad.map[i][j];
			}
		}
		MapService.uploadMap(ad.mapName, ad.map[0].length, ad.map.length, str);
		ad.submitSuccess();
	}
}
