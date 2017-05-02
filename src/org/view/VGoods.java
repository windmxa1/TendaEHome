package org.view;

/**
 * VGoods entity. @author MyEclipse Persistence Tools
 */

public class VGoods implements java.io.Serializable {

	// Fields

	private VGoodsId id;

	// Constructors

	/** default constructor */
	public VGoods() {
	}

	/** full constructor */
	public VGoods(VGoodsId id) {
		this.id = id;
	}

	// Property accessors

	public VGoodsId getId() {
		return this.id;
	}

	public void setId(VGoodsId id) {
		this.id = id;
	}

}