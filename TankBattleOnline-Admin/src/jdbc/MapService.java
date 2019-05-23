package jdbc;

public class MapService {
	public static Map findMap(int id) {
		return MapDao.findMap(id);
	}

	public static int uploadMap(String name, int width, int height, String mapstring) {
		Map map = new Map();
		map.setName(name);
		map.setWidth(width);
		map.setHeight(height);
		map.setMapString(mapstring);
		return MapDao.uploadMap(map);
	}
}