package sale.controller.employee;

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;

import sale.base.BaseSale;
import sale.table.Member;
import sale.util.LookupBean;
import sale.util.UserUtil;

public class EmployeeController extends BaseSale{
	private UserUtil userUtil;
	private LookupBean lookupBean;
	private List<Member> listEmployee;
	private String errorMessage;
	private String nameSearch;
	private String emailSearch;
	private String phoneNumberSearch;
	
	public String listEmployee(){
		if(null == userUtil.getMember())
			return ERROR;
		setActionURL(getPath());
		return SUCCESS;
	}
	
	public String searchEmployee(){
		try{
			
			if(null != phoneNumberSearch && phoneNumberSearch.trim().length() > 0 && !isNumeric(phoneNumberSearch)){
				errorMessage = "Số điện thoại không được là chữ!";
				return SUCCESS;
			}
			listEmployee = lookupBean.getMemberDao().searchEmployee(nameSearch, emailSearch, phoneNumberSearch);
			errorMessage = "";
		}catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String deleteEmployee(){
		try{
			if(null == userUtil.getMember())
				return ERROR;
			ActionContext context = ActionContext.getContext();
			Map<String, Object> params = context.getParameters();
			String userNameDelete = findParam("userNameDelete");
			if(null != userNameDelete){
				lookupBean.getMemberDao().deleteMember(userNameDelete);
				if(null != listEmployee){
					int index = 0;
					for(Member member : listEmployee){
						if(member.getUserName().equals(userNameDelete)){
							listEmployee.remove(index);
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
	
	public List<Member> getListEmployee() {
		return listEmployee;
	}
	public void setListEmployee(List<Member> listEmployee) {
		this.listEmployee = listEmployee;
	}
	public void setUserUtil(UserUtil userUtil) {
		this.userUtil = userUtil;
	}
	public void setLookupBean(LookupBean lookupBean) {
		this.lookupBean = lookupBean;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public String getNameSearch() {
		return nameSearch;
	}

	public String getEmailSearch() {
		return emailSearch;
	}

	public String getPhoneNumberSearch() {
		return phoneNumberSearch;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public void setNameSearch(String nameSearch) {
		this.nameSearch = nameSearch;
	}

	public void setEmailSearch(String emailSearch) {
		this.emailSearch = emailSearch;
	}

	public void setPhoneNumberSearch(String phoneNumberSearch) {
		this.phoneNumberSearch = phoneNumberSearch;
	}
	
	public UserUtil getUserUtil() {
		return userUtil;
	}
}
