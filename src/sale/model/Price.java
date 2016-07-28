package sale.model;

import java.io.Serializable;
import java.util.Date;

public class Price implements Serializable{
	private int id;
	private Long price;
	private Date fromDay;
	private Date toDay;
	private String currency;
	private String type; //buy and sale
	private String productId;
	private int groupId;
	
	public int getId() {
		return id;
	}
	public Long getPrice() {
		return price;
	}
	public Date getFromDay() {
		return fromDay;
	}
	public Date getToDay() {
		return toDay;
	}
	public String getCurrency() {
		return currency;
	}
	public String getType() {
		return type;
	}
	public String getProductId() {
		return productId;
	}
	public int getGroupId() {
		return groupId;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
	public void setFromDay(Date fromDay) {
		this.fromDay = fromDay;
	}
	public void setToDay(Date toDay) {
		this.toDay = toDay;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	
	
	
	

}
