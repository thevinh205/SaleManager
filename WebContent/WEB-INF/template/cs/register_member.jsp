<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<html>
	<head>
	    <title>Đăng ký thành viên</title>
	</head>
	<body>
		<s:include value="/WEB-INF/template/header/header.jsp"></s:include>
		<s:include value="/WEB-INF/template/cs/left_menu.jsp"></s:include>
		<div class="divContent ">
			<s:if test="errorMessage != null">
				<p style="color: red" align="center"><s:property value="errorMessage"/></p>
			</s:if>
			<form method="POST" action="registerMemberAction">
			   <table align="center" width="600" height="500" border="2" cellpadding="5" style="margin-top:10px">
				  <tr >
				   	<th colspan="2" style="text-align: center"><h2>Đăng ký thành viên</h2></td>
				  </tr>
				   <tr>
				       <td><label>Họ tên</label></td>
				       <td><input type="text" name="fullName" size="40" placeholder="Nhập họ tên" value="<s:property value='fullName'/>"/></td>
				   </tr>
				   <tr>
				       <td><label>Username</label></td>
				       <td><input type="text" name="userName" size="40" placeholder="Nhập username" value="<s:property value='userName'/>"/></td>
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
				       		<label style="margin-right: 30px"><input type="radio" name="gender" id="Nam" value="Nam" style="margin-right: 10px"/>Nam</label>
				       		<label><input type="radio" name="gender" id="Nữ" value="Nữ" style="margin-right: 10px"/>Nữ</label>
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
				       <td><input type="date" name="birthdays"/></td>
				   </tr>
				   <tr>
				       <td colspan="2" align="center">
				       		<input type="reset" value="Làm lại" class="btnStyle"/>
				           <input type="submit" value="Đăng ký" class="btnStyle"/>
				       </td>
				   </tr>
				</table>  
			</form>
		</div>
	</body>
</html>