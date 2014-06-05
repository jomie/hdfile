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
    
    <title>私人文件</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="shortcut icon" href="img/cloud-up_icon.png">
  
  <!-- <link href="css/niko.css" rel="stylesheet" media="screen"> -->
  <!-- <script src="js/jquery-1.10.2.js"></script> -->
  <script type="text/javascript" src="jquery-easyui-1.3.6/jquery.min.js"></script>
  <script src="js/bootstrap.min.js"></script>
  
  <link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.6/themes/default/easyui.css">
  <link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.6/themes/icon.css">
  <!-- <link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.6/demo/demo.css"> -->
  <link href="css/left_nav_bar.css" rel="stylesheet" media="screen">
  <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
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
  
  <script type="text/javascript" src="jquery-easyui-1.3.6/jquery.easyui.min.js"></script>
  <script src="yui_3.15.0/build/yui/yui-min.js" type="text/javascript"></script>
   
  <script type="text/javascript">
  	$(function(){
  		if ("" != "<s:actionmessage/>" ) {
  			alert("<s:actionmessage/>");
  		}
  		
  		$("a[name='moveShowModalBtn']").bind("click", function(){
  			console.log("oldName"+$(this).attr("oldName"));
  			$("#oldName").val($(this).attr("oldName"));
  		});
  		$("#moveBtn").bind("click", function(){
  			console.log("moveBtn click:"+$(this).val());
  			console.log("oldName:"+$("#oldName").val()) ;
  			console.log("newDir val : "+$("#newDir").val());
  			//$("#move_form").submit();
  			if ("" == $("#newDir").val()) {
  				$("#newDir").val("/");
  			}
  			window.location = "move-bxx?dir=" + $("#dir").val() +"&oldName="+$("#oldName").val()+"&newDir="+$("#newDir").val()+"&ifPrivate=0";
  			//alert("move-bxx?dir=" + $("#dir").val() +"&oldName="+$("#oldName").val()+"&newDir="+$("#newDir").val());
  		});
  		$("a[name='shareFileBtn']").bind("click", function(){
  			var filename = $(this).attr("filename");
  			alert(filename); 
  				//return false;
  			console.log("moveBXXBtn click:"+filename);
  			window.location = "bxx-to-share?dir=" + $("#dir").val() +"&oldName="+filename+"&ifPrivate=0";
  		});
  		$("a[name='renameShowModalBtn']").bind("click", function(){
  			console.log("oldName:"+$(this).attr("oldName"));
  			$("#oldName").val($(this).attr("oldName"));
  		});
  		$("#renameBtn").bind("click", function(){
  			console.log("renameBtn click:"+$(this).val());
  			console.log("oldName:"+$("#oldName").val()) ;
  			console.log("newName val : "+$("#newName").val());
  			
  			var params = "dir=" + $("#dir").val() +"&oldName="+$("#oldName").val()+"&newName="+$("#newName").val();
  			alert(params);
  			$.post("rename-bxx", params, function(data){
  				
  				var resultObj = JSON.parse(data);
  				if(resultObj.status == "success")
  					window.location =  "filelist-bxx?dir=" + $("#dir").val();
  				else
  					alert("重命名失败.");
  			});
  			//alert("move-bxx?dir=" + $("#dir").val() +"&oldName="+$("#oldName").val()+"&newDir="+$("#newDir").val());
  		});
  		$("a[name='deleteShowModalBtn']").bind("click", function(){
  			//console.log("deleteName:"+$(this).attr("deleteName"));
  			$("#deleteName").val($(this).attr("deleteName"));
  			$("#deleteId").val($(this).attr("deleteId"));
  		});
  		$("#deleteBtn").bind("click", function(){
  			var jump = "delete-bxx?dir="+"<s:property value='dir'/>"+"&oldName="+$("#deleteName").val();
  					//+ "&fileId="+$("#deleteId").val();
  			//alert(jump);
  			location = jump;
  		});
  		$("#mkdirBtn").bind("click", function(){
  			$.post("mkdir-bxx", $("#mkdirForm").serialize(), function(data){
  				
  				var resultObj = JSON.parse(data);
  				if(resultObj.status == "success")
  					window.location =  "filelist-bxx?dir=" + $("#dir").val();
  				else
  					alert("新建失败.");
  			});
  		});
  	});
  </script>
  <script type="text/javascript" src="js/bt2-toggle.js"></script>
</head>

<body style="padding:0px;">

  <jsp:include page="/niko_header.jsp"></jsp:include>

  <!--  -->
  <div class="" style="margin:70px 0px 0px 0px">
   	<jsp:include page="left_bar_bxx.jsp"></jsp:include>
     
     
   <div class="row-fluid">
     
	 <!-- 10个 -->
     <div class="span10 offset2"  style="margin-right:50px;padding-right:50px;">
       

      <ul class="breadcrumb">
        <li><a href="filelist-bxx?dir=/">根目录</a></li>
        <!-- <li><a href="#">niko</a> <span class="divider">/</span></li> -->
        <!-- <li class="active">pdf</li> -->
        	<s:set name="tempDirLvl" value="emptyStr"></s:set>
        <s:iterator value="folderLevelArr" id="oneFolder">
        	<li><a href="filelist-bxx?dir=<s:property value='#tempDirLvl + #oneFolder + \"/\"'/>"><s:property value="#oneFolder"/> </a><span class="divider">/</span></li>
        	<s:set name="tempDirLvl" value="#tempDirLvl + #oneFolder + \"/\""></s:set>
        </s:iterator>
      </ul>
      
      <div class="row-fluid" style="margin-bottom:20px;">
      	<!-- 上传组件 -->
      	<div class="span4">
		<a href="#ajaxUploadModal" role="button" class="btn btn btn-primary" data-toggle="modal"><img alt="上传" style="margin-right:6px;" src="img/icon_upload_24.png">上传至保险箱</a>
      	<a class="btn btn-info" href="#mkdirModal" role="button" data-toggle="modal"><img alt="" style="margin-right:6px;" src="img/icon_mkdir_28_24.png">新建文件夹</a>
      	</div>
      	
      	<div class="pagination span8" style="margin:2px;">
		      <ul>
		        <li>
		        <a reclass="btn" href="filelist-bxx?dir=<s:property value='dir'/>&pageNo=<s:property value='lastPageNo'/>">上一页</a>
				</li>
				<li>
				<a reclass="btn" href="filelist-bxx?dir=<s:property value='dir'/>&pageNo=<s:property value='nextPageNo'/>">下一页</a>
				</li>
				<li class="disabled">
				<a class="">第<s:property value='%{pageNo+1}'/>页</a>
				</li>
				<li class="disabled">
				<a class="active">共<s:property value='pageCount'/>页</a>
				</li>
		      </ul>
		    </div>
      </div>
      
      
	<!-- 上传Modal -->
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
	  <!--
	    <button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
	    <button class="btn btn-primary">Save changes</button>
	  -->
	  <label>文件标签 : <input id="tags" type="text" value="" style="margin:0px 20px 0px"></label>
	  </div>
	</div>
	<!--  -->
	<script type="text/javascript">
    	$(function(){
    		$("#filenames").addClass("table");
    	});
    </script>
      
      <!-- 新建目录的modal -->
      <div id="mkdirModal" class="modal hide fade">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
          <h3>请输入文件夹名</h3>
        </div>
        <form id="mkdirForm" action="mkdir-bxx" method="post">
        <div class="modal-body">
          <!-- <p>One fine body…</p> -->
          	<input name="newDirName" type="text" class="input-large"/>
	    	<input name="dir" type="hidden" value="<s:property value='dir'/>"/>
        </div>
        <div class="modal-footer">
          <a href="#" class="btn"  data-dismiss="modal">取消</a>
          <a id="mkdirBtn" class="btn btn-info">新建</a>
        </div>
        </form>
      </div>


      <table class="table table-hover">
       
       <s:iterator value="list1" id="m">
       
       		<s:set name="d" value="dir" />
    			
    		<tr>
    		<td>
    				<img alt="" src="img/icon_folder_open_28.png">
    		</td>
    		<td id="td-2nd">
    			<s:if test="#d==\"/\"">
	    			<a href="filelist-bxx?dir=/<s:property value='#m["pathSuffix"]'/>/"><s:property value="#m['pathSuffix']"/></a>
    			</s:if>
    			<s:else>
	    			<a href="filelist-bxx?dir=<s:property value='#d'/><s:property value='#m["pathSuffix"]'/>/"><s:property value="#m['pathSuffix']"/></a>
    			</s:else>
    		</td>
    		<td>&nbsp;<a><s:property value="#m['length']"/></a></td>
    		<!-- 下载_进入 
    		<td>
    		    <a class="btn btn-info" href="filelist-bxx?dir=/<s:property value='#m["pathSuffix"]'/>/">进入</a>
    		</td>-->
    		<td><a name="moveShowModalBtn" href="#moveModal" role="button" class="btn btn-success" data-toggle="modal"
    						oldName="<s:property value='#m["pathSuffix"]'/>">移动至</a>
    		</td>
    		<td><a name="renameShowModalBtn" href="#renameModal" role="button" class="btn btn-success" data-toggle="modal"
    					oldName="<s:property value='#m["pathSuffix"]'/>">重命名</a></td>
    		<td><a name="deleteShowModalBtn" href="#deleteModal" role="button" class="btn btn-success" data-toggle="modal" 
    						deleteName="<s:property value='#m["pathSuffix"]'/>">删除</a></td>
    						
    		
    		</tr>
    	</s:iterator>
    	
    	
    	
    	
    	<!--    	      	  	 list2 : file   	    	    	 --> 
    	
    	<s:iterator value="list2" id="m">
       
       		<s:set name="d" value="dir" />
    			
    		<tr>
    		<td>
    				<img alt="" src="img/icon_documents_28.png">
    		</td>
    		<td id="td-2nd">
    			<s:if test="#d==\"/\"">
	    			<a href="javascript:void(0);"><s:property value="#m['pathSuffix']"/></a>
    			</s:if>
    			<s:else>
	    			<a href="javascript:void(0);"><s:property value="#m['pathSuffix']"/></a>
    			</s:else>
    		</td>
    		<td>&nbsp;<a><s:property value="#m['length']"/></a></td>
    		<!-- 下载_进入 
    		<td>
    		    <a class="btn btn-info" href="filelist-bxx?dir=/<s:property value='#m["pathSuffix"]'/>/">进入</a>
    		</td>-->
    		<td><a name="moveShowModalBtn" href="#moveModal" role="button" class="btn btn-success" data-toggle="modal"
    						oldName="<s:property value='#m["pathSuffix"]'/>">移动至</a>
    		</td>
    		<td><a name="renameShowModalBtn" href="#renameModal" role="button" class="btn btn-success" data-toggle="modal"
    					oldName="<s:property value='#m["pathSuffix"]'/>">重命名</a></td>
    		<td>
    			<div class="btn-group">
				  <a local_name="toggle-file" class="btn dropdown-toggle" data-toggle="dropdown" href="#">
				           更多
				    <span class="caret"></span>
				  </a>
				  <ul local_name="toggle_ul" class="dropdown-menu" style="min-width: 90px; margin-top: 0px;/*重要*/">
				    <!-- dropdown menu links -->
				    <li>
				    	<a reclass="btn" href="download-bxx?downname=<s:property value='#m["pathSuffix"]'/>&dir=<s:property value='#d'/>&bxx=1">下载</a>
				    </li>
				    <li>
						<a name="deleteShowModalBtn" href="#deleteModal" role="button" reclass="btn btn-info" data-toggle="modal" 
    						deleteName="<s:property value='#m["pathSuffix"]'/>">删除</a>
					</li>
				    <li class="divider"></li>
				    <li>
						<a name="shareFileBtn" href="javascript:void(0);" filename="<s:property value="#m['pathSuffix']"/>" reclass="btn btn-info">分享该文件</a>
					</li>
				  </ul>
				</div>
    		</td>
    		 
    		</tr>
    	</s:iterator>
     </table>
     <!-- common -->
	  <input id="dir" name="" type="hidden" value="<s:property value='dir'/>">
      <input id="newDir" name="" type="hidden" value="">
      <input id="oldName" name="" type="hidden" value="">
      <input id="isPrivate" name="" type="hidden" value="0">
      <input id="" name="" type="hidden" value="">


	<!-- 移动至的modal -->
     <div id="moveModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
     <form id="move_form" action="move-bxx">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h3 id="myModalLabel">选择目标文件夹:</h3>
      </div>
      <div class="modal-body">
        <!-- <p>One fine body…</p> -->
        <!-- 树形插件 -->
        <ul id="folderList" >
			<li><div id="_easyui_tree_2" class="tree-node">
					<span class="tree-hit tree-collapsed"></span>
					<span class="tree-icon tree-folder "></span>
					<span class="tree-title">novels</span>
				</div>
			</li>
		</ul>
      </div>
      
      <div class="modal-footer">
        <a href="#" class="btn" data-dismiss="modal">取消</a>
        <a id="moveBtn" href="javascript:void(0);" class="btn btn-primary">移动</a>
      </div>
      </form>
    </div>
    
    
    
    <!-- 重命名的modal -->
      <div id="renameModal" class="modal hide fade">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
          <h3>请输入新名称</h3>
        </div>
        <form id="renameForm" method="post">
        <div class="modal-body">
          <!-- <p>One fine body…</p> -->
          	<input id="newName" name="newName" type="text" class="input-large"/>
	    	<input name="dir" type="hidden" value="<s:property value='dir'/>"/> 
	    	
        </div>
        <div class="modal-footer">
          <a href="#" class="btn"  data-dismiss="modal">取消</a>
          <a id="renameBtn" class="btn btn-info">重命名</a>
        </div>
        </form>
      </div>
    
    <!-- 删除的modal -->
    <div id="deleteModal" class="modal hide fade">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h3>确定删除吗?</h3>
      </div>
      <div class="modal-body">
        <!-- <p>One fine body…</p> -->
        <p>删除后将会移到回收站.</p>
      </div>
      <div class="modal-footer">
        <a href="#" class="btn"  data-dismiss="modal">取消</a>
        <input id="deleteName" name="" type="hidden" value="">
        <input id="deleteId" name="" type="hidden" value="">
        <a id="deleteBtn" href="javascript:void(0);" class="btn btn-primary">删除</a>
      </div>
    </div>
   
	<!-- 
    <div class="pagination">
      <ul>
        <li>
        <a class="btn" href="filelist-bxx?dir=<s:property value='dir'/>&pageNo=<s:property value='lastPageNo'/>">上一页</a>
		</li>
		<li>
		<a class="btn" href="filelist-bxx?dir=<s:property value='dir'/>&pageNo=<s:property value='nextPageNo'/>">下一页</a>
		</li>
		<li>
		<a class="btn btn-link">第<s:property value='%{pageNo+1}'/>页</a>
		</li>
		<li>
		<a class="btn btn-link">共<s:property value='pageCount'/>页</a>
		</li>
      </ul>
    </div>
     -->
  </div>
</div>
</div>

	<script type="text/javascript">
		var treeObj = $('#folderList').tree({
		    url:'querySubFolder-bxx',
		    onClick : function(node) {
		    	console.log("cur chosen:"+node.id);
		    	$("#newDir").val(node.id);
		    	console.log($('#newDir').val());
		    }
		});
		console.log(treeObj.toString()) ;
		console.log("[0]:"+treeObj[0].childNodes);
	</script>
	
	<script src="js/niko_upload_bxx.js" type="text/javascript"></script>

<div style="height:100px;"></div>

</body>
</html>
