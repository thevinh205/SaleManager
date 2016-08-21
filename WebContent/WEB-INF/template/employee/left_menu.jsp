<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
 
<html>
	<head>
	</head>
	<body>
		<div id="wrapper">
	        <div id="sidebar-wrapper" style="max-width: 225px; overflow: hidden;">
	            <ul class="sidebar-nav nav-pills nav-stacked" id="menu">
	 
	                <li class="<s:if test='actionURL == "employeeList"'>active</s:if>">
	                    <a href="employeeList"><span class="fa-stack fa-lg pull-left"><i class="fa fa-dashboard fa-stack-1x "></i></span> Danh sách nhân viên</a>
	                </li>
	                <li class="<s:if test='actionURL == "registerEmployee"'>active</s:if>">
	                    <a href="registerEmployee"><span class="fa-stack fa-lg pull-left"><i class="fa fa-flag fa-stack-1x "></i></span> Đăng ký nhân viên</a>
	                </li>
	
	            </ul>
	        </div>
	    </div>
	</body>
</html>