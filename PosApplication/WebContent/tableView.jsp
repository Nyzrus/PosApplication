<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link rel="stylesheet" type="text/css" href="css/tableView.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Table View</title>
</head>
<body>
	<form action="table" name="tableForm" method="post">
		<div id="floor">
			<div class="row1">
					<input type="submit" class="tableButton" name="table" value="Table 1"/>
				<input type="submit" class="tableButton" name="table" value="Table 2">
			</div>
			<div class="row2">
				<input type="submit" class="tableButton" name="table" value="Table 3">
				<input type="submit" class="tableButton" name="table" value="Table 4">
			</div>
		</div>
		<div id="optionsMenu">
			<input type="submit" class="menuButton" name="table" id="orderView" value="View All Orders"/>
			<input type="submit" class="menuButton" name="table" id="logOut" value="Log Out"/>
		</div>
	</form>
</body>
</html>