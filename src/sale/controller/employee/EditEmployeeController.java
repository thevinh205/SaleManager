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

public class EditEmployeeController extends BaseSale{
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
	private EmployeeController employeeController;
	private UserUtil userUtil;
	private LookupBean lookupBean;
	
	public String editEmployee(){
		if(null == userUtil.getMember())
			return ERROR;
		String userNameEdit = findParam("username");
		if(null != userNameEdit){
			if(!userNameEdit.equals(userName))
				errorMessage = null;
			if(null == errorMessage){
				Member member = lookupBean.getMemberDao().getMember(userNameEdit);
				if(null != member)
					setParameter(member);
			}
		}
		setActionURL(getPath());
		return SUCCESS;
	}
	
	public String editEmployeeAction(){
		String result = validateInput();
		if(SUCCESS.equals(result)){
			try{
				Member memberUpdate = new Member();
				memberUpdate.setUserName(userName.trim());
				memberUpdate.setName(fullName.trim());
				memberUpdate.setGender(gender);
				memberUpdate.setEmail(email.trim());
				memberUpdate.setPhoneNumber(phoneNumber.trim());
				memberUpdate.setAddress(address.trim());
				memberUpdate.setBirthDate(birthdays);
				lookupBean.getMemberDao().updateMember(memberUpdate, pass.trim());
				reloadListCustomer();
				reloadUserLogin();
				errorMessage = null;
			}catch (Exception e) {
				e.printStackTrace();
				errorMessage = "Có lỗi trong quá trình chỉnh sửa thông tin khách hàng!";
				return ERROR;
			}
		}
		return result;
	}
	
	public void reloadListCustomer(){
		List<Member> listMember = employeeController.getListEmployee();
		if(null != listMember){
			for(Member member : listMember){
				if(member.getUserName().equals(userName)){
					member.setAddress(address.trim());
					member.setName(fullName.trim());
					member.setGender(gender);
					member.setEmail(email);
					member.setPhoneNumber(phoneNumber);
					member.setBirthDate(birthdays);
					break;
				}
			}
		}
	}
	
	public void reloadUserLogin(){
		if(null !=userUtil.getMember() && userUtil.getMember().getUserName().equals(userName)){
			userUtil.getMember().setAddress(address.trim());
			userUtil.getMember().setName(fullName.trim());
			userUtil.getMember().setGender(gender);
			userUtil.getMember().setEmail(email);
			userUtil.getMember().setPhoneNumber(phoneNumber);
			userUtil.getMember().setBirthDate(birthdays);
			
		}
	}
	
	public void setParameter(Member member){
		fullName = member.getName();
		userName = member.getUserName();
		gender = member.getGender();
		email = member.getEmail();
		phoneNumber = member.getPhoneNumber();
		address = member.getAddress();
		birthdays = member.getBirthDate();
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
	
	
	public UserUtil getUserUtil() {
		return userUtil;
	}

	public void setUserUtil(UserUtil userUtil) {
		this.userUtil = userUtil;
	}

	public void setEmployeeController(EmployeeController employeeController) {
		this.employeeController = employeeController;
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
		
		if(null != pass && pass.trim().length() > 0 && !pass.equals(rePass)){
			errorMessage = "Password và re password không chính xác!";
			return ERROR;
		}
		
		if(null == email || email.trim().equals("")){
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

	public void setLookupBean(LookupBean lookupBean) {
		this.lookupBean = lookupBean;
	}
	
}
