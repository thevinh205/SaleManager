package sale.controller.employee;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;

import sale.base.BaseSale;
import sale.table.Member;
import sale.util.LookupBean;
import sale.util.UserUtil;

public class RegisterEmployeeController extends BaseSale{
	private String fullName;
	private String userName;
	private String pass;
	private String rePass;
	private String gender;
	private String email;
	private String phoneNumber;
	private String address;
	private Date birthdays;
	private String errorMessage;
	private UserUtil userUtil;
	private LookupBean lookupBean;
	
	public String registerEmployee(){
		if(null == userUtil.getMember())
			return ERROR;
		setActionURL(getPath());
		return SUCCESS;
	}
	
	public String registerEmployeeAction(){
		String result =  validateInput();
		if(SUCCESS.equals(result)){
			try{
				Member member = new Member();
				member.setUserName(userName.trim());
				member.setName(fullName.trim());
				member.setGender(gender);
				member.setEmail(email.trim());
				member.setPhoneNumber(phoneNumber.trim());
				member.setAddress(address.trim());
				member.setBirthDate(birthdays);
				member.setLevel(1);
				member.setState("open");
				member.setCreateDate(new Date());
				member.setRole("employee");
				lookupBean.getMemberDao().registerMember(member, pass.trim());
				reset();
			}catch (Exception e) {
				e.printStackTrace();
				result = ERROR;
			}
		}
		return result;
	}

	public String getFullName() {
		return fullName;
	}

	public String getUserName() {
		return userName;
	}

	public String getPass() {
		return pass;
	}

	public String getRePass() {
		return rePass;
	}

	public String getGender() {
		return gender;
	}

	public String getEmail() {
		return email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public Date getBirthdays() {
		return birthdays;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public void setRePass(String rePass) {
		this.rePass = rePass;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setBirthdays(Date birthdays) {
		this.birthdays = birthdays;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	/**
	 * Validate input register member
	 * @return
	 */
	public String validateInput(){
		if(null == fullName || fullName.trim().equals("")){
			errorMessage = "Tên không được để trống!";
			return ERROR;
		}
		
		if(null == userName || userName.trim().equals("")){
			errorMessage = "Username không được để trống!";
			return ERROR;
		}
		
		if(null != lookupBean.getMemberDao().getMember(userName)){
			errorMessage = "UserName này đã tồn tại!";
			return ERROR;
		}
		
		if(null == pass || pass.trim().equals("")){
			errorMessage = "Password không được để trống!";
			return ERROR;
		}
		
		if(null == rePass || rePass.equals("")){
			errorMessage = "Re password không được để trống!";
			return ERROR;
		}
		
		if(!pass.equals(rePass)){
			errorMessage = "Password và re password không chính xác!";
			return ERROR;
		}
		
		if(null == email && email.trim().length() == 0){
			errorMessage = "Email không được để trống!";
			return ERROR;
		}
		
		if(null == phoneNumber || phoneNumber.trim().equals("")){
			errorMessage = "Số điện thoại không được để trống!";
			return ERROR;
		}
		
		if(!isNumeric(phoneNumber)){
			errorMessage = "Số điện thoại không được là chữ!";
			return ERROR;
		}
		errorMessage = "";
		return SUCCESS;
	}
	
	public void reset(){
		fullName = "";
		userName = "";
		pass = "";
		rePass = "";
		gender = "";
		email = "";
		phoneNumber = "";
		address = "";
		birthdays = null;
		errorMessage = "";
	}
	
	public UserUtil getUserUtil() {
		return userUtil;
	}

	public void setUserUtil(UserUtil userUtil) {
		this.userUtil = userUtil;
	}

	public void setLookupBean(LookupBean lookupBean) {
		this.lookupBean = lookupBean;
	}
	
}
