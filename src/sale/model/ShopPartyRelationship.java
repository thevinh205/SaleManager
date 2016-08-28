package sale.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="shop_party_relationship")
public class ShopPartyRelationship implements Serializable{
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="shop_id")
	private int shopId;
	
	@Column(name="product_id")
	private String productId;
	
	@Column(name="member_userName")
	private String memberUserName;
	
	@Column(name="order_id")
	private int orderId;
	
	@Column(name="type")
	private String type;
	
	@Column(name="create_date")
	private Date createDate;
	
	@Column(name="count")
	private int count;
	
	@Column(name="position")
	private String position;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getShopId() {
		return shopId;
	}
	public String getProductId() {
		return productId;
	}
	public String getMemberUserName() {
		return memberUserName;
	}
	public int getOrderId() {
		return orderId;
	}
	public String getType() {
		return type;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setShopId(int shopId) {
		this.shopId = shopId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public void setMemberUserName(String memberUserName) {
		this.memberUserName = memberUserName;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
}
