<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
      <%@ taglib uri="http://lingnan.edu.cn" prefix="t" %>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
      <%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>Insert title here</title>
</head>
<body>
<!--    <t:hello/>  -->
  <c:set var="a" value="aaa"/>
  <c:out value="${a}"/><br>
  <c:catch var="ex">
  <%=9/0 %>
  </c:catch>
  <c:out value="${ex}"/><br>
  <c:if test="${a=='abaa'}">
      if is true<br>
  </c:if>
  <c:choose>
     <c:when test="${a=='abab'}">
       when is true<br>
     </c:when>
     <c:otherwise>
     otherwise is true<br>
     </c:otherwise>
 </c:choose>
    <% 
    String[]aa = new String[5];
    aa[0]="aaa";aa[1]="bbb";aa[2]="ccc";aa[3]="ddd";aa[4]="eee";
    request.setAttribute("bb", aa);
    %>
  <c:forEach var="cc" items="${bb}">
     <c:out value="${cc }"/>
  </c:forEach>
  <br>
  <c:set var="dd" value="zhangsan,lisi,wangwu,zhaoliu"/>
  <c:forTokens var="ee" items="${dd}" delims=",">
  <c:out value="${ee}"/>
  </c:forTokens>
  <br><br>
  <!-- sql tag -->
  <sql:setDataSource
      var="mydb"
      driver="com.mysql.jdbc.Driver"
      url="jdbc:mysql://localhost:3306/rent"
      user="root"
      password="root"
  />
   <sql:query var="result" dataSource="${mydb}" sql="select* from fowner"/>
   <table>
      <tr><td>编号</td>姓名<td>密码</td><td>权限</td><td>电话</td><td>状态</td></tr>
      <c:forEach var="re" items="${result.rows}">
          <tr>
           <td><c:out value="${re.fid}"/></td>
           <td><c:out value="${re.fname}"/></td>
           <td><c:out value="${re.fpassword}"/></td>
           <td><c:out value="${re.fsuper}"/></td>
           <td><c:out value="${re.fpn}"/></td>
           <td><c:out value="${re.fsta}"/></td>
          </tr>   
      </c:forEach>
  
  
   </table>
</body>
</html>