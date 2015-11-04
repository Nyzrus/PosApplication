<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/login.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login Page</title>
</head>
<body>
<center>
	<h3>Airen Surzyn's Demo POS Application</h3>
	<form action="login" method="post" class="loginForm">
		Username: <input type="text" name="userId">
		<br>Password: <input type="password" name="password">
		<br><br><input type="submit" />
	</form>
	<form action="addUser.jsp" method="post">
		<br><input type="submit" value="Add New User"/>
	</form>
</center>
</body>
</html>