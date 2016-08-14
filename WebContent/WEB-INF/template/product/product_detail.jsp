<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>

<html>
	<head>
	    <title>Chi tiết sản phẩmsản phẩm</title>
	</head>
	<body>
		<s:include value="/WEB-INF/template/header/header.jsp"></s:include>
		<s:include value="/WEB-INF/template/product/left_menu.jsp"></s:include>
		<div class="headerAddProduct" style="    width: calc(100% - 230px); float:right">
			<p class="titleAddProduct">Chi tiết sản phẩm</p>
			<a class="linkAddCategory" href="editProduct?id=<s:property value='product.id'/>">Cập nhật</a>
		</div> 
		<div class="divContent" style="min-height: calc(100% - 85px); border-bottom: none">
			<div id="titleProductDiv" class="titleProductDiv">
				<p class="titleProduct"><s:property value="product.productName"/> </p>
			</div>	 
			<div class="imageProduct">
				<div id="imageShow" class="imageProductShowDiv">
					<img class="imageProductShow" src="<s:url action='ImageAction'><s:param name='imageId'><s:property value='imageShow'/></s:param></s:url>"/>
					<div class="imageSmall" >
						<s:iterator value="product.images" var="image">
							<a href="javascript:void(0)">
								<img src="<s:url action='ImageAction'><s:param name='imageId'><s:property value='#image.urlThumb'/></s:param></s:url>" 
									onclick="changeImageProductView('<s:property value="#image.id"/>', '<s:property value="#image.url"/>')" 
									class="imgThumb" id="imgThumb<s:property value='#image.id'/>"/>
							</a>
						</s:iterator>
					</div>
				</div>
				
			</div>
			<div class="contentProduct">
				<p class="desProduct">
					<s:property value="product.description"  escapeHtml="false"/>
				</p>
				<div class="divPriceProduct">
				<table>
					<tr>
						<td style="float:right;"><span class="priceProduct">Mã sản phẩm :</span></td>
						<td><s:property value="product.id"/></td>
					</tr>
					<tr>
						<td style="float:right;"><span class="priceProduct">Tên sản phẩm :</span></td>
						<td><s:property value="product.productName"/></td>
					</tr>
					<tr>
						<td style="float:right;"><span class="priceProduct">Giá bán :</span></td>
						<td><s:property value="product.priceSell"/> VND</td>
					</tr>
					<tr>
						<td style="float:right;"><span class="priceProduct">Giá mua :</span></td>
						<td><s:property value="product.priceBuy"/> VND</td>
					</tr>
					<tr>
						<td style="float:right;"><span class="priceProduct">Tình trạng :</span></td>
						<td><s:property value="product.status"/></td>
					</tr>
					<tr>
						<td style="float:right;"><span class="priceProduct">Nhóm sản phẩm :</span></td>
						<td><s:property value="product.categoryName"/></td>
					</tr>
					<tr>
						<td style="float:right;"><span class="priceProduct">Ngày tạo :</span></td>
						<td><s:date name="product.createDate" format="dd/MM/yyyy" /></td>
					</tr>
				</table>
				</div>	
			</div>
		</div>
	</body>
</html>