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

public class UdcusServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//1
		String s =req.getParameter("s");
		boolean flag = false;
		CustomerDAO cu = new CustomerDAO();
		if(s==null){//修改
		String cid = req.getParameter("cid");
		String cname= req.getParameter("cname");
		String cpassword = req.getParameter("cpassword");
		int csuper = Integer.parseInt(req.getParameter("csuper"));
		String cpn = req.getParameter("cpn");
		
		Customer c = new Customer();
		c.setcId(cid);
		c.setcName(cname);
	    c.setcPassword(cpassword);
	    c.setcSuper(csuper);
		c.setcPn(cpn);
		cu = new CustomerDAO();
		flag = cu.updateCustomer(c);
		}else{//删除一条记录
				 String cid = req.getParameter("cid");
				 flag =cu.delete(cid);
				 
			}
		if(flag){
			   resp.sendRedirect(req.getContextPath()+"/fowner/ff/customer.jsp");
			}else{
				resp.sendRedirect(req.getContextPath()+"/error.html");
		         }
		}
		
}
