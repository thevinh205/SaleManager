package sale.controller.shop;

import sale.base.BaseSale;
import sale.model.Shop;
import sale.util.LookupBean;
import sale.util.UserUtil;

public class ShopDetailController extends BaseSale{
	private UserUtil userUtil;
	private LookupBean lookupBean;
	private Shop shop;
	
	public String shopDetail(){
		if(null == userUtil.getMember())
			return ERROR;
		return SUCCESS;
	}
	
	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public void setUserUtil(UserUtil userUtil) {
		this.userUtil = userUtil;
	}
	public void setLookupBean(LookupBean lookupBean) {
		this.lookupBean = lookupBean;
	}
}
