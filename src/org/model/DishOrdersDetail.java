package org.model;

/**
 * DishOrdersDetail entity. @author MyEclipse Persistence Tools
 */

public class DishOrdersDetail implements java.io.Serializable {

	// Fields

	private Long id;
	private Long orderId;
	private Long dishId;
	private Integer num;

	// Constructors

	/** default constructor */
	public DishOrdersDetail() {
	}

	/** full constructor */
	public DishOrdersDetail(Long orderId, Long dishId, Integer num) {
		this.orderId = orderId;
		this.dishId = dishId;
		this.num = num;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOrderId() {
		return this.orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getDishId() {
		return this.dishId;
	}

	public void setDishId(Long dishId) {
		this.dishId = dishId;
	}

	public Integer getNum() {
		return this.num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

}