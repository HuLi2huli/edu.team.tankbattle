package client.control.strategy;

import client.control.net.protocol.MissileNewMsg;
import client.modal.Missile;
import client.modal.Tank;
import client.parameter.Musics;

public class NormalFireAction implements FireAction {	//策略模式	开火策略
	@Override
	public void fireAction(Tank tank) {
		if (!tank.isLive())
			return;
		int x = tank.getX() + Missile.XSPEED; // 确定子弹的坐标
		int y = tank.getY() + Missile.YSPEED;
		Missile m = new Missile(tank.getId(), x, y, tank.isGood(), tank.getPtDir(), tank.getTc());// 产生一颗子弹
		tank.getTc().getMissiles().add(m);

		MissileNewMsg msg = new MissileNewMsg(m);
		tank.getTc().getNc().send(msg);
		Musics.musicFire.play(1);
	}
}
