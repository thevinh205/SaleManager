package sale.model;

import java.io.Serializable;
import java.util.Date;

public class CategoryProduct implements Serializable{
	private int id;
	private String name;
	private String description;
	private String status;
	private Date createDate;
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getDescription() {
		return description;
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
	public void setName(String name) {
		this.name = name;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	

}
