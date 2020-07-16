package cn.edu.lingnan.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthorityFilter implements Filter {

	public void destroy() {
		

	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
	     //�����¼�û���Ȩ��Ϊ1�������û���������Է���������ʵ�ҳ��
		//�����¼�û���Ȩ��Ϊ0����ͨ�û�����ĳЩҳ���㲻�ܷ��ʣ���һ�����ܷ��ʵ�ҳ��admin��admin.html
		HttpServletRequest req = (HttpServletRequest) arg0;
		HttpServletResponse resp = (HttpServletResponse) arg1;
		HttpSession f = req.getSession();
		Integer flag = (Integer)f.getAttribute("fSuper");
	    if(flag!=null){
	    	if(flag == 1){
	    		arg2.doFilter(arg0, arg1);
	    	}else{
			resp.sendRedirect(req.getContextPath()+"/authority.html");
	    	}
	    }else{
	    	resp.sendRedirect(req.getContextPath()+"/index.html");
	    }
	}

	public void init(FilterConfig arg0) throws ServletException {

	}

}
