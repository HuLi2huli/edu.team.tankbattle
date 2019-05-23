package client.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import client.dao.DBUtils;
import client.jdbc.po.Map;

public class MapDao {

	public static Map findMap(int id) {
		// 通过帮助类获取数据库链接对象
		Connection con = DBUtils.getConnection();
		Map map = null;
		// 获取预处理对象
		try {
			PreparedStatement pst = con.prepareStatement("select * from maptable where id=?");
			// 解析占位符
			pst.setInt(1, id);
			// 执行sql
			ResultSet rs = pst.executeQuery();
			// 遍历结果集
			while (rs.next()) {
				map = new Map();
				map.setId(rs.getInt("id"));
				map.setName(rs.getString("name"));
				map.setWidth(rs.getInt("width"));
				map.setHeight(rs.getInt("height"));
				map.setMapString(rs.getString("mapstring"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}
}
