<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<html>
	<body>
		<table class="tftable" border="1" style="text-align: center; width:720px; margin-top: 0px" id="tableProd<s:property value='productSelect.id'/>">
			<tr>
				<td style="text-align: center; width: 10%"><s:property value='productSelect.id'/></td>
				<td style="text-align: center; width: 20%"><s:property value='productSelect.productName'/></td>
				<td style="text-align: center; width: 10%"><s:property value='productSelect.categoryName'/></td>
				<td style="text-align: center; width: 10%"><s:property value='productSelect.color'/></td>
				<td style="text-align: center; width: 10%"><s:property value='productSelect.size'/></td>
				<td style="text-align: center; width: 10%">
					<span id="countProdShow<s:property value='productSelect.id'/>"><s:property value='countProduct'/></span></td>
				<td style="text-align: center; width: 10%">
					<img src="<s:url action='ImageAction'><s:param name='imageId'><s:property value='productSelect.avatar'/></s:param></s:url>" style="width:50px; height: 50px"/>
				</td>
				<td style="text-align: center; width: 15%">
					<span id="priceProdShow<s:property value='productSelect.id'/>"><fmt:formatNumber groupingUsed="true" value="${productSelect.priceSell}" /></span>  VNĐ
				</td>
				<td style="text-align: center; width: 5%">
					<a href="javascript:void(0)" onclick="deleteProdOrder('<s:property value="productSelect.id"/>')">Xóa</a>
				</td>
			</tr>
		</table>
	</body>
</html>