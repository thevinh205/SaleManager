<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html; charset=UTF-8" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>

<html>
	<head>
	    <title>Chi tiết shop</title>
	</head>
	<body>
		<s:include value="/WEB-INF/template/header/header.jsp"></s:include>
		<s:include value="/WEB-INF/template/shop/left_menu.jsp"></s:include>
		<div class="headerAddProduct" style="    width: calc(100% - 230px); float:right">
			<p class="titleAddProduct"><s:property value="shopView.shop.name"/></p>
		</div> 
		<div class="divContent" style="min-height: calc(100% - 85px); border-bottom: none">
		
			<div class="" style="padding-left: 0px">
			  <ul class="nav nav-tabs">
			    <li class="active"><a href="javascript:void(0)" onclick="changeTab('listProduct')"><strong>Sản phẩm</strong></a></li>
			    <li><a href="javascript:void(0)" onclick="changeTab('listEmployee')"><strong>Nhân viên</strong></a></li>
			    <li><a href="javascript:void(0)" onclick="changeTab('listOrder')"><strong>Đơn hàng</strong></a></li>
			    <li><a href="javascript:void(0)" onclick="changeTab('statistic')"><strong>Thống kê</strong></a></li>
			  </ul>
			
			  <div class="tab-content">
			    <div id="contentTab" class="tab-pane fade in active" style="width: 100%">
			      <s:include value="/WEB-INF/template/shop/product_list.jsp"></s:include>
			      <s:property value="%{shopOrderController.listOrder"/>
			    </div>
			  </div>
			</div>

			<script>
				$(document).ready(function(){
				    $(".nav-tabs a").click(function(){
				        $(this).tab('show');
				    });
				});
			</script>

		</div>
	</body>
</html>