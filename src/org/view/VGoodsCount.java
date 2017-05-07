package org.view;

/**
 * VGoodsCount entity. @author MyEclipse Persistence Tools
 */

public class VGoodsCount implements java.io.Serializable {

	// Fields

	private VGoodsCountId id;

	// Constructors

	/** default constructor */
	public VGoodsCount() {
	}

	/** full constructor */
	public VGoodsCount(VGoodsCountId id) {
		this.id = id;
	}

	// Property accessors

	public VGoodsCountId getId() {
		return this.id;
	}

	public void setId(VGoodsCountId id) {
		this.id = id;
	}

}