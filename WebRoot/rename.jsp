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
	
	<link rel="stylesheet" type="text/css" href="css/styles.css">
	
  </head>
  
  <body>
  
  	<div><jsp:include page="header.jsp"></jsp:include></div>
    <div id="wrap">
    <center>
    <div style="padding:100px">
    <h3 class="lead">重命名    <s:property value="oldName"/></h3>
    <form action="rename" method="post">
    	<input name="newName" type="text" value="<s:property value='oldName'/>"/>
    	<input name="dir" type="hidden" value="<s:property value='dir'/>"/>
    	<input name="oldName" type="hidden" value="<s:property value='oldName'/>"/>
    	<input class="btn btn-info" type="submit" value="rename" />
    </form>
    </div>
    </center>
    </div>
  </body>
</html>
