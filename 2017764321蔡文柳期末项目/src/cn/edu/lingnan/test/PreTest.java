package cn.edu.lingnan.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PreTest {

	public static void main(String[] args) {
		Connection conn=null;
		//Statement stat=null;
		PreparedStatement prep = null;
		ResultSet rs =null;
    try {
		Class.forName("com.mysql.jdbc.Driver");//×¢²á
		 conn=DriverManager.getConnection
					("jdbc:mysql://localhost:3306/rent", "root", "root");//2Á¬½Ó
		 //stat=conn.createStatement();
		 //rs =stat.executeQuery("select * from fowner");
		 prep = conn.prepareStatement("insert into customer values(?,?,?,?,?)");
		 prep.setString(1, "c06");
		 prep.setString(2, "lisi6");
	     prep.setString(3, "lisi6");
	     prep.setInt(4, 2);
	     prep.setInt(5, 654324);
	     prep.addBatch();
		 prep.setString(1, "c07");
		 prep.setString(2, "lisi7");
	     prep.setString(3, "lisi7");
	     prep.setInt(4, 2);
	     prep.setInt(5, 654322);
	     prep.addBatch();
		 prep.setString(1, "c08");
		 prep.setString(2, "lisi8");
	     prep.setString(3, "lisi8");
	     prep.setInt(4, 2);
	     prep.setInt(5, 654323);
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
 
