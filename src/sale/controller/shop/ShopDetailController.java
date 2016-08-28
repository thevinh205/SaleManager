package sale.controller.shop;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import sale.base.BaseSale;
import sale.model.CategoryProduct;
import sale.model.Member;
import sale.model.Product;
import sale.model.Shop;
import sale.model.ShopPartyRelationship;
import sale.model.ShopView;
import sale.util.LookupBean;
import sale.util.UserUtil;

public class ShopDetailController extends BaseSale{
	private UserUtil userUtil;
	private LookupBean lookupBean;
	private ShopView shopView;
	private String idProdSearch;
	private String nameProdSearch;
	private String groupProduct;
	private List<Product> productList;
	private List<CategoryProduct> categoryList;
	private List<Member> employeeList;
	private List<Member> allEmployee;
	private String errorMessage;
	
	public String shopDetail(){
		try{
			if(null == userUtil.getMember())
				return ERROR;
			String shopId = findParam("shopId");
			if(!isBlankOrNull(shopId) && (null == shopView || shopView.getId() != Integer.parseInt(shopId))){
				shopView = lookupBean.getShopDao().getShop(Integer.parseInt(shopId));
				productList = null;
				employeeList  = null;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String searchProduct(){
		try{
			if(null != shopView){
				if(null != groupProduct && groupProduct.equals("Tất cả"))
					groupProduct = "";
				productList = lookupBean.getShopDao().searchProductOfShop(shopView.getId(), idProdSearch, nameProdSearch, groupProduct, 0, 10);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public List<CategoryProduct> getCategoryList() {
		try{
		if(null == categoryList){
			categoryList = new LinkedList<>(lookupBean.getProductDao().getListCategoryProduct());
			CategoryProduct categoryProduct = new CategoryProduct();
			categoryProduct.setId(0);
			categoryProduct.setName("Tất cả");
			categoryList.add(0, categoryProduct);
		}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return categoryList;
	}
	
	public String addProductToShop(){
		if(null == userUtil.getMember())
			return ERROR;
		String productIdAdd = findParam("productIdAdd");
		String countProduct = findParam("countProduct");
		if(!isBlankOrNull(productIdAdd)){
			int count = 0;
			if(null != shopView && !isBlankOrNull(countProduct))
				count = Integer.parseInt(countProduct);
			ShopPartyRelationship shopPartyRelationship = new ShopPartyRelationship();
			shopPartyRelationship.setShopId(shopView.getId());
			shopPartyRelationship.setProductId(productIdAdd);
			shopPartyRelationship.setCount(count);
			shopPartyRelationship.setType("product");
			shopPartyRelationship.setCreateDate(new Date());
			lookupBean.getShopDao().saveShopParyRelationship(shopPartyRelationship);
		}
		return SUCCESS;
	}
	
	public List<Member> getEmployeeList() {
		try{
			if(null == employeeList && null != shopView){
				employeeList = lookupBean.getShopDao().getlistEmployeeOfShop(shopView.getId());
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return employeeList;
	}

	public List<Member> getAllEmployee() {
		try{
			if(null == allEmployee)
				allEmployee = lookupBean.getMemberDao().getAllEmployee();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return allEmployee;
	}
	
	public String addEmployeeToShop(){
		try{
			errorMessage = null;
			if(null == userUtil.getMember())
				return ERROR;
			String empUserName = findParam("empUserName");
			String empPosition = findParam("empPosition");
			if(!isBlankOrNull(empUserName) && !isBlankOrNull(empPosition) && null != shopView){
				Member member = lookupBean.getMemberDao().getMember(empUserName);
				if(null == member || !member.getRole().equals("employee")){
					errorMessage = "Nhân viên này không tồn tại";
				}
				else if(lookupBean.getShopDao().checkEmployeeInShop(shopView.getId(), empUserName, empPosition)){
					errorMessage = "Nhân viên này đã được thêm vào shop!";
				}
				else{
					ShopPartyRelationship shopParty = new ShopPartyRelationship();
					shopParty.setMemberUserName(empUserName);
					shopParty.setShopId(shopView.getId());
					shopParty.setCreateDate(new Date());
					shopParty.setType("employee");
					shopParty.setPosition(empPosition);
					lookupBean.getShopDao().saveShopParyRelationship(shopParty);
					employeeList = lookupBean.getShopDao().getlistEmployeeOfShop(shopView.getId());
				}
				
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String getIdProdSearch() {
		return idProdSearch;
	}

	public String getNameProdSearch() {
		return nameProdSearch;
	}

	public String getGroupProduct() {
		return groupProduct;
	}

	public void setIdProdSearch(String idProdSearch) {
		this.idProdSearch = idProdSearch;
	}

	public void setNameProdSearch(String nameProdSearch) {
		this.nameProdSearch = nameProdSearch;
	}

	public void setGroupProduct(String groupProduct) {
		this.groupProduct = groupProduct;
	}

	public ShopView getShopView() {
		return shopView;
	}

	public void setShopView(ShopView shopView) {
		this.shopView = shopView;
	}

	public void setUserUtil(UserUtil userUtil) {
		this.userUtil = userUtil;
	}
	public void setLookupBean(LookupBean lookupBean) {
		this.lookupBean = lookupBean;
	}

	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
}
