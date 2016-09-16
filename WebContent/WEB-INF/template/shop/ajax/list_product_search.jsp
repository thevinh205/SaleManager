<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<html>
	<body>
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
			<s:iterator value="listProductSearch" status="product">
				<tr id="rowPro<s:property value='id'/>">
					<td><s:property value="id"/></td>
					<td>
						<s:property value="productName"/>
					</td>
					<td>
						<s:property value="categoryName"/>
					</td>
					<td>
						<s:property value="Màu sắc"/>					
					</td>
					<td>
						<s:property value="size"/>	
					</td>
					<td>
						<input type="number" id="countProduct<s:property value='id'/>" style="height: 25px" value="1"/>
					</td>
					<td>
						<img src="<s:url action='ImageAction'><s:param name='imageId'><s:property value='avatar'/></s:param></s:url>" style="width:50px; height: 50px"/>
					</td>
					<td>
						<span id="priceProductSelect<s:property value="id"/>"><fmt:formatNumber groupingUsed="true" value="${priceSell}" /></span> VNĐ
					</td>
					<td>
					 	<a style="margin-right: 5px" onclick="addProductOrder('<s:property value="id"/>')" href="javascript:void(0))">Chọn</a>
					</td>
				</tr>
			</s:iterator>
		</table>
		
		<div class="paginator" style="margin: 20px 0 40px 0; text-align: center;">
			<ul class="pagination">
				<li><a  href="javascript:void(0)" onclick="selectPageProductAdd('1')">&lt;&lt;</a></li>
				<s:iterator value="listPageProd" status="pageItr" var="pageVar">	
					<s:if test="#pageVar == '...' ">
						<li><a href="javascript:void(0)"><s:property/></a></li>
					</s:if>
				  	<s:else>
				  		<li>
				  			<s:if test="#pageVar == indexPage">
				  				<a onclick="selectPageProductAdd(<s:property/>)" href="javascript:void(0)" class="active"><s:property/></a>
				  			</s:if>
					  		<s:else>
					  			<a onclick="selectPageProductAdd(<s:property/>)" href="javascript:void(0)" ><s:property/></a>
					  		</s:else>
				  		</li>
				  	</s:else>	
			  	</s:iterator>
			  	<li><a onclick="selectPageProductAdd(<s:property value='totalPage'/>)" href="javascript:void(0)">&gt;&gt;</a></li>
			</ul>
		</div>
	  
	  <p name="pageCurrent" style="display:none"><s:property value='indexPage'/></p>
	</body>
</html>