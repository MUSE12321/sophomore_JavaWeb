import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class JDBCTest {

	public static void main(String[] args) {
		Connection conn=null;
		Statement stat=null;
		ResultSet rs =null;
    try {
		Class.forName("com.mysql.jdbc.Driver");//×¢²á
		 conn=DriverManager.getConnection
					("jdbc:mysql://localhost:3306/rent", "root", "root");//2Á¬½Ó
		 stat=conn.createStatement();
		 rs =stat.executeQuery("select * from fowner");
		while(rs.next())
		{
			System.out.println(rs.getString("fName"));
		}//3
		 
	   
		
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
			 if(stat!=null)
			    stat.close();
			 if(conn!=null)
		        conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		
	}
 
	
	}

}
