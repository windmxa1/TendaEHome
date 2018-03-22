package org.model;

public class OrdersDetail implements java.io.Serializable {
	// Fields

	private Long id;
	private Long orderId;
	private Long goodsId; // 商品编号
	private Integer num = 1;// 已选商品数目
	private Double prices; // 商品总价
	private String goodsUrl;// 商品图片
	private String name; // 商品名称
	private Double price; // 商品单价
	private String description; // 商品描述
	private Boolean isSelect; // CheckBox的选中状态
	private Long time; // 商品最新修改时间
	private Integer isGift;
	private Integer actId;
	private String actName;
	private Double actMinPrice;
	private Boolean isShopSelect;
	private Boolean isFirst;

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

	public OrdersDetail(Long orderId, Long goodsId, Integer num, Integer actId) {
		this.orderId = orderId;
		this.goodsId = goodsId;
		this.num = num;
		this.actId = actId;
	}

	public OrdersDetail(Long goodsId, Long time) {
		this.goodsId = goodsId;
		this.time = time;
	}

	public Long getTime() {
		return time;
	}

	public void setTime(Long time) {
		this.time = time;
	}

	public Boolean getIsSelect() {
		return isSelect;
	}

	public void setIsSelect(Boolean isSelect) {
		this.isSelect = isSelect;
	}

	// public boolean isSelect() {
	// return select;
	// }
	//
	// public void setSelect(boolean select) {
	// this.select = select;
	// }

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
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getIsGift() {
		return this.isGift;
	}

	public void setIsGift(Integer isGift) {
		this.isGift = isGift;
	}

	public Integer getActId() {
		return this.actId;
	}

	public void setActId(Integer actId) {
		this.actId = actId;
	}

	public String getActName() {
		return this.actName;
	}

	public void setActName(String actName) {
		this.actName = actName;
	}

	public Boolean getIsShopSelect() {
		return isShopSelect;
	}

	public void setIsShopSelect(Boolean isShopSelect) {
		this.isShopSelect = isShopSelect;
	}

	public Boolean getIsFirst() {
		return isFirst;
	}

	public void setIsFirst(Boolean isFirst) {
		this.isFirst = isFirst;
	}

	public Double getActMinPrice() {
		return actMinPrice;
	}

	public void setActMinPrice(Double actMinPrice) {
		this.actMinPrice = actMinPrice;
	}

}