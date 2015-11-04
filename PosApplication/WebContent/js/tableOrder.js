
var itemArray = [];

function calculatePrice(){
	var totalPrice = 0;
	var tempPrice = 0;
	document.getElementById('priceLabel').innerHTML = 5;
	var i;
	var menuVar = document.getElementById('orderSelectMenu');
	for(i=0; i < menuVar.length;i++){
		switch(menuVar.options[i].text) {
		case "Pizza":
		    tempPrice = 15;
		    break;
		case "Burger":
		    tempPrice = 8;
		    break;
		case "Fries":
		    tempPrice = 3;
		    break;
		case "Beer":
		    tempPrice = 5;
		    break;
		case "Soda":
		    tempPrice = 2;
		    break;
		default:
		    tempPrice = 0;
			break;
		}
		totalPrice = tempPrice + totalPrice;
	}
	document.getElementById('priceLabel').innerHTML = totalPrice;
}



function updateOrderList(itemName){
		itemArray.push(itemName);
		
		var menuVar = document.getElementById('orderSelectMenu');
		var tempOption = document.createElement('option');
		tempOption.appendChild(document.createTextNode(itemName));
		tempOption.value=itemName;
		menuVar.appendChild(tempOption);
		calculatePrice();
	}

function submitOrder(){
	var formObject = document.forms["orderButtonForm"];
	formObject.elements["hiddenJSValue"].value = itemArray;
	formObject.elements["hiddenReturnValue"].value="add";
	document.forms["orderButtonForm"].submit();
}

function deleteItem(){
	var menuVar = document.getElementById('orderSelectMenu');
	if(menuVar.selectedIndex == -1){
		document.getElementById("warningLabel").innerHTML = "Must select an item from the List";
		return;
	}
	menuVar.remove(menuVar.selectedIndex);
	document.getElementById("warningLabel").innerHTML = "";
	calculatePrice();
}

function deleteAllItems(){
	var menuVar = document.getElementById('orderSelectMenu');
	var i;
	for(i=menuVar.options.length-1; i>=0;i--){
		menuVar.remove(i);
	}
	var formObject = document.forms["orderButtonForm"];
	formObject.elements["hiddenJSValue"].value = "";
	formObject.elements["hiddenReturnValue"].value="clear";
	document.forms["orderButtonForm"].submit();
}

function commitChanges(){
	var menuVar = document.getElementById('orderSelectMenu');
	var i;
	itemArray = [];
	for(i=menuVar.options.length-1; i>=0;i--){
		itemArray.push(menuVar.options[i].text);
	}
	var formObject = document.forms["orderButtonForm"];
	formObject.elements["hiddenJSValue"].value = itemArray;
	formObject.elements["hiddenReturnValue"].value="update";
	document.forms["orderButtonForm"].submit();
	
}
