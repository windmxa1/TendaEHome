package org.view;

/**
 * VFranchiseeId entity. @author MyEclipse Persistence Tools
 */

public class VFranchiseeId implements java.io.Serializable {

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
	private Double rating;

	// Constructors

	/** default constructor */
	public VFranchiseeId() {
	}

	/** minimal constructor */
	public VFranchiseeId(Long id, String franchiseeNum, String name,
			String address, String phone) {
		this.id = id;
		this.franchiseeNum = franchiseeNum;
		this.name = name;
		this.address = address;
		this.phone = phone;
	}

	/** full constructor */
	public VFranchiseeId(Long id, String franchiseeNum, String nickname,
			String name, String address, String phone, String healthPic,
			String promissPic, String ipc, String lat, String lon, Long userid,
			Integer catalogId, Integer state, String description, Double rating) {
		this.id = id;
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
		this.rating = rating;
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

	public Double getRating() {
		return this.rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof VFranchiseeId))
			return false;
		VFranchiseeId castOther = (VFranchiseeId) other;

		return ((this.getId() == castOther.getId()) || (this.getId() != null
				&& castOther.getId() != null && this.getId().equals(
				castOther.getId())))
				&& ((this.getFranchiseeNum() == castOther.getFranchiseeNum()) || (this
						.getFranchiseeNum() != null
						&& castOther.getFranchiseeNum() != null && this
						.getFranchiseeNum()
						.equals(castOther.getFranchiseeNum())))
				&& ((this.getNickname() == castOther.getNickname()) || (this
						.getNickname() != null
						&& castOther.getNickname() != null && this
						.getNickname().equals(castOther.getNickname())))
				&& ((this.getName() == castOther.getName()) || (this.getName() != null
						&& castOther.getName() != null && this.getName()
						.equals(castOther.getName())))
				&& ((this.getAddress() == castOther.getAddress()) || (this
						.getAddress() != null && castOther.getAddress() != null && this
						.getAddress().equals(castOther.getAddress())))
				&& ((this.getPhone() == castOther.getPhone()) || (this
						.getPhone() != null && castOther.getPhone() != null && this
						.getPhone().equals(castOther.getPhone())))
				&& ((this.getHealthPic() == castOther.getHealthPic()) || (this
						.getHealthPic() != null
						&& castOther.getHealthPic() != null && this
						.getHealthPic().equals(castOther.getHealthPic())))
				&& ((this.getPromissPic() == castOther.getPromissPic()) || (this
						.getPromissPic() != null
						&& castOther.getPromissPic() != null && this
						.getPromissPic().equals(castOther.getPromissPic())))
				&& ((this.getIpc() == castOther.getIpc()) || (this.getIpc() != null
						&& castOther.getIpc() != null && this.getIpc().equals(
						castOther.getIpc())))
				&& ((this.getLat() == castOther.getLat()) || (this.getLat() != null
						&& castOther.getLat() != null && this.getLat().equals(
						castOther.getLat())))
				&& ((this.getLon() == castOther.getLon()) || (this.getLon() != null
						&& castOther.getLon() != null && this.getLon().equals(
						castOther.getLon())))
				&& ((this.getUserid() == castOther.getUserid()) || (this
						.getUserid() != null && castOther.getUserid() != null && this
						.getUserid().equals(castOther.getUserid())))
				&& ((this.getCatalogId() == castOther.getCatalogId()) || (this
						.getCatalogId() != null
						&& castOther.getCatalogId() != null && this
						.getCatalogId().equals(castOther.getCatalogId())))
				&& ((this.getState() == castOther.getState()) || (this
						.getState() != null && castOther.getState() != null && this
						.getState().equals(castOther.getState())))
				&& ((this.getDescription() == castOther.getDescription()) || (this
						.getDescription() != null
						&& castOther.getDescription() != null && this
						.getDescription().equals(castOther.getDescription())))
				&& ((this.getRating() == castOther.getRating()) || (this
						.getRating() != null && castOther.getRating() != null && this
						.getRating().equals(castOther.getRating())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
		result = 37
				* result
				+ (getFranchiseeNum() == null ? 0 : this.getFranchiseeNum()
						.hashCode());
		result = 37 * result
				+ (getNickname() == null ? 0 : this.getNickname().hashCode());
		result = 37 * result
				+ (getName() == null ? 0 : this.getName().hashCode());
		result = 37 * result
				+ (getAddress() == null ? 0 : this.getAddress().hashCode());
		result = 37 * result
				+ (getPhone() == null ? 0 : this.getPhone().hashCode());
		result = 37 * result
				+ (getHealthPic() == null ? 0 : this.getHealthPic().hashCode());
		result = 37
				* result
				+ (getPromissPic() == null ? 0 : this.getPromissPic()
						.hashCode());
		result = 37 * result
				+ (getIpc() == null ? 0 : this.getIpc().hashCode());
		result = 37 * result
				+ (getLat() == null ? 0 : this.getLat().hashCode());
		result = 37 * result
				+ (getLon() == null ? 0 : this.getLon().hashCode());
		result = 37 * result
				+ (getUserid() == null ? 0 : this.getUserid().hashCode());
		result = 37 * result
				+ (getCatalogId() == null ? 0 : this.getCatalogId().hashCode());
		result = 37 * result
				+ (getState() == null ? 0 : this.getState().hashCode());
		result = 37
				* result
				+ (getDescription() == null ? 0 : this.getDescription()
						.hashCode());
		result = 37 * result
				+ (getRating() == null ? 0 : this.getRating().hashCode());
		return result;
	}

}