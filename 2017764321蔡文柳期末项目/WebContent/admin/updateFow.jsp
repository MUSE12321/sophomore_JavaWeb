<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
    <%@ page import="java.util.*,cn.edu.lingnan.dto.Fowner" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>Insert title here</title>
</head>
<body>
<form action="updateFow">
<table>
<tr>
     <td>Id</td>
     <td>姓名</td>
     <td>密码</td>
     <td>权限</td>
     <td>电话</td>
     
 </tr> 
<%
  Vector<Fowner> v =(Vector<Fowner>)session.getAttribute("allFow");
   Iterator<Fowner> it = v.iterator();
   String fid = request.getParameter("fid");
   Fowner f = null;
   while(it.hasNext()){
	   f = it.next();
	   if(f.getfId().equals(fid)){      
%>	
 <tr>
     <td><input type = "text" name = "fid" readonly="true" value=<%=f.getfId() %>></td>
     <td><input type = "text" name = "fname" value=<%=f.getfName() %>></td>
     <td><input type = "password" name = "fpassword" value=<%=f.getfPassword() %>></td>
     <td><input type = "text" name = "fsuper" value=<%=f.getfSuper()%>></td>
     <td><input type = "text" name = "fpn" value=<%=f.getfPn() %>></td>
     <td><input type="submit" value="确认修改"></td>
 </tr> 
 <%
   }}
%>
</table>
</form>
</body>
</html>