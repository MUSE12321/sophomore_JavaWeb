package cn.edu.lingnan.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import cn.edu.lingnan.dto.*;
import cn.edu.lingnan.util.DataAccess;

public class FownerDAO {
	                 //---查找---//
	//根据用户名和密码查找用户是否存在，如果存在返回true，不存在返回false
	public int Login(String _name, String _password){
		int flag=0;
		Connection conn=null;
		PreparedStatement prep=null;
		ResultSet rs =null;
    try {
//		Class.forName("com.mysql.jdbc.Driver");//1注册
//		 conn=DriverManager.getConnection
//					("jdbc:mysql://localhost:3306/rent", "root", "root");//2连接
//		 stat=conn.createStatement();
//		 String sql ="select * from fowner where fName='"
//		             +_name+"'and fpassword='"+_password+"'"; 
//		 rs =stat.executeQuery("select * from fowner");
		 conn = DataAccess.getConnection();
		 prep = conn.prepareStatement
		    ("select * from fowner where fName= ? and fPassword= ?");
		 prep.setString(1, _name);
		 prep.setString(2, _password);
		 rs = prep.executeQuery();
		 
		 if(rs.next())
			 flag= rs.getInt("fSuper");
//		while(rs.next())
//		{
//			System.out.println(rs.getString("fName"));
//		}//3
		 
	}
    catch (SQLException e) {
		e.printStackTrace();
	}finally{
		DataAccess.getConnection();
		
	}
 
		return flag;
		
	}
	//2019.6.6
	public boolean findByName(String _name){
		boolean flag = false;
		Connection conn=null;
		PreparedStatement prep=null;
		ResultSet rs =null;
    try {

		 conn = DataAccess.getConnection();
		 prep = conn.prepareStatement
		    ("select * from fowner where fName= ? ");
		 prep.setString(1, _name);
		 rs = prep.executeQuery();
		 
		 if(rs.next())
			 flag= true;
	}
    catch (SQLException e) {
		e.printStackTrace();
	}finally{
		DataAccess.getConnection();
		
	}
 
		return flag;
	}
	
	//查找全部学生记录，返回一个对象组
	public Vector<Fowner> findAllFowner(){
		Vector<Fowner> v=new Vector<Fowner>();
		Connection conn=null;
		
		Statement stat=null;
		ResultSet rs =null;
    try {
		 conn= DataAccess.getConnection();
		 stat=conn.createStatement();
		 String sql ="select * from fowner";
		 rs =stat.executeQuery(sql);
		
		while(rs.next())
		{
			Fowner f = new Fowner();
			f.setfId(rs.getString("fId"));
			f.setfName(rs.getString("fName"));
			f.setfPassword(rs.getString("fPassword"));
			f.setfSuper(rs.getInt("fSuper"));
			f.setfPn(rs.getString("fPn"));
			v.add(f);
					
			
		}
		 
	   
		
	} 
    catch (SQLException e) {
		e.printStackTrace();
	}finally{
		DataAccess.getConnection();
		 
	}
		return v;
		
	}
//	//插入
//	public int inserInfoToFowner(Fowner f){
//		int flag = 0;
//		String fId = f.getfId();
//		String fName = f.getfName();
//		String fPassword =f.getfPassword();
//		int fSuper = f.getfSuper();
//		int fPn = f.getfPn();
//		int fSta = f.getfSta();
//		
//		
//		Connection conn=null;
//		PreparedStatement prep1=null;
//		ResultSet rs1 =null;
//		PreparedStatement prep2=null;
//		ResultSet rs2 =null;
//	
//    try {
//		Class.forName("com.mysql.jdbc.Driver");//注册
//		 conn=DriverManager.getConnection
//					("jdbc:mysql://localhost:3306/rent", "root", "root");//2连接
//		 prep1 =conn.prepareStatement("insert into fowner values(?,?,?,?,?,?)");
//		 prep1 = conn.prepareStatement
//				 ("select * from fowner where fId= ?");
//		 prep1.setString(1, fId);
//		 rs1 =prep1.executeQuery();
//		 while(!rs1.next()){
//			 flag =1;
//			 return flag;
//		 }
//		 //假设房东号已存在返回1
//		 
//		
//		 
//		 prep1 =conn.prepareStatement("insert into fowner values(?,?,?,?,?,?)");
//		 prep1.setString(1, fId);
//		 prep1.setString(2,fName);
//		 prep1.setString(3,fPassword);
//		 prep1.setInt(4, fSuper);
//		 prep1.setInt(5, fPn);
//		 prep1.setInt(6, fSta);
//		 prep1.executeUpdate();
//		 flag =2;
//		
//	} catch (ClassNotFoundException e) {
//		e.printStackTrace();
//	}
//    catch (SQLException e) {
//		e.printStackTrace();
//	}finally{
//		 try {
//			 if(rs1!=null)
//			    rs1.close();
//			 if(prep1!=null)
//				    prep1.close();
//			 if(rs2!=null)
//				    rs2.close();
//				 if(prep2!=null)
//					    prep2.close();
//			
//			 if(conn!=null)
//		        conn.close();
//		} catch (SQLException e) {
//			flag=3;
//			e.printStackTrace();
//		}
//		 
//		
//	}
//		return flag;
//		
//	}
	
	           //---插入---//
	public int inserInfoToFowner(Fowner f){
		int flag = 0;
		String fId = f.getfId();
		String fName = f.getfName();
		String fPassword =f.getfPassword();
		int fSuper = f.getfSuper();
		String fPn = f.getfPn();
		
		Connection conn=null;
		PreparedStatement prep=null;
		
    try {
		Class.forName("com.mysql.jdbc.Driver");//注册
		 conn=DriverManager.getConnection
					("jdbc:mysql://localhost:3306/rent", "root", "root");//2连接
		 prep =conn.prepareStatement("insert into fowner values(?,?,?,?,?)");
		 prep.setString(1, fId);
		 prep.setString(2,fName);
		 prep.setString(3,fPassword);
		 prep.setInt(4, fSuper);
		 prep.setString(5, fPn);
		 prep.executeUpdate();
		 flag=2;
		
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	}
    catch (SQLException e) {
		e.printStackTrace();
	}finally{
		 try {
			 if(prep!=null)
			    prep.close();
			 if(conn!=null)
		        conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 
		
	}
	
		return flag;
		
	}
	
	
	
	            //---更新---//
	public boolean updataFowner(Fowner f){
		boolean flag = false;
		String fId = f.getfId();
		String fName = f.getfName();
		String fPassword =f.getfPassword();
		int fSuper = f.getfSuper();
		String fPn =f.getfPn();

		
		Connection conn=null;	
		PreparedStatement prep =null;
	//	ResultSet rs =null;
	    try {
			 conn = DataAccess.getConnection();
			 prep = conn.prepareStatement
					 ("update fowner set fName = ?,fPassword = ?,fSuper=?,fPn = ? where fId = ?" );//补充完整
			 prep.setString(1, fName);
			 prep.setString(2,fPassword);
			 prep.setInt(3, fSuper);
			 prep.setString(4,fPn);
			 prep.setString(5, fId);
			 prep.executeUpdate();
			 flag = true;
	    }
	    catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DataAccess.closeConnection(prep,conn);
			 
		}
	 
		
		return flag;
		
	}
	
	
	            //--删除房东信息--//
	public boolean deleteFowner(String _fid){
				boolean flag = false;
				boolean flag1=false;
				Connection conn=null;	
  			    PreparedStatement prep =null;
				PreparedStatement prep1 =null;
				PreparedStatement prep2 =null;
				ResultSet rs =null;
				try {
					conn = DataAccess.getConnection();
					 //既然删不了房东id，先删房子
					prep = conn.prepareStatement
							("select * from house where fId = _fid");
					flag1=true;
					if(flag1)
					{
					 prep1 = conn.prepareStatement
							 ("delete from house where fId = ? ");
					 prep1.setString(1, _fid);
					 prep1.executeUpdate();
					 prep2 = conn.prepareStatement
							 ("delete from fowner where fId = ? ");
					 prep2.setString(1, _fid);
					 prep2.executeUpdate();
					 flag = true;
					}else{
				
					 prep2 = conn.prepareStatement
							 ("delete from fowner where fId = ? ");
					 prep2.setString(1, _fid);
					 prep2.executeUpdate();
					 flag = true;
						
					}
			    }catch (SQLException e) {
					e.printStackTrace();
				}finally{
					DataAccess.closeConnection(prep1,conn);
					DataAccess.closeConnection(prep2,conn);
					 
				}
			 
					
				
				return flag;			
			
			}

	
	
	
}
