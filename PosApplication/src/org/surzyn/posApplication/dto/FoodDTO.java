package org.surzyn.posApplication.dto;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="FOOD_ITEMS")
public class FoodDTO {

	@Id @GeneratedValue
	private int food_id;
	private int tableNumber;
	private String foodName;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="order_dto")
	private OrderDTO order_dto;

	
	public FoodDTO(){
		
	}
	
	public FoodDTO(OrderDTO order, int tableNumber, String foodName){
		this.order_dto = order;
		this.tableNumber = tableNumber;
		this.foodName = foodName;
	}
	
	public int getFood_id() {
		return food_id;
	}

	public void setFood_id(int food_id) {
		this.food_id = food_id;
	}

	public OrderDTO getOrder_dto() {
		return order_dto;
	}

	public void setOrder_dto(OrderDTO order_dto) {
		this.order_dto = order_dto;
	}

	public String getFoodName() {
		return foodName;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

	public int getTableNumber() {
		return tableNumber;
	}

	public void setTableNumber(int tableNumber) {
		this.tableNumber = tableNumber;
	}


	
	
}
