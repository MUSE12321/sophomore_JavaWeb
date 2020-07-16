package cn.edu.lingnan.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class DataAccess {

	public static Connection getConnection(){
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");//1注册
			 conn=DriverManager.getConnection
						("jdbc:mysql://localhost:3306/rent", "root", "root");//2连接
			
		} catch (ClassNotFoundException e) {
			System.out.println("数据库连接失败，连接用的jar包找不到...");
		}
	    catch (SQLException e) {
	    	System.out.println("数据库连接失败，请检查数据库连接参数的设置...");
		}	
    	return conn;
	}
	
	public static void closeConnection(ResultSet rs, Statement stat, Connection conn){
		 try {
			 if(rs!=null)
			    rs.close();
			 if(stat!=null)
			    stat.close();
			 if(conn!=null)
		        conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 
		
	} //同名方法不同参数--重载
	public static void closeConnection(ResultSet rs, PreparedStatement prep, Connection conn){
		 try {
			 if(rs!=null)
			    rs.close();
			 if(prep!=null)
			    prep.close();
			 if(conn!=null)
		        conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void closeConnection(Statement prep, Connection conn){
		 try {
			 if(prep!=null)
			    prep.close();
			 if(conn!=null)
		        conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void closeConnection(PreparedStatement prep, Connection conn){
		 try {
			 if(prep!=null)
			    prep.close();
			 if(conn!=null)
		        conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	}


    