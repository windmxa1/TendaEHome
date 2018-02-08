package org.view;

/**
 * VActivity entity. @author MyEclipse Persistence Tools
 */

public class VActivity implements java.io.Serializable {

	// Fields

	private VActivityId id;

	// Constructors

	/** default constructor */
	public VActivity() {
	}

	/** full constructor */
	public VActivity(VActivityId id) {
		this.id = id;
	}

	// Property accessors

	public VActivityId getId() {
		return this.id;
	}

	public void setId(VActivityId id) {
		this.id = id;
	}

}