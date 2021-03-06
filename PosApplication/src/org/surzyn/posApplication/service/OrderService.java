package org.surzyn.posApplication.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.surzyn.posApplication.database.DatabaseAccess;
import org.surzyn.posApplication.dto.FoodDTO;
import org.surzyn.posApplication.dto.OrderDTO;

/*
 * The OrderService class is used as a buffer between the View and Database classes
 * to treat the data as necessary to make it fit for insertion or querying into the database.
 * 
 * In the case of getting orders/deleting orders, little action is required in this layer.
 * 
 * In the other case of adding information into the database, the input information
 * must be treated before being passed to the DatabaseAccess class
 * 
 */

public class OrderService {

DatabaseAccess db = new DatabaseAccess();
	
	/*
	 * addOrder calls the DatabaseAccess class to insert the orders into the DB
	 * - Parses the individual items from the TableOrderServlet by splitting on
	 * commas in the orderString
	 * - adds the items to a foodSet and assigns that set(embedded) to the OrderDTO
	 * object that will be added to the database
	 */
	public void addOrder(String orderString, int tableNumber){
		
		OrderDTO order = new OrderDTO();
		order.setServerName("airen");
		String[] orderList = orderString.split(",");
		Set<FoodDTO> foodSet = new HashSet<FoodDTO>();
		for(int i = 0; i < orderList.length;i++){
			foodSet.add(new FoodDTO(order, tableNumber, orderList[i]));
			
		}
		order.setFoodList(foodSet);
		order.setTable_id(tableNumber);
		db.addOrder(order);
	}
	
	public List<FoodDTO> getOrdersByTableNumber(int tableNumber){
		return db.getOrdersByTable(tableNumber);
	}
	
	public List<FoodDTO> getAllOrders(){
		return db.getAllOrders();
	}
	
	public void deleteAllFromTable(int tableNumber){
		db.deleteAllFromTable(tableNumber);
	}
	
	/*
	 * updateTable() has the same responsibility of parsing the data as addOrder, but
	 * calls db.updateTable() with an added parameter
	 */
	public void updateTable(String orderString, int tableNumber){
		OrderDTO order = new OrderDTO();
		order.setServerName("airen");
		String[] orderList = orderString.split(",");
		Set<FoodDTO> foodSet = new HashSet<FoodDTO>();
		for(int i = 0; i < orderList.length;i++){
			foodSet.add(new FoodDTO(order, tableNumber, orderList[i]));
		}
		order.setFoodList(foodSet);
		order.setTable_id(tableNumber);;
		db.updateTable(order, tableNumber);
	}
	
	
	
}
