<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html; charset=UTF-8" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>

<html>
	<body>
		<div>
			<div style="width: calc(100%); float: right; border-bottom: 1px solid #e4e0e0;">
				<p  style="font-size: 20px; font-weight: bold; float:left; margin-left: 20px">Danh sách sản phẩm</p>
				<a style="float: right; margin-right: 20px" href="javascipt:void(0)" data-toggle="modal" data-target="#modalAddProduct">Thêm mới</a>
			</div>
			 <div id=searchCustomer style="margin:10px 0 0 5px; width: calc(100% - 10px)" align="center">
				<form method="POST" action="searchProduct">
					<strong>Mã sản phẩm:</strong> 
					<input type="text" name="idProdSearch" value="<s:property value='idProdSearch'/>" style="margin-right:10px"/>
					
					<strong>Tên sản phẩm:</strong> 
					<input type="text" name="nameProdSearch" value="<s:property value='nameProdSearch'/>" style="margin-right:10px"/>
					
					<strong>Nhóm sản phẩm:</strong> 
					<select class="btnDropDown" name="groupProduct">
						<s:iterator value="categoryList" status="category">
							  <option><s:property value="name"/></option>
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
						<th style="text-align: center">Số lượng</th>
						<th style="text-align: center">Hình ảnh</th>
						<th style="text-align: center"></th>
					</tr>
					<s:iterator value="productList" status="product">
						<tr id="rowPro<s:property value='id'/>">
							<td><a href="/SaleManager/product/productDetail?productId=<s:property value='id'/>" target="_blank"><s:property value="id"/></a></td>
							<td><s:property value="productName"/></td>
							<td><s:property value="categoryName"/></td>
							<td>
								<fmt:formatNumber groupingUsed="true" value="${priceBuy}" />  VNĐ							
							</td>
							<td>
								<fmt:formatNumber groupingUsed="true" value="${priceSell}" /> VNĐ
							</td>
							<td><s:property value="count"/></td>
							<td>
								<img src="<s:url action='ImageAction'><s:param name='imageId'><s:property value='avatar'/></s:param></s:url>" style="width:100px; height: 100px"/>
							</td>
							<td>
							 	<a style="margin-right: 5px" href="/SaleManager/product/editProduct?id=<s:property value='id'/>">Sửa</a>
							  |	<a style="margin-left: 5px" href="javascipt:void(0)" data-toggle="modal" 
							  	   onclick="setDeleteProdShop('<s:property value="id"/>', '<s:property value="productName"/>')" data-target="#myModal">Xóa</a>
							</td>
						</tr>
					</s:iterator>
				</table>
				
				<div class="paginator" style="margin: 20px 0 40px 0">
					<ul class="pagination">
						<li><a href="shopDetail?page=1">&lt;&lt;</a></li>
						<s:iterator value="listPageProd" status="pageItr" var="pageVar">	
							<s:if test="#pageVar == '...' ">
								<li><a href="javascript:void(0)"><s:property/></a></li>
							</s:if>
						  	<s:else>
						  		<li>
						  			<s:if test="#pageVar == indexPage">
						  				<a href="shopDetail?page=<s:property/>" class="active"><s:property/></a>
						  			</s:if>
							  		<s:else>
							  			<a href="shopDetail?page=<s:property/>"><s:property/></a>
							  		</s:else>
						  		</li>
						  	</s:else>	
					  	</s:iterator>
					  	<li><a href="shopDetail?page=<s:property value='totalPage'/>">&gt;&gt;</a></li>
					</ul>
				</div>
			</div>
		</div>
		
		<!-- Modal delete -->
	  <div class="modal fade" id="myModal" role="dialog">
	    <div class="modal-dialog modal-sm" style="width:400px">
	      <div class="modal-content">
	        <div class="modal-header">
	          <button type="button" class="close" data-dismiss="modal">&times;</button>
	          <h4 class="modal-title">Xóa sản phẩm</h4>
	        </div>
	        <div class="modal-body">
	          <p>Bạn có muốn xóa sản phẩm <strong id="prodNameDelete"></strong></p>
	        </div>
	        <div class="modal-footer">
	          <a class="btn btn-default" data-dismiss="modal" href="javascrip:void(0)" onclick="deleteProductInShop()">Đồng ý</a>
	        </div>
	      </div>
	    </div>
	  </div>
	  
	  <!-- Modal add new -->
	  <div class="modal fade" id="modalAddProduct" role="dialog">
		    <div class="modal-dialog" style="width:380px">
		      <!-- Modal content-->
		      <div class="modal-content">
		        <div class="modal-header">
		          <button type="button" class="close" data-dismiss="modal">&times;</button>
		          <h4 class="modal-title">Thêm sản phẩm tới shop</h4>
		        </div>
		        <div class="modal-body">
		        	<p style="color: red" align="center" id="msgAddProd"></p>
		        	<table>
			        	<tr>
			        		<td>
			        			<strong>Mã sản phẩm:</strong> 
			        		</td>
			        		<td>
			        			<input type="text" name="idProdAdd" style="margin-right:15px"/> 	
			        		</td>
			        	</tr>
			        	<tr>
			        		<td>
			        			<strong>Số lượng:</strong> 
			        		</td>
			        		<td>
			        			<input type="number" name="countProduct" style="margin-right:15px; height:30px"/> 	
			        		</td>
			        	</tr>
		        	</table>					
		        </div>
		        <div class="modal-footer">
		          <button id="btnCancel" type="button" class="btn btn-default" data-dismiss="modal" style="background:red; color:white">Hủy bỏ</button>
		          <button type="button" class="btn btn-default" style="background:green; color:white" onclick="addProductToShop()">Thêm</button>
		        </div>
		      </div>
		      
		    </div>
		  </div>
	  
	</body>
</html>