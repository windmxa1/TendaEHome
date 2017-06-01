package org.view;

/**
 * VTv entity. @author MyEclipse Persistence Tools
 */

public class VTv implements java.io.Serializable {

	// Fields

	private VTvId id;

	// Constructors

	/** default constructor */
	public VTv() {
	}

	/** full constructor */
	public VTv(VTvId id) {
		this.id = id;
	}

	// Property accessors

	public VTvId getId() {
		return this.id;
	}

	public void setId(VTvId id) {
		this.id = id;
	}

}