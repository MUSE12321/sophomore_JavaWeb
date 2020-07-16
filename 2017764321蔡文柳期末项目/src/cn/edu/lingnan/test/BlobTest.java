package cn.edu.lingnan.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import cn.edu.lingnan.util.DataAccess;

public class BlobTest {
	public static void main(String[] args) {
		Connection conn=null;	
		PreparedStatement prep =null;
		ResultSet rs =null;
		Statement stmt = null;
		 try {
			 conn = DataAccess.getConnection();
			 
	   //     ------blob存----
//			 File f = new File("E:\\a1.jpg");
//			 FileInputStream fis = new FileInputStream(f);//抛错
//			 prep = conn.prepareStatement
//					 ("insert into blobtable values(?,?)");
//			 prep.setInt(1, 1);
//			 prep.setBinaryStream(2, fis, (int)f.length());
//			 prep.executeUpdate();
//			 
			 
			 //-----blob取
//			 stmt = conn.createStatement();
//			 rs = stmt.executeQuery("select * from blobtable where id = 1");
//			 rs.next();
//		     InputStream is = rs.getBinaryStream("binaryfile");//抛错
//			 File f = new File("E:\\a11.jpg");
//		     FileOutputStream fos = new FileOutputStream(f);
//		
//        int i=0;
//			while((i=is.read())!=-1){
//					fos.write(i);
//			}
			 
			 //     ------clob存----
//			 File f = new File("E:\\a.txt");
//			 FileInputStream fis = new FileInputStream(f);//抛错
//			 prep = conn.prepareStatement
//					 ("insert into clobtable values(?,?)");
//			 prep.setInt(1, 1);
//			 //prep.setBinaryStream(2, fis, (int)f.length());
//			 prep.setAsciiStream(2, fis, (int)f.length());
//			 prep.executeUpdate();
//			 		 
			 //-----clob取
			 stmt = conn.createStatement();
			 rs = stmt.executeQuery("select * from clobtable where id = 1");
			 rs.next();
		     //InputStream is = rs.getBinaryStream("binaryfile");//抛错
			 InputStream is = rs.getAsciiStream("textfile");
			 File f = new File("E:\\a11.txt");
		     FileOutputStream fos = new FileOutputStream(f);
             int i=0;
			while((i=is.read())!=-1){
					fos.write(i);
			}
			 
		 }
//		 catch (FileNotFoundException e) {
//				e.printStackTrace();
//			}//放在前面包含关系
		 catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		 catch (IOException e) {
				e.printStackTrace();
			}
	    catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DataAccess.closeConnection(prep,conn);
			 
		}
			
	}

}
