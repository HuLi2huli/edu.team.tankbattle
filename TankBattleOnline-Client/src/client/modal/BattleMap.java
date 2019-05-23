package client.modal;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;

import client.control.command.Explode;
import client.jdbc.po.Map;
import client.parameter.Images;

/**
 * 对战地图
 */
public class BattleMap {
	public static final int Width = 40; /* 表示图像像素大小 非矩阵维度 */
	public static final int Height = 40;

	public static String Name;

	private static Image[] images = Images.imagesBattleMapItems;

	public static int map[][] = { 
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } };

	public static void drawItems(Graphics g) { // 描绘坦克和子弹下层的地图物品
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				switch (map[j][i]) {
				case 0:
					g.drawImage(images[0], i * Width, j * Height, Width, Height, null);
					break;
				case 1:
					g.drawImage(images[1], i * Width, j * Height, Width, Height, null);
					break;
				case 2:
					g.drawImage(images[2], i * Width, j * Height, Width, Height, null);
					break;
				case 3:
					g.drawImage(images[3], i * Width, j * Height, Width, Height, null);
					break;
				case 4:
					g.drawImage(images[4], i * Width, j * Height, Width, Height, null);
					break;
				default:
					break;
				}
			}
		}
	}

	public static void drawItems2(Graphics g) { // 描绘坦克和子弹上层的物品，目前只有草丛
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 20; j++) {
				switch (map[j][i]) {
				case 5:
					g.drawImage(images[5], i * Width, j * Height, Width, Height, null);
					break;
				}
			}
		}
	}

	public static void setMap(int[][] m) {
		map = m;
	}

	public static void parseMap(Map m) {
		Name = m.getName();
		int width = m.getWidth();
		int height = m.getHeight();
		String mapinfo = m.getMapString();
		int[][] maptemp = new int[width][height];
		int index = 0;
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				char c = mapinfo.charAt(index++);
				maptemp[i][j] = (int) c - (int) ('0');
				// System.out.print("[" + c + "," + maptemp[i][j] + "]");
			}
		}
		map = maptemp;
	}
}
