package org.surzyn.posApplication.dto;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="ORDER_TABLE")
public class OrderDTO {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int order_id;
	private int table_id;
	
	private String serverName;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="order_dto")
	private Set<FoodDTO> foodList = new HashSet<FoodDTO>();

	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	public int getTable_id() {
		return table_id;
	}

	public void setTable_id(int table_id) {
		this.table_id = table_id;
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public Set<FoodDTO> getFoodList() {
		return foodList;
	}

	public void setFoodList(Set<FoodDTO> foodList) {
		this.foodList = foodList;
	}


	
	
}
