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
			<div style="width: calc(100%); float: right;">
				<p  style="font-size: 20px; font-weight: bold; float:left; margin-left: 20px">Danh sách đơn hàng</p>
				<a style="float: right; margin-right: 20px" href="javascipt:void(0)" data-toggle="modal" data-target="#modalAddProduct">Thêm đơn hàng</a>
			</div>
			
			 <div id=searchCustomer style="margin:10px 0 0 5px; width: calc(100% - 10px)" align="center">
				<form >
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
								<input type="text" id="datepicker" name="startDate">
							</td>
							<td>
								<strong>Đến ngày:</strong>
							</td>
							<td>
								<input type="text" id="datepicker" name="endDate">
							</td>
							<td>
								<input type="button" value="Tìm kiếm" class="btnStyle" onclick="searchOrder()"/>
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
						<s:iterator value="listOrderHeader" status="order">
							<s:set var="varMbUsername" value="memberUsername" />
							<tr id="rowPro<s:property value='id'/>">
								<td><s:property value="id"/></td>
								<td>
									<s:bean name="sale.converter.MemberConverter" var="converter">
										<s:param name="memberUsername"><s:property value="customerUsername"/></s:param>
										<s:property value="memberName" />
									</s:bean>
								</td>
								<td>
									<s:bean name="sale.converter.MemberConverter" var="converter">
										<s:param name="memberUsername"><s:property value="employeeUsername"/></s:param>
										<s:property value="memberName" />
									</s:bean>
								</td>
								<td>
									<s:date name="createDate" format="dd/MM/yyyy" />						
								</td>
								<td>
									<fmt:formatNumber groupingUsed="true" value="${totalPrice}" /> VNĐ
								</td>
								<td>ShipChung</td>
								<td>
									<s:property value="status"/>	
								</td>
								<td>
								 	<a style="margin-right: 5px" href="editProduct?id=<s:property value='id'/>">Chi tiết</a>
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
		</div>
		
		<!-- Modal delete -->
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
			        			<input type="number" name="countProduct" style="margin-right:15px"/> 	
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