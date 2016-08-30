<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
 
<html>
	<head>
		<meta http-equiv="Content-type" content="text/html;charset=UTF-8" />
		<link rel="stylesheet" type="text/css" href="<s:url value='/resources/css/main.css' />">
		<link rel="stylesheet" type="text/css" href="<s:url value='/resources/css/product.css' />">
		<link rel="stylesheet" type="text/css" href="<s:url value='/resources/css/bootstrap.min.css' />">
		<link rel="stylesheet" type="text/css" href="<s:url value='/resources/css/simple-sidebar.css' />">
		<link rel="stylesheet" type="text/css" href="<s:url value='/resources/css/datepicker.css' />">
		
		<script type="text/javascript" src="<s:url value='/resources/js/jquery.min.js' />"></script>
		<script type="text/javascript" src="<s:url value='/resources/js/bootstrap.min.js' />"></script>
		<script type="text/javascript" src="<s:url value='/resources/js/common.js' />"></script>
		<script type="text/javascript" src="<s:url value='/resources/js/nicEdit.js'/>"></script>
		<script type="text/javascript" src="<s:url value='/resources/js/product.js' />"></script>
		<script type="text/javascript" src="<s:url value='/resources/js/bootstrap-datepicker.js' />"></script>
	</head>
	<body>
		<div id="loadingIcon"></div>
		<div style="width:100%;height:45px; background-color:#3B5166; color: white;">
			<div style="float:left; margin: 2px 0 0 20px">
				<a href="/SaleManager/index">
					<img style="width:40px" src="<s:url value='/resources/images/home.png' />"></img>
				</a>
			</div>
			<div style="float:right; margin: 13px 30px 0 0">
				<a href="javascript:void(0)" style="color:white"><s:property value="userUtil.member.name"/></a>
				<div class="dropdownMember">
					<a class="caretMember dropbtn" href="javascript:void(0)" onclick="myFunction()"></a>
				  	<div id="myDropdown" class="dropdown-content">
					    <a href="#home">Thông tin</a>
					    <a href="#about">Sửa thông tin</a>
					    <a href="logoutAction">Đăng xuất</a>
				  	</div>
				</div>
			</div>
		</div>
	</body>
</html>