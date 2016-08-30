package sale.controller.shop;

import java.util.List;

import sale.base.BaseSale;
import sale.table.Shop;
import sale.util.LookupBean;
import sale.util.UserUtil;

public class ShopController extends BaseSale{
	private UserUtil userUtil;
	private LookupBean lookupBean;
	private List<Shop> listShop;
	
	public String shopList(){
		try{
			if(null == userUtil.getMember())
				return ERROR;
			if(null == listShop)
				listShop = lookupBean.getShopDao().getListShop();
			setActionURL(getPath());
		}catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public List<Shop> getListShop() {
		return listShop;
	}

	public void setListShop(List<Shop> listShop) {
		this.listShop = listShop;
	}

	public void setUserUtil(UserUtil userUtil) {
		this.userUtil = userUtil;
	}
	public void setLookupBean(LookupBean lookupBean) {
		this.lookupBean = lookupBean;
	}
}
