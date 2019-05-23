package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MapDao {

	public static Map findMap(int id) {
		// 通过帮助类获取数据库链接对象
		Connection con = DBUtils.getConnection();
		Map map = null;
		// 获取预处理对象
		try {
			PreparedStatement pst = con.prepareStatement("select * from maptable where id=?");
			// 解析占位符
			pst.setLong(1, id);
			// 执行sql
			ResultSet rs = pst.executeQuery();
			// 遍历结果集
			while (rs.next()) {
				map = new Map();
				map.setId(rs.getInt("id"));
				map.setName(rs.getString("name"));
				map.setMapString(rs.getString("mapString"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}

	public static int uploadMap(Map map) {
		// 通过帮助类获取数据库链接
		Connection con = DBUtils.getConnection();
		int count = 0;
		// 获取预处理对象操作数据库
		try {
			PreparedStatement pst = con
					.prepareStatement("insert into maptable(id,name,width,height,mapstring) values(null,?,?,?,?)");
			// 解析占位符
			pst.setString(1, map.getName());
			pst.setInt(2, map.getWidth());
			pst.setInt(3, map.getHeight());
			pst.setString(4, map.getMapString());
			// 执行sql
			count = pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return count;
	}
}
