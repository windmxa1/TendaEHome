package org.view;

/**
 * VOrders entity. @author MyEclipse Persistence Tools
 */

public class VOrders implements java.io.Serializable {

	// Fields

	private VOrdersId id;

	// Constructors

	/** default constructor */
	public VOrders() {
	}

	/** full constructor */
	public VOrders(VOrdersId id) {
		this.id = id;
	}

	// Property accessors

	public VOrdersId getId() {
		return this.id;
	}

	public void setId(VOrdersId id) {
		this.id = id;
	}

}