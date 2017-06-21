package sale.controller.order;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;

import sale.base.BaseSale;
import sale.controller.shop.ShopDetailController;
import sale.model.Product;
import sale.model.ShopView;
import sale.table.Member;
import sale.table.OrderHeader;
import sale.table.OrderPartyRelationship;
import sale.util.LookupBean;
import sale.util.UserUtil;

public class CreateOrderController extends BaseSale{
	private UserUtil userUtil;
	private LookupBean lookupBean;
	private Product productSelect;
	private int countProduct;
	private List<Product> listProductOrder = new LinkedList<Product>();
	private String errorMessage;
	private ShopDetailController shopDetailController;
	
	public void setShopDetailController(ShopDetailController shopDetailController) {
		this.shopDetailController = shopDetailController;
	}
	
	public String addProductOrder(){
		try{
			String productIdAdd = findParam("productIdAdd");
			String count = findParam("countProduct");
			if(null != count && isNumeric(count))
				countProduct = Integer.parseInt(count);
			if(!isBlankOrNull(productIdAdd)){
				productSelect = lookupBean.getProductDao().getProduct(productIdAdd);
			}
		}catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
	}
	
	public String createOrder(){
		try{
			if(null == userUtil.getMember())
				return ERROR;
			errorMessage = null;
			ActionContext context = ActionContext.getContext();
			Map<String, Object> params = context.getParameters();
			String cusName = findParam(params, "customerName");
			String email = findParam(params, "email");
			String phoneNumber = findParam(params, "phoneNumber");
			String address = findParam(params, "cusAddress");
			String shipperId = findParam(params, "shipperId");
			String listProductString = findParam(params, "listProductString");
			if(validateInput(cusName, phoneNumber, address, shipperId, listProductString)){
				saveOrder(cusName, email, phoneNumber, address, shipperId, listProductString);
			}
		}catch (Exception e) {
			e.printStackTrace();
			errorMessage = "Có lỗi trong quá trình tạo đơn hàng !";
			return ERROR;
		}
		return SUCCESS;
	}
	
	public void saveOrder(String cusName, String email, String phoneNumber, 
							String address, String shipperId, String listProdId){
		Long totalPrice = 0L;
		ShopView shopView = shopDetailController.getShopView();
		//create order party relationship
		String[] listIds = listProdId.split(";");
		if(listIds.length >= 1 && null != shopView){
//			Member member = lookupBean.getMemberDao().getMemberByPhoneNumber(phoneNumber);
//			//create customer
//			if(null == member){
//				member = new Member();
//				member.setUserName(phoneNumber);
//				member.setEmail(email);
//				member.setPhoneNumber(phoneNumber);
//				member.setName(cusName);
//				member.setAddress(address);
//				member.setLevel(1);
//				member.setState("open");
//				member.setBirthDate(new Date());
//				member.setCreateDate(new Date());
//				member.setRole("customer");
//				
//				lookupBean.getMemberDao().registerMember(member, phoneNumber);
//			}
			
			int orderId = lookupBean.getOrderDao().getMaxOrderId() + 1;
			List<OrderPartyRelationship> listOrderPartyRelationship = new LinkedList<>();
			for(int i=0; i<listIds.length; i++){
				if(listIds[i].trim().length() > 0){
					String[] productIds = listIds[i].split("_");
					if(productIds.length == 2){
						Product product = lookupBean.getProductDao().getProduct(productIds[0]);
						OrderPartyRelationship orderPartyRelationship = new OrderPartyRelationship();
						orderPartyRelationship.setOrderId(orderId);
						orderPartyRelationship.setShopId(shopView.getId());
						orderPartyRelationship.setProductId(productIds[0]);
						orderPartyRelationship.setCount(Integer.parseInt(productIds[1]));
						orderPartyRelationship.setStatus("open");
						orderPartyRelationship.setCreateDate(new Date());
						listOrderPartyRelationship.add(orderPartyRelationship);
						
						totalPrice += (product.getPriceSell() * Integer.parseInt(productIds[1]));
					}
				}
			}
		
			//create order header
			OrderHeader orderHeader = new OrderHeader();
			orderHeader.setId(orderId);
			orderHeader.setCreateDate(new Date());
			orderHeader.setCustomerUsername("guess");
			orderHeader.setEmployeeUsername(userUtil.getMember().getUserName());
			//orderHeader.setShipperId(Integer.parseInt(shipperId));
			orderHeader.setStatus("resolve");
			orderHeader.setCreateDate(new Date());
			orderHeader.setRequireDate(new Date());
			orderHeader.setShopId(shopView.getId());
			orderHeader.setTotalPrice(totalPrice);
			
			lookupBean.getOrderDao().createOrder(orderHeader, listOrderPartyRelationship);
		}
	}
	
	public Boolean validateInput(String cusName, String phoneNumber, 
									String address, String shipperId, String listProductString){
//		if(isBlankOrNull(cusName)){
//			errorMessage = "Tên khách hàng không được để trống !";
//			return false;
//		}
//		
//		if(isBlankOrNull(phoneNumber)){
//			errorMessage = "Số điệnt thoại không được để trống !";
//			return false;
//		}
//		
//		if(!isNumeric(phoneNumber)){
//			errorMessage = "Số điện thoại phải là số !";
//			return false;
//		}
//		
//		if(isBlankOrNull(address)){
//			errorMessage = "Địa chỉ không được để trống !";
//			return false;
//		}
//		
//		if(isBlankOrNull(shipperId)){
//			errorMessage = "Vui lòng chọn nhà vận chuyển !";
//			return false;
//		}
		
		if(isBlankOrNull(listProductString)){
			errorMessage = "Vui lòng chọn sản phẩm !";
			return false;
		}
		return true;
		
	}
	
	public UserUtil getUserUtil() {
		return userUtil;
	}
	
	public Product getProductSelect() {
		return productSelect;
	}
	
	public Integer getCountProduct() {
		return countProduct;
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

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
