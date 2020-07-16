package cn.edu.lingnan.servlet;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.edu.lingnan.dao.CustomerDAO;
import cn.edu.lingnan.dto.Customer;

public class AllCustomerServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//µ÷ÓÃDAO
		CustomerDAO cu = new CustomerDAO();
		Vector<Customer> v = cu.findAllCustomer();
		HttpSession c = req.getSession();
		c.setAttribute("allcus", v);
	
		  //·µ»Ø
		resp.sendRedirect(req.getContextPath()+"/admin/allcus.jsp");
		
	}

}

