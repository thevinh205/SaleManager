<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>

<html>
	<head>
	    <title>Danh mục sản phẩm</title>
	</head>
	<body>
		<s:include value="/WEB-INF/template/header/header.jsp"></s:include>
		<s:include value="/WEB-INF/template/product/left_menu.jsp"></s:include>
		<div class="divContent ">
			 <div class="headerAddProduct">
			   		<p class="titleAddProduct">Danh mục sản phẩm</p>
			   		<a class="linkAddCategory" href="javascript:void(0)" data-toggle="modal" data-target="#modalAddCategory">Thêm mới</a>
			 </div> 
			 <div id=searchCustomer style="margin:10px 0 0 10px; width: calc(100% - 10px)" align="center">				
				<table class="tftable" border="1" style="text-align: center; width:95%">
					<tr >
						<th style="text-align: center">Mã danh mục</th>
						<th style="text-align: center">Tên danh mục</th>
						<th style="text-align: center">Mô tả</th>
						<th style="text-align: center">Trạng thái</th>
						<th style="text-align: center">Ngày tạo</th>
						<th style="text-align: center"></th>
					</tr>
					<s:iterator value="categoryProducts" status="category">
						<tr id="rowCategory<s:property value='id'/>">
							<td><s:property value="id"/></td>
							<td>
								<p id="name<s:property value='id'/>"><s:property value="name"/></p>
								<input type="text" size="35" id="inputName<s:property value='id'/>" value="<s:property value='name'/>" style="display:none"/>
							</td>
							<td>
								<p id="des<s:property value='id'/>" ><s:property value="description"/></p>
								<textarea rows="5" cols="45" id="description<s:property value='id'/>" style="display:none"><s:property value='description'/></textarea>
							</td>
							<td>
								<p id="status<s:property value='id'/>"><s:property value="status"/></p>
								<select class="btnDropDown" id="status<s:property value='id'/>" style="display:none; width:100px">
									<option>open</option>
									<option>close</option>
								</select>
							</td>
							<td><s:date name="createDate" format="dd/MM/yyyy" /></td>
							<td>
								<div id="action<s:property value='id'/>" >
								 	<a style="margin-right: 5px" href="javascript:void(0)" onclick="showEditCategory('<s:property value="id"/>')">Sửa</a>
								  |	<a style="margin-left: 5px" href="javascipt:void(0)" data-toggle="modal" 
								  	   onclick="setDeleteCategory('<s:property value="id"/>')" data-target="#myModal">Xóa</a>
							  	 </div>
							  	 <div id="editAction<s:property value='id'/>" style="display:none">
							  	 	<a style="margin-right: 5px" href="javascrip:void(0)" onclick="editCategoryAction('<s:property value="id"/>')">Xong</a>
							  	 </div>
							</td>
						</tr>
					</s:iterator>
				</table>
			</div>
		</div>
		
		<!-- Modal delete -->
	  <div class="modal fade" id="myModal" role="dialog">
	    <div class="modal-dialog modal-sm">
	      <div class="modal-content">
	        <div class="modal-header">
	          <button type="button" class="close" data-dismiss="modal">&times;</button>
	          <h4 class="modal-title">Xóa Danh Mục</h4>
	        </div>
	        <div class="modal-body">
	          <p>Bạn có muốn xóa danh mục này</p>
	        </div>
	        <div class="modal-footer">
	          <a class="btn btn-default" data-dismiss="modal" href="javascrip:void(0)" onclick="deleteCategory()">Đồng ý</a>
	        </div>
	      </div>
	    </div>
	  </div>
	  
	  <!-- Modal add category -->
	   <!-- Modal -->
		  <div class="modal fade" id="modalAddCategory" role="dialog">
		    <div class="modal-dialog">
		    
		      <!-- Modal content-->
		      <div class="modal-content">
		        <div class="modal-header">
		          <button type="button" class="close" data-dismiss="modal">&times;</button>
		          <h4 class="modal-title">Thêm mới danh mục</h4>
		        </div>
		        <div class="modal-body">
		          <table align="center" width="450" height="150" border="2" cellpadding="5" style="margin-top:10px">
					   <tr>
					       <td><label style="width:120px">Tên danh mục</label></td>
					       <td><input type="text" name="categoryName" size="40" placeholder="Nhập tên danh mục" value="<s:property value='categoryName'/>"/></td>
					   </tr>
					   <tr>
					       <td><label>Mô tả</label></td>
					       <td>
					       	<textarea rows="5" cols="42" name="description"><s:property value='description'/></textarea>
					       </td>
					   </tr>
					   <tr>
					       <td><label>Trạng thái</label></td>
					       <td>
					       		<select class="btnDropDown" name="groupStatus" >
									<option>open</option>
									<option>close</option>
								</select>
							</td>
					   </tr>

				</table>
		        </div>
		        <div class="modal-footer">
		          <button type="button" class="btn btn-default" data-dismiss="modal" style="background:red; color:white">Hủy bỏ</button>
		          <button type="button" class="btn btn-default" style="background:green; color:white" onclick="addCategory()">Đồng ý</button>
		        </div>
		      </div>
		      
		    </div>
		  </div>
	</body>
</html>