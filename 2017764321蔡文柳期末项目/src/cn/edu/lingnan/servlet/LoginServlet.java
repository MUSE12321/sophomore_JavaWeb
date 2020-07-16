package cn.edu.lingnan.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.edu.lingnan.dao.CustomerDAO;
import cn.edu.lingnan.dao.FownerDAO;

public class LoginServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//1.获取客户端提交的参数
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		System.out.println("LoginServlet:"+username+"  "+password);
		
		//2.处理一下参数，调用业务逻辑
		FownerDAO fn = new FownerDAO();
		CustomerDAO cu = new CustomerDAO();
		int fSuper = fn.Login(username,password);
	    HttpSession f = req.getSession();
	    f.setAttribute("fSuper", fSuper);
	   f.setAttribute("fName", username);
		//3.根据返回的结果处理
		if(fSuper!=0){
			resp.sendRedirect(req.getContextPath()+"/homepage/shouye.html");
		}else{
			int cSuper = cu.Login(username,password);
		    HttpSession c = req.getSession();
		    c.setAttribute("cSuper", cSuper);
		    c.setAttribute("cName", username);
		    if(cSuper!=0){
		    	resp.sendRedirect(req.getContextPath()+"/homepage/shouye.html");
		    }else{
		    	resp.sendRedirect(req.getContextPath()+"/error.html");
		    }
			
		}
		
	}

}
