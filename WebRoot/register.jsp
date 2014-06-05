<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>注册页面</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="shortcut icon" href="img/cloud-up_icon.png">
	
	<script src="js/jquery-1.10.2.js" type="text/javascript"></script>
	<script language="javascript">
		function validRegister(vform){
			var username = vform.username.value;
			var password = vform.password.value;
			var password2 = vform.password2.value;
			//alert(password+","+password2);
			var email = vform.mail.value;
			
			//alert(password != password2);
			if(password != password2){
				alert("两个密码不相等!");
				return false;
			}
			//alert(password != password2);
			if(username == ""){
				alert("用户名不能为空!");
				return false;
			}
			if(password == ""){
				alert("密码不能为空!");
				return false;
			}
			if(mail == ""){
				alert("邮件不能为空!");
				return false;
			}
			
			return true;
		}
	</script>
	<link rel="stylesheet" type="text/css" href="css/styles.css">
	<style type="text/css">
		
	</style>
	
  </head>
  
  <body>
  	<div id="wrap">
  	<div><jsp:include page="header.jsp"></jsp:include></div>
    <center>
	    
	<div style="padding:100px">
	<span class="text-error"><s:actionmessage/></span>
	<form action="register" method="post" onsubmit="javascript:return validRegister(this);">
	<table>
		<tr><td></td><td></td></tr>
		<tr><td>邮箱:</td><td><input name="mail" type="text" /></td></tr>
		<tr><td>用户名:</td><td><input name="username" type="text" /></td></tr>
		<tr><td>密码:</td><td><input name="password" type="password" /></td></tr>
		<tr><td>确认密码:</td><td><input name="password2" type="password" /></td></tr>
		<tr><td><input class="btn btn-primary" value="register" type="submit" /></td><td><a class="btn btn-warning" href="login.jsp">返回登录</a></td></tr>
	</table>
	</form>
	</div>
    </center>
    </div>
    
    <jsp:include page="footer.jsp"></jsp:include>
  </body>
</html>
