package org.view;

/**
 * VUserFeedback entity. @author MyEclipse Persistence Tools
 */

public class VUserFeedback implements java.io.Serializable {

	// Fields

	private VUserFeedbackId id;

	// Constructors

	/** default constructor */
	public VUserFeedback() {
	}

	/** full constructor */
	public VUserFeedback(VUserFeedbackId id) {
		this.id = id;
	}

	// Property accessors

	public VUserFeedbackId getId() {
		return this.id;
	}

	public void setId(VUserFeedbackId id) {
		this.id = id;
	}

}