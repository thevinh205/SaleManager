<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
	<head>
	    <title>Thêm mới sản phẩm</title>
	</head>
	<body>
		<s:include value="/WEB-INF/template/header/header.jsp"></s:include>
		<s:include value="/WEB-INF/template/product/left_menu.jsp"></s:include>
		<div class="divContent ">
			
			<form method="POST" action="addProductAction">
			
			   <div class="headerAddProduct">
			   		<p class="titleAddProduct">Thêm sản phẩm</p>
			   </div> 
			   
			   <div class="contentAddProduct" style="margin-left: 20px">
					<p style="color: red; display:none" align="center" id="errorAddProduct"><s:property value="errorMessage"/></p>
			   		<h3>Thông tin</h3>
			   		<ul class="formInfoProduct">
			   			<li>
			   				<span class="titleFr">Mã sản phẩm</span>
			   				<input type="text" name="productId" placeholder="Mã hàng tự động" class="txtFormAddProduct">
			   			</li>
			   			<li>
			   				<span class="titleFr">Tên sản phẩm</span>
			   				<input name="productName" type="text" class="txtFormAddProduct">
			   			</li>
			   			<li>
			   				<span class="titleFr">Nhóm hàng</span>
							<select class="btnDropDown" name="groupProduct">
								<s:iterator value="categoryList" status="category">
									  <option><s:property value="name"/></option>
								</s:iterator>
							</select>
						  	<a class="ax-browse-c ax-button" title="Thêm nhóm hàng" style="margin: 3px 0 0 5px; background: #5dba00;">
			      			<span class="ax-text">Thêm</span>
			      			</a>
			   			</li>
			   			<li>
			   				<span class="titleFr">Giá bán (VNĐ)</span>
			   				<input type="text" name="sellPrice" class="txtFormAddProduct" step="0.01" min="0"
			   				 onkeypress='return event.charCode >= 48 && event.charCode <= 57'/>
			   			</li>
			   			<li>
			   				<span class="titleFr">Giá vốn (VNĐ)</span>
			   				<input type="text" name="buyPrice" class="txtFormAddProduct" onkeypress='return event.charCode >= 48 && event.charCode <= 57'/>
			   			</li>
			   			<li>
			   				<span class="titleFr">Tồn kho</span>
			   				<input type="number" name="inventory" class="txtFormAddProduct"/>
			   			</li>
			   		</ul>

			   </div>
			   
			   
			   <div class="contentAddProduct">
			   		<h3 > 
			   			<a href="#" class=" ">
			          		<span class=" glyphicon-plus"></span> 
			          		Thông tin mở rộng
			        	</a>
			      	</h3>
			      	<div class="productContentExtend">
			      		<h3 style="margin-left:10px">Hình ảnh</h3>
			      		<a class="ax-browse-c ax-button" title="Add files" style="margin-left: 20px">
			      			<span class="ax-plus-icon ax-icon"></span> 
			      			<span class="ax-text">Add files</span>
			      			<input type="file" class="ax-browse" name="ax_file_input" multiple="" onchange="loadFile(event)">
			      		</a>
			      		
			      		<a class="ax-upload-all ax-button" title="Upload all files" onclick="uploadImage()">
			      			<span class="ax-upload-icon ax-icon"></span> 
			      			<span class="ax-text">Start upload</span>
			      		</a>
			      		
			      		<a class="ax-clear ax-button" title="Remove all" onclick="removeAllFilePreview()">
			      			<span class="ax-clear-icon ax-icon"></span> 
			      			<span class="ax-text">Remove all</span>
			      		</a>
			      		<div id="imgProductPreview" class="imgProductPreview">
			      		</div>
			      		<div style="margin: 0 0 10px 10px">
				      		<h3 >Mô tả</h3>
				      		<textarea name="descriptionPro" id="descriptionPro" style="width: 95%;height: 200px">
				            </textarea>
				            
				            <script type="text/javascript" src="http://js.nicedit.com/nicEdit-latest.js"></script> <script type="text/javascript">
							//<![CDATA[
							        bkLib.onDomLoaded(function() { nicEditors.allTextAreas() });
							  //]]>
							  </script>
			            </div>
			      	</div>
			      	
			   	</div>
			   <div id="actionFormProduct" style="margin: 20px 0 30px 70px; float: left;">
		      		<a class="ax-browse-c ax-button" title="Thêm mới" style="background: #5dba00;" href="javascript:void(0)" onclick="addNewProduct()">
		      			<span class="ax-text">Thêm mới</span>
		      		</a>
		      		<a class="ax-clear ax-button" title="Remove all">
		      			<span class="ax-text">Hủy bỏ</span>
		      		</a>
		      	</div>
			   
			</form>
		</div>
	</body>
</html>