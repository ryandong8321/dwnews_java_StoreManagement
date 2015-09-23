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
<link href="<%=basePath%>assets/metronic/plugins/gritter/css/jquery.gritter.css" rel="stylesheet" type="text/css"/>
<link href="<%=basePath%>assets/metronic/plugins/bootstrap-daterangepicker/daterangepicker-bs3.css" rel="stylesheet" type="text/css"/>
<link href="<%=basePath%>assets/metronic/plugins/fullcalendar/fullcalendar/fullcalendar.css" rel="stylesheet" type="text/css"/>
<link href="<%=basePath%>assets/metronic/plugins/jqvmap/jqvmap/jqvmap.css" rel="stylesheet" type="text/css"/>
<link href="<%=basePath%>assets/metronic/plugins/jquery-easy-pie-chart/jquery.easy-pie-chart.css" rel="stylesheet" type="text/css"/>
<!-- END PAGE LEVEL PLUGIN STYLES -->
<!-- BEGIN THEME STYLES -->
<link href="<%=basePath%>assets/metronic/css/style-metronic.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>assets/metronic/css/style.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>assets/metronic/css/style-responsive.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>assets/metronic/css/plugins.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>assets/metronic/css/pages/tasks.css" rel="stylesheet" type="text/css"/>
<link href="<%=basePath%>assets/metronic/css/themes/default.css" rel="stylesheet" type="text/css" id="style_color" />
<link href="<%=basePath%>assets/metronic/css/print.css" rel="stylesheet" type="text/css" media="print"/>
<link href="<%=basePath%>assets/metronic/css/custom.css" rel="stylesheet" type="text/css" />
<!-- END THEME STYLES -->
<!-- BEGIN BOOTSTRAP TABLE -->
<link rel="stylesheet" type="text/css" href="<%=basePath%>assets/bootstrap_table/bootstrap-table.css" />
<!-- END BOOTSTRAP TABLE -->
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
							<li class="btn-group">
								<button type="button" class="btn blue dropdown-toggle"
									data-toggle="dropdown" data-hover="dropdown" data-delay="1000"
									data-close-others="true">
									<span> 操作 </span> <i class="fa fa-angle-down"></i>
								</button>
								<ul class="dropdown-menu pull-right" role="menu">
									<li><a href="javascript:$('#frmShowCategory').submit()"> 新建类别 </a></li>
									<li><a href="javascript:modifyNewCategory()"> 修改类别 </a></li>
									<li><a href="javascript:deleteNewCategory()"> 删除类别 </a></li>
								</ul>
							</li>
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
						<!-- BEGIN TABLE DATA-->
						<table id="table" data-toggle="table" data-height="460" data-side-pagination="server"
							data-pagination="true"
							data-url="<%=basePath%>categorymanagement/inittable.do">
							<thead>
								<tr>
									<th data-field="state" data-checkbox="true"></th>
									<th data-field="id">编号</th>
									<th data-field="categoryName">类别名称</th>
								</tr>
							</thead>
						</table>
						<!-- END TABLE DATA-->
					</div>
				</div>
				<!-- END PAGE CONTENT-->
			</div>
			<form action="<%=basePath%>categorymanagement/showcategory.do" id="frmShowCategory" method="POST">
				<input type="hidden" id="categoryId" name="categoryId" />
			</form>
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
	<script src="<%=basePath%>assets/metronic/scripts/core/app.js"></script>
	<!-- BEGIN ALERT BOX -->
	<script src="<%=basePath%>assets/metronic/scripts/custom/ui-alert-dialog-api.js"></script>
	<script src="<%=basePath%>assets/metronic/plugins/bootbox/bootbox.min.js" type="text/javascript"></script>
	<!-- END ALERT BOX -->
	<!-- BEGIN BOOTSTRAP TABLE -->
	<script type="text/javascript" src="<%=basePath%>assets/bootstrap_table/bootstrap-table.js"></script>
	<!-- END BOOTSTRAP TABLE -->
	<script>
		jQuery(document).ready(function() {
			// initiate layout and plugins
			App.init();

		});
		
		function modifyNewCategory(){
			var $table = $('#table');
			var options=$table.bootstrapTable('getSelections');
			if (options.length<=0){
				alert("no selection");
			}else if (options.length>1){
				alert("select too more");
			}else{
				$("#categoryId").val(options[0].id);
				$("#frmShowCategory").submit();
			}
		}
		
		function deleteNewCategory(){
			var $table = $('#table');
			var options=$table.bootstrapTable('getSelections');
			if (options.length<=0){
				alert("no selection");
			}else{			
				var arr=new Array();
				$(options).each(function(){
					arr.push(this.id);
				});
				alert(arr);
			}
		}
	</script>
	<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>