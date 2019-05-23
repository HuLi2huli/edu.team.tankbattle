package client.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;

/***
 * 链接数据库的帮助类
 * @author Administrator
 *
 */
public class DBUtils {
      private static String DRIVER_CLASS="com.mysql.jdbc.Driver";
      private static String URL="jdbc:mysql://119.29.139.239:3306/TankBattleOnline";
      private static String USER_NAME="root";
      private static String PASS_WORD="2016zhku";
      static{
    	  try {
			Class.forName(DRIVER_CLASS);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      }
      public static Connection getConnection(){
    	  Connection con=null;
    	 try {
    		 con= DriverManager.getConnection(URL, USER_NAME, PASS_WORD);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	 return con;
      }
      public static void close(Connection con,PreparedStatement pst,ResultSet rs){
    	  if(rs!=null){
    		  try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	  }
    	  if(pst!=null){
    		  try {
				pst.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	  }
    	  if(con!=null){
    		  try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	  }
      }
}
