package cn.edu.lingnan.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import cn.edu.lingnan.util.DataAccess;
public class FownerTest {

	public static void main(String[] args) {
//		FownerDAO f=new FownerDAO();
//		System.out.println(f.findFownerByNameAndPassword("zhangsan1", "zhangsan1"));
//		Vector<Fowner> v = new Vector<Fowner>();
//		v = f.findAllFowner();
//		System.out.println(v.size());
                 //----0319---//
        Connection conn=null;
		Statement stat=null;
		ResultSet rs =null;
    try {
		Class.forName("com.mysql.jdbc.Driver");//1ע��
		 conn=DriverManager.getConnection
					("jdbc:mysql://localhost:3306/rent", "root", "root");//2����
		 stat = conn.createStatement
		 (ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
		 //--2--
		rs = stat.executeQuery("select * from fowner");
		System.out.println(rs.isBeforeFirst());
		rs.first();
		System.out.println(rs.isBeforeFirst());
		//rs.last();
		//rs.absolute(4);  //����λ��
		rs.relative(3);   //���λ��
		System.out.println(rs.isLast());
		System.out.println(rs.getString("fName"));
		rs.previous();   //ǰ��
		rs.previous();
		rs.previous();
		System.out.println(rs.getString("fName"));
		//System.out.println(rs.isFirst());
		rs.first();
		System.out.println(rs.getRow());
		rs.last();
		System.out.println(rs.getRow());
		
		          //--3����--����Ĳ���//
		
//	    rs.moveToInsertRow();	//���Ƶ�һ�������ٽ��в���
//	    rs.updateString("fId", "f05");
//	    rs.updateString("fName", "ssss");
//	    rs.updateString("fPassword", "ssss");
//	    rs.updateInt("fSuper", 4);
//	    rs.updateInt("fPn", 666666);
//	    rs.insertRow();
		
		//����//
//		rs.absolute(3);
//		rs.updateString("fPassword", "tttt");
//		rs.updateRow();
		 
		//ɾ��//
		rs.last();
		rs.deleteRow();
		 
		
		
	
		
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	}
    catch (SQLException e) {
		e.printStackTrace();
	}finally{
//		 try {
//			 if(rs!=null)
//			    rs.close();
//			 if(stat!=null)
//			    stat.close();
//			 if(conn!=null)
//		        conn.close();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		 
		DataAccess.getConnection();//���������try-catch���
	}
		
	}

}
