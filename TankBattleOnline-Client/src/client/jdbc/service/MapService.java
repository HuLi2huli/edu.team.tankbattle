package client.jdbc.service;

import client.jdbc.dao.MapDao;
import client.jdbc.po.Map;

public class MapService {
	public static Map findMap(int id){
		return MapDao.findMap(id);
	}
}