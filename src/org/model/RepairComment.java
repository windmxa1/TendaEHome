package org.model;

/**
 * RepairComment entity. @author MyEclipse Persistence Tools
 */

public class RepairComment implements java.io.Serializable {

	// Fields

	private Long id;
	private Integer point;
	private String description;
	private String suggestion;
	private String staffNo;
	private Long userid;

	// Constructors

	/** default constructor */
	public RepairComment() {
	}

	/** full constructor */
	public RepairComment(Integer point, String description, String suggestion,
			String staffNo, Long userid) {
		this.point = point;
		this.description = description;
		this.suggestion = suggestion;
		this.staffNo = staffNo;
		this.userid = userid;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getPoint() {
		return this.point;
	}

	public void setPoint(Integer point) {
		this.point = point;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSuggestion() {
		return this.suggestion;
	}

	public void setSuggestion(String suggestion) {
		this.suggestion = suggestion;
	}

	public String getstaffNo() {
		return this.staffNo;
	}

	public void setstaffNo(String staffNo) {
		this.staffNo = staffNo;
	}

	public Long getUserid() {
		return this.userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

}