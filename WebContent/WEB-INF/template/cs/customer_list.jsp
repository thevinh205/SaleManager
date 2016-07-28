<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
 
<html>
	<head>
		<title>Danh sách khách hàng</title>
	</head>
	<body>
		<s:include value="/WEB-INF/template/header/header.jsp"></s:include>
		<s:include value="/WEB-INF/template/cs/left_menu.jsp"></s:include>
		
		<div class="divContent ">
			<div id=searchCustomer style="margin:10px 0 0 10px" align="center">
				<s:if test="errorMessage != null">
					<p style="color: red" align="center"><s:property value="errorMessage"/></p>
				</s:if>
				<form method="POST" action="searchCustomer">
					<strong>Tên:</strong> 
					<input type="text" name="nameSearch" value="<s:property value='nameSearch'/>" style="margin-right:15px"/>
					
					<strong>Email:</strong> 
					<input type="text" name="emailSearch" value="<s:property value='emailSearch'/>" style="margin-right:15px"/>
					
					<strong>Số điện thoại:</strong> 
					<input type="text" name="phoneNumberSearch" value="<s:property value='phoneNumberSearch'/>" style="margin-right:15px"/>
					<input type="submit" value="Tìm kiếm" class="btnStyle"/>
				</form>
			</div>
			<div id="listCustomer">
				<p align="center" style="font-size: 20px; font-weight: bold;">Danh sách khách hàng</p>
				<table class="tftable" border="1" style="text-align: center">
					<tr >
						<th style="text-align: center">Tên</th>
						<th style="text-align: center">Username</th>
						<th style="text-align: center">Email</th>
						<th style="text-align: center">Số điện thoại</th>
						<th style="text-align: center">Địa chỉ</th>
						<th style="text-align: center">Level</th>
						<th style="text-align: center">Giới tính</th>
						<th style="text-align: center"></th>
					</tr>
					<s:iterator value="memberList" status="memberRow">
						<tr id="rowCus<s:property value='userName'/>">
							<td><s:property value="name"/></td>
							<td><s:property value="userName"/></td>
							<td><s:property value="email"/></td>
							<td><s:property value="phoneNumber"/></td>
							<td style="max-width: 300px"><s:property value="address"/></td>
							<td><s:property value="level"/></td>
							<td><s:property value="gender"/></td>
							<td>
							 	<a style="margin-right: 5px" href="editMember?username=<s:property value='userName'/>">Sửa</a>
							  |	<a style="margin-left: 5px" href="javascipt:void(0)" data-toggle="modal" 
							  	   onclick="setDeleteCustomer('<s:property value="userName"/>')" data-target="#myModal">Xóa</a>
							</td>
						</tr>
					</s:iterator>
				</table>
			</div>
		</div>
		
		<!-- Modal -->
	  <div class="modal fade" id="myModal" role="dialog">
	    <div class="modal-dialog modal-sm">
	      <div class="modal-content">
	        <div class="modal-header">
	          <button type="button" class="close" data-dismiss="modal">&times;</button>
	          <h4 class="modal-title">Xóa khánh hàng</h4>
	        </div>
	        <div class="modal-body">
	          <p>Bạn có muốn xóa khách hàng này</p>
	        </div>
	        <div class="modal-footer">
	          <a class="btn btn-default" data-dismiss="modal" href="javascrip:void(0)" onclick="deleteCustomer()">Đồng ý</a>
	        </div>
	      </div>
	    </div>
	  </div>
	</div>
	</body>
</html>