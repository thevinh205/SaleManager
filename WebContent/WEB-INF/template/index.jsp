<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
 
<html>
	<head>
		<title>Trang chủ</title>
	</head>
	<body>
		<s:include value="/WEB-INF/template/header/header.jsp"></s:include>
		<div stype="width:100%" id="shop1">
			<div class="divShop">
				<a href="/SaleManager/shop/listShop">
                    <div class="sapo_services_item">
                    	<img class="imageIconShop" src="<s:url value='/resources/images/cua_hang.png' />"/>
                    </div>
                    <span align="center" class="titleShop">Cửa hàng</span>
           		</a>
           	</div>
           	
           	<div class="divShop" id="shop2">
				<a href="/SaleManager/product/productList">
                    <div class="sapo_services_item">
                    	<img class="imageIconShop" src="<s:url value='/resources/images/san_pham.png' />"/>	
                    </div>
                    <span align="center" class="titleShop">Sản phẩm</span>
           		</a>
           	</div>
           	
           	<div class="divShop" id="shop3">
				<a href="#">
                    <div class="sapo_services_item">
                    	<img class="imageIconShop" src="<s:url value='/resources/images/so_sach.png' />"/>	
                    </div>
                    <span align="center" class="titleShop">Sổ sách</span>
           		</a>
           	</div>
           	
           	<div class="divShop" id="shop4">
				<a href="#" align="center">
                    <div class="sapo_services_item">
                    	<img class="imageIconShop" src="<s:url value='/resources/images/theo_doi.png' />"/>	
                    </div>
                    <span align="center" class="titleShop">Theo dõi</span>
           		</a>
           	</div>
           	
           	<div class="divShop" id="shop5">
				<a href="#" align="center">
                    <div class="sapo_services_item">
                    	<img class="imageIconShop" src="<s:url value='/resources/images/mua_ban.png' />"/>	
                    </div>
                    <span align="center" class="titleShop">Mua bán</span>
           		</a>
           	</div>
           	
           	<div class="divShop" id="shop6">
				<a href="/SaleManager/customer/customer_list" align="center">
                    <div class="sapo_services_item">
                    	<img class="imageIconShop" src="<s:url value='/resources/images/cham_soc_khach_hang.png' />"/>	
                    </div>
                    <span align="center" class="titleShop">Hỗ trợ khách hàng</span>
           		</a>
           	</div>
           	
           	<div class="divShop" id="shop7">
				<a href="/SaleManager/employee/employeeList" align="center">
                    <div class="sapo_services_item">
                    	<img class="imageIconShop" src="<s:url value='/resources/images/nhan_su.png' />"/>	
                    </div>
                    <span align="center" class="titleShop">Nhân sự</span>
           		</a>
           	</div>
           	
           	<div class="divShop" id="shop7">
				<a href="#" align="center">
                    <div class="sapo_services_item">
                    	<img class="imageIconShop" src="<s:url value='/resources/images/nha_kho.png' />"/>	
                    </div>
                    <span align="center" class="titleShop">Nhà kho</span>
           		</a>
           	</div>
           	
           	<div class="divShop" id="shop7">
				<a href="#" align="center">
                    <div class="sapo_services_item">
                    	<img class="imageIconShop" src="<s:url value='/resources/images/bao_cao.png' />"/>	
                    </div>
                    <span align="center" class="titleShop">Báo cáo</span>
           		</a>
           	</div>

		</div>
	</body>
</html>