package client.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import client.dao.DBUtils;
import client.jdbc.po.User;

public class UserDao {

	public static User findUser(String name, String pwd) {
		// 通过帮助类获取数据库链接对象
		Connection con = DBUtils.getConnection();
		User user = null;
		// 获取预处理对象
		try {
			PreparedStatement pst = con.prepareStatement("select * from usertable where name=? and password=?");
			// 解析占位符
			pst.setString(1, name);
			pst.setString(2, pwd);
			// 执行sql
			ResultSet rs = pst.executeQuery();
			// 遍历结果集
			while (rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
				user.setPhone(rs.getString("phone"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
}
