package org.model;

/**
 * CommentImg entity. @author MyEclipse Persistence Tools
 */

public class CommentImg implements java.io.Serializable {

	// Fields

	private Long id;
	private String url;
	private Long commentId;

	// Constructors

	/** default constructor */
	public CommentImg() {
	}

	/** full constructor */
	public CommentImg(String url, Long commentId) {
		this.url = url;
		this.commentId = commentId;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Long getCommentId() {
		return this.commentId;
	}

	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}

}