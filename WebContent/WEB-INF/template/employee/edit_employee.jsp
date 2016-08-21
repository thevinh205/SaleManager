<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<html>
	<head>
	    <title>Chỉnh sửa thông tin nhân viên</title>
	</head>
	<body>
		<s:include value="/WEB-INF/template/header/header.jsp"></s:include>
		<s:include value="/WEB-INF/template/cs/left_menu.jsp"></s:include>
		<div class="divContent ">
			<s:if test="errorMessage != null">
				<p style="color: red" align="center"><s:property value="errorMessage"/></p>
			</s:if>
			<form method="POST" action="editEmployeeAction">
			   <table align="center" width="600" height="500" border="2" cellpadding="5" style="margin-top:10px">
				  <tr >
				   	<th colspan="2" style="text-align: center"><h2>Chỉnh sửa thông tin nhân viên</h2></td>
				  </tr>
				   <tr>
				       <td><label>Họ tên</label></td>
				       <td><input type="text" name="fullName" size="40" placeholder="Nhập họ tên" value="<s:property value='fullName'/>"/></td>
				   </tr>
				   <tr>
				       <td><label>Password</label></td>
				       <td><input type="password" name="pass" size="40" placeholder="Nhập mật khẩu"/></td>
				   </tr>
				   <tr>
				       <td><label>Nhập lại password</label></td>
				       <td><input type="password" name="rePass" size="40" placeholder="Nhập lại mật khẩu"/></td>
				   </tr>
				   <tr>
				       <td><label>Giới tính</label></td>
				       <td>	
				       		<label style="margin-right: 30px">
				       			<input type="radio" name="gender" id="Nam" value="Nam" checked="<s:if test='gender == "Name"'>checked</s:if>" style="margin-right: 10px"/>Nam
				       		</label>
				       		<label>
				       			<input type="radio" name="gender" id="Nữ" value="Nữ" checked="<s:if test='gender == "Nữ"'>checked</s:if>" style="margin-right: 10px"/>Nữ
				       		</label>
				       </td>
				   </tr>
				   <tr>
				       <td><label>Email</label></td>
				       <td><input type="text" name="email" size="40" placeholder="Nhập email" value="<s:property value='email'/>"/></td>
				   </tr>
				   <tr>
				       <td><label>Số điện thoại</label></td>
				       <td><input type="text" name="phoneNumber" size="40" placeholder="Nhập số điện thoại" value="<s:property value='phoneNumber'/>"/></td>
				   </tr>
				    <tr>
				       <td><label>Địa chỉ</label></td>
				       <td><textarea name="address" rows="3" cols="40"><s:property value='address'/></textarea></td>
				   </tr>
				   <tr>
				       <td><label>Ngày sinh</label></td>
				       <td><input type="date" name="birthdays" value="<s:date name='birthdays' format='yyyy-MM-dd' />"/></td>
				   </tr>
				   <tr>
				       <td colspan="2" align="center">
				       		<input type="reset" class="btnStyle" value="Hủy bỏ" onclick="location.href='customer_list'"></a>
				           <input type="submit" value="Chỉnh sửa" class="btnStyle"/>
				       </td>
				   </tr>
				</table>  
			</form>
		</div>
	</body>
</html>