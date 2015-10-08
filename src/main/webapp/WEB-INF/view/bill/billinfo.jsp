<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	request.setAttribute("BasePath", basePath);
%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<link href="http://cdn.bootcss.com/select2/4.0.0/css/select2.min.css" rel="stylesheet">
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
					<li class="">
						<a href="<%=basePath%>itemmanagement/itemslist.do"> 
							<i class="fa fa-bookmark-o"></i>
							<span class="title"> 商品管理 </span> 
							<span class="arrow "></span>
						</a>
					</li>
					<li class="">
						<a href="<%=basePath%>departmentmanagement/departmentstree.do"> 
							<i class="fa fa-bookmark-o"></i>
							<span class="title"> 部门管理 </span> 
							<span class="arrow "></span>
						</a>
					</li>
					<li class="start active ">
						<a href="javascript:;"> 
							<i class="fa fa-folder-open"></i>
							<span class="title"> 出 / 入库管理 </span> 
							<span class="arrow open"></span>
						</a>
						<ul class="sub-menu">
							<li>
								<a href="<%=basePath%>billmanagement/billlist.do?ocategory=1">
									<i class="fa fa-cogs"></i> 出库管理 
									<c:choose>
										<c:when test="${ocategory=='1'}">
											<span class="selected"></span>
										</c:when>
									</c:choose>
									<span class="arrow "></span>
								</a>
							</li>
							<li>
								<a href="<%=basePath%>billmanagement/billlist.do?ocategory=2">
									<i class="fa fa-globe"></i> 入库管理
									<c:choose>
										<c:when test="${ocategory=='2'}">
											<span class="selected"></span>
										</c:when>
									</c:choose>
									<span class="arrow "></span>
								</a>
							</li>
						</ul>
					</li>
					<li class="">
						<a href="javascript:;"> 
							<i class="fa fa-bookmark-o"></i>
							<span class="title"> 统计 </span> 
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
							管理
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
									<i class="fa fa-reorder"></i>申请信息
								</div>
							</div>
							<div class="portlet-body form">
								<!-- BEGIN FORM-->
								<form id="frmBill" action="<%=basePath%>billmanagement/savebill.do" class="form-horizontal form-bordered" method="POST">
									<div class="form-body">
										<!-- BEGIN FORM VALIDATE ALERT-->
										<div class="alert alert-danger display-hide">
											<button class="close" data-close="alert"></button>
											You have some form errors. Please check below.
										</div>
										<div class="alert alert-success display-hide">
											<button class="close" data-close="alert"></button>
											Your form validation is successful!
										</div>
										<!-- END FORM VALIDATE ALERT-->
										<div class="form-group">
											<label class="control-label col-md-3">
												<a class=" btn default" href="#billDepartment" data-toggle="modal">
													 申请部门
												</a>
												<span class="required">*</span>
											</label>
											<div class="col-md-9">
												<input type="text" placeholder="申请部门" class="form-control" value="${bill.billDepartment.departmentName }" id="departmentName" name="departmentName" readOnly="readOnly"/>
												<input type="hidden" value="${bill.billDepartment.id }" id="billDepartment.id" name="billDepartment.id"/>
											</div>
										</div>
										<div class="form-group">
											<label class="control-label col-md-3">
												<a class=" btn default" href="#ajax" data-toggle="modal">
													 商品类别
												</a>
												<span class="required">*</span>
											</label>
											<div class="col-md-9">
												<input type="text" placeholder="商品类别" class="form-control" value="${bill.itemCategory.categoryName }" id="categoryName" name="categoryName" readOnly="readOnly"/>
												<input type="hidden" value="${bill.itemCategory.id }" id="itemCategory.id" name="itemCategory.id"/>
											</div>
										</div>
										<div class="form-group" id="div_billitem_parent">
											<label class="control-label col-md-3">商品名称</label><span class="required">*</span>
											<div class="col-md-9" id="div_billitem_select">
												<select class="js-states form-control" multiple="multiple" id="billItemId" name="billItemId"></select>
											</div>
										</div>
										<div class="form-group">
											<label class="control-label col-md-3">商品二维码<span class="required">*</span></label>
											<div class="col-md-9">
												<input type="text" placeholder="商品二维码" class="form-control" value="${bill.itemBarCode }" id="itemBarCode" name="itemBarCode" readOnly="readOnly"/>
											</div>
										</div>
										<div class="form-group">
											<label class="control-label col-md-3">申请数量<span class="required">*</span></label>
											<div class="col-md-9">
												<input type="text" placeholder="申请数量" class="form-control" value="${bill.billCount }" id="billCount" name="billCount" />
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
									<input type="hidden" id="billId" name="billId" value="${bill.id }" />
									<input type="hidden" id="operationCategory" name="operationCategory" value="${ocategory }" />
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
												<button type="button" class="btn green" data-dismiss="modal">OK</button>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="modal fade" id="billDepartment" role="basic" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="row">
									<div class="col-md-12">
										<div class="portlet blue box">
											<div class="portlet-title">
												<div class="caption">
													<i class="fa fa-cogs"></i>部门
												</div>
											</div>
											<div class="portlet-body">
												<div id="using_json_2" class="tree-demo">
												</div>
											</div>
											<div class="modal-footer">
												<button type="button" class="btn green" data-dismiss="modal">OK</button>
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
	<%-- <script src="<%=basePath%>assets/metronic/plugins/jquery-ui/jquery-ui-1.10.3.custom.min.js" type="text/javascript"></script> --%>
	<script src="<%=basePath%>assets/metronic/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
	<script src="<%=basePath%>assets/metronic/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js" type="text/javascript"></script>
	<script src="<%=basePath%>assets/metronic/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
	<script src="<%=basePath%>assets/metronic/plugins/jquery.blockui.min.js" type="text/javascript"></script>
	<script src="<%=basePath%>assets/metronic/plugins/jquery.cokie.min.js" type="text/javascript"></script>
	<script src="<%=basePath%>assets/metronic/plugins/uniform/jquery.uniform.min.js" type="text/javascript"></script>
	<!-- END CORE PLUGINS -->
	<!-- BEGIN VALIDATE -->
	<script src="<%=basePath%>assets/metronic/plugins/jquery-validation/dist/jquery.validate.min.js" type="text/javascript"></script>
	<script src="<%=basePath%>assets/metronic/plugins/jquery-validation/dist/additional-methods.min.js" type="text/javascript"></script>
	<!-- END VALIDATE -->
	<!-- BEGIN ALERT BOX -->
	<script src="<%=basePath%>assets/metronic/plugins/bootbox/bootbox.min.js" type="text/javascript"></script>
	<!-- END ALERT BOX -->
	<!-- BEGIN SELECT2 -->
	<script src="http://cdn.bootcss.com/select2/4.0.0/js/select2.min.js"></script>
	<!-- END SELECT2 -->
	<!-- BEGIN JSTREE -->
	<script src="<%=basePath%>assets/jstree/dist/jstree.js"></script>
	<!-- END JSTREE -->
	<script src="<%=basePath%>assets/metronic/scripts/core/app.js"></script>
	<script>
		jQuery(document).ready(function() {
			var $billItem, billItemsData;
			// initiate layout and plugins
			App.init();
			
			//initiate select2
			$billItem=$("#billItemId");
			
			$.ajax({
		        type: "POST",
		        async:true,
		        contentType: "application/json; charset=utf-8",
		        url: "<%=basePath%>billmanagement/getallitems.do",
		        data: "{'categoryId':'"+document.getElementById("itemCategory.id").value()+"'}",
		        dataType: 'json',
		        success: function(result) {
		        	billItemsData=result.data;
		        }
		    });
			
			$billItem.select2({
				data:billItemsData
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
			
			$(function () {
				$('#using_json_2').jstree({ 
					'core' : {
						"multiple" : false,
					    'data' : {
							contentType: "application/json; charset=utf-8",
					        url: "<%=basePath%>departmentmanagement/inittreedata.do",
					        dataType: 'json'
					    }
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
			$billItem.on("select2:select", function (e) { changeItemInfo($billItem.val()); });
			$billItem.on("select2:unselect", function (e) { changeItemInfo($billItem.val());});
			
			$('#using_json_1').on("select_node.jstree", function (e, data) {
				$("#categoryName").val(data.instance.get_node(data.selected).text);
				document.getElementById("itemCategory.id").value=data.selected;
				changeItemSelection(data.selected);
			});
			$('#using_json_2').on("select_node.jstree", function (e, data) {
				$("#departmentName").val(data.instance.get_node(data.selected).text);
				document.getElementById("billDepartment.id").value=data.selected;
			});
			
			handleValidation();
			
			var result="${result}";
			try{
				if (result){
					showMessage(result);
				}
			}catch(error){
			}
		});
		
		function handleValidation() {
			var frmItems = $('#frmBill'), checkError = $('.alert-danger', frmItems), checkSuccess = $('.alert-success', frmItems);
			
			frmItems.validate({
				errorElement: 'span', //default input error message container
				errorClass: 'help-block', // default input error message class
				focusInvalid: false, // do not focus the last invalid input
				ignore: "",
				rules: {
					categoryName: {
						required: true
					},
					billItemId: {
						required: true
					},
					itemBarCode: {
						maxlength: 128,
						required: true
					},
					billCount: {
						maxlength: 6,
						number: true,
						required: true
					},
					departmentName:{
						required: true
					}
				},
				
				invalidHandler: function (event, validator) { //display error alert on form submit
					checkSuccess.hide();
					checkError.show();
				},
				
				highlight: function (element) { // hightlight error inputs
					$(element).closest('.form-group').addClass('has-error'); // set error class to the control group
				},
				
				unhighlight: function (element) { // revert the change done by hightlight
					$(element).closest('.form-group').removeClass('has-error'); // set error class to the control group
				},
				success: function (label) {
					label.closest('.form-group').removeClass('has-error'); // set success class to the control group
				},
				
				submitHandler: function (form) {
					checkSuccess.show();
					checkError.hide();
					form.submit();
				}
			});
		}
		
		function changeItemInfo(itemId){
			var item;
			$.ajax({
		        type: "POST",
		        async:false,
		        contentType: "application/json; charset=utf-8",
		        url: "<%=basePath%>billmanagement/finditem.do",
		        data: "{'itemId':'"+itemId+"'}",
		        dataType: 'json',
		        success: function(result) {
		        	item=result.data;
		        	document.getElementById("itemCategory.id").value=item.categoryId;
		        	$("#cateogoryName").val(item.categoryName);
		        	$("#itemBarCode").val(item.itemBarCode);
		        }
		    });
		}
		
		function changeItemSelection(categoryId){
			var data;
			
			$("#div_billitem_select").remove();
			$("#div_billitem_parent").append('<div class="col-md-9" id="div_billitem_select"><select class="js-states form-control" multiple="multiple" id="billItemId" name="billItemId"></select></div>');
			
			$.ajax({
		        type: "POST",
		        async:false,
		        contentType: "application/json; charset=utf-8",
		        url: "<%=basePath%>billmanagement/getallitem.do",
		        data: "{'categoryId':'"+document.getElementById("itemCategory.id").value()+"'}",
		        dataType: 'json',
		        success: function(result) {
		        	data=result.data;
		        }
		    });
			
			$("#billItemId").select2({
				data:data
			});
			
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