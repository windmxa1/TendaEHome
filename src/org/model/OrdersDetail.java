package org.model;

/**
 * OrdersDetail entity. @author MyEclipse Persistence Tools
 */

public class OrdersDetail implements java.io.Serializable {

	// Fields

	private Long id;
	private Long orderId;
	private Long goodsId; // 商品编号
	private Integer num;// 已选商品数目
	private Double prices; // 商品总价
	private String goodsUrl;// 商品图片
	private String name; // 商品名称
	private Double price; // 商品单价
	private String description; // 商品描述
	public boolean isSelect; // CheckBox的选中状态
	private Long time; // 商品最新修改时间
	private Integer baseObjId;
	// Constructors

	

	/** default constructor */
	public OrdersDetail() {
	}

	/** full constructor */
	public OrdersDetail(Long orderId, Long goodsId, Integer num) {
		this.orderId = orderId;
		this.goodsId = goodsId;
		this.num = num;
	}
	public OrdersDetail(Long goodsId, Long time) {
		this.goodsId = goodsId;
		this.time = time;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOrderId() {
		return this.orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getGoodsId() {
		return this.goodsId;
	}

	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}

	public Integer getNum() {
		return this.num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Double getPrices() {
		return this.prices;
	}

	public void setPrices(Double prices) {
		this.prices = prices;
	}

	public String getGoodsUrl() {
		return goodsUrl;
	}

	public void setGoodsUrl(String goodsUrl) {
		this.goodsUrl = goodsUrl;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isSelect() {
		return isSelect;
	}

	public void setSelect(boolean isSelect) {
		this.isSelect = isSelect;
	}

	public Long getTime() {
		return time;
	}

	public void setTime(Long time) {
		this.time = time;
	}
	public Integer getBaseObjId() {
		return baseObjId;
	}

	public void setBaseObjId(Integer baseObjId) {
		this.baseObjId = baseObjId;
	}
}