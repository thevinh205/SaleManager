package sale.model;

import java.io.Serializable;
import java.util.Date;

public class Member implements Serializable{
	private Integer id;
	private String userName;
	private String email;
	private String phoneNumber;
	private String name;
	private String address;
	private Integer level;
	private String state;
	private Date birthDate;
	private Date createDate;
	private String gender;
	private String role;
	
	
	public Integer getId() {
		return id;
	}
	public String getUserName() {
		return userName;
	}
	public String getEmail() {
		return email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public String getName() {
		return name;
	}
	public String getAddress() {
		return address;
	}
	public Integer getLevel() {
		return level;
	}
	public String getState() {
		return state;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public void setState(String state) {
		this.state = state;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	
}
