<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
    <%@ page import="java.util.*,cn.edu.lingnan.dto.Customer" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>Insert title here</title>
</head>
<body>
<form action="updatecus">
 <table  >
<tr align="center">
     <td>租客编号</td>
     <td>姓名</td>
     <td>密码</td>
     <td>权限</td>
     <td>联系方式</td>
 </tr> 
<%
   Vector<Customer> v =(Vector<Customer>)session.getAttribute("allcus");
   Iterator<Customer> it = v.iterator();
   String cid = request.getParameter("cid");
   Customer c = null;
   while(it.hasNext()){
	   c = it.next();
	   if(c.getcId().equals(cid)){
 
%>	
 <tr align="center">
     <td><input type="text" name="cid" readonly="true" value=<%=c.getcId()%>></td>
     <td><input type="text" name="cname" value=<%=c.getcName()%>></td>
     <td><input type = "password" name = "cpassword" value=<%=c.getcPassword() %>></td>
     <td><input type = "text" name = "csuper" value=<%=c.getcSuper()%>></td>
     <td><input type="text" name="cpn" value=<%=c.getcPn()%>></td>
     <td><input type="submit" value="确认修改"></td>
</tr>
<%
   }}
%>
</table>
</form>
</body>
</html>