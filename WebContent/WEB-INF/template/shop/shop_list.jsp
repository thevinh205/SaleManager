<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html; charset=UTF-8" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>

<html>
	<head>
	    <title>Danh sách shop</title>
	</head>
	<body>
		<s:include value="/WEB-INF/template/header/header.jsp"></s:include>
		<s:include value="/WEB-INF/template/product/left_menu.jsp"></s:include>
		<div class="headerAddProduct" style=" width: calc(100% - 230px); float:right">
			<p class="titleAddProduct">Danh sách shop</p>
		</div> 
		<div class="divContent" style="min-height: calc(100% - 85px); border-bottom: none">
			<s:iterator value="listShop" status="shop">
				<div class="divShop">
					<a href="shopDetail?shopId=<s:property value='id'/>" target="_blank">
	                    <div class="sapo_services_item">
	                    	<img class="imageIconShop" src="<s:url value='/resources/images/cua_hang.png' />"/>
	                    </div>
	                    <span align="center" class="titleShop"><s:property value="name"/></span>
	           		</a>
	           	</div> 
           	</s:iterator>
		</div>
	</body>
</html>