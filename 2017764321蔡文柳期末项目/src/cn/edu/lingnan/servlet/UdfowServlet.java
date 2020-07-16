package cn.edu.lingnan.servlet;

import java.io.IOException;
import java.util.Iterator;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.edu.lingnan.dao.FownerDAO;
import cn.edu.lingnan.dto.Fowner;

public class UdfowServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	 String s = req.getParameter("s");
	 boolean flag = false;
	 FownerDAO fn = new FownerDAO();
	 if(s==null){//修改
		String fId = req.getParameter("fid");
		String fName = req.getParameter("fname");
		String fPassword = req.getParameter("fpassword");
		String fPn = req.getParameter("fpn");
		//System.out.println(fId+fName+fPn+fSta);
		Fowner f = new Fowner();
		f.setfId(fId);
		f.setfName(fName);
		f.setfPassword(fPassword);
		f.setfPn(fPn);
		fn = new FownerDAO();
		flag = fn.updataFowner(f);
	 }else{//删除一条记录
			 String fid = req.getParameter("fid");
			 flag = fn.deleteFowner(fid);
			 
		 }
//	    Vector<Fowner> v = fn.findAllFowner();
//		HttpSession session = req.getSession();
//		session.setAttribute("allFow", v);
	 
	 if(flag){
		   resp.sendRedirect(req.getContextPath()+"/fowner/ff/fowner.jsp");
		}else{
			resp.sendRedirect(req.getContextPath()+"/error.html");
	         }
			
	 }

}
