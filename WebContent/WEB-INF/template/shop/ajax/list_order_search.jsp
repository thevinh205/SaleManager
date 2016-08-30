<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<html>
	<body>
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
	  
	</body>
</html>