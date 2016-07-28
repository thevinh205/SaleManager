<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
 
<html>
	<head>
	</head>
	<body>
		<div id="wrapper">
	        <div id="sidebar-wrapper" style="max-width: 225px; overflow: hidden;">
	            <ul class="sidebar-nav nav-pills nav-stacked" id="menu">
	 
	                <li class="<s:if test='actionURL == "customer_list"'>active</s:if>">
	                    <a href="customer_list"><span class="fa-stack fa-lg pull-left"><i class="fa fa-dashboard fa-stack-1x "></i></span> Danh sách khách hàng</a>
	                </li>
	                <li class="<s:if test='actionURL == "register_member"'>active</s:if>">
	                    <a href="register_member"><span class="fa-stack fa-lg pull-left"><i class="fa fa-flag fa-stack-1x "></i></span> Đăng ký khách hàng</a>
	                    <%-- <ul class="nav-pills nav-stacked" style="list-style-type:none;">
	                        <li><a href="#"><span class="fa-stack fa-lg pull-left"><i class="fa fa-flag fa-stack-1x "></i></span>link1</a></li>
	                        <li><a href="#"><span class="fa-stack fa-lg pull-left"><i class="fa fa-flag fa-stack-1x "></i></span>link2</a></li>
	 
	                    </ul> --%>
	                </li>
	                <li>
	                    <a href="#"><span class="fa-stack fa-lg pull-left"><i class="fa fa-cloud-download fa-stack-1x "></i></span>Thống kê mua hàng</a>
	                </li>
	                 <li>
	                    <a href="#"><span class="fa-stack fa-lg pull-left"><i class="fa fa-cloud-download fa-stack-1x "></i></span>Report</a>
	                </li>
	                <li>
	                    <a href="#"><span class="fa-stack fa-lg pull-left"><i class="fa fa-cloud-download fa-stack-1x "></i></span>Gửi mail</a>
	                </li>
	
	            </ul>
	        </div>
	    </div>
	</body>
</html>