<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>Insert title here</title>
</head>
<style>
   .one{
        background-image: url(../homepage/IMG/0.jpg);
        margin-left: 200px;
   }
</style>
<body class="one">
<c:out value="这是管理员才可以访问的页面！" escapeXml="false"/>
<br><br>
<tr>
    <td>
        <form action="fowner">
        <a href="addfow.jsp" style="color:#cccfff;text-decoration:none;">添加房东信息</a>
        <input type="submit" value="find Allfowner">   
        </form>
    </td>
<br>
    <td>
        <form action="findAllHouse">
        <a href="addhouse.jsp" style="color:#cccfff;text-decoration:none;">添加房子信息</a>
        <input type="submit" value="find Allhouse">
        </form>
    </td>
<br>
    <td>
        <form action="findAllCutomer">
        <a href="addcus.jsp"  style="color:#cccfff;text-decoration:none;">添加租客信息</a>
        <input type="submit" value="find Allcustomer">
        </form>
    </td>
<br>



</tr>
<!--第二种方法  <a href="">查找所有用户</a>-->
</body>
</html>