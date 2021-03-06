<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html; charset=UTF-8" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>

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
					
					<input type="submit" value="Tìm kiếm" class="btnStyle" onclick="showIconLoading();"/>
				</form>
				
				<table class="tftable" border="1" style="text-align: center; width:95%">
					<tr >
						<th style="text-align: center">Mã sản phẩm</th>
						<th style="text-align: center">Tên sản phẩm</th>
						<th style="text-align: center">Nhóm sản phẩm</th>
						<th style="text-align: center">Giá mua</th>
						<th style="text-align: center">Giá bán</th>
						<th style="text-align: center">Ngày tạo</th>
						<th style="text-align: center">Hình ảnh</th>
						<th style="text-align: center"></th>
					</tr>
					<s:iterator value="productList" status="product">
						<tr id="rowPro<s:property value='id'/>">
							<td><a href="productDetail?productId=<s:property value='id'/>" target="_blank"><s:property value="id"/></a></td>
							<td><s:property value="productName"/></td>
							<td><s:property value="categoryName"/></td>
							<td>
								<fmt:formatNumber groupingUsed="true" value="${priceBuy}" /> VNĐ
							</td>
							<td>
								<fmt:formatNumber groupingUsed="true" value="${priceSell}" /> VNĐ
							</td>
							<td><s:date name="createDate" format="dd/MM/yyyy" /></td>
							<td>
								<img src="<s:url action='ImageAction'><s:param name='imageId'><s:property value='avatar'/></s:param></s:url>" style="width:100px; height: 100px"/>
							</td>
							<td>
							 	<a style="margin-right: 5px" href="editProduct?id=<s:property value='id'/>">Sửa</a>
							  |	<a style="margin-left: 5px" href="javascipt:void(0)" data-toggle="modal" 
							  	   onclick="setDeleteProduct('<s:property value="id"/>')" data-target="#myModal">Xóa</a>
							</td>
						</tr>
					</s:iterator>
				</table>
				
				<div class="paginator" style="margin: 20px 0 40px 0">
					<ul class="pagination">
						<li><a href="productList?page=1">&lt;&lt;</a></li>
						<s:iterator value="listPage" status="pageItr" var="pageVar">	
							<s:if test="#pageVar == '...' ">
								<li><a href="javascript:void(0)"><s:property/></a></li>
							</s:if>
						  	<s:else>
						  		<li>
						  			<s:if test="#pageVar == indexPage">
						  				<a href="productList?page=<s:property/>" class="active"><s:property/></a>
						  			</s:if>
							  		<s:else>
							  			<a href="productList?page=<s:property/>"><s:property/></a>
							  		</s:else>
						  		</li>
						  	</s:else>	
					  	</s:iterator>
					  	<li><a href="productList?page=<s:property value='totalPage'/>">&gt;&gt;</a></li>
					</ul>
				</div>
			</div>
		</div>
		
		<!-- Modal -->
	  <div class="modal fade" id="myModal" role="dialog">
	    <div class="modal-dialog modal-sm">
	      <div class="modal-content">
	        <div class="modal-header">
	          <button type="button" class="close" data-dismiss="modal">&times;</button>
	          <h4 class="modal-title">Xóa sản phẩm</h4>
	        </div>
	        <div class="modal-body">
	          <p>Bạn có muốn xóa sản phẩm này</p>
	        </div>
	        <div class="modal-footer">
	          <a class="btn btn-default" data-dismiss="modal" href="javascrip:void(0)" onclick="deleteProduct()">Đồng ý</a>
	        </div>
	      </div>
	    </div>
	  </div>
	  
	</body>
</html>