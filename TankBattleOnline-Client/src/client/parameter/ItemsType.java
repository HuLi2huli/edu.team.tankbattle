package client.parameter;

public class ItemsType { // 地图的物品代号
	public static final int Nothing = 0; // 空物品
	public static final int Brick = 1; // 砖块
	public static final int Water = 2; // 水
	public static final int Metal = 3; // 金属块
	public static final int Lawn = 5; // 草地
	public static final int Drug = -1; // 药剂
	public static final int Bomb = -2; // 炸弹
	public static final int Mines = -3; // 地雷

	public static boolean isCollisionEnable(int type) { // 判断坦克是否可以对物品碰撞(穿透)
		switch (type) {
		case Nothing: // 空物品 可碰撞
			return true;
		case Lawn: // 草地 可碰撞
			return true;
		case Water: // 水 不可碰撞
			return false;
		case Brick: // 砖块 不可碰撞
			return false;
		case Metal: // 金属 可碰撞
			return false;
		case Drug: // 药剂 可碰撞
			return true;
		case Bomb: // 炸弹 可碰撞
			return true;
		case Mines: // 地雷 可碰撞
			return true;
		default:
			return true;
		}
	}

	public boolean isBreakEnable(int type) { // 判断子弹是否可对物品破坏
		/*
		 * 一般判断顺序 可否穿透→yes 直接穿透 →no 可否破坏 → yes 破坏,子弹消亡 → no 不破坏,子弹消亡
		 */
		switch (type) {
		case Nothing: // 空物品
			return false;
		case Lawn: // 草地
			return false;
		case Water: // 水
			return false;
		case Brick: // 砖块
			return true;
		case Metal: // 金属
			return false;
		case Drug: // 药剂
			return false;
		case Bomb: // 炸弹
			return false;
		case Mines: // 地雷
			return false;
		default:
			return true;
		}
	}
}
