<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%!
					public String optionCreator(String s){
						String[] orderArray = s.split(",");
						String optionString = "";
						for(int i = 0; i< orderArray.length;i++){
							optionString += "<option>"+orderArray[i]+"</option>";
						}
						return optionString;
				}
					
%>

<html>
<head>
	<link rel="stylesheet" type="text/css" href="css/tableOrder.css">
	<script type="text/javascript" src="js/tableOrder.js"></script>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script>
	window.onload = calculatePrice;
	</script>
	<title>Table Order</title>
</head>
<body>
	<div class="leftDiv">
		<div id="tableIdentifier">
			<%String tNumber = (String) session.getAttribute("tableNumber");%>
			<b>Table <%=tNumber %></b>
		</div>
		<div id="tableOrders">
			<select name="orderSelectMenu" id="orderSelectMenu" MULTIPLE style="height:100%; width: 30%; font-size: 15px;">
				<%
				String order = (String) session.getAttribute("tableSpecificOrder");
				String tester = optionCreator(order); 
				%>
				<%= tester %>
			</select>
			
		</div>
		
		<div id="orderMetaInfo">
			Total: $<label id="priceLabel"></label>
			
		</div>
	</div>
	<div class="rightDiv">
		<div id="orderOptions">
			<form name="orderButtonForm" action="tableOrder" method="post">
				<input type="button" class="itemButton" id="foodItem" name="foodItem" value="Pizza" onClick="updateOrderList('Pizza')">
				<input type="button" class="itemButton" id="foodItem" name="foodItem" value="Burger" onClick="updateOrderList('Burger')">
				<input type="button" class="itemButton" id="foodItem" name="foodItem" value="Beer" onClick="updateOrderList('Beer')">
				<input type="button" class="itemButton" id="foodItem" name="foodItem" value="Soda" onClick="updateOrderList('Soda')">
				<input type="button" class="itemButton" id="foodItem" name="foodItem" value="Fries" onClick="updateOrderList('Fries')">
				<input type=hidden name="hiddenJSValue" id="hiddenJSValue" value="nope"/>
				<input type=hidden name="hiddenTableValue" id="hiddenTableValue" value='<%=tNumber %>'/>
				<input type=hidden name="hiddenReturnValue" id="hiddenReturnValue" value="n"/>
				 
			</form>
		</div>
		<div id="orderSubmit">
			<form name="submitButtonForm" action="tableOrder" method="post">
				<input type="button" class="submitButton" value="Submit Order" onClick="submitOrder()">
				<input type="button" class="submitButton" value="Delete Item" onClick="deleteItem()">
				<br>
				<input type="button" class="submitButton" name="commitButton" value="Clear Order" onClick="deleteAllItems()">
				<input type="button" class="submitButton" name="commitButton" value="Save Changes" onClick="commitChanges()">
				<br><label id="warningLabel"></label>
			</form>
		</div>
	</div>
</body>
</html>