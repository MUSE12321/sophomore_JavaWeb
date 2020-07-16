package cn.edu.lingnan.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.lingnan.dao.CustomerDAO;
import cn.edu.lingnan.dto.Customer;


public class Reg1Servlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String cid = req.getParameter("cid");
		String username = req.getParameter("username");
		String password = req.getParameter("password1");
		String csuper = req.getParameter("csuper");
		String cpn = req.getParameter("cpn");
		Customer c = new Customer();
		c.setcId(cid);
		c.setcName(username);
		c.setcPassword(password);
		c.setcSuper(Integer.parseInt(csuper));
		c.setcPn(cpn);
		CustomerDAO cu = new CustomerDAO();
		int flag = cu.insert(c);
		if(flag == 2)
			resp.sendRedirect(req.getContextPath()+"/index.html");
		else
			resp.sendRedirect(req.getContextPath()+"/reg1.jsp");
		
		
	}
	
}
