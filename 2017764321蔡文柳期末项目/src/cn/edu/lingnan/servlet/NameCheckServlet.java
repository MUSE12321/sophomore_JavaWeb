package cn.edu.lingnan.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.lingnan.dao.CustomerDAO;
import cn.edu.lingnan.dao.FownerDAO;
import cn.edu.lingnan.dao.HouseDAO;

public class NameCheckServlet extends HttpServlet {

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String username=req.getParameter("username");
	    FownerDAO fn = new FownerDAO();
	    CustomerDAO cu = new CustomerDAO();
	    HouseDAO hd = new HouseDAO();
	    boolean flag1 = fn.findByName(username);
	    if(flag1){
	    	resp.getWriter().print("true");
	    }else{
	    	boolean flag2 = cu.findByName(username);
	    	if(flag2){
	    		resp.getWriter().print("true");
	    	}else{
	    		boolean flag3 = hd.findByName("hid");
	    		 resp.getWriter().print("false");
	    	     }
	    	  
	         }
	    	
	}
	
	
}
