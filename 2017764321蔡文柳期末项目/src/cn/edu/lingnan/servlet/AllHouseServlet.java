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

public class AllHouseServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
      //µ÷ÓÃDAO
		HouseDAO hd = new HouseDAO();
		Vector<House> v = hd.findAllHouse();
		HttpSession h = req.getSession();
		h.setAttribute("allhouse", v);
		//·µ»Ø
		resp.sendRedirect(req.getContextPath()+"/admin/allhouse.jsp");
	}

}
