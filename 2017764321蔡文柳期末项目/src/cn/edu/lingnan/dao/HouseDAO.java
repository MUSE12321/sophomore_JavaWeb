package cn.edu.lingnan.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import cn.edu.lingnan.dto.House;
import cn.edu.lingnan.util.DataAccess;
public class HouseDAO {
	public boolean findByName(String _name){
		boolean flag = false;
		Connection conn=null;
		PreparedStatement prep=null;
		ResultSet rs =null;
    try {

		 conn = DataAccess.getConnection();
		 prep = conn.prepareStatement
		    ("select * from house where hid= ? ");
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
	
	         //--查找全部房子记录，返回一个对象组--//
		public Vector<House> findAllHouse(){
			Vector<House> v = new Vector<House>();
			Connection conn=null;
			
			Statement stat=null;
			ResultSet rs =null;
	    try {
	    	 conn = DataAccess.getConnection();
			 stat=conn.createStatement();
			 String sql ="select * from house";
			 
			 rs =stat.executeQuery(sql);
			
			while(rs.next())
			{
				House h = new House();
				h.sethId(rs.getString("hId"));
				h.setfId(rs.getString("fId"));
				h.setfName(rs.getString("fName"));
				h.setcId(rs.getString("cId"));
				h.setDz(rs.getString("dz"));
				h.setPri(rs.getString("Pri"));
				h.sethSta(rs.getInt("hSta"));
				h.setfPn(rs.getString("fPn"));
				v.add(h);
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
	
	
	
	       //---插入房子记录---//
	public int insertInfoToHouse(House h){
		int flag= 0;
		String hId = h.gethId();
		String fId = h.getfId();
		String fName = h.getfName();
		String cId = h.getcId();
		String dz = h.getDz();
		String Pri = h.getPri();
		int   hSta = h.gethSta();
		String fPn = h.getfPn();
		
		
		
		Connection conn=null;
		PreparedStatement prep=null;
		PreparedStatement prep1=null;
		ResultSet rs1 =null;
		PreparedStatement prep2=null;
		ResultSet rs2 =null;
		PreparedStatement prep3=null;
		ResultSet rs3 =null;
		PreparedStatement prep4=null;
		ResultSet rs4=null;
    try {
		Class.forName("com.mysql.jdbc.Driver");//注册
		 conn=DriverManager.getConnection
					("jdbc:mysql://localhost:3306/rent", "root", "root");//2连接
		 //假设房东号不存在返回3
		 prep2 = conn.prepareStatement
				 ("select * from fowner where fId= ?");
		 prep2.setString(1, fId);
		 rs2 =prep2.executeQuery();
		 while(!rs2.next()){
			 flag =3;
			 return flag;
		 }
		 
 
		 prep =conn.prepareStatement("insert into house values(?,?,?,?,?,?,?,?)");
		 prep.setString(1, hId);
		 prep.setString(2,fId);
		 prep.setString(3, fName);
		 prep.setString(4, cId);
		 prep.setString(5, dz);
		 prep.setString(6,Pri);
		 prep.setInt(7, hSta);
		 prep.setString(8, fPn);
		 
		 prep.executeUpdate();
		 flag =2;
		
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	}
    catch (SQLException e) {
		e.printStackTrace();
	}finally{
		 try {
			 if(rs1!=null)
			    rs1.close();
			 if(prep1!=null)
				    prep1.close();
			 if(rs2!=null)
				    rs2.close();
				 if(prep2!=null)
					    prep2.close();
				 if(rs3!=null)
					    rs3.close();
					 if(prep3!=null)
						    prep3.close();
					 if(rs4!=null)
						    rs4.close();
						 if(prep4!=null)
							    prep4.close();
			 if(prep!=null)
			    prep.close();
			 if(conn!=null)
		        conn.close();
		} catch (SQLException e) {
			flag=6;
			e.printStackTrace();
		}
		 
		
	}
		return flag;
		
	}
	
	
	           //---更新----//
		public boolean updateHouse(House h){
			boolean flag = false;
			String hId = h.gethId();
			String fId = h.getfId();
			String fName = h.getfName();
			String cId = h.getcId();
			String dz = h.getDz();
			String Pri = h.getPri();
			int   hSta = h.gethSta();
			String fPn = h.getfPn();
			
		Connection conn=null;	
		PreparedStatement prep =null;
		ResultSet rs =null;
	    try {
			 conn = DataAccess.getConnection();
			 prep = conn.prepareStatement
					 ("update house set fId = ?,fName = ?, cId = ?, dz = ?,Pri =? ,hSta = ?, fPn = ? where hId = ?");
			 prep.setString(1, fId);
			 prep.setString(2,fName);
			 prep.setString(3,cId);
			 prep.setString(4,dz);
			 prep.setString(5,Pri);
			 prep.setInt(6, hSta);
			 prep.setString(7,fPn);
			 prep.setString(8,hId);
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
		     //--删除--//
		public boolean deleteHouse(String _hid){
			boolean flag = false;
			Connection conn=null;	
			PreparedStatement prep =null;
			ResultSet rs =null;
		    try {
				 conn = DataAccess.getConnection();
				 prep = conn.prepareStatement
						 ("delete from house where hId = ? ");
				 prep.setString(1, _hid);
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
