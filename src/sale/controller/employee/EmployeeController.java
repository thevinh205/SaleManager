package sale.controller.employee;

import java.util.List;

import sale.base.BaseSale;
import sale.model.Member;
import sale.util.LookupBean;
import sale.util.UserUtil;

public class EmployeeController extends BaseSale{
	private UserUtil userUtil;
	private LookupBean lookupBean;
	private List<Member> listEmployee;
	
	public String listEmployee(){
		if(null == userUtil.getMember())
			return ERROR;
		setActionURL(getPath());
		return SUCCESS;
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
	
}
