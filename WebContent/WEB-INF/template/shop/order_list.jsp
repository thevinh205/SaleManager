<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<html>
	<head>
		<script>
			$(document).ready(function() {
				$("input[id*='datepicker']").datepicker({
				    format: "dd/mm/yyyy"
				});
			});
		</script>
	</head>
	<body>
		<div>
			<div style="width: calc(100%); float: right; border-bottom: 1px solid #e4e0e0;">
				<p  style="font-size: 20px; font-weight: bold; float:left; margin-left: 20px">Danh sách đơn hàng</p>
				<s:if test="userUtil.member.role=='employee'">
					<a style="float: right; margin-right: 20px" href="javascipt:void(0)" data-toggle="modal" data-target="#modalCreateOrder">Thêm đơn hàng</a>
				</s:if>
			</div>
			
			 <div id=searchCustomer style="margin:10px 0 0 5px; width: calc(100% - 10px)" align="center">
				<form style="border-bottom: 1px solid #e4e0e0;">
					<table>
						<tr>
							<td>
								<strong>Mã đơn hàng:</strong> 
							</td>
							
							<td>
								<input type="text" name="idOrderSearch" value="<s:property value='idOrderSearch'/>" style="margin-right:10px"/>
							</td>
							
							<td>
								<strong>Tên khách hàng:</strong> 
							</td>
							
							<td>
								<input type="text" name="nameCusSearch" value="<s:property value='nameCusSearch'/>" style="margin-right:10px"/>
							</td>
							
							<td>
								<strong>Tên nhân nhân viên:</strong> 
							</td>
							
							<td>
								<input type="text" name="nameEmpSearch" value="<s:property value='nameEmpSearch'/>" style="margin-right:10px"/>
							</td>
						</tr>
						<tr>
							<td>
								<strong>Từ ngày:</strong>
							</td>
							<td>
								<input type="text" id="datepicker" name="startDate" placeholder="dd/mm/yyyy" style="text-align:center">
							</td>
							<td>
								<strong>Đến ngày:</strong>
							</td>
							<td>
								<input type="text" id="datepicker" name="endDate" placeholder="dd/mm/yyyy" style="text-align:center">
							</td>
							<td>
								<input type="submit" value="Tìm kiếm" class="btnStyle" onclick="searchOrder(); return false"/>
							</td>
						</tr>
					</table>
				</form>
				<div id="contentListOrder">
					<table class="tftable" border="1" style="text-align: center; width:95%">
						<tr >
							<th style="text-align: center">Mã đơn hàng</th>
							<th style="text-align: center">Tên khách hàng</th>
							<th style="text-align: center">Tên nhân viên</th>
							<th style="text-align: center">Ngày tạo</th>
							<th style="text-align: center">Tổng tiền</th>
							<th style="text-align: center">Nhà vận chuyển</th>
							<th style="text-align: center">Trạng thái</th>
							<th style="text-align: center"></th>
						</tr>
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
		</div>
		
		<!-- Modal delete -->
	  <div class="modal fade" id="myModal" role="dialog">
	    <div class="modal-dialog modal-sm">
	      <div class="modal-content">
	        <div class="modal-header">
	          <button type="button" class="close" data-dismiss="modal">&times;</button>
	          <h4 class="modal-title">Xóa đơn hàng</h4>
	        </div>
	        <div class="modal-body">
	          <p>Bạn có muốn xóa đơn hàng này</p>
	        </div>
	        <div class="modal-footer">
	          <a class="btn btn-default" data-dismiss="modal" href="javascrip:void(0)" onclick="deleteProduct()">Đồng ý</a>
	        </div>
	      </div>
	    </div>
	  </div>
	  
	  <!-- Modal create order -->
	  <div class="modal fade" id="modalCreateOrder" role="dialog">
		    <div class="modal-dialog" style="width:780px">
		      <!-- Modal content-->
		      <div class="modal-content">
		        <div class="modal-header">
		          <button type="button" class="close" data-dismiss="modal">&times;</button>
		          <h4 class="modal-title"><strong>Tạo đơn hàng</strong></h4>
		        </div>
		        <div class="modal-body">
		        	<p style="color: red; display:none" align="center" id="errorCreateOrder"></p>
		        	<table style="width:100%">
			        	<tr>
			        		<td>
			        			<strong>Tên khách hàng:</strong> 
			        		</td>
			        		<td>
			        			<input type="text" name="customerName" style="margin-right:15px; width: 250px"/>  <span style="color:red">(*)</span>
			        		</td>
			        	</tr>
			        	<tr>
			        		<td>
			        			<strong>Địa chỉ email:</strong> 
			        		</td>
			        		<td>
			        			<input type="text" name="cusEmail" style="margin-right:15px; width: 250px"/>  
			        		</td>
			        	</tr>
			        	<tr>
			        		<td>
			        			<strong>Số điện thoại:</strong> 
			        		</td>
			        		<td>
			        			<input type="text" name="cusPhoneNumber" style="margin-right:15px; width: 250px"/> <span style="color:red">(*)</span> 
			        		</td>
			        	</tr>
			        	<tr>
			        		<td>
			        			<strong>Địa chỉ giao hàng:</strong> 
			        		</td>
			        		<td>
			        			<textarea rows="4" cols="50" name="cusAddress"></textarea> <span style="color:red; position: absolute; margin: 20px 0 0 20px">(*)</span>
			        		</td>
			        	</tr>
			        	<tr>
			        		<td>
			        			<strong>Nhà vận chuyển:</strong> 
			        		</td>
			        		<td>
			        		
			        		<s:bean name="shopDetailController" var="shopDetail">
							</s:bean>
				        		<select class="btnDropDown" name="shipperId">
									<s:iterator value="#shopDetail.listShipment" status="shipment">
										  <option value="<s:property value='id'/>"><s:property value="name"/></option>
									</s:iterator>
								</select>
								<span style="color:red; margin-left:20px">(*)</span> 
			        		</td>
			        	</tr>
			        	<tr>
			        		<td>
			        			<strong>Danh sách sản phẩm:</strong> 
			        		</td>
			        		<td>
			        			<a href="javascript:void(0)"  data-toggle="modal" data-target="#modalAddProduct">Thêm</a>
			        		</td>
			        	</tr>
			        	</tr>
			        		<td colspan="2">
				        		<div id="popupTableListProdOrder">
				        			<table class="tftable" border="1" style="text-align: center; width:720px">
										<tr >
											<th style="text-align: center; width: 10%">Mã sản phẩm</th>
											<th style="text-align: center; width: 20%">Tên sản phẩm</th>
											<th style="text-align: center; width: 10%">Loại</th>
											<th style="text-align: center; width: 10%">Màu sắc</th>
											<th style="text-align: center; width: 10%">Kích thước</th>
											<th style="text-align: center; width: 10%">Số lượng</th>
											<th style="text-align: center; width: 10%">Hình ảnh</th>
											<th style="text-align: center; width: 15%">Giá thành</th>
											<th style="text-align: center; width: 5%"></th>
										</tr>
									</table>
								</div>
			        		</td>
			        	</tr>
			        	<tr>
			        		<td>
			        			<strong>Tổng tiền hàng:</strong> 
			        		</td>
			        		<td>
			        			<span id="productMoney">0</span> VNĐ
			        		</td>
			        	</tr>
			        	<tr>
			        		<td>
			        			<strong>Tiền vận chuyển:</strong> 
			        		</td>
			        		<td>
			        			<span id="feeShipment">0</span> VNĐ
			        		</td>
			        	</tr>
			        	<tr>
			        		<td>
			        			<strong>Tổng tiền:</strong> 
			        		</td>
			        		<td>
			        			<span id="totalMoney">0</span> VNĐ
			        		</td>
			        	</tr>
		        	</table>				
		        </div>
		        <div class="modal-footer">
		          <button id="btnCancel" type="button" class="btn btn-default" data-dismiss="modal" style="background:red; color:white">Hủy bỏ</button>
		          <button type="button" class="btn btn-default" style="background:green; color:white" onclick="createOrder()">Tạo</button>
		        </div>
		      </div>
		      
		    </div>
		  </div>
		  
		  
		  <!-- Modal add new product -->
	  	<div class="modal fade" id="modalAddProduct" role="dialog">
		    <div class="modal-dialog" style="width:880px">
		      <!-- Modal content-->
		      <div class="modal-content">
		        <div class="modal-header">
		          <button type="button" class="close" data-dismiss="modal">&times;</button>
		          <h4 class="modal-title">Thêm sản phẩm</h4>
		        </div>
		        <div class="modal-body">
		        	<p style="color: red" align="center" id="msgAddProd"></p>
		        	<table style="width:100%" id="listSearchProduct">
			        	<tr>
			        		<td>
			        			<strong>Mã sản phẩm:</strong> 
			        		</td>
			        		<td>
			        			<input type="text" name="idProdSearch" style="margin-right:15px"/> 	
			        		</td>
			        	</tr>
			        	<tr>
			        		<td>
			        			<strong>Tên sản phẩm:</strong> 
			        		</td>
			        		<td>
			        			<input type="text" name="nameProdSearch" style="margin-right:15px"/> 	
			        		</td>
			        	</tr>
			        	<tr>
			        		<td>
			        			<strong>Nhóm sản phẩm:</strong> 
			        		</td>
			        		<td>
			        			<select class="btnDropDown" name="groupProductSearch">
			        				<option value="">Tất cả</option>
									<s:iterator value="categoryList" status="category">
										  <option><s:property value="name"/></option>
									</s:iterator>
								</select>
			        		</td>
			        	</tr>
			        	<tr>
			        		<td></td>
			        		<td>
			        			<button type="button" class="btn btn-default" style="background:green; color:white" onclick="searchProductAdd()">Tìm kiếm</button>
			        		</td>
			        	</tr>
			        	<tr>
			        		<td colspan="2">
			        		<div id="idTableProdSearch">
			        			<table class="tftable" border="1" style="text-align: center; width:95%">
									<tr >
										<th style="text-align: center">Mã sản phẩm</th>
										<th style="text-align: center">Tên sản phẩm</th>
										<th style="text-align: center">Loại</th>
										<th style="text-align: center">Màu sắc</th>
										<th style="text-align: center">Kích thước</th>
										<th style="text-align: center">Số lượng</th>
										<th style="text-align: center">Hình ảnh</th>
										<th style="text-align: center">Giá thành</th>
										<th style="text-align: center"></th>
									</tr>
								</table>
								</div>
								
			        		</td>
			        	</tr>
		        	</table>					
		        </div>
		        <div class="modal-footer">
		          <button id="btnCancelAddProd" type="button" class="btn btn-default" data-dismiss="modal" style="background:red; color:white">Hủy bỏ</button>
		          <button type="button" class="btn btn-default" style="background:green; color:white" onclick="addProductToShop()">Thêm</button>
		        </div>
		      </div>
		      
		    </div>
		  </div>
	  
	</body>
</html>