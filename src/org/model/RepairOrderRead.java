package org.model;

/**
 * RepairOrderRead entity. @author MyEclipse Persistence Tools
 */

public class RepairOrderRead implements java.io.Serializable {

	// Fields

	private Long id;
	private Integer staffId;
	private Long repairOrderId;

	// Constructors

	/** default constructor */
	public RepairOrderRead() {
	}

	/** full constructor */
	public RepairOrderRead(Integer staffId, Long repairOrderId) {
		this.staffId = staffId;
		this.repairOrderId = repairOrderId;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getStaffId() {
		return this.staffId;
	}

	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}

	public Long getRepairOrderId() {
		return this.repairOrderId;
	}

	public void setRepairOrderId(Long repairOrderId) {
		this.repairOrderId = repairOrderId;
	}

}