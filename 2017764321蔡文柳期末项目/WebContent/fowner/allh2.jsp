<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
     <%@ page import="java.util.*,cn.edu.lingnan.dto.House" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="allhou.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>Insert title here</title>
</head>
<body>
<table border="1" width="900" height="380" >
<tr align="center">
     <td><input id="btn1" type="button" value="ȫѡ" onclick="allcheck();"/></td>
     <td>����</td>
     <td>�������</td>
     <td>��������</td>
     <td>��ͱ��</td>
     <td>���ӵ�ַ</td>
     <td>���</td>
     <td>����״̬</td>
     <td>������ϵ��ʽ</td>
     <td><input id="btn2" type="button" value="����ɾ��" onclick="delAllHou();"/></td>
 </tr> 
<%
   Vector<House> v =(Vector<House>)session.getAttribute("allhouse");
   Iterator<House> it = v.iterator();
   House h = null;
   while(it.hasNext()){
	   h = it.next();
 
%>	
 <tr align="center">
     <td><input type="checkbox" name="check" value=<%=h.gethId() %>></td>
     <td><%=h.gethId() %></td>
     <td><%=h.getfId() %></td>
     <td><%=h.getfName() %></td>
     <td ><%=h.getcId() %></td>
     <td><%=h.getDz() %></td>
     <td><%=h.getPri() %></td>
     <td><%=h.gethSta() %></td>
     <td><%=h.getfPn() %></td>
     <td><a href="updateHouse.jsp?hid=<%=h.gethId() %>">�޸�</a>
         <a href="updateHouse?s=del&hid=<%=h.gethId()%>"
            onclick="return confirm('��ȷ��Ҫɾ��������¼��');">ɾ��</a>
     </td>
    
 </tr> 
 <tr><a href=""></a></tr>
 <%
   }
%>
</table>
<br>
<div style="margin-left: 550px; width:500px; hight:120px;color:red">
<td">˵��������״̬Ϊ5��ʾ�ѳ��⣬Ϊ0��ʾδ���⣡</td></div>
</body>
</html>