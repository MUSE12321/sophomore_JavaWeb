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
     <td><input id="btn1" type="button" value="ȫѡ" onclick="allcheck();"/></td>
     <td>�������</td>
     <td>��������</td>
     <td>Ȩ��</td>
     <td>��ϵ��ʽ</td>
     <td><input id="btn2" type="button" value="����ɾ��" onclick="delAllFow();"/></td>
     <td><a href="addfow.jsp" style="color:black;text-decoration:none;">���</a></td>
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
         <a href="updateFow.jsp?fid=<%=f.getfId()%>">�޸�</a>
         <a href="updateFow?s=del&fid=<%=f.getfId()%>"
            onclick="return confirm('��ȷ��Ҫɾ��������¼��');">ɾ��</a>
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