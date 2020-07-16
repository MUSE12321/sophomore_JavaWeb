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


public class AllFownerServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	//����DAO���������û���Ϣ
		FownerDAO fn = new FownerDAO();
		Vector<Fowner> v = fn.findAllFowner();
		HttpSession f = req.getSession();
		f.setAttribute("allFow", v); //��	
		//���ص�fowner.jpsҳ������ʾ�Ĳ��ҵ�������Ϣ
		resp.sendRedirect(req.getContextPath()+"/admin/allfow.jsp");
	}

}
