package org.view;

/**
 * VOrdersGift entity. @author MyEclipse Persistence Tools
 */

public class VOrdersGift implements java.io.Serializable {

	// Fields

	private VOrdersGiftId id;

	// Constructors

	/** default constructor */
	public VOrdersGift() {
	}

	/** full constructor */
	public VOrdersGift(VOrdersGiftId id) {
		this.id = id;
	}

	// Property accessors

	public VOrdersGiftId getId() {
		return this.id;
	}

	public void setId(VOrdersGiftId id) {
		this.id = id;
	}

}