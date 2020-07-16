<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
    <%@ page import="java.util.*,cn.edu.lingnan.dto.Fowner,cn.edu.lingnan.dao.FownerDAO" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>Insert title here</title>
</head>
<body>
<form action="udfow">
<table>
<tr>
     <td>Id</td>
     <td>姓名</td>
     <td>密码</td>
     <td>电话</td>
     <td></td>
 </tr> 
<%
   FownerDAO fn = new FownerDAO();
   Vector<Fowner> ff = fn.findAllFowner();
   Iterator<Fowner> it = ff.iterator();
   String fName = request.getParameter("fName");
   Fowner f = null;
   while(it.hasNext()){
	   f = it.next();
	   if(f.getfName().equals(fName)){
		   
%>		   
 <tr>
     <td><input type = "text" name = "fid" readonly="true" value=<%=f.getfId() %>></td>
     <td><input type = "text" name = "fname" value=<%=f.getfName() %>></td>
     <td><input type = "text" name = "fpassword" value=<%=f.getfPassword() %>></td>
     <td><input type = "text" name = "fpn" value=<%=f.getfPn() %>></td>
     <td><input type="submit" value="确认修改"></td>
     <td><a href="ff/fowner.jsp" style="color:black;text-decoration:none;">取消</a></td>
 </tr> 
 <%
   }}
%>
</table>
</form>
</body>
</html>