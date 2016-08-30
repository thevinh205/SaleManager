package sale.controller.order;

import sale.base.BaseSale;
import sale.util.LookupBean;
import sale.util.UserUtil;

public class OrderController extends BaseSale{
	private UserUtil userUtil;
	private LookupBean lookupBean;
	private String errorMessage;
	
	public void setUserUtil(UserUtil userUtil) {
		this.userUtil = userUtil;
	}
	
	public void setLookupBean(LookupBean lookupBean) {
		this.lookupBean = lookupBean;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}
}
