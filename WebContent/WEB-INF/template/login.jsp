<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="<s:url value='/resources/css/main.css' />">
</head>
<body>

<s:if test="errorMessage != null">
	<p style="color: red" align="center"><s:property value="errorMessage"/></p>
</s:if>
	
<form method="POST" action="loginAction">
   <table align="center">
	  <tr>
	   	<td colspan="2" align="center"><h2>Login</h2></td>
	  </tr>
	   <tr>
	       <td><label>Username</label></td>
	       <td><input type="text" name="userName" value="<s:property value="userName"/>"/></td>
	   </tr>
	   <tr>
	       <td><label>Password</label></td>
	       <td><input type="password" name="password" /></td>
	   </tr>
	   <tr>
	       <td colspan="2" align="center">
	           <input type="submit" value="Login" class="btnStyle"/>
	       </td>
	   </tr>
	</table>  
</form>
</body>
</html>