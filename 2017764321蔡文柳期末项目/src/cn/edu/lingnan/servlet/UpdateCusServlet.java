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

public class UpdateCusServlet extends HttpServlet {


	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//1
		String s =req.getParameter("s");
		boolean flag = false;
		CustomerDAO cu = new CustomerDAO();
		if(s==null){//修改
		String cid = req.getParameter("cid");
		String cname= req.getParameter("cname");
		String cPassword = req.getParameter("cpassword");
		int  cSuper = Integer.parseInt(req.getParameter("csuper"));
		String cpn = req.getParameter("cpn");
		Customer c = new Customer();
		c.setcId(cid);
		c.setcName(cname);
		c.setcPassword(cPassword);
		c.setcSuper(cSuper);
		c.setcPn(cpn);
		cu = new CustomerDAO();
		flag = cu.updateCustomer(c);
		}else{//删除
			if(s.equals("delall")){
				String[] allcid = req.getParameterValues("allcid");
				 System.out.println(allcid);
				 String[] temp = allcid[0].split(",");
			     for(String a:temp){
			    	 System.out.println(a);
			    	 cu.delete(a);
			     }
			     flag = true;
			 }else{//删除一条记录
				 String cid = req.getParameter("cid");
				 flag =cu.delete(cid);
				 
			}
		}
		//2
		Vector<Customer> v = cu.findAllCustomer();
		HttpSession session = req.getSession();
		session.setAttribute("allcus", v);
		//3
		if(flag){
			resp.sendRedirect(req.getContextPath()+"/admin/allcus.jsp");
			//System.out.println("修改成功");			
		}
			
		else
			resp.sendRedirect(req.getContextPath()+"/error.html");
	}
	
}
