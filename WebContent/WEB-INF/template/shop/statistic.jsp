<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html; charset=UTF-8" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>

<html>
	<body>
		<div>
			<div style="width: calc(100%); float: right; border-bottom: 1px solid #e4e0e0;">
				<p  style="font-size: 20px; font-weight: bold; float:left; margin-left: 20px">Thống kê</p>
			</div>
			 <div id=searchCustomer style="margin:10px 0 0 5px; width: calc(100% - 10px)" align="center">
				<form method="POST" action="statictisOrder" style="border-bottom: 1px solid #e4e0e0;">
					<strong>Loại:</strong> 
					<select class="btnDropDown" name="typeStatistic">
						<option value="day">Ngày</option>
						<option value="week">Tuần</option>
						<option value="month">Tháng</option>
						<option value="year">Năm</option>
					</select>
					
					<input type="submit" value="Tìm kiếm" class="btnStyle" onclick="showIconLoading()"/>
				</form>

			</div>
		</div>
	  
	  
	</body>
</html>