<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
    <%@ page import="java.util.*,cn.edu.lingnan.dto.Customer,cn.edu.lingnan.dao.CustomerDAO" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>Insert title here</title>
</head>
<body>
<form action="udcus">
 <table  >
<tr align="center">
     <td>���</td>
     <td>�������</td>
     <td>����</td>
     <td>Ȩ��</td>
     <td>��ϵ��ʽ</td>
     <td></td>
 </tr> 
<%
   CustomerDAO cu = new CustomerDAO();
   Vector<Customer> cc = cu.findAllCustomer();
   Iterator<Customer> it = cc.iterator();
   String cName = request.getParameter("cName");
   Customer c = null;
   while(it.hasNext()){
	   c = it.next();
	   if(c.getcName().equals(cName)){
 
 %>	
 <tr align="center">
     <td><input type="text" name="cid" readonly="true" value=<%=c.getcId()%>></td>
     <td><input type="text" name="cname" value=<%=c.getcName()%>></td>
     <td><input type="text" name="cpassword" value=<%=c.getcPassword()%>></td>
     <td><input type="text" name="csuper" readonly="true" value=<%=c.getcSuper()%>></td>
     <td><input type="text" name="cpn" value=<%=c.getcPn()%>></td>
     <td><input type="submit" value="ȷ���޸�"></td>
     <td><a href="ff/customer.jsp" style="color:black;text-decoration:none;">ȡ��</a></td>
</tr>
<%
   }}
%>
</table>
</form>
</body>
</html>