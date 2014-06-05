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
    
    <title>搜索结果</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="shortcut icon" href="img/cloud-up_icon.png">
	
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
	<style type="text/css">
		#tags_div a {
			font-size : 14px;
			font-family: "Microsoft Yahei", "Helvetica Neue",Helvetica,Arial,sans-serif;
			font-weight: normal;
		}
	</style>

	<script type="text/javascript" src="jquery-easyui-1.3.6/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	
	<script>
		$(function(){
			/*$("a[name='tips_a']").tooltip(
				{
				title : 
				function myMethod(){
					return "my dear niko.";
				}
				}
			);*/
			
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
			
			$("#toggle_ul a").bind("click", function(){
				var href = $(this).attr("rehref");
				$.post(href, "", function(data) {
					var result = JSON.parse(data);
					alert(result.status);
					if ("success" == result.status) {
						$("#btn-group").html("<a class='btn' href='javascript:void(0);'>你已评分</a>");
						$("#grade_td").html(result.newGrade);
					}
				});
			});
		});
		
	</script>
	<script type="text/javascript" src="js/bt2-toggle.js"></script>
  </head>
  
  <body>
  
  	<jsp:include page="/niko_header.jsp"></jsp:include>
	
	<div class="row-fluid" style="margin:70px 0px 0px 0px;">
  	<div id="tags_div" class="span2">
  		<s:iterator value="tagsMap">
  			<s:set var="tag" value="value"/>
  			<span class="badge badge-warning"><a href="search-ft?tid=<s:property value='key'/>&tname=<s:property value='#tag.tagname'/>">
  					<s:property value="#tag.tagname"/></a></span>
  		</s:iterator>
  	</div>
  	
  	<div class="span10" restyle="margin:70px 30px 0px 200px">
    <table class="table table-striped  table-hover">
    	<s:iterator value="pager.resultList" id="oneResult">
    		<tr>
    		  <td><s:property value="#oneResult.fileid"/></td>
    		  <td><a name="tips_a" href="javascript:void(0);"  redata-toggle="tooltip" redata-placement="top" redata-title="myMethod();"><s:property value="#oneResult.filename"/></a></td>
    		  <td><s:iterator value="#oneResult.fileTags" id="oneTag"><span class="badge badge-success"><s:property value="#oneTag.tags.tagname"/></span></s:iterator></td>
    		  <td id="grade_td" style="padding-top:10px;">4</td>
    		  <td>
    		  <!-- <s:property value="#oneResult.hasGraded"/> -->
    		  <s:if test="#oneResult.hasGraded == false">
    		  	<div id="btn-group" class="btn-group">
				  <a local_name="toggle-file" class="btn dropdown-toggle" data-toggle="dropdown" href="#">
				    	打分
				    <span class="caret"></span>
				  </a>
				  <ul id="toggle_ul" local_name="toggle_ul" class="dropdown-menu" style="min-width: 90px; margin-top: 0px;/*重要*/">
				    <!-- dropdown menu links -->
				    <li><a rehref="submitGrade?fileid=<s:property value='#oneResult.fileid'/>&grade=1">1分</a></li>
				    <li><a rehref="submitGrade?fileid=<s:property value='#oneResult.fileid'/>&grade=2">2分</a></li>
				    <li><a rehref="submitGrade?fileid=<s:property value='#oneResult.fileid'/>&grade=3">3分</a></li>
				    <li><a rehref="submitGrade?fileid=<s:property value='#oneResult.fileid'/>&grade=4">4分</a></li>
				    <li><a rehref="submitGrade?fileid=<s:property value='#oneResult.fileid'/>&grade=5">5分</a></li>
				  </ul>
				</div>
			  </s:if>
			  <s:else>
			  	<a class='btn' href='javascript:void(0);'>你已评分</a>
			  </s:else>
    		  </td>
    		  <td>
    		  
    		  	<s:if test="#oneResult.curUserFavor == false">
	    		  	<a name="fv_btn" id="favorBtn" class="btn" rehref="favor?dir=<s:property value="dir"/>&fileid=<s:property value="#oneResult.fileid"/>" >收藏</a>
	    		</s:if>
    		  	<s:else>
    		  		<a name="fv_btn" id="favorBtn" class="btn" rehref="unfavor?dir=<s:property value="dir"/>&fileid=<s:property value="#oneResult.fileid"/>" >取消收藏</a>
    		  	</s:else>
    		  <a id="" class="btn" href="downOT?pathWithUsername=<s:property value='#oneResult.filepath+#oneResult.filename'/>" >下载</a>
    		  </td>
    		  
    		</tr>
    	</s:iterator>
    	<tr></tr>
    </table>
    <div class="pagination">
      <!-- <ul>
        <li>
        <a class="btn" href="search?keyword=<s:property value='keyword'/>&pageNo=<s:property value='prePage'/>">上一页</a>
		</li>
		<li>
		<a class="btn" href="search?keyword=<s:property value='keyword'/>&pageNo=<s:property value='nextPage'/>">下一页</a>
		</li>
		<li>
		<a class="btn btn-link">第<s:property value='pageNo'/>页</a>
		</li>
		<li>
		<a class="btn btn-link">共<s:property value='%{pager.rowCount/pageSize + 1}'/>页</a> 
		</li>
      </ul>
       -->
      
      <ul>
      	<!-- 
        <li>
        <a class="btn" href="search?keyword=<s:property value='keyword'/>&pageNo=<s:property value='prePage'/>">上一页</a>
		</li>
		<li>
		<a class="btn" href="search?keyword=<s:property value='keyword'/>&pageNo=<s:property value='nextPage'/>">下一页</a>
		</li>
		 -->
		
		<s:if test="%{pageNo/5+6} < %{pager.rowCount/pageSize+1}">
			<s:set var="endIndexNo" value="%{pageNo/5+6}"/>
		</s:if>
		<s:else>
			<s:set var="endIndexNo" value="%{pager.rowCount/pageSize+1}"/>
		</s:else>
		
		<s:iterator id="fiveItr" begin="%{pageNo/5+1}" end="#endIndexNo">
			<!-- index:<s:property value="#fiveItr"/> -->
			<s:if test="pageNo == #fiveItr">
				<li class="active"><span><a href="javascript:void(0);"><s:property value="pageNo"/></a></span></li>
			</s:if>
			<s:else>
				<li ><span>
						<s:property value="pageNo"/><a href="search-ft?tid=<s:property value='tid'/>&pageNo=<s:property value='#fiveItr'/>"><s:property value="pageNo"/></a>
					 </span>
				</li>
			</s:else>
		</s:iterator>
		<!-- 
		<li>
		<a class="btn btn-link">第<s:property value='pageNo'/>页</a>
		</li>
		 -->
		<li>
		<a class="active">共<s:property value='%{pager.rowCount/pageSize + 1}'/>页</a> 
		</li>
		<!-- <li>» -->
      </ul>
    </div>
    </div>
    
    </div>
    
    
    
    
    
    
    
  </body>
</html>
