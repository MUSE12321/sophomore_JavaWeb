<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<title>enroll</title>
<meta http-equiv="Content-Type" content="text/html; charset=GBK"/>
<script type="text/javascript" src="reg1.js"></script>
<link href="home/css/style.css" rel='stylesheet' type='text/css' />
<meta name="keywords" content="Church Sign In Form,Login Forms,Sign up Forms,Registration Forms,News latter Forms,Elements"./>
<script type="application/x-javascript" src="reg1.js"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
</script>
<!--webfonts-->
<link href='http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,400,300,600,700,800' rel='stylesheet' type='text/css'>
<!--//webfonts-->
</head>
<body>
<div class="app-cross">
			<div class="cross"><img src="home/images/cross.png" class="img-responsive" alt="" /></div>
<div align="center">
<h1 align="center">ע�������</h1>
<form name="form" action="register1">
��ţ�<input type="text" name="cid" id="cid"><br>
�û�����<input type="text" name="username" id="username" onBlur="userNameCheck()"><br>
���룺<input type="password" name="password1" id="password1"><br>
����ȷ�ϣ�<input type="password" name="password2" id="password2"><br>
Ȩ�ޣ�
<select name="csuper">
<option value="2">��ͨ�û�
</select><br>
��ϵ��ʽ��<input type="text" name="cpn" id="cpn"><br>
<input type="submit" value="ע��" onclick="return check();">
</form>
<div class="buttons">
					<a  href="index.html"class="hvr-shutter-in-vertical">��¼</a>
				</div>
					<h4>New here ? <a href="index.html"> Sign Up</a></h4>
			
		</div>
</div>
</body>
</html>