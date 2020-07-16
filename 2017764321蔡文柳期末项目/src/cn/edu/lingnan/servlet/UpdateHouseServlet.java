package cn.edu.lingnan.servlet;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.edu.lingnan.dao.HouseDAO;
import cn.edu.lingnan.dto.House;

public class UpdateHouseServlet extends HttpServlet {


	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//1
		String s = req.getParameter("s");
		boolean flag = false;
		HouseDAO hd = new HouseDAO();
		if(s==null){//修改
		String hid = req.getParameter("hid");
		String fid = req.getParameter("fid");
		String fname = new String(req.getParameter("fname").getBytes("ISO-8859-1"),"GBK");
	//	System.out.println(fname);
		String cid =req.getParameter("cid");
		String dz = new String(req.getParameter("dz").getBytes("ISO-8859-1"),"GBK");
		String pri = req.getParameter("pri");
	    int  hsta = Integer.parseInt(req.getParameter("hsta"));
	    String fpn = req.getParameter("fpn");
	    House h = new House();
	    h.sethId(hid);
	    h.setfId(fid);
	    h.setfName(fname);
	    h.setcId(cid);
	    h.setDz(dz);
	    h.setPri(pri);
	    h.sethSta(hsta);
	    h.setfPn(fpn);
	    hd = new HouseDAO();
	    flag= hd.updateHouse(h);
	}else{//删除
		if(s.equals("delall")){//批量删除
			 
			 String[] allhid = req.getParameterValues("shaosen");
			 System.out.println(allhid);
			 String[] temp = allhid[0].split(",");
		     for(String a:temp){
		    	 System.out.println(a);
		    	 hd.deleteHouse(a);
		     }
		     flag = true;
		 }else{//删除一条记录
			 String hid = req.getParameter("hid");
			 flag = hd.deleteHouse(hid);
			 
		}
		
	}
	    //2
		Vector<House> v = hd.findAllHouse();
		HttpSession session = req.getSession();
		session.setAttribute("allhouse", v);
	    //3
	    if(flag)
	    	resp.sendRedirect(req.getContextPath()+"/admin/allhouse.jsp");
	    else
	    	resp.sendRedirect(req.getContextPath()+"/error.html");
	}
}
