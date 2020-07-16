<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
    <%@ page import="java.util.*,cn.edu.lingnan.dto.Customer" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<script type="text/javascript" src="allcus.js"></script>
<title>Insert title here</title>
</head>
<body>
 <table border="1" width="700" height="350">
<tr align="center">
     <td><input id="btn1" type="button" value="全选" onclick="allcheck();"/></td>
     <td>租客编号</td>
     <td>姓名</td>
     <td>密码</td>
     <td>权限</td>
     <td>联系方式</td>
     <td><input id="btn2" type="button" value="批量删除" onclick="delAllCus();"/></td>
     <td><a href="addcus.jsp" style="color:black;text-decoration:none;">添加</a></td>
 </tr> 
<%
   Vector<Customer> v =(Vector<Customer>)session.getAttribute("allcus");
   Iterator<Customer> it = v.iterator();
   Customer c = null;
   while(it.hasNext()){
	   c = it.next();
 
%>	
 <tr align="center">
     <td><input type="checkbox" name="check" value=<%=c.getcId() %>></td>
     <td><%=c.getcId() %></td>
     <td><%=c.getcName() %></td>
     <td><%=c.getcPassword() %></td>
     <td><%=c.getcSuper() %></td>
     <td><%=c.getcPn()%></td>
      <td><a href="updatecus.jsp?cid=<%=c.getcId()%>">修改</a>
          <a href="updatecus?s=del&cid=<%=c.getcId()%>"
            onclick="return confirm('您确定要删除该条记录吗');">删除</a>
            </td>
            <td></td>
 </tr> 
 <%
   }
%>
</table>
</body>
</html>