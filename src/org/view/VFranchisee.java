package org.view;

/**
 * VFranchisee entity. @author MyEclipse Persistence Tools
 */

public class VFranchisee implements java.io.Serializable {

	// Fields

	private VFranchiseeId id;

	// Constructors

	/** default constructor */
	public VFranchisee() {
	}

	/** full constructor */
	public VFranchisee(VFranchiseeId id) {
		this.id = id;
	}

	// Property accessors

	public VFranchiseeId getId() {
		return this.id;
	}

	public void setId(VFranchiseeId id) {
		this.id = id;
	}

}