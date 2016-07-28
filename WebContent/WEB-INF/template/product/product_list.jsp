<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<html>
	<head>
	    <title>Danh sách sản phẩm</title>
	</head>
	<body>
		<s:include value="/WEB-INF/template/header/header.jsp"></s:include>
		<s:include value="/WEB-INF/template/product/left_menu.jsp"></s:include>
		<div class="divContent ">
			 <div class="headerAddProduct">
			   		<p class="titleAddProduct">Danh sách sản phẩm</p>
			 </div> 
			 <div id=searchCustomer style="margin:10px 0 0 10px; width: calc(100% - 10px)" align="center">
				<s:if test="errorMessage != null">
					<p style="color: red" align="center"><s:property value="errorMessage"/></p>
				</s:if>
				<form method="POST" action="searchProductList">
					<strong>Mã sản phẩm:</strong> 
					<input type="text" name="idProdSearch" value="<s:property value='idProdSearch'/>" style="margin-right:15px"/>
					
					<strong>Tên sản phẩm:</strong> 
					<input type="text" name="nameProdSearch" value="<s:property value='nameProdSearch'/>" style="margin-right:15px"/>
					
					<strong>Loại:</strong> 
					<select class="btnDropDown" name="groupProduct">
						<s:iterator value="categoryList" status="category">
							  <option ><s:property value="name"/></option>
						</s:iterator>
					</select>
					
					<input type="submit" value="Tìm kiếm" class="btnStyle" onclick="showIconLoading()"/>
				</form>
				
				<table class="tftable" border="1" style="text-align: center; width:95%">
					<tr >
						<th style="text-align: center">Mã sản phẩm</th>
						<th style="text-align: center">Tên sản phẩm</th>
						<th style="text-align: center">Nhóm sản phẩm</th>
						<th style="text-align: center">Giá mua</th>
						<th style="text-align: center">Giá bán</th>
						<th style="text-align: center">Ngày tạo</th>
						<th style="text-align: center">Mô tả</th>
						<th style="text-align: center">Hình ảnh</th>
						<th style="text-align: center"></th>
					</tr>
					<s:iterator value="productList" status="product">
						<tr id="rowCus<s:property value='userName'/>">
							<td><s:property value="id"/></td>
							<td><s:property value="productName"/></td>
							<td><s:property value="categoryName"/></td>
							<td><s:property value="priceBuy"/></td>
							<td><s:property value="priceBuy"/></td>
							<td><s:date name="createDate" format="dd/MM/yyyy" /></td>
							<td style="max-width: 300px"><s:property value="description" escapeHtml="false"/></td>
							<td>
								<img src="<s:url action='ImageAction'><s:param name='imageId'><s:property value='avatar'/></s:param></s:url>" style="width:100px; height: 100px"/>
							</td>
							<td>
							 	<a style="margin-right: 5px" href="editMember?username=<s:property value='id'/>">Sửa</a>
							  |	<a style="margin-left: 5px" href="javascipt:void(0)" data-toggle="modal" 
							  	   onclick="setDeleteCustomer('<s:property value="id"/>')" data-target="#myModal">Xóa</a>
							</td>
						</tr>
					</s:iterator>
				</table>
				
				<div class="paginator" style="margin: 20px 0 40px 0">
					<ul class="pagination">
					  <li><a href="#">«</a></li>
					  <li><a href="#">1</a></li>
					  <li><a class="active" href="#">2</a></li>
					  <li><a href="#">3</a></li>
					  <li><a href="#">...</a></li>
					  <li><a href="#">6</a></li>
					  <li><a href="#">7</a></li>
					  <li><a href="#">»</a></li>
					</ul>
				</div>
			</div>
		</div>
	</body>
</html>