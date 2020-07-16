<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
    <%@ page import="java.util.*,cn.edu.lingnan.dto.Fowner" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="allfow.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>Insert title here</title>
</head>
<body>
<table border="1" width="700" height="350" >
<tr align="center">
     <td><input id="btn1" type="button" value="全选" onclick="allcheck();"/></td>
     <td>房东编号</td>
     <td>房东姓名</td>
     <td>权限</td>
     <td>联系方式</td>
     <td><input id="btn2" type="button" value="批量删除" onclick="delAllFow();"/></td>
     <td><a href="addfow.jsp" style="color:black;text-decoration:none;">添加</a></td>
 </tr> 
 
<%
  Vector<Fowner> v =(Vector<Fowner>)session.getAttribute("allFow");
   Iterator<Fowner> it = v.iterator();
   Fowner f = null;
   while(it.hasNext()){
	   f = it.next();
	   if(!f.getfId().equals("000")){
	
%>	
 <tr align="center">
     <td><input type="checkbox" name="check" value=<%=f.getfId() %>></td>
     <td><%=f.getfId() %></td>
     <td><%=f.getfName() %></td>
     <td><%=f.getfSuper() %></td>
     <td><%=f.getfPn() %></td>
     <td>
         <a href="updateFow.jsp?fid=<%=f.getfId()%>">修改</a>
         <a href="updateFow?s=del&fid=<%=f.getfId()%>"
            onclick="return confirm('您确定要删除该条记录吗');">删除</a>
     </td>
     <td></td>
 </tr> 
 <%
   }
	   
	   }
%>
</table>
</body>
</html>