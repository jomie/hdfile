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
	
	<link rel="stylesheet" type="text/css" href="css/header.css">
	<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
  </head>
  
  <body>
    <div id="head">
    	<div id="logo"><a href="filelist"><img alt="niko" src="pic/logo.jpg"></a></div>
    	
    	<div id="userop">
    		<s:if test="#session['adminName']==null">
    			<a class="btn btn-primary" href="login.jsp">登录</a>
    		</s:if>
    		<s:else>
    			<a id="logout" class="btn btn-primary" href="logout">注销</a>
    			<span id="username" class="btn btn-link">欢迎你, <s:property value="#session['adminName']"/></span>
    		</s:else>
    	</div>
    </div>
  </body>
</html>
