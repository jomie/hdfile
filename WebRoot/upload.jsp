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
    
    <title>欢迎登陆</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="css/styles.css">
	<link rel="stylesheet" type="text/css" href="css/footer.css">
	<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
	
	<script src="js/jquery-1.10.2.js" type="text/javascript"></script>
	<script src="js/bootstrap.min.js" type="text/javascript"></script>
	<script src="js/bootstrap.file-input.js" type="text/javascript"></script>
	<script src="yui_3.15.0/build/yui/yui-min.js" type="text/javascript"></script>
	
	<script type="text/javascript">
	$(function(){$('.file-inputs').bootstrapFileInput();});
	</script>
	<style>
	
	
	
	#filelist {
	    margin-top: 15px;
	}
	
	#uploadFilesButtonContainer, #selectFilesButtonContainer, #overallProgress {
	    display: inline-block;
	}
	
	#overallProgress {
	    float: right;
	}
	
	</style>
  </head>
  
  <body>
  
    <div id="wrap">
  	<div><jsp:include page="header.jsp"></jsp:include></div>
    <center>
	<div style="padding:00px">
    <h3 class="lead">上传到    <s:property value="dir"/></h3>
	<form class="form-horizontal" action="upload" method="post" enctype="multipart/form-data">
    	<input class="file-inputs" name="upload" type="file" title="Search for a file to add"/>
    	<input name="dir" type="hidden" value="<s:property value='dir'/>"/>
    	<input type="submit" class="btn btn-info" value="upload now" />
    </form>
	</div>
	
	<!-- 上传组件 -->
	<a href="#ajaxUploadModal" role="button" class="btn" data-toggle="modal">HTML5异步上传</a>
	<!-- Modal -->
	<div id="ajaxUploadModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	  <div class="modal-header">
	    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	    <h3 id="myModalLabel">Modal header</h3>
	  </div>
	  <div class="modal-body">
	    <p>One fine body…</p>
	    <!-- 上传组件 -->
	    <div id="uploaderContainer">
		    <div id="selectFilesButtonContainer">
		    </div>
		    <div id="uploadFilesButtonContainer">
		      <button type="button" id="uploadFilesButton"
		              class="yui3-button" style="width:250px; height:35px;">Upload Files</button>
		    </div>
		    <div id="overallProgress">
		    </div>
		</div>
		<div id="filelist">
		  <table id="filenames">
		    <thead>
		       <tr><th>File name</th><th>File size</th><th>Percent uploaded</th></tr>
		       <tr id="nofiles">
		        <td colspan="3">
		            No files have been selected.
		        </td>
		       </tr>
		    </thead>
		    <tbody>
		    </tbody>
		  </table>
		</div>
	    
	  </div>
	  <div class="modal-footer">
	    <button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
	    <button class="btn btn-primary">Save changes</button>
	  </div>
	</div>
	
    </center>
    
    
    <hr>
    
	</div>
	
	
    
	
	<jsp:include page="footer.jsp"></jsp:include>
    
    <script src="js/niko_upload.js" type="text/javascript"></script>
    <script type="text/javascript">
    	$(function(){
    		$("#filenames").addClass("table");
    	});
    </script>
  </body>
</html>
