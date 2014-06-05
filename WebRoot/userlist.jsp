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
		function delete_confirm(a)
		{
			//alert(a.innerHTML);
			var r=confirm("确定删除?");
			  if (r==true)
			    {
			    alert($(a).parent().parent().find("td:eq(0) span").html());
			    var delUsername = $(a).parent().parent().find("td:eq(0) span").html();
			    var jump = "deluser?delUsername="+delUsername;
			    alert(jump);
			    window.location.href=jump;
			    }
			  else
			    {}
		}
		
		function reset_confirm(a)
		{
			//alert(a.innerHTML);
			var r=confirm("确定重置?");
			  if (r==true)
			  {
			    alert($(a).parent().parent().find("td:eq(0) span").html());
			    var u = $(a).parent().parent().find("td:eq(0) span").html();
			    //var jump = "reset?resetUsername="+u;
			    //alert(jump);
			    
			    $.post("resetuser",
				{
				  resetUsername:u
				},
				function(data,status){
				  alert("^_^ " + data + " ^_^");
				});
			  }
			  else
			    {}
		}
	</script>
	<link rel="stylesheet" type="text/css" href="css/styles.css">
	<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
	
  </head>
  
  <body>
  
  	<div><jsp:include page="admin-header.jsp"></jsp:include></div>
    <div id="wrap">
    <center>
	    
	<div style="padding:100px;width:400px;">
	<h2>用户列表</h2>
	<table class="table table-hover">
		<s:iterator value="userList" id="u">
			<tr>
				<td style="width:180px;"><span class="btn btn-inverse"><s:property value="u"/></span></td>
				<td><a class="btn btn-danger" href="javascript:void(0);" onclick="javascript:delete_confirm(this);">删除用户</a></td>
				<td><a class="btn btn-danger" href="javascript:void(0);" onclick="javascript:reset_confirm(this);">重置密码</a></td>
			<tr>
		</s:iterator>
	</table>
	</div>
    </center>
    </div>
    <jsp:include page="footer.jsp"></jsp:include>
  </body>
</html>
