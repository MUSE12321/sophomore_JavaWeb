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
		//1.��ȡ�ͻ����ύ�Ĳ���
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		System.out.println("LoginServlet:"+username+"  "+password);
		
		//2.����һ�²���������ҵ���߼�
		FownerDAO fn = new FownerDAO();
		CustomerDAO cu = new CustomerDAO();
		int fSuper = fn.Login(username,password);
	    HttpSession f = req.getSession();
	    f.setAttribute("fSuper", fSuper);
	   f.setAttribute("fName", username);
		//3.���ݷ��صĽ������
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
