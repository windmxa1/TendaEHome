package org.view;

/**
 * VComment entity. @author MyEclipse Persistence Tools
 */

public class VComment implements java.io.Serializable {

	// Fields

	private VCommentId id;

	// Constructors

	/** default constructor */
	public VComment() {
	}

	/** full constructor */
	public VComment(VCommentId id) {
		this.id = id;
	}

	// Property accessors

	public VCommentId getId() {
		return this.id;
	}

	public void setId(VCommentId id) {
		this.id = id;
	}

}