package client.jdbc.service;

import client.jdbc.dao.UserDao;
import client.jdbc.po.User;

public class UserService {
	public static User login(String name, String password){
		return UserDao.findUser(name, password);
	}
}