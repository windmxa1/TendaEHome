package org.view;

/**
 * VGarousel entity. @author MyEclipse Persistence Tools
 */

public class VGarousel implements java.io.Serializable {

	// Fields

	private VGarouselId id;

	// Constructors

	/** default constructor */
	public VGarousel() {
	}

	/** full constructor */
	public VGarousel(VGarouselId id) {
		this.id = id;
	}

	// Property accessors

	public VGarouselId getId() {
		return this.id;
	}

	public void setId(VGarouselId id) {
		this.id = id;
	}

}