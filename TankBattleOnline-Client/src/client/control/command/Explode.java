package client.control.command;

import java.awt.*;

import javax.swing.ImageIcon;

import client.parameter.Images;
import client.view.TankClient;

/**
 * 子弹爆炸的命令 子弹击中坦克后产生的爆炸
 */
public class Explode {
	private int x, y;// 爆炸的坐标
	private boolean live = true;// 爆炸的生命
	private TankClient tc;

	private int step = 0;// 播放图片的计数器
	private static boolean init = false;// 在正式画出爆炸之前先在其他地方画出一次爆炸, 确保爆炸的图片加入到内存中

	private static Image[] images = Images.imagesExplode;


	public Explode(int x, int y, TankClient tc) {
		this.x = x;
		this.y = y;
		this.tc = tc;
	}

	public void draw(Graphics g) {
		if (!init) {// 先在其他地方画一次爆炸
			for (int i = 0; i < images.length; i++) {
				g.drawImage(images[i], -100, -100, null);
			}
			init = true;
		}
		if (!live) {// 爆炸炸完了就从容器移除
			tc.getExplodes().remove(this);
			return;
		}
		if (step == images.length) {// 把爆炸数组中的图片都画一次
			live = false;
			step = 0;
			return;
		}
		g.drawImage(images[step++], x, y, null);
	}
}