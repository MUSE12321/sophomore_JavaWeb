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

public class AddfowServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String fid = req.getParameter("fid");
		String username = req.getParameter("username");
		String password = req.getParameter("password1");
		String fsuper = req.getParameter("fsuper");
		String fpn = req.getParameter("fpn");
		Fowner f = new Fowner();
		f.setfId(fid);
		f.setfName(username);
		f.setfPassword(password);
		f.setfSuper(Integer.parseInt(fsuper));
		f.setfPn(fpn);
		FownerDAO fn = new FownerDAO();
		int flag = fn.inserInfoToFowner(f);
		Vector<Fowner> v = fn.findAllFowner();
		HttpSession ff = req.getSession();
		ff.setAttribute("allFow", v); //´æ
		if(flag == 2)
			resp.sendRedirect(req.getContextPath()+"/admin/allfow.jsp");
		else
			resp.sendRedirect(req.getContextPath()+"/register.jsp");
		
		
	}

}
