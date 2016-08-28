<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
 
<html>
	<head>
		<script type="text/javascript" src="<s:url value='/resources/js/shop.js' />"></script>
	</head>
	<body>
		<div id="wrapper">
	        <div id="sidebar-wrapper" style="max-width: 225px; overflow: hidden;">
	            <ul class="sidebar-nav nav-pills nav-stacked" id="menu">
	 
	                <li class="<s:if test='actionURL == "listShop"'>active</s:if>">
	                    <a href="listShop"><span class="fa-stack fa-lg pull-left"><i class="fa fa-dashboard fa-stack-1x "></i></span> Danh sách shop</a>
	                </li>
	                <li class="<s:if test='actionURL == "addShop"'>active</s:if>">
	                    <a href="addShop"><span class="fa-stack fa-lg pull-left"><i class="fa fa-flag fa-stack-1x "></i></span> Thêm mới shop</a>
	                </li>
	                <li class="<s:if test='actionURL == "listCategory"'>active</s:if>">
	                    <a href="listCategory"><span class="fa-stack fa-lg pull-left"><i class="fa fa-cloud-download fa-stack-1x "></i></span>Quản lý danh mục</a>
	                </li>
	                 <li>
	                    <a href="#"><span class="fa-stack fa-lg pull-left"><i class="fa fa-cloud-download fa-stack-1x "></i></span>Thống kê</a>
	                </li>
	
	            </ul>
	        </div>
	    </div>
	</body>
</html>