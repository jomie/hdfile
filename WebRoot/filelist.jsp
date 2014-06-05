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
	<!-- 
	<link rel="stylesheet" type="text/css" href="css/styles.css">
	<link rel="stylesheet" type="text/css" href="css/filelist.css">
	 -->
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
    		<a class="btn" href="javascript:history.back();">返回</a>
    		<a class="btn" href="filelist?dir=<s:property value='@org.niko.utils.StringUtil@getParentURL(dir)'/>">返回父目录</a>
    		<a class="btn" href="toupload?dir=<s:property value="dir"/>">上传</a>
    		<a class="btn" href="tomkdir?dir=<s:property value="dir"/>">新建文件夹</a>
    		<span>
    			<a class="btn" href="filelist?dir=<s:property value='dir'/>&pageNo=<s:property value='lastPageNo'/>">上一页</a>
    			<a class="btn" href="filelist?dir=<s:property value='dir'/>&pageNo=<s:property value='nextPageNo'/>">下一页</a>
    			<a class="btn btn-link">第<s:property value='%{pageNo+1}'/>页</a>
    			<a class="btn btn-link">共<s:property value='pageCount'/>页</a>
    		</span>
    	</h3>
    	<center>
    	<table id="filetable" class="table table-hover">
    		<s:set name="d" value="dir" />
    	
    		<s:iterator value="list1" id="m">
    		<tr>
    		<td>
    				<i class="icon-folder-close"></i>
    		</td>
    		<td id="td-2nd">
    			<s:if test="#d==\"/\"">
	    			<a href="filelist?dir=/<s:property value='#m["pathSuffix"]'/>/"><s:property value="#m['pathSuffix']"/></a>
    			</s:if>
    			<s:else>
	    			<a href="filelist?dir=<s:property value='#d'/><s:property value='#m["pathSuffix"]'/>/"><s:property value="#m['pathSuffix']"/></a>
    			</s:else>
    		</td>
    		<td><a><s:property value="#m['length']"/></a></td>
    		<!-- 下载_进入 -->
    		<td>
    		    <a class="btn btn-info" href="filelist?dir=/<s:property value='#m["pathSuffix"]'/>/">进入</a>
    		</td>
    		<td>
    		    <a class="btn" href="tomove?dir=<s:property value='#d'/><s:property value='#m["pathSuffix"]'/>">移动</a>
    		</td>
    		<td><a class="btn" href="torename?dir=<s:property value='#d'/>&oldName=<s:property value='#m["pathSuffix"]'/>">重命名</a></td>
    		<td><a class="btn" href="javascript:void(0);" onclick="javascript:delete_confirm(this);">删除</a></td>
    		</tr>
    		</s:iterator>
    		
<!-- file iterator -->
    		<s:iterator value="list2" id="m">
    		<tr>
    		<td>
    			<i class="icon-file"></i>
    		</td>
    		<td id="td-2nd">
    			<a><s:property value="#m['pathSuffix']"/></a>
    		</td>
    		<td><a><s:property value="#m['length']"/></a></td>
    		<!-- 下载_进入 -->
    		<td>
    		    <a class="btn" href="download?downname=<s:property value='#m["pathSuffix"]'/>&dir=<s:property value='#d'/>">下载</a>
    		</td>
    		<td>
    		    <a class="btn" href="tomove?dir=<s:property value='#d'/>&oldName=<s:property value='#m["pathSuffix"]'/>">移动</a>
    		</td>
    		<td><a class="btn" href="torename?dir=<s:property value='#d'/>&oldName=<s:property value='#m["pathSuffix"]'/>">重命名</a></td>
    		<td><a class="btn" href="javascript:void(0);" onclick="javascript:delete_confirm(this);">删除</a></td>
    		</tr>
    		</s:iterator>
    	</table>
    	</center>
    </div>
    <hr>
    <jsp:include page="footer.jsp"></jsp:include>
  </body>
</html>
