<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
 
<html>
	<body>
		
		<div id="listCustomer">
			<div style="width: calc(100%); float: right; margin: 10px 0 10px 0; border-bottom: 1px solid #e4e0e0;">
				<p  style="font-size: 20px; font-weight: bold; float:left; margin-left: 20px">Danh sách nhân viên</p>
				<a style="float: right; margin-right: 20px" href="javascipt:void(0)" data-toggle="modal" data-target="#modalAddEmp">Thêm mới</a>
			</div>
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
				<s:iterator value="employeeList" status="memberRow" >
					<tr id="rowCus<s:property value='#memberRow.index'/>">
						<td><s:property value="name"/></td>
						<td><s:property value="email"/></td>
						<td><s:property value="phoneNumber"/></td>
						<td style="max-width: 300px"><s:property value="address"/></td>
						<td><s:property value="level"/></td>
						<td><s:property value="gender"/></td>
						<td><s:property value="position"/></td>
						<td>
						 	<a style="margin-right: 5px" href="/SaleManager/employee/editEmployee?username=<s:property value='userName'/>" target="_blank">Sửa</a>
						  |	<a style="margin-left: 5px" href="javascipt:void(0)" data-toggle="modal" 
						  	   onclick="setEmpDelete('<s:property value="userName"/>', '<s:property value="position"/>', 
						  	   		'<s:property value="name"/>', '<s:property value="#memberRow.index"/>')" data-target="#myModalDeleteEmp">Xóa</a>
						</td>
					</tr>
				</s:iterator>
			</table>
		</div>
		
		<!-- Modal -->
	  <div class="modal fade" id="myModalDeleteEmp" role="dialog">
	    <div class="modal-dialog modal-sm" style="width:350px">
	      <div class="modal-content">
	        <div class="modal-header">
	          <button type="button" class="close" data-dismiss="modal">&times;</button>
	          <h4 class="modal-title">Xóa nhân viên</h4>
	        </div>
	        <div class="modal-body">
	          <p>Tên nhân viên: <strong id="nameEmpDelete">Vinh</strong></p>
	          <p>Vị trí: <strong id="positionNameDelete"></strong></p>
	        </div>
	        <div class="modal-footer">
	          <a class="btn btn-default" data-dismiss="modal" href="javascrip:void(0)" onclick="deleteEmployeeOfShop()">Đồng ý</a>
	        </div>
	      </div>
	    </div>
	  </div>
	  
	  <!-- Modal add new -->
	  <div class="modal fade" id="modalAddEmp" role="dialog">
		    <div class="modal-dialog" style="width:380px">
		      <!-- Modal content-->
		      <div class="modal-content">
		        <div class="modal-header">
		          <button type="button" class="close" data-dismiss="modal">&times;</button>
		          <h4 class="modal-title">Thêm nhân viên cho shop</h4>
		        </div>
		        <div class="modal-body">
		        	<p style="color: red" align="center" id="msgAddEmp"></p>
		        	<table>
			        	<tr>
			        		<td>
			        			<strong>Nhân viên:</strong> 
			        		</td>
			        		<td>
			        			<select class="btnDropDown" name="empAdd">
									<s:iterator value="allEmployee" status="emp">
										  <option value="<s:property value='userName'/>"><s:property value="name"/></option>
									</s:iterator>
								</select>
			        		</td>
			        	</tr>
			        	<tr>
			        		<td>
			        			<strong>Vị trí:</strong> 
			        		</td>
			        		<td>
			        			<select class="btnDropDown" name="empPosition">
									<s:iterator value="listPositionEmployee" status="position">
										  <option ><s:property/></option>
									</s:iterator>
								</select>
			        		</td>
			        	</tr>
		        	</table>					
		        </div>
		        <div class="modal-footer">
		          <button id="btnCancel" type="button" class="btn btn-default" data-dismiss="modal" style="background:red; color:white">Hủy bỏ</button>
		          <button type="button" class="btn btn-default" style="background:green; color:white" onclick="addEmployeeToShop()">Thêm</button>
		        </div>
		      </div>
		      
		    </div>
		  </div>
	  
	</div>
	</body>
</html>