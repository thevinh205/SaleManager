<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
 
<html>
	<body>
		
		<div id="listCustomer">
			<p align="center" style="font-size: 20px; font-weight: bold;">Danh sách nhân viên</p>
			<table class="tftable" border="1" style="text-align: center">
				<tr >
					<th style="text-align: center">Tên</th>
					
					<th style="text-align: center">Email</th>
					<th style="text-align: center">Số điện thoại</th>
					<th style="text-align: center">Địa chỉ</th>
					<th style="text-align: center">Level</th>
					<th style="text-align: center">Giới tính</th>
					<th style="text-align: center">Vị trí</th>
					<th style="text-align: center"></th>
				</tr>
				<s:iterator value="memberList" status="memberRow">
					<tr id="rowCus<s:property value='userName'/>">
						<td><s:property value="name"/></td>
						<td><s:property value="email"/></td>
						<td><s:property value="phoneNumber"/></td>
						<td style="max-width: 300px"><s:property value="address"/></td>
						<td><s:property value="level"/></td>
						<td><s:property value="gender"/></td>
						<td><s:property value="position"/></td>
						<td>
						 	<a style="margin-right: 5px" href="editMember?username=<s:property value='userName'/>">Sửa</a>
						  |	<a style="margin-left: 5px" href="javascipt:void(0)" data-toggle="modal" 
						  	   onclick="setDeleteCustomer('<s:property value="userName"/>')" data-target="#myModal">Xóa</a>
						</td>
					</tr>
				</s:iterator>
			</table>
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