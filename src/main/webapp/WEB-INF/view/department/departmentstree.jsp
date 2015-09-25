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
<link href="<%=basePath%>assets/jstree/dist/themes/default/style.min.css" rel="stylesheet" type="text/css"/>
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
					<li class="">
						<a href="<%=basePath%>categorymanagement/categoriestree.do"> 
							<i class="fa fa-bookmark-o"></i>
							<span class="title"> 类别管理 </span> 
							<span class="selected"></span>
							<span class="arrow "></span>
						</a>
					</li>
					<li class="start active ">
						<a href="<%=basePath%>departmentmanagement/departmentstree.do"> 
							<i class="fa fa-bookmark-o"></i>
							<span class="title"> 部门管理 </span> 
							<span class="arrow open "></span>
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
							部门管理
						</h3>
						<ul class="page-breadcrumb breadcrumb">
							<li class="btn-group">
								<button type="button" class="btn blue dropdown-toggle"
									data-toggle="dropdown" data-hover="dropdown" data-delay="1000"
									data-close-others="true">
									<span> 操作 </span> <i class="fa fa-angle-down"></i>
								</button>
								<ul class="dropdown-menu pull-right" role="menu">
									<li><a href="javascript:createDepartment()"> 新建部门 </a></li>
									<li><a href="javascript:modifyDepartment()"> 修改部门 </a></li>
									<li><a href="javascript:deleteDepartment()"> 删除部门 </a></li>
								</ul>
							</li>
							<li>
								<i class="fa fa-home"></i>
								库存管理
								<i class="fa fa-angle-right"></i>
							</li>
							<li>
								<a href="#">部门管理</a>
								<i class="fa fa-angle-right"></i>
							</li>
						</ul>
						<!-- END PAGE TITLE & BREADCRUMB-->
					</div>
				</div>
				<!-- END PAGE HEADER-->
				<!-- BEGIN PAGE CONTENT-->
				<div class="row">
					<div class="col-md-6">
						<div class="portlet blue box">
							<div class="portlet-title">
								<div class="caption">
									<i class="fa fa-cogs"></i>公司部门
								</div>
							</div>
							<div class="portlet-body">
								<div id="using_json_1" class="tree-demo">
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-6">
					<div class="portlet green box">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-cogs"></i>部门信息
							</div>
						</div>
						<div class="portlet-body">
							<div class="portlet-body form">
								<!-- BEGIN FORM-->
								<form action="" class="form-horizontal form-bordered" onsubmit="return checkAllInfo();" method="POST">
									<div class="form-body">
										<div class="form-group">
											<label class="control-label col-md-3">上级部门</label>
											<div class="col-md-9">
												<input type="text" placeholder="上级部门" class="form-control" value="" id="parentName" name="parentName" readonly="readonly"/>
											</div>
										</div>
										<div class="form-group">
											<label class="control-label col-md-3">部门名称</label>
											<div class="col-md-9">
												<input type="text" placeholder="部门名称" class="form-control" value="" id="departmentName" name="departmentName" readonly="readonly"/>
											</div>
										</div>
									</div>
									<div class="form-actions fluid">
										<div class="row">
											<div class="col-md-12">
												<div class="col-md-offset-3 col-md-9">
													<button type="button" class="btn green" onclick="saveDepartment()">Submit</button>
													<button type="button" class="btn default">Cancel</button>
												</div>
											</div>
										</div>
									</div>
									<input type="hidden" id="departmentId" name="departmentId" value="${department.id }" />
									<input type="hidden" id="departmentParentId" name="departmentParentId" value="${department.parentId }" />
									<input type="hidden" id="parentIds" name="parentIds" value="${parentIds }" />
									<input type="hidden" id="operation_status" name="operation_status" value="${operation_status }" />
								</form>
								<!-- END FORM-->
							</div>
						</div>
					</div>
				</div>
				</div>
				<!-- END PAGE CONTENT-->
			</div>
			<form action="" id="frmShowDepartment" method="POST">
				<input type="hidden" id="departmentId" name="departmentId" />
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
	<script src="<%=basePath%>assets/metronic/plugins/bootbox/bootbox.min.js" type="text/javascript"></script>
	<!-- END ALERT BOX -->
	<script src="<%=basePath%>assets/jstree/dist/jstree.js"></script>
	<script>
		jQuery(document).ready(function() {
			// initiate layout and plugins
			App.init();
			
			//initiate jstree
			$(function () {
				$('#using_json_1').jstree({ 
					'core' : {
						"multiple" : false,
					    'data' : {
							contentType: "application/json; charset=utf-8",
					        url: "<%=basePath%>departmentmanagement/inittreedata.do",
					        dataType: 'json'
					    }
					},
					"checkbox" : {
						"keep_selected_style" : true,
						"three_state":false,
						"whole_node":false,
						"tie_selection":false,
						"cascade":"down+undetermined"
				    },
					"types" : {
		                "default" : {
		                    "icon" : "fa fa-folder icon-warning icon-lg"
		                },
		                "file" : {
		                    "icon" : "fa fa-file icon-warning icon-lg"
		                }
		            },
		            "plugins": ["types","checkbox"]
				});
			});
			
			//add event listener
			$('#using_json_1').on("changed.jstree", function (e, data) {
			  	/* alert(data.selected);
				var i, j, r = [];
			    for(i = 0, j = data.selected.length; i < j; i++) {
			      r.push(data.instance.get_node(data.selected[i]).text);
			    }
			    alert(r); */
			    $("#parentName").val($('#using_json_1').jstree(true).get_node(data.instance.get_parent(data.selected)).text);
			    $("#departmentName").val(data.instance.get_node(data.selected).text);
			    $("#departmentId").val(data.selected);
			    $("#departmentParentId").val(data.instance.get_parent(data.selected));
			});
		});
		
		//refresh tree
		function refreshTree(node){
			if (node){
				$('#using_json_1').jstree(true).load_node(node);
			}else{
				$('#using_json_1').jstree(true).refresh();
			}
		}
		
		//get tree instance
		function getInstanceOfTree(tree){
			var treeInstance;
			if (tree){
				treeInstance=$('#'+tree).jstree(true);
			}else{
				treeInstance=$('#using_json_1').jstree(true);
			}
			return treeInstance;
		}
		
		//create department button action
		function createDepartment(){
			$("#departmentName").attr('readonly', false);
			
			var ref=getInstanceOfTree(),selection=ref.get_selected();
			
			$("#parentName").val(ref.get_node(selection).text);
		    $("#departmentName").val("");
		    $("#departmentId").val(null);
		    $("#departmentParentId").val(selection);
		}

		//modify department button action
		function modifyDepartment() {
			var ref=getInstanceOfTree();
			if (!ref.get_selected()){
				showMessage("Hitting text to select option that you want to modify.");
				return;
			}
			$("#departmentName").attr('readonly', false);
		}
		
		//delete department button action
		function deleteDepartment() {
			var ref=getInstanceOfTree();
			if(!ref.get_checked()){
				showMessage("check option you want to delete.");
				return;
			}
			bootbox.confirm("<font size='3'>You checked option(s) will be deleted and all included subclass were deleted at the same time.</font>", function (result){
				if (result==true){
					$.ajax({
						type : "POST",
						async : false,
						contentType : "application/json; charset=utf-8",
						url : "<%=basePath%>departmentmanagement/deletedepartment.do",
				        data: "{'departmentIds':'"+ref.get_checked()+"'}",
				        dataType: 'json',
				        success: function(result) {
				        	if (result.status==1){
				        		cleanAllFields();
				   			 	refreshTree();
				        	}
				        	showMessage(result.data);
				        }
				    });
				}
			});
		}
		
		function cleanAllFields(){
			$("#parentName").val("");
		    $("#departmentName").val("");
		    $("#departmentId").val(null);
		    $("#departmentParentId").val(null);
		}

		//check field and do save action
		function saveDepartment() {
			var flag = true, id, parentid, departmentName;

			if (!$("#departmentName").val()) {
				constractAlertMessage("Input department NAME please!");
				flag = false;
			}

			if (!flag) {
				showMessage();
				return;
			}

			saveDepartmentInfo();
		}
		
		//save department information
		function saveDepartmentInfo(){
			var id, parentid, departmentName;
			
			id = $("#departmentId").val();
			parentId = $("#departmentParentId").val();
			departmentName = $("#departmentName").val();

			$.ajax({
				type : "POST",
				async : false,
				contentType : "application/json; charset=utf-8",
				url : "<%=basePath%>departmentmanagement/savedepartment.do",
		        data: "{'departmentId':'"+id+"' , 'parentId':'"+parentId+"' , 'departmentName':'"+departmentName+"'}",
		        dataType: 'json',
		        success: function(result) {
		        	if (result.status==1){
		   			 	$("#departmentName").attr('readonly',true);
		   			 	refreshTree();
		   			 	openTreeNode();
		        	}
		        	showMessage(result.data);
		        }
		    });
		}
		
		function openTreeNode(node){
			var ref=getInstanceOfTree();
			if (node){
				operationNode=node;
			}else{
				operationNode=ref.get_selected();
			}
			if (!ref.is_open(operationNode)){
				ref.open_node(operationNode,false,0);
			}
		}
		
		//construct message for display
		var message;
		function constructAlertMessage(msg){
			if (!message){
				message=msg;
			}else{
				message+="<br/>"+msg;
			}
		}
		
		function showMessage(msg){
			if (msg){
				message=msg;
			}
			bootbox.alert("<font size='4'>"+message+"</font>");
		}
	</script>
	<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>