package sale.table;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="order_party_relationship")
public class OrderPartyRelationship implements Serializable{
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name="shop_id")
	private int shopId;
	
	@Column(name="order_id")
	private int orderId;
	
	@Column(name="product_id")
	private String productId;
	
	@Column(name="count")
	private int count;
	
	@Column(name="status")
	private String status;
	
	@Column(name="create_date")
	private Date createDate;

	public int getId() {
		return id;
	}

	public int getShopId() {
		return shopId;
	}

	public int getOrderId() {
		return orderId;
	}

	public String getProductId() {
		return productId;
	}

	public int getCount() {
		return count;
	}

	public String getStatus() {
		return status;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setShopId(int shopId) {
		this.shopId = shopId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
}
