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
  	<div><jsp:include page="header.jsp"></jsp:include></div>
  	<div id="container">
    
   	<h1>404</h1>
  	
  	</div>
  	</div>
  	
  	<hr>
	<div id="footer">
		<jsp:include page="footer.jsp"></jsp:include>
	</div>
    
  </body>
</html>
