package org.view;

/**
 * VStaffPromotion entity. @author MyEclipse Persistence Tools
 */

public class VStaffPromotion implements java.io.Serializable {

	// Fields

	private VStaffPromotionId id;

	// Constructors

	/** default constructor */
	public VStaffPromotion() {
	}

	/** full constructor */
	public VStaffPromotion(VStaffPromotionId id) {
		this.id = id;
	}

	// Property accessors

	public VStaffPromotionId getId() {
		return this.id;
	}

	public void setId(VStaffPromotionId id) {
		this.id = id;
	}

}