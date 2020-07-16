<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
    <title>Fowner</title>
    <link href="css/demo.css" rel="stylesheet" type="text/css">
    <!--Framework-->
    <script src="js/jquery-1.10.2.min.js" type="text/javascript"></script>
    <script src="js/jquery-ui.js" type="text/javascript"></script>
    <!--End Framework-->
    <script src="js/jquery.ffform.js" type="text/javascript"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $('#form').ffform({ animation: 'flip', submitButton: '#submit', validationIndicator: '#validation', errorIndicator: '#error', successIndicator: '#success', 'fields': [{ 'id': 'name', required: true,requiredMsg:'Name is required', type: 'alpha', validate: true, msg: 'Invalid Name' }, { 'id': 'email', required: true,requiredMsg:'E-Mail is required', type: 'email', validate: true, msg: 'Invalid E-Mail Address' }, { 'id': 'phone', required: false, type: 'custom', validate: false, msg: 'Invalid Phone #' }, { 'id': 'message', required: false, type: 'text', validate: false, msg: ''}] });
        });
    </script>
    <style>
        .bb{
           background-image: url(images/1.png);
          }
          
    	.one{
    		margin-left:-150px;
    	}	
    </style>


</head>
<body class="bb" >
<%
String username = (String)session.getAttribute("fName");
%>
<div class = "one" ><img src="home/images/one.png" />
  <div class="demos-buttons">
        <h3>
            Fowner</h3>
          <a href="fowner.html" class="submit active">home</a><br><br>
        <a href="../../register.jsp" class="submit">注册</a> <br>
        <a href="../allh3" class="submit">查看房子信息</a>
        <br>
        <a href="../udfow.jsp?fName=<%=username %>" class="submit">查看个人信息</a>
        <br>
        <a href="../../homepage/shouye.html" class="submit">返回主页面</a>
    </div>
    <section id="getintouch" >
        <div class="container" style="border-bottom: 0;">
            <h1>
                <span>欢迎来到房东主页!</span>
            </h1>
        </div>
    </section>
</div>
</body>
</html>