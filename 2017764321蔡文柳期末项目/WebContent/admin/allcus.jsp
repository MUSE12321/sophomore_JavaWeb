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
     <td><input id="btn1" type="button" value="ȫѡ" onclick="allcheck();"/></td>
     <td>��ͱ��</td>
     <td>����</td>
     <td>����</td>
     <td>Ȩ��</td>
     <td>��ϵ��ʽ</td>
     <td><input id="btn2" type="button" value="����ɾ��" onclick="delAllCus();"/></td>
     <td><a href="addcus.jsp" style="color:black;text-decoration:none;">���</a></td>
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
      <td><a href="updatecus.jsp?cid=<%=c.getcId()%>">�޸�</a>
          <a href="updatecus?s=del&cid=<%=c.getcId()%>"
            onclick="return confirm('��ȷ��Ҫɾ��������¼��');">ɾ��</a>
            </td>
            <td></td>
 </tr> 
 <%
   }
%>
</table>
</body>
</html>