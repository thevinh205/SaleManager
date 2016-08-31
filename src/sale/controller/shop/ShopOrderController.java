package sale.controller.shop;


import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
	private DateFormat df = new SimpleDateFormat("dd/MM/yyyy"); 
	
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
				int orderId = 0;
				Date dateStart = null;
				Date dateEnd = null;
				if(!isBlankOrNull(idOrderSearch))
					orderId = Integer.parseInt(idOrderSearch);
				if(!isBlankOrNull(startDate)){
					java.util.Date date = df.parse(startDate);
					dateStart = new Date(date.getTime());
				}
				if(!isBlankOrNull(endDate)){
					java.util.Date date = df.parse(endDate);
					dateEnd = new Date(date.getTime());
				}
				listOrderHeader = lookupBean.getOrderDao().searchOrderHeader(shopView.getId(), 
						orderId, nameCusSearch, nameEmpSearch, dateStart, dateEnd);
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
	
	public UserUtil getUserUtil() {
		return userUtil;
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
