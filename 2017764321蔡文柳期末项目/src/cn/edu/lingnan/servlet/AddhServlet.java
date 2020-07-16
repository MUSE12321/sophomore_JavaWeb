package cn.edu.lingnan.servlet;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.edu.lingnan.dao.CustomerDAO;
import cn.edu.lingnan.dao.HouseDAO;
import cn.edu.lingnan.dto.Customer;
import cn.edu.lingnan.dto.House;

public class AddhServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String hid = req.getParameter("hid");
		String fid = req.getParameter("fid");
		String username = new String(req.getParameter("username").getBytes("ISO-8859-1"),"GBK");
		String cid = req.getParameter("cid");
		String dz = new String(req.getParameter("dz").getBytes("ISO-8859-1"),"GBK");
		String pri = req.getParameter("pri");
		String hSta = req.getParameter("hSta");
		String fpn = req.getParameter("fpn");
		House h = new House();
		h.sethId(hid);
		h.setfId(fid);
		h.setfName(username);
		h.setcId(cid);
		h.setDz(dz);
		h.setPri(pri);
		h.sethSta(Integer.parseInt(hSta));
		h.setfPn(fpn);
		HouseDAO hd = new HouseDAO();
		int flag = hd.insertInfoToHouse(h);
		Vector<House> v = hd.findAllHouse();
		HttpSession hh = req.getSession();
		hh.setAttribute("allhouse", v);
		if(flag == 2)
			resp.sendRedirect(req.getContextPath()+"/admin/allhouse.jsp");
		else
			resp.sendRedirect(req.getContextPath()+"/admin/addhouse.jsp");
		
		
	}

}
