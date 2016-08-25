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
		<s:include value="/WEB-INF/template/product/left_menu.jsp"></s:include>
		<div class="headerAddProduct" style="    width: calc(100% - 230px); float:right">
			<p class="titleAddProduct">Shop quận 12</p>
		</div> 
		<div class="divContent" style="min-height: calc(100% - 85px); border-bottom: none">
		
			<div class="" style="padding-left: 0px">
			  <ul class="nav nav-tabs">
			    <li class="active"><a href="#product">Sản phẩm</a></li>
			    <li><a href="#employee">Nhân viên</a></li>
			    <li><a href="#order">Đơn hàng</a></li>
			    <li><a href="#statistic">Thống kê</a></li>
			  </ul>
			
			  <div class="tab-content">
			    <div id="product" class="tab-pane fade in active" style="width: 100%">
			      <s:include value="/WEB-INF/template/shop/product_list.jsp"></s:include>
			    </div>
			    <div id="employee" class="tab-pane fade" style="width: 100%">
			      <s:include value="/WEB-INF/template/shop/employee_list.jsp"/>
			    </div>
			    <div id="order" class="tab-pane fade" style="width: 100%">
			      <h3>Menu 2</h3>
			      <p>Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam.</p>
			    </div>
			    <div id="statistic" class="tab-pane fade">
			      <h3>Menu 3</h3>
			      <p>Eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo.</p>
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