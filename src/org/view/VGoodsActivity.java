package org.view;

/**
 * VGoodsActivity entity. @author MyEclipse Persistence Tools
 */

public class VGoodsActivity implements java.io.Serializable {

	// Fields

	private VGoodsActivityId id;

	// Constructors

	/** default constructor */
	public VGoodsActivity() {
	}

	/** full constructor */
	public VGoodsActivity(VGoodsActivityId id) {
		this.id = id;
	}

	// Property accessors

	public VGoodsActivityId getId() {
		return this.id;
	}

	public void setId(VGoodsActivityId id) {
		this.id = id;
	}

}