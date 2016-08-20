package sale.controller.member;

import java.util.*;


import com.opensymphony.xwork2.util.ValueStack;

import sale.base.BaseSale;
import sale.model.Member;
import sale.util.LookupBean;
import sale.util.UserUtil;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class CustomerController extends BaseSale{
	private String errorMessage;
	private String nameSearch;
	private String emailSearch;
	private String phoneNumberSearch;
	private List<Member> memberList;
	private UserUtil userUtil;
	private LookupBean lookupBean;
	
	/**
	 * access to page login
	 * @return
	 */
	public String customerList(){
		if(null == userUtil.getMember())
			return ERROR;
		setActionURL(getPath());
		return SUCCESS;
	}
	
	public String searchCustomer(){
		try{
			
			if(null != phoneNumberSearch && phoneNumberSearch.trim().length() > 0 && !isNumeric(phoneNumberSearch)){
				errorMessage = "Số điện thoại không được là chữ!";
				return SUCCESS;
			}
			memberList = lookupBean.getMemberDao().searchCustomer(nameSearch, emailSearch, phoneNumberSearch);
			errorMessage = "";
		}catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String deleteCustomer(){
		try{
			if(null == userUtil.getMember())
				return ERROR;
			ActionContext context = ActionContext.getContext();
			Map<String, Object> params = context.getParameters();
			String userNameDelete = findParam("userNameDelete");
			if(null != userNameDelete){
				lookupBean.getMemberDao().deleteMember(userNameDelete);
				if(null != memberList){
					int index = 0;
					for(Member member : memberList){
						if(member.getUserName().equals(userNameDelete)){
							memberList.remove(index);
							return SUCCESS;
						}
						index++;
					}
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return ERROR;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getNameSearch() {
		return nameSearch;
	}

	public void setNameSearch(String nameSearch) {
		this.nameSearch = nameSearch;
	}

	public String getEmailSearch() {
		return emailSearch;
	}

	public void setEmailSearch(String emailSearch) {
		this.emailSearch = emailSearch;
	}

	public String getPhoneNumberSearch() {
		return phoneNumberSearch;
	}

	public void setPhoneNumberSearch(String phoneNumberSearch) {
		this.phoneNumberSearch = phoneNumberSearch;
	}

	public List<Member> getMemberList() {
		return memberList;
	}

	public void setMemberList(List<Member> memberList) {
		this.memberList = memberList;
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