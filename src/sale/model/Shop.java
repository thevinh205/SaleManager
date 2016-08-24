package sale.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

public class Shop implements Serializable{
	private int id;
	private String name;
	private String description;
	private String address;
	private long phoneNumber;
	private List<Member> listEmployee;
	private List<Product> listProduct;
	private Date createDate;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public long getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public List<Member> getListEmployee() {
		return listEmployee;
	}
	public void setListEmployee(List<Member> listEmployee) {
		this.listEmployee = listEmployee;
	}
	public List<Product> getListProduct() {
		return listProduct;
	}
	public void setListProduct(List<Product> listProduct) {
		this.listProduct = listProduct;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
}
