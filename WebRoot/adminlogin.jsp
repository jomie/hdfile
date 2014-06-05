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
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<script src="js/jquery-1.10.2.js" type="text/javascript"></script>
	<script type="text/javascript">
		function checkSubmit(){
			alert($("input[name='adminName']").val());
			if($("input[name='adminName']").val() == ""){
				alert("请输入用户名!");
				return false;		
			}
			alert($("input[name='adminPassword']").val());
			if($("input[name='adminPassword']").val() == ""){
				alert("请输入密码!");
				return false;				
			}
			return true;
		}
	</script>
	
	<link rel="stylesheet" type="text/css" href="css/styles.css">
	<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
	<style type="text/css">
		#center{
			border : 0px;
		}
		
		
		
		#loginform-container{
			
			position: relative;
			width: 600px;
			left : 50%;
			margin-left : -300px;
			margin-top : 50px;
			padding-top : 50px;
		}
	</style>
  </head>
  
  <body>
  	<div id="wrap">
  	<div><jsp:include page="admin-header.jsp"></jsp:include></div>
  	<div id="container">
    
    <div id="loginform-container">
    <!-- 
    <form action="hdlogin" method="post" onsubmit="javascript:return checkSubmit(this);">
	    <span><s:actionmessage/></span>
	    <table>
	    	<tr><td>用户名:</td>
				<td><input name="username" type="text" /></td></tr>
	    	<tr><td>密    码:</td><td><input name="password" type="password" /></td></tr>
	    	<tr><td><input type="submit" value="login now"/></td><td><a href="register.jsp">注册</a></td></tr>
	    </table>
	</form>
	 -->
	 
	<form action="adminLogin" method="post" onsubmit="javascript:return checkSubmit(this);" id="login-form" class="form-horizontal">
  		<div class="control-group">
    		<label class="control-label" for="inputUsername">管理员</label>
	   		<div class="controls">
	  	    <input name="adminName" type="text" id="inputUsername">
	   		</div>
   		</div>
   		<div class="control-group">
    		<label class="control-label" for="inputPassword">密    码</label>
	   		<div class="controls">
	  	    <input name="adminPassword" type="password" id="inputPassword">
	   		</div>
   		</div>
   		<div class="control-group">
   			<div class="controls">
      		
    	    <button type="submit" class="btn btn-primary">Sign in</button>
    	    <a class="btn btn-warning" href="register.jsp">注册</a>
  	    	</div>
  		</div>
   	</form>
   	
  	</div>
  	</div>
  	</div>
  	
  	<hr>
	<div id="footer">
		<jsp:include page="footer.jsp"></jsp:include>
	</div>
    
  </body>
</html>
