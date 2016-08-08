package sale.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Product implements Serializable{
	private String id;
	private String productName;
	private int groupId; //category
	private List<Price> listPriceSell;
	private Long priceBuy;
	private Long priceSell;
	private String description;
	private Date createDate;
	private String type;
	private String color;
	private String size;
	private int weight;
	private String style;
	private List<Image> images;
	private String avatar;
	private String status;
	private String categoryName;
	
	public String getId() {
		return id;
	}
	public String getProductName() {
		return productName;
	}
	public int getGroupId() {
		return groupId;
	}
	public List<Price> getListPriceSell() {
		return listPriceSell;
	}
	public Long getPriceBuy() {
		return priceBuy;
	}
	public String getDescription() {
		return description;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public String getType() {
		return type;
	}
	public String getColor() {
		return color;
	}
	public String getSize() {
		return size;
	}
	public int getWeight() {
		return weight;
	}
	public String getStyle() {
		return style;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public void setListPriceSell(List<Price> listPriceSell) {
		this.listPriceSell = listPriceSell;
	}
	public void setPriceBuy(Long priceBuy) {
		this.priceBuy = priceBuy;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	public List<Image> getImages() {
		return images;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setImages(List<Image> images) {
		this.images = images;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public Long getPriceSell() {
		return priceSell;
	}
	public void setPriceSell(Long priceSell) {
		this.priceSell = priceSell;
	}

	
}
