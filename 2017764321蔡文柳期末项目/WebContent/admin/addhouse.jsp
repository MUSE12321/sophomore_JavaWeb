<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<title>enroll</title>
<meta http-equiv="Content-Type" content="text/html; charset=GBK"/>
<script type="text/javascript" src="addhouse.js"></script>
<link href="../home/css/style.css" rel='stylesheet' type='text/css' />
<meta name="keywords" content="Church Sign In Form,Login Forms,Sign up Forms,Registration Forms,News latter Forms,Elements"./>
<script type="application/x-javascript" src="addhouse.js"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
</script>
<!--webfonts-->
<link href='http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,400,300,600,700,800' rel='stylesheet' type='text/css'>
<!--//webfonts-->
</head>
<body>
<div class="app-cross">
			<div class="cross"><img src="../home/images/cross.png" class="img-responsive" alt="" /></div>
<div align="center">
<h1 align="center">添加房子</h1>
<form name="form" action="addhouse">
房子编号：<input type="text" name="hid" id="hid" onBlur="userNameCheck()"><br>
房东编号：<input type="text" name="fid" id="fid"><br>
房东姓名：<input type="text" name="username" id="username" ><br>
租客：<select name="cid">
<option value="null">null
</select><br>
地址：<input type="text" name="dz" id="dz"><br>
租金：<input type="text" name="pri" id="pri"><br>
房子出租状态：
<select name="hSta">
<option value="0">未出租
</select><br>
房东联系方式：<input type="text" name="fpn" id="fpn"><br>
<input type="submit" value="添加" onclick="return check();">
</form>
<div class="buttons">
					<a  href="admin.jsp"class="hvr-shutter-in-vertical">管理员界面</a>
				</div>
					
			
		</div>
</div>
</body>
</html>