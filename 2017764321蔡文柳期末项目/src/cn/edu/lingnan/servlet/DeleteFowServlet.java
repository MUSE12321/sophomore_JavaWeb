package cn.edu.lingnan.servlet;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.edu.lingnan.dao.FownerDAO;
import cn.edu.lingnan.dto.Fowner;

public class DeleteFowServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
//		//1
//		String fid = req.getParameter("fid");
//		
//		//2
//		FownerDAO fn = new FownerDAO();
//		boolean flag = fn.deleteFowner(fid);
//		Vector<Fowner> v = fn.findAllFowner();
//		HttpSession session = req.getSession();
//		session.setAttribute("allFow", v);
//		
//		//3
//		if(flag)
//			resp.sendRedirect(req.getContextPath()+"/admin/allfow.jsp");
//		else
//			resp.sendRedirect(req.getContextPath()+"/error.html");
	}
}
