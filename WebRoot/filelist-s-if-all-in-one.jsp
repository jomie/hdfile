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
	<link rel="stylesheet" type="text/css" href="css/filelist.css">
	<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
	
	<script src="js/jquery-1.10.2.js" type="text/javascript"></script>
	<script type="text/javascript">
		function delete_confirm(a)
		{
			//alert(a.innerHTML);
			var r=confirm("确定删除?");
			  alert($(a).parent().parent().find("td:eq(1) a").html());
			  if (r==true)
			    {
			    
			    var filename = $(a).parent().parent().find("td:eq(1) a").html();
			    var jump = "delete?dir="+"<s:property value='dir'/>"+"&filename="+filename;
			    //alert(jump);
			    window.location.href=jump;
			    }
			  else
			    {
			    
			    }
		}
	</script>
  </head>
  
  <body>
  	<jsp:include page="header.jsp"></jsp:include>
    <div id="wrap">
    	<h3><s:property value="dir"/></h3>
    	<h3>
    		<a class="btn" href="javascript:history.back();">返回    </a>
    		<a class="btn" href="toupload?dir=<s:property value="dir"/>">上传</a>
    		<a class="btn" href="tomkdir?dir=<s:property value="dir"/>">新建文件夹</a>
    		<span>
    			<a class="btn" href="tomkdir?dir=<s:property value="dir"/>">下一页</a>
    		</span>
    	</h3>
    	<center>
    	<table id="filetable" class="table table-hover">
    		<s:set name="d" value="dir" />
    	
    		<s:iterator value="list1" id="m">
    		<tr>
    		<td>
    			<s:if test="#m['type']=='FILE'">
    				<i class="icon-file"></i>
    			</s:if>
    			<s:else>
    				<i class="icon-folder-close"></i>
    			</s:else> 
    		</td>
    		<td id="td-2nd">
	    		<s:if test="#m['type']=='DIRECTORY'">
	    			<s:if test="#d==\"/\"">
		    			<a href="filelist?dir=/<s:property value='#m["pathSuffix"]'/>/">
		    			<s:property value="#m['pathSuffix']"/>
		    			</a>
	    			</s:if>
	    			<s:else>
		    			<a href="filelist?dir=<s:property value='#d'/><s:property value='#m["pathSuffix"]'/>/">
		    			<s:property value="#m['pathSuffix']"/>
		    			</a>
	    			</s:else>
	    		</s:if>
	    		<s:else>
	    			<a><s:property value="#m['pathSuffix']"/></a>
	    		</s:else>
    		</td>
    		<td><a><s:property value="#m['length']"/></a></td>
    		<!-- 下载_进入 -->
    		<td>
    		  <s:if test="#m['type']=='FILE' ">
    			<a class="btn" href="download?downname=<s:property value='#m["pathSuffix"]'/>&dir=<s:property value='#d'/>">下载</a>
    		  </s:if>
    		  <s:else>
    		    <a class="btn btn-info" href="filelist?dir=/<s:property value='#m["pathSuffix"]'/>/">进入</a>
    		  </s:else>
    			
    		</td>
    		<td><a class="btn" href="torename?dir=<s:property value='#d'/>&oldName=<s:property value='#m["pathSuffix"]'/>">重命名</a></td>
    		<td><a class="btn" href="javascript:void(0);" onclick="javascript:delete_confirm(this);">删除</a></td>
    		</tr>
    		</s:iterator>
    	</table>
    	</center>
    </div>
    
    <div><s:debug></s:debug></div>
    <jsp:include page="footer.jsp"></jsp:include>
  </body>
</html>
