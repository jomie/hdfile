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
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="shortcut icon" href="img/cloud-up_icon.png">
	<!-- <link rel="stylesheet" type="text/css" href="css/header.css"> -->
	<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
	
	
	<script type="text/javascript">
		$(function(){
			/*$("#searchBtn").bind("click", function(){
				$("#search-form").submit();
			});*/
			
			$("#search-form").bind("submit", function(){
				if ("" == $("#keyword").val()) {
					alert("请输入关键字");
					return false;
				} else {
					$(this).submit();
				}
			});
			
		});
	</script>
	
  </head>
  
  <body>
  <!-- 
  	这里引用会冲突, 
  <script type="text/javascript" src="js/jquery-1.10.2.js"></script>
  <script src="js/bootstrap.min.js"></script>
   -->
  
  <!-- header -->
  <div class="navbar navbar-inverse navbar-fixed-top">
    <div class="navbar-inner">
      <div class="container">
        <a class="brand" href="filelist">niko-storage</a>
        <ul class="nav">
          <li class="active"><a href="filelist">首页</a></li>
          <li><a href="myFavor">我的收藏</a></li>
          <li><a href="myDown">我的下载</a></li>
          
          <!-- 
          <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">我的<span class="caret"></span></a>
            <ul class="dropdown-menu">
              <li><a href="#">收藏</a></li>
              <li><a href="#">上传</a></li>
              <li><a href="#">下载</a></li>
            </ul>
          </li>
           -->
        </ul>

        <form id="search-form" action="search" method="post" class="navbar-search" style="margin-top:10px;">
          <input id="keyword" name="keyword" type="text" class="search-query">
          <button id="searchBtn" class="btn-link" rehref="javascript:void(0);">搜索</button>
        </form>

        <!-- -->
        <ul class="nav pull-right">
          
			<s:if test="#session['username']==null">
    			<li><a href="login.jsp">请登录</a></li>
    		</s:if>
    		<s:else>
    			<li><a href="logout">注销</a></li>
    			<li><a href="#">欢迎你, <s:property value="#session['username']"/></a></li>
    		</s:else>
          <li class="divider-vertical"></li>
          <li><a href="#"></a></li>
        </ul>
      </div>
    </div>
  </div> 
  
  </body>
</html>
