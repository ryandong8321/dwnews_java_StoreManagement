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
<link href="http://cdn.bootcss.com/select2/4.0.0/css/select2.min.css" rel="stylesheet">
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
							<span class="arrow "></span>
						</a>
					</li>
					<li class="">
						<a href="<%=basePath%>brandmanagement/brandslist.do"> 
							<i class="fa fa-bookmark-o"></i>
							<span class="title"> 品牌管理 </span> 
							<span class="arrow "></span>
						</a>
					</li>
					<li class="">
						<a href="<%=basePath%>providermanagement/providerslist.do"> 
							<i class="fa fa-bookmark-o"></i>
							<span class="title"> 供应商管理 </span> 
							<span class="arrow "></span>
						</a>
					</li>
					<li class="start active ">
						<a href="<%=basePath%>itemmanagement/itemslist.do"> 
							<i class="fa fa-bookmark-o"></i>
							<span class="title"> 商品管理 </span> 
							<span class="selected"></span>
							<span class="arrow open"></span>
						</a>
					</li>
					<li class="">
						<a href="<%=basePath%>departmentmanagement/departmentstree.do"> 
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
							商品管理
						</h3>
						<!-- END PAGE TITLE & BREADCRUMB-->
					</div>
				</div>
				<!-- END PAGE HEADER-->
				<!-- BEGIN PAGE CONTENT-->
				<div class="row">
					<div class="col-md-12">
						<div class="portlet box blue ">
							<div class="portlet-title">
								<div class="caption">
									<i class="fa fa-reorder"></i>商品信息
								</div>
							</div>
							<div class="portlet-body form">
								<!-- BEGIN FORM-->
								<form action="<%=basePath%>itemmanagement/saveitem.do" class="form-horizontal form-bordered" onsubmit="return checkAllInfo();" method="POST">
									<div class="form-body">
										<div class="form-group">
											<label class="control-label col-md-3">商品名称</label>
											<div class="col-md-9">
												<input type="text" placeholder="商品名称" class="form-control" value="${item.itemName }" id="itemName" name="itemName" />
											</div>
										</div>
										<div class="form-group">
											<label class="control-label col-md-3">商品单位</label>
											<div class="col-md-9">
												<input type="text" placeholder="商品单位" class="form-control" value="${item.itemUnit }" id="itemUnit" name="itemUnit" />
											</div>
										</div>
										<div class="form-group">
											<label class="control-label col-md-3">商品单价</label>
											<div class="col-md-9">
												<input type="text" placeholder="商品单价" class="form-control" value="${item.itemPrice }" id="itemPrice" name="itemPrice" />
											</div>
										</div>
										<div class="form-group">
											<label class="control-label col-md-3">商品二维码</label>
											<div class="col-md-9">
												<input type="text" placeholder="商品二维码" class="form-control" value="${item.itemBarCode }" id="itemBarCode" name="itemBarCode" />
											</div>
										</div>
										<div class="form-group">
											<label class="control-label col-md-3">商品库存</label>
											<div class="col-md-9">
												<input type="text" placeholder="商品库存" class="form-control" value="${item.itemStoreCount }" id="itemStoreCount" name="itemStoreCount" />
											</div>
										</div>
										<div class="form-group">
											<label class="control-label col-md-3">商品规格</label>
											<div class="col-md-9">
												<input type="text" placeholder="商品规格" class="form-control" value="${item.itemStandard }" id="itemStandard" name="itemStandard" />
											</div>
										</div>
										<div class="form-group">
											<label class="control-label col-md-3">
												<a class=" btn default" href="#ajax" data-toggle="modal">
													 商品类别
												</a>
											</label>
											<div class="col-md-9">
												<input type="text" placeholder="商品类别" class="form-control" value="${item.category.categoryName }" id="categoryName" name="categoryName"/>
												<input type="hidden" value="${item.category.id }" id="category.id" name="category.id"/>
											</div>
										</div>
										<div class="form-group" id="div_brand_parent">
											<label class="control-label col-md-3">商品品牌</label>
											<div class="col-md-9" id="div_brand_select">
												<select class="js-states form-control" multiple="multiple" id="brandId" name="brandId"></select>
											</div>
										</div>
										<div class="form-group" id="div_provider_parent">
											<label class="control-label col-md-3">供货商</label>
											<div class="col-md-9" id="div_provider_select">
												<select class="js-states form-control" multiple="multiple" id="providerIds" name="providerIds"></select>
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
									<input type="hidden" id="itemId" name="itemId" value="${item.id }" />
									<input type="hidden" id="operation_status" name="operation_status" value="${operation_status }" />
								</form>
								<!-- END FORM-->
							</div>
						</div>
					</div>
					
					<div class="modal fade" id="ajax" role="basic" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="row">
									<div class="col-md-12">
										<div class="portlet blue box">
											<div class="portlet-title">
												<div class="caption">
													<i class="fa fa-cogs"></i>产品类别
												</div>
											</div>
											<div class="portlet-body">
												<div id="using_json_1" class="tree-demo">
												</div>
											</div>
											<div class="modal-footer">
												<button type="button" class="btn green" data-dismiss="modal">Close</button>
												<button type="button" class="btn green" onclick="save()">Save</button>
											</div>
										</div>
									</div>
								</div>
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
	<script src="<%=basePath%>assets/metronic/scripts/core/app.js"></script>
	<!-- BEGIN ALERT BOX -->
	<script src="<%=basePath%>assets/metronic/plugins/bootbox/bootbox.min.js" type="text/javascript"></script>
	<!-- END ALERT BOX -->
	<script src="http://cdn.bootcss.com/select2/4.0.0/js/select2.min.js"></script>
	<script src="<%=basePath%>assets/jstree/dist/jstree.js"></script>
	<script>
		jQuery(document).ready(function() {
			var $brands, $providers, brandsData, providersData;
			// initiate layout and plugins
			App.init();
			
			//initiate select2
			$brands=$("#brandId");
			$providers=$("#providerIds");
			
			$.ajax({
		        type: "POST",
		        async:false,
		        contentType: "application/json; charset=utf-8",
		        url: "<%=basePath%>itemmanagement/getallbrand.do",
		        data: "{'itemId':'"+$("#itemId").val()+"'}",
		        dataType: 'json',
		        success: function(result) {
		        	brandsData=result.data;
		        }
		    });
			
			$brands.select2({
				data:brandsData
			});
			
			$.ajax({
		        type: "POST",
		        async:false,
		        contentType: "application/json; charset=utf-8",
		        url: "<%=basePath%>itemmanagement/getallproviders.do",
		        data: "{'brandId':'"+$("#brandId").val()+"','itemId':'"+$("#itemId").val()+"'}",
		        dataType: 'json',
		        success: function(result) {
		        	providersData=result.data;
		        }
		    });
			
			$providers.select2({
				data:providersData
			});
			
			//initiate tree
			$(function () {
				$('#using_json_1').jstree({ 
					'core' : {
						"multiple" : false,
					    'data' : {
							contentType: "application/json; charset=utf-8",
					        url: "<%=basePath%>categorymanagement/inittreedata.do",
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
		            "plugins": ["types"]
				});
			});
			
			//add event listener
			$brands.on("select2:select", function (e) { changeProviders($brands.val()); });
			$brands.on("select2:unselect", function (e) { changeProviders($brands.val());});
			
			$('#using_json_1').on("select_node.jstree", function (e, data) {
				alert("data.selected-->"+data.selected);
				$("#categoryName").val(data.instance.get_node(data.selected).text);
				document.getElementById("category.id").value=data.selected;
				
				alert(document.getElementById("category.id").value);
			});
		});
		
		
		function changeProviders(brandId){
			var data;
			
			$("#div_provider_select").remove();
			$("#div_provider_parent").append('<div class="col-md-9" id="div_provider_select"><select class="js-states form-control" multiple="multiple" id="providerIds" name="providerIds"></select></div>');
			
			$.ajax({
		        type: "POST",
		        async:false,
		        contentType: "application/json; charset=utf-8",
		        url: "<%=basePath%>itemmanagement/getallproviders.do",
		        data: "{'brandId':'"+$("#brandId").val()+"','itemId':'"+$("#itemId").val()+"'}",
		        dataType: 'json',
		        success: function(result) {
		        	data=result.data;
		        }
		    });
			
			$("#providerIds").select2({
				data:data
			});			
		}

		//check field and do save action
		function checkAllInfo() {
			var flag = true;

			if (!$("#itemName").val()) {
				constractAlertMessage("Input item's NAME please!");
				flag = false;
			}
			if (!$("#itemPrice").val()) {
				constractAlertMessage("Input item's PRICE please!");
				flag = false;
			}
			if (!$("#itemBarCode").val()) {
				constractAlertMessage("Input item's BAR CODE please!");
				flag = false;
			}
			if (!$("#itemStoreCount").val()) {
				constractAlertMessage("Input item's STORE COUNT please!");
				flag = false;
			}
			if (!$("#categoryId").val()) {
				constractAlertMessage("Select CATEGORY of item please!");
				flag = false;
			}

			if (!flag) {
				showMessage();
			}
			
			return flag;
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