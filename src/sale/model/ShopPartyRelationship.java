package sale.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="shop_party_relationship")
public class ShopPartyRelationship implements Serializable{
	@Id
	@Column(name="shop_id")
	private int shopId;
	
	@Column(name="product_id")
	private String productId;
	
	@Column(name="member_id")
	private int memberId;
	
	@Column(name="order_id")
	private int orderId;
	
	@Column(name="type")
	private String type;
	
	@Column(name="create_date")
	private Date createDate;
	
	public int getShopId() {
		return shopId;
	}
	public String getProductId() {
		return productId;
	}
	public int getMemberId() {
		return memberId;
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
	public void setMemberId(int memberId) {
		this.memberId = memberId;
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
	
}
