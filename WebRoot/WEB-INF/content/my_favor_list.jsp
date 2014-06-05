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
    
    <title>我的收藏</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="shortcut icon" href="img/cloud-up_icon.png">
	
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link href="css/left_nav_bar.css" rel="stylesheet" media="screen">

	<script src="js/jquery-1.10.2.js"></script>
	<script src="js/bootstrap.min.js"></script>
	
	<script>
		$(function(){
			$("a[name='fv_btn']").bind("click", function(){
				var aaa = $(this);
				var href = $(this).attr("rehref");
				$.post(href, "", function(data){
					alert(decodeURI(data));
					if (aaa.html() == "收藏") {
						aaa.html("取消收藏");
					} else {
						aaa.html("收藏");
					}
				});
			});
		});
	</script>
  </head>
  
  <body>
  <jsp:include page="/niko_header.jsp"></jsp:include>
  <div class="row-fluid" style="margin:70px 0px 0px 0px">
  
  	<jsp:include page="left_bar_favor.jsp"></jsp:include>
    
  	<div class="span10 offset2" style="margin:10px 50px 0px 200px">
    <table class="table table-striped">
    	<s:iterator value="pager.resultList" id="oneResult">
    		<tr>
    		  <td><s:property value="#oneResult.fileid"/></td>
    		  <td><s:property value="#oneResult.filename"/></td>
    		   <td>
    		  
    		  	<s:if test="#oneResult.curUserFavor == false">
	    		  	<a name="fv_btn" id="favorBtn" class="btn" rehref="favor?dir=<s:property value="dir"/>&fileid=<s:property value="#oneResult.fileid"/>" >收藏</a>
	    		</s:if>
    		  	<s:else>
    		  		<a name="fv_btn" id="favorBtn" class="btn" rehref="unfavor?dir=<s:property value="dir"/>&fileid=<s:property value="#oneResult.fileid"/>" >取消收藏</a>
    		  	</s:else>
    		  </td>
    		  <td><a id="" class="btn" href="downOT?pathWithUsername=<s:property value='#oneResult.filepath+#oneResult.filename'/>" >下载</a></td>
    		  
    		</tr>
    	</s:iterator>
    	<tr></tr>
    </table>
    </div>
    
    <div style="margin: 5px 200px">
    </div>
    
    
    
    
    
  </div> 
  </body>
</html>
