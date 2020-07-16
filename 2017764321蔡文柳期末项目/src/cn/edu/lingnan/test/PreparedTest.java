package cn.edu.lingnan.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PreparedTest {

	public static void main(String[] args) {
		Connection conn=null;
		//Statement stat=null;
		PreparedStatement prep=null;
		ResultSet rs =null;
    try {
		Class.forName("com.mysql.jdbc.Driver");//×¢²á
		 conn=DriverManager.getConnection
					("jdbc:mysql://localhost:3306/rent", "root", "root");//2Á¬½Ó
		 //stat=conn.createStatement();//3
		 //rs =stat.executeQuery("select * from fowner");//
		 prep =conn.prepareStatement("insert into fowner values(?,?,?,?,?,?)");
		 prep.setString(1, "f06");
		 prep.setString(2,"zhangsan6");
		 prep.setString(3,"zhangsan6");
		 prep.setInt(4, 2);
		 prep.setString(5,"A06");
		 prep.setInt(6,123460);
		 prep.addBatch();
		 prep.setString(1, "f07");
		 prep.setString(2,"zhangsan7");
		 prep.setString(3,"zhangsan7");
		 prep.setInt(4, 2);
		 prep.setString(5,"A07");
		 prep.setInt(6,123470);
		 prep.addBatch();
		 prep.setString(1, "f08");
		 prep.setString(2,"zhangsan8");
		 prep.setString(3,"zhangsan8");
		 prep.setInt(4, 2);
		 prep.setString(5,"A08");
		 prep.setInt(6,123480);
		 prep.addBatch();
		 int[] a =prep.executeBatch();
		 for(int b : a)
			 System.out.println(b);
	   
		
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	}
    catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally{
		 try {
			 if(rs!=null)
			    rs.close();
			 if(prep!=null)
			    prep.close();
			 if(conn!=null)
		        conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		
	}
 
	}

}
