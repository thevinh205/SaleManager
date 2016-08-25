package sale.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="product")
public class ProductTable implements Serializable{
	@Id
	@Column(name="id")
	private String id;
	
	@Column(name="name")
	private String productName;
	
	@Column(name="group_id")
	private int groupId; //category
	
	private List<Price> listPriceSell;
	
	@Column(name="price_buy")
	private Long priceBuy;
	
	@Column(name="price_sell")
	private Long priceSell;
	
	@Column(name="description")
	private String description;
	
	@Column(name="create_date")
	private Date createDate;
	
	@Column(name="type")
	private String type;
	
	@Column(name="color")
	private String color;
	
	@Column(name="size")
	private String size;
	
	@Column(name="weight")
	private int weight;
	
	@Column(name="style")
	private String style;
	
	private List<Image> images;
	
	@Column(name="avatar")
	private String avatar;
	
	@Column(name="status")
	private String status;
	
	@Column(name="category_name")
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
