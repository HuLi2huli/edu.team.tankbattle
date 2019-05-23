package client.modal;

import client.control.event.TankHitEvent;
import client.parameter.Dir;
import client.parameter.Images;
import client.parameter.ItemsType;
import client.parameter.Musics;
import client.view.TankClient;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;

public class Missile { // 子弹对象
	public static final int Width = 5;
	public static final int Height = 5;
	public static final int XSPEED = 10;
	public static final int YSPEED = 10;
	private static int ID = 10;
	private int id;
	private TankClient tc;
	private int tankId;
	private int x, y;
	private Dir dir = Dir.R;
	private boolean live = true;
	private boolean good;

	private static Image[] imgs = Images.imagesMissile;
	private static Map<String, Image> map = new HashMap<>();
	static {
		map.put("n", imgs[0]);
		map.put("m", imgs[1]);
	}

	public Missile(int tankId, int x, int y, boolean good, Dir dir) {
		this.tankId = tankId;
		this.x = x;
		this.y = y;
		this.good = good;
		this.dir = dir;
		this.id = ID++;
	}

	public Missile(int tankId, int x, int y, boolean good, Dir dir, TankClient tc) {
		this(tankId, x, y, good, dir);
		this.tc = tc;
	}

	public void draw(Graphics g) {
		if (!live) {
			tc.getMissiles().remove(this);
			return;
		}
		g.drawImage(good ? map.get("n") : map.get("m"), x, y, null);
		move();
	}

	private void move() {// 每画一次, 子弹的坐标移动一次
		switch (dir) {
		case L:
			x -= XSPEED;
			break;
		case LU:
			x -= XSPEED;
			y -= YSPEED;
			break;
		case U:
			y -= YSPEED;
			break;
		case RU:
			x += XSPEED;
			y -= YSPEED;
			break;
		case R:
			x += XSPEED;
			break;
		case RD:
			x += XSPEED;
			y += YSPEED;
			break;
		case D:
			y += YSPEED;
			break;
		case LD:
			x -= XSPEED;
			y += YSPEED;
			break;
		case STOP:
			break;
		}
		// 碰撞检测
		if (hitMap(BattleMap.map)) {
			live = false;
		}
		if (x < 0 || y < 0 || x > TankClient.GAME_Width || y > TankClient.GAME_Height) {
			live = false;
		}
	}

	public Rectangle getRect() {
		return new Rectangle(x, y, Width, Height);
	}

	public boolean hitTank(Tank t) {// 子弹击中坦克的方法
		if (this.live && t.isLive() && this.good != t.isGood() && this.getRect().intersects(t.getRect())) {
			this.live = false;// 子弹死亡
			t.actionToTankHitEvent(new TankHitEvent(this));// 告知观察的坦克被打中了
			Musics.musicBulletExplode.play(1);
			return true;
		}
		return false;
	}

	public boolean hitMap(int[][] map) {// 子弹和地图碰撞检测
		Rectangle a = new Rectangle(x, y, Width, Height);
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (map[i][j] != ItemsType.Water && !ItemsType.isCollisionEnable(map[i][j])) {
					Rectangle b = new Rectangle(j * BattleMap.Width, i * BattleMap.Height, BattleMap.Width,
							BattleMap.Height);
					if (a.intersects(b)) {
						if (map[i][j] == 1) {
							map[i][j] = 0;// 摧毁土墙
						} else if (map[i][j] == 3) {
							Musics.musicIron.play(1);
						}
						Musics.musicBulletExplode.play(1);
						return true;
					}
				}
			}
		}
		return false;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTankId() {
		return tankId;
	}

	public void setTankId(int tankId) {
		this.tankId = tankId;
	}

	public boolean isLive() {
		return live;
	}

	public void setLive(boolean live) {
		this.live = live;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Dir getDir() {
		return dir;
	}

	public void setDir(Dir dir) {
		this.dir = dir;
	}

	public boolean isGood() {
		return good;
	}

	public void setGood(boolean good) {
		this.good = good;
	}
}
