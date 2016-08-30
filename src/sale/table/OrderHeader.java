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
@Table(name="order_header")
public class OrderHeader implements Serializable{
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name="customer_username")
	private String customerUsername;
	
	@Column(name="employee_username")
	private String employeeUsername;
	
	@Column(name="shipper_id")
	private int shipperId;
	
	@Column(name="status")
	private String status;
	
	@Column(name="create_date")
	private Date createDate;
	
	@Column(name="require_date")
	private Date requireDate;
	
	@Column(name="shipped_date")
	private Date shippedDate;
	
	@Column(name="total_price")
	private Long totalPrice;
	
	@Column(name="shop_id")
	private int shopId;
	
	public int getId() {
		return id;
	}
	public int getShipperId() {
		return shipperId;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public Date getRequireDate() {
		return requireDate;
	}
	public Date getShippedDate() {
		return shippedDate;
	}
	public Long getTotalPrice() {
		return totalPrice;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setShipperId(int shipperId) {
		this.shipperId = shipperId;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public void setRequireDate(Date requireDate) {
		this.requireDate = requireDate;
	}
	public void setShippedDate(Date shippedDate) {
		this.shippedDate = shippedDate;
	}
	public void setTotalPrice(Long totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getShopId() {
		return shopId;
	}
	public void setShopId(int shopId) {
		this.shopId = shopId;
	}
	public String getCustomerUsername() {
		return customerUsername;
	}
	public String getEmployeeUsername() {
		return employeeUsername;
	}
	public void setCustomerUsername(String customerUsername) {
		this.customerUsername = customerUsername;
	}
	public void setEmployeeUsername(String employeeUsername) {
		this.employeeUsername = employeeUsername;
	}
}
