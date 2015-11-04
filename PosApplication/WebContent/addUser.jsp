<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add new User</title>
</head>
<body>
<center>
	<h3>Add New User</h3>
	<form name="employeeForm" action="add" method="post">
		Username: <input type="text" name="username">
		<br>First Name: <input type="text" name="firstName">
		<br>Last Name: <input type="text" name="lastName">
		<br>Password: <input type="text" name="password">
		<br><br>
		<input type="submit">
	</form>
</center>
</body>
</html>