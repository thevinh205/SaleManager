package sale.controller.shop;

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;

import sale.base.BaseSale;
import sale.model.ShopView;
import sale.table.Member;
import sale.table.OrderHeader;
import sale.util.LookupBean;
import sale.util.UserUtil;

public class ShopOrderController extends BaseSale{
	private UserUtil userUtil;
	private LookupBean lookupBean;
	private String errorMessage;
	private List<OrderHeader> listOrderHeader;
	private ShopDetailController shopDetailController;
	
	public String listOrder(){
		if(null == userUtil.getMember())
			return ERROR;
		return SUCCESS;
	}	
	
	public String searchOrder(){
		try{
			ActionContext context = ActionContext.getContext();
			Map<String, Object> params = context.getParameters();
			String idOrderSearch = findParam(params, "idOrderSearch");
			String nameCusSearch = findParam(params, "nameCusSearch");
			String nameEmpSearch = findParam(params, "nameEmpSearch");
			String startDate = findParam(params, "startDate");
			String endDate = findParam(params, "endDate");
			ShopView shopView = shopDetailController.getShopView();
			if(null != shopView){
				listOrderHeader = lookupBean.getOrderDao().getListOrderHeader(shopView.getId());
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public List<OrderHeader> getListOrderHeader() {
		return listOrderHeader;
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

	public void setShopDetailController(ShopDetailController shopDetailController) {
		this.shopDetailController = shopDetailController;
	}
}
