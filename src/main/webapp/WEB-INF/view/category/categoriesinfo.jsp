<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	request.setAttribute("BasePath", basePath);
%>
<!DOCTYPE html>
<!-- <html lang="en" class="no-js"> -->
<html lang="en">
<!-- BEGIN HEAD -->
<head>
<meta charset="utf-8" />
<title>Store Management</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1.0" name="viewport" />
<meta content="" name="description" />
<meta content="" name="author" />

<!-- BEGIN GLOBAL MANDATORY STYLES -->
<link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700&subset=all" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>assets/metronic/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>assets/metronic/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>assets/metronic/plugins/uniform/css/uniform.default.css" rel="stylesheet" type="text/css" />
<!-- END GLOBAL MANDATORY STYLES -->
<!-- BEGIN PAGE LEVEL PLUGIN STYLES -->
<%-- <link rel="stylesheet" type="text/css" href="<%=basePath%>assets/metronic/plugins/select2/select2.css"/>
<link rel="stylesheet" type="text/css" href="<%=basePath%>assets/metronic/plugins/select2/select2-metronic.css"/> --%>
<link href="//cdnjs.cloudflare.com/ajax/libs/select2/4.0.0/css/select2.min.css" rel="stylesheet" />
<!-- END PAGE LEVEL PLUGIN STYLES -->
<!-- BEGIN THEME STYLES -->
<link href="<%=basePath%>assets/metronic/css/style-metronic.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>assets/metronic/css/style.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>assets/metronic/css/style-responsive.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>assets/metronic/css/plugins.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>assets/metronic/css/themes/default.css" rel="stylesheet" type="text/css" id="style_color" />
<link href="<%=basePath%>assets/metronic/css/custom.css" rel="stylesheet" type="text/css" />
<!-- END THEME STYLES -->
<link rel="shortcut icon" href="favicon.ico" />
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="page-header-fixed">
	<!-- BEGIN HEADER -->
	<div class="header navbar navbar-fixed-top">
		<!-- BEGIN TOP NAVIGATION BAR -->
		<div class="header-inner">
			<!-- BEGIN LOGO -->
			<!-- END LOGO -->
			<!-- BEGIN RESPONSIVE MENU TOGGLER -->
			<a href="javascript:;" class="navbar-toggle" data-toggle="collapse"
				data-target=".navbar-collapse"> <img
				src="<%=basePath%>assets/metronic/img/menu-toggler.png" alt="" />
			</a>
			<!-- END RESPONSIVE MENU TOGGLER -->
			<!-- BEGIN TOP NAVIGATION MENU -->
			<ul class="nav navbar-nav pull-right">
				<!-- BEGIN NOTIFICATION DROPDOWN -->
				<!-- END NOTIFICATION DROPDOWN -->
				<!-- BEGIN INBOX DROPDOWN -->
				<!-- END INBOX DROPDOWN -->
				<!-- BEGIN TODO DROPDOWN -->
				<!-- END TODO DROPDOWN -->
				<!-- BEGIN USER LOGIN DROPDOWN -->
				<li class="dropdown user"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" data-hover="dropdown"
					data-close-others="true"> <span class="username"> Bob
							Nilson </span> <i class="fa fa-angle-down"></i>
				</a>
					<ul class="dropdown-menu">
						<li><a href="extra_profile.html"> <i class="fa fa-user"></i>
								My Profile
						</a></li>
						<li><a href="inbox.html"> <i class="fa fa-envelope"></i>
								My Inbox <span class="badge badge-danger"> 3 </span>
						</a></li>
						<li class="divider"></li>
						<li><a href="login.html"> <i class="fa fa-key"></i> Log
								Out
						</a></li>
					</ul></li>
				<!-- END USER LOGIN DROPDOWN -->
			</ul>
			<!-- END TOP NAVIGATION MENU -->
		</div>
		<!-- END TOP NAVIGATION BAR -->
	</div>
	<!-- END HEADER -->
	<div class="clearfix"></div>
	<!-- BEGIN CONTAINER -->
	<div class="page-container">
		<!-- BEGIN SIDEBAR -->
		<div class="page-sidebar-wrapper">
			<div class="page-sidebar navbar-collapse collapse">
				<!-- BEGIN SIDEBAR MENU -->
				<ul class="page-sidebar-menu" data-auto-scroll="true" data-slide-speed="200">
					<li class="start active ">
						<a href="<%=basePath%>categorymanagement/categorieslist.do"> 
							<i class="fa fa-bookmark-o"></i>
							<span class="title"> 类别管理 </span> 
							<span class="selected"></span>
							<span class="arrow open"></span>
						</a>
					</li>
					<li class="">
						<a href="javascript:;"> 
							<i class="fa fa-bookmark-o"></i>
							<span class="title"> 部门管理 </span> 
							<span class="arrow "></span>
						</a>
					</li>
					<li class="">
						<a href="javascript:;"> 
							<i class="fa fa-bookmark-o"></i>
							<span class="title"> 出 / 入库管理 </span> 
							<span class="arrow "></span>
						</a>
					</li>
					<li class="">
						<a href="javascript:;"> 
							<i class="fa fa-bookmark-o"></i>
							<span class="title"> 统计 </span> 
							<span class="arrow "></span>
						</a>
					</li>
					<li class="">
						<a href="javascript:;">
							<i class="fa fa-bookmark-o"></i> 
							<span class="title"> 供货商管理 </span>
							<span class="arrow "></span>
						</a>
					</li>
				</ul>
				<!-- END SIDEBAR MENU -->
			</div>
		</div>
		<!-- END SIDEBAR -->
		<!-- BEGIN CONTENT -->
		<div class="page-content-wrapper">
			<div class="page-content">
				<!-- BEGIN SAMPLE PORTLET CONFIGURATION MODAL FORM-->
				<!-- END SAMPLE PORTLET CONFIGURATION MODAL FORM-->
				<!-- BEGIN STYLE CUSTOMIZER -->
				<!-- END STYLE CUSTOMIZER -->
				<!-- BEGIN PAGE HEADER-->
				<div class="row">
					<div class="col-md-12">
						<!-- BEGIN PAGE TITLE & BREADCRUMB-->
						<h3 class="page-title">
							类别管理
						</h3>
						<ul class="page-breadcrumb breadcrumb">
							<li>
								<i class="fa fa-home"></i>
								库存管理
								<i class="fa fa-angle-right"></i>
							</li>
							<li>
								<a href="#">类别管理</a>
								<i class="fa fa-angle-right"></i>
							</li>
						</ul>
						<!-- END PAGE TITLE & BREADCRUMB-->
					</div>
				</div>
				<!-- END PAGE HEADER-->
				<!-- BEGIN PAGE CONTENT-->
				<div class="row ">
					<div class="col-md-12">
						<div class="portlet box blue ">
							<div class="portlet-title">
								<div class="caption">
									<i class="fa fa-reorder"></i>Form Sample
								</div>
								<div class="tools">
									<a href="javascript:;" class="collapse"> </a> <a
										href="#portlet-config" data-toggle="modal" class="config">
									</a> <a href="javascript:;" class="reload"> </a> <a
										href="javascript:;" class="remove"> </a>
								</div>
							</div>
							<div class="portlet-body form">
								<!-- BEGIN FORM-->
								<form action="<%=basePath%>categorymanagement/savecategory.do" class="form-horizontal form-bordered" onsubmit="return checkAllInfo();" method="POST">
									<div class="form-body">
										<div class="form-group">
											<label class="control-label col-md-3">类别名称</label>
											<div class="col-md-9">
												<input type="text" placeholder="类别名称" class="form-control" value="${category.categoryName }" id="categoryName" name="categoryName" />
											</div>
										</div>
										<div class="form-group" id="div_parent">
											<label class="control-label col-md-3">上级类别</label>
											<div class="col-md-9" id="div_select">
												<select class="js-states js-example-events form-control" multiple="multiple"></select>
											</div>
										</div>
									</div>
									<div class="form-actions fluid">
										<div class="row">
											<div class="col-md-12">
												<div class="col-md-offset-3 col-md-9">
													<button type="submit" class="btn green">
														<i class="fa fa-check"></i> Submit
													</button>
													<button type="button" class="btn default">Cancel</button>
												</div>
											</div>
										</div>
									</div>
									<input type="hidden" id="categoryId" name="categoryId" value="${category.id }" />
									<input type="hidden" id="categoryParentId" name="categoryParentId" value="${category.parentId }" />
									<input type="hidden" id="parentIds" name="parentIds" value="${parentIds }" />
									<input type="hidden" id="operation_status" name="operation_status" value="${operation_status }" />
								</form>
								<!-- END FORM-->
							</div>
						</div>
					</div>
				</div>
				<!-- END PAGE CONTENT-->
			</div>
		</div>
		<!-- END CONTENT -->
	</div>
	<!-- END CONTAINER -->
	<!-- BEGIN FOOTER -->
	<div class="footer">
		<div class="footer-inner"></div>
		<div class="footer-tools">
			<span class="go-top"> <i class="fa fa-angle-up"></i>
			</span>
		</div>
	</div>
	<!-- END FOOTER -->
	<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
	<!-- BEGIN CORE PLUGINS -->
	<script src="<%=basePath%>assets/metronic/plugins/jquery-1.10.2.min.js" type="text/javascript"></script>
	<script src="<%=basePath%>assets/metronic/plugins/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>
	<script src="<%=basePath%>assets/metronic/plugins/jquery-ui/jquery-ui-1.10.3.custom.min.js" type="text/javascript"></script>
	<script src="<%=basePath%>assets/metronic/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
	<script src="<%=basePath%>assets/metronic/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js" type="text/javascript"></script>
	<script src="<%=basePath%>assets/metronic/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
	<script src="<%=basePath%>assets/metronic/plugins/jquery.blockui.min.js" type="text/javascript"></script>
	<script src="<%=basePath%>assets/metronic/plugins/jquery.cokie.min.js" type="text/javascript"></script>
	<script src="<%=basePath%>assets/metronic/plugins/uniform/jquery.uniform.min.js" type="text/javascript"></script>
	<!-- END CORE PLUGINS -->
	
	<%-- <script src="<%=basePath%>assets/metronic/plugins/select2/select2.min.js" type="text/javascript"></script> --%>
	<script src="//cdnjs.cloudflare.com/ajax/libs/select2/4.0.0/js/select2.min.js"></script>
	<script src="<%=basePath%>assets/metronic/scripts/core/app.js" type="text/javascript"></script>
	<script src="<%=basePath%>assets/metronic/scripts/custom/form-samples.js" type="text/javascript"></script>
	
	<!-- BEGIN ALERT BOX -->
	<script src="<%=basePath%>assets/metronic/scripts/custom/ui-alert-dialog-api.js" type="text/javascript"></script>
	<script src="<%=basePath%>assets/metronic/plugins/bootbox/bootbox.min.js" type="text/javascript"></script>
	<!-- END ALERT BOX -->
	<script>
		var pid,id;
		jQuery(document).ready(function() {
			// initiate layout and plugins
			App.init();
			
			var data;
			if ($("#operation_status").val()==1){
				alert("Save category success!");
				window.location.href="<%=basePath%>categorymanagement/categorieslist.do";
			}else if ($("#operation_status").val()==0){
				alert("Save category failed, please try again!");
			}
			
			var parentIds=$("#parentIds").val();
			if (parentIds==""){
				data = getRemoteData(-1);
			}else{
				var m=new Array();
				if (parentIds.indexOf(",")>0){
					var tmp=parentIds.split(",");
					alert("tmp-->"+tmp);
					for (var i=0;i<tmp.length;i++){
						m[i]=parseInt(tmp[i]);
					}
				}else{
					m[0]=parseInt(parentIds);
				}
				
				$.ajax({
			        type: "POST",
			        async:false,
			        contentType: "application/json; charset=utf-8",
			        url: "<%=basePath%>categorymanagement/getcategoryrank.do",
			        data: "{'ids':'"+m+"' , 'parentId':'"+m[m.length-1]+"' , 'notExistId':'"+$("#categoryId").val()+"'}",
			        dataType: 'json',
			        success: function(result) {
			        	data=result.data;
			        }
			    });
				
				id=m.join();
				pid=m[m.length-1];			
			}
			
			alert("m-->"+m);
			var $sltCategory = $(".js-example-events");
			$sltCategory.select2({
				data:data
			}); 
			
			$sltCategory.val(m).trigger("change");
			$sltCategory.on("select2:select", function (e) { renewSelect($sltCategory,getRemoteData($sltCategory.val()));});
			$sltCategory.on("select2:unselect", function (e) { unSelect($sltCategory);});
		});
		
		function getRemoteData(ids){
			if (ids==-1){
				id="";
			}else if(id==""){
				id=ids;
			}else{
				id+=","+ids;
			}
			pid=ids;
			
			var retureValue;
			$.ajax({
		        type: "POST",
		        async:false,
		        contentType: "application/json; charset=utf-8",
		        url: "<%=basePath%>categorymanagement/getcategoryrank.do",
		        data: "{'ids':'"+id+"' , 'parentId':'"+pid+"' , 'notExistId':'"+$("#categoryId").val()+"'}",
		        dataType: 'json',
		        success: function(result) {
		        	retureValue=result.data;
		        }
		    });
			return retureValue;
		}
		
		function renewSelect(obj,data){
			obj.select2("destroy");
			$("#div_select").remove();
			$("#div_parent").append('<div class="col-md-9" id="div_select"><select class="js-states js-example-events form-control" multiple="multiple" id="newSelect"></select></div>');
			
			var $sltCategory = $("#newSelect");
			$sltCategory.select2({
				data:data
			});
			
			var m=new Array();
			if (id.indexOf(",")>0){
				var arr=id.split(",");
				for (var i=0;i<arr.length;i++){
					m[i]=parseInt(arr[i]);
				}
			}else{
				m[0]=parseInt(id);
			}
			
			$sltCategory.val(m).trigger("change");
			$sltCategory.on("select2:select", function (e) { renewSelect($sltCategory,getRemoteData($sltCategory.val()));});
			$sltCategory.on("select2:unselect", function (e) { unSelect($sltCategory,e);});
		}
		
		function unSelect(obj,evt){
			// evt parameters-->"data":{"selected":false,"disabled":false,"text":"California","id":"CA",
			//  "title":"","_resultId":"select2-m96d-result-ev93-CA","element":"[DOM node]"}
			
			var deleteId=evt.params.data.id;
			var arrIds=id.split(",");
			var arrNewIds=new Array();
			var ind=0;
			for (var i=0;i<arrIds.length;i++){
				if (deleteId==arrIds[i]){
					break;
				}
				arrNewIds[ind++]=arrIds[i];
			}
			
			if(arrNewIds.length==0){
				arrNewIds[0]=-1;
			}
			
			var newData;
			$.ajax({
		        type: "POST",
		        async:false,
		        contentType: "application/json; charset=utf-8",
		        url: "<%=basePath%>categorymanagement/getcategoryrank.do",
		        data: "{'ids':'"+arrNewIds+"' , 'parentId':'"+arrNewIds[arrNewIds.length-1]+"' , 'notExistId':'"+$("#categoryId").val()+"'}",
		        dataType: 'json',
		        success: function(result) {
		        	newData=result.data;
		        }
		    });
			
			id=arrNewIds.join();
			pid=arrNewIds[arrNewIds.length-1];
			
			renewSelect(obj,newData);
		}
		
		function checkAllInfo(){
			if (!$("#categoryName").val()){
				alert("input categoryName");
				return false;
			}
			
			alert("pid-->"+pid);
			$("#categoryParentId").val(pid);
			alert("parentId-->"+$("#categoryParentId").val());
			
			return ture;
		}
	</script>
	<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>