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
<form action="updateHouse">
<table>
<tr>
     <td>����</td>
     <td>�������</td>
     <td>��������</td>
     <td>��ͱ��</td>
     <td></td>
      
 </tr> 
 
<%
   Vector<House> v =(Vector<House>)session.getAttribute("allhouse");
   Iterator<House> it = v.iterator();
   String hid = request.getParameter("hid");
   House h = null;
   while(it.hasNext()){
	   h = it.next();
     if(h.gethId().equals(hid)){
%>	
 <tr>
     <td><input type="text" name="hid" readonly="true" value=<%=h.gethId() %>></td>
     <td><input type="text" name="fid"readonly="true"  value=<%=h.getfId() %>></td>
     <td><input type="text" name="fname" readonly="true" value=<%=h.getfName() %>></td>
     <td><input type="text" name="cid"  value=<%=h.getcId() %>></td>
   </tr>
 <tr>
<tr>
     <td>���ӵ�ַ</td>
     <td>���</td>
     <td>����״̬</td>
     <td>������ϵ��ʽ</td>

</tr>
     <td><input type="text" name="dz" value=<%=h.getDz()%>></td>
     <td><input type="text" name="pri" value=<%=h.getPri() %>></td>
     <td><input type="text" name="hsta" value=<%=h.gethSta()%>></td>
     <td><input type="text" name="fpn" value=<%=h.getfPn()%>></td>
     <td><input type="submit" value="ȷ���޸�"></td>

    
 </tr> 
 <%
     }
   }
%>
</table>
</form>
</body>
</html>