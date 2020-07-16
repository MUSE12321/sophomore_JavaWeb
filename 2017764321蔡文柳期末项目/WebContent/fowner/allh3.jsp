<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
     <%@ page import="java.util.*,cn.edu.lingnan.dto.House" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>Insert title here</title>
</head>
<body>
<table border="1" width="900" height="380" >
<tr align="center">
     <td>房号</td>
     <td>房东编号</td>
     <td>房东姓名</td>
     <td>租客编号</td>
     <td>房子地址</td>
     <td>租金</td>
     <td>房子状态</td>
     <td>房东联系方式</td>
 </tr> 
<%
   Vector<House> v =(Vector<House>)session.getAttribute("allhouse");
   Iterator<House> it = v.iterator();
   House h = null;
   while(it.hasNext()){
	   h = it.next();
 
%>	
 <tr align="center">
     <td><%=h.gethId() %></td>
     <td><%=h.getfId() %></td>
     <td><%=h.getfName() %></td>
     <td ><%=h.getcId() %></td>
     <td><%=h.getDz() %></td>
     <td><%=h.getPri() %></td>
     <td><%=h.gethSta() %></td>
     <td><%=h.getfPn() %></td>  
 </tr> 
 <%
   }
%>
</table>
<br>
<div style="margin-left: 550px; width:500px; hight:120px;color:red">
<td">说明：房子状态为5表示已出租，为0表示未出租！</td></div>
</body>
</html>