package org.model;

/**
 * Franchisee entity. @author MyEclipse Persistence Tools
 */

public class Franchisee implements java.io.Serializable {

	// Fields

	private Long id;
	private String franchiseeNum;
	private String nickname;
	private String name;
	private String address;
	private String phone;
	private String healthPic;
	private String promissPic;
	private String ipc;
	private String lat;
	private String lon;
	private Long userid;
	private Integer catalogId;
	private Integer state;
	private String description;

	// Constructors

	/** default constructor */
	public Franchisee() {
	}

	/** minimal constructor */
	public Franchisee(String franchiseeNum, String name, String address,
			String phone) {
		this.franchiseeNum = franchiseeNum;
		this.name = name;
		this.address = address;
		this.phone = phone;
	}

	/** full constructor */
	public Franchisee(String franchiseeNum, String nickname, String name,
			String address, String phone, String healthPic, String promissPic,
			String ipc, String lat, String lon, Long userid, Integer catalogId,
			Integer state, String description) {
		this.franchiseeNum = franchiseeNum;
		this.nickname = nickname;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.healthPic = healthPic;
		this.promissPic = promissPic;
		this.ipc = ipc;
		this.lat = lat;
		this.lon = lon;
		this.userid = userid;
		this.catalogId = catalogId;
		this.state = state;
		this.description = description;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFranchiseeNum() {
		return this.franchiseeNum;
	}

	public void setFranchiseeNum(String franchiseeNum) {
		this.franchiseeNum = franchiseeNum;
	}

	public String getNickname() {
		return this.nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getHealthPic() {
		return this.healthPic;
	}

	public void setHealthPic(String healthPic) {
		this.healthPic = healthPic;
	}

	public String getPromissPic() {
		return this.promissPic;
	}

	public void setPromissPic(String promissPic) {
		this.promissPic = promissPic;
	}

	public String getIpc() {
		return this.ipc;
	}

	public void setIpc(String ipc) {
		this.ipc = ipc;
	}

	public String getLat() {
		return this.lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLon() {
		return this.lon;
	}

	public void setLon(String lon) {
		this.lon = lon;
	}

	public Long getUserid() {
		return this.userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public Integer getCatalogId() {
		return this.catalogId;
	}

	public void setCatalogId(Integer catalogId) {
		this.catalogId = catalogId;
	}

	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}