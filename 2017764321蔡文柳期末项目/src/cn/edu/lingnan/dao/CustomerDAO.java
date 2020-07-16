package cn.edu.lingnan.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import cn.edu.lingnan.dto.Customer;
import cn.edu.lingnan.util.DataAccess;

public class CustomerDAO {
	public int Login(String _name, String _password){
		int flag=0;
		Connection conn=null;
		PreparedStatement prep=null;
		ResultSet rs =null;
    try {
    	conn=DataAccess.getConnection();
		 prep = conn.prepareStatement
		    ("select * from customer where cName= ? and cPassword= ?");
		 prep.setString(1, _name);
		 prep.setString(2, _password);
		 rs = prep.executeQuery();
		 
		 if(rs.next())
			 flag=rs.getInt("cSuper");
//		while(rs.next())
//		{
//			System.out.println(rs.getString("cName"));
//		}//3
		 
	   
		
	} 
    catch (SQLException e) {
		e.printStackTrace();
	}finally{
		DataAccess.getConnection();	
	}
 
		return flag;
		
	}
	
	public boolean findByName(String _name){
		boolean flag = false;
		Connection conn=null;
		PreparedStatement prep=null;
		ResultSet rs =null;
    try {
    	 conn = DataAccess.getConnection();
		 prep = conn.prepareStatement
		    ("select * from customer where cName= ?");
		 prep.setString(1, _name);
		 rs = prep.executeQuery();
		 
		 if(rs.next())
			 flag=true;
	} 
    catch (SQLException e) {
		e.printStackTrace();
	}finally{
		DataAccess.getConnection();	
	}
 
		return flag;
		
	}
	//查找全部顾客记录，返回一个对象组
	public Vector<Customer> findAllCustomer(){
		Vector<Customer> v = new Vector<Customer>();
		Connection conn=null;
		
		Statement stat=null;
		ResultSet rs =null;
    try {
    	 conn = DataAccess.getConnection();
		 stat=conn.createStatement();
		 String sql ="select * from customer";
		 
		 rs =stat.executeQuery(sql);
		
		while(rs.next())
		{
			Customer c = new Customer();
			c.setcId(rs.getString("cId"));
			c.setcName(rs.getString("cName"));
			c.setcPassword(rs.getString("cPassword"));
			c.setcPn(rs.getString("cPn"));
			c.setcSuper(rs.getInt("cSuper"));
			v.add(c);
		}
		

	} 
    catch (SQLException e) {
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
			e.printStackTrace();
		}
		 
		
	}
 
		return v;
	}
		
	         //---更新----//
	public boolean updateCustomer(Customer c){
		boolean flag = false;
		String cId = c.getcId();
		String cName = c.getcName();
		String cPassword =c.getcPassword();
		int cSuper = c.getcSuper();
		String cPn = c.getcPn();
		
	Connection conn=null;	
	PreparedStatement prep =null;
	ResultSet rs =null;
    try {
		 conn = DataAccess.getConnection();
		 prep = conn.prepareStatement
				 ("update customer set cName = ?,cPassword=?,cSuper= ?,cPn = ? where cId = ?");
		 prep.setString(1, cName);
		 prep.setString(2, cPassword);
		 prep.setInt(3, cSuper);
		 prep.setString(4, cPn);
		 prep.setString(5, cId);
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
	
	         //---插入---//
		public int insert(Customer c){
			int flag = 0;
			String cId = c.getcId();
			String cName = c.getcName();
			String cPassword =c.getcPassword();
			int cSuper = c.getcSuper();
			String cPn = c.getcPn();
		
			Connection conn=null;
			PreparedStatement prep=null;
			
	    try {
			Class.forName("com.mysql.jdbc.Driver");//注册
			 conn=DriverManager.getConnection
						("jdbc:mysql://localhost:3306/rent", "root", "root");//2连接
			 prep =conn.prepareStatement("insert into customer values(?,?,?,?,?)");
             prep.setString(1, cId);
			 prep.setString(2,cName);
			 prep.setString(3,cPassword);
			 prep.setInt(4, cSuper);
			 prep.setString(5, cPn);
			// prep.setString(5, cId);
			 
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
		
	
	         //--删除租客信息 --//
			public boolean delete(String _cid){
				boolean flag = false;
				Connection conn=null;	
				PreparedStatement prep =null;
			    try {
					 conn = DataAccess.getConnection();
					 prep = conn.prepareStatement
							 ("delete from customer where cId = ? ");
					 prep.setString(1, _cid);
					 prep.executeUpdate();
					 flag = true;
			    }catch (SQLException e) {
					e.printStackTrace();
				}finally{
					DataAccess.closeConnection(prep,conn);
					 
				}
			 
					
				
				return flag;
				
			}



}
