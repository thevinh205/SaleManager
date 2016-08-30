package sale.controller.product;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionContext;

import sale.base.BaseSale;
import sale.model.CategoryProduct;
import sale.model.Product;
import sale.util.LookupBean;
import sale.util.UserUtil;

public class AddProductController extends BaseSale{
	private UserUtil userUtil;
	private CategoryProductController categoryProductController;
	private LookupBean lookupBean;
	private String productId;
	private String productName;
	private String groupProduct;
	private String sellPrice;
	private String buyPrice;
	private String inventory;
	private String errorMessage;
	private String description;
	private String typeAvatar;
	
	private List<CategoryProduct> categoryList;
	
	
	public String addProduct(){
		if(null == userUtil.getMember())
			return ERROR;
		setActionURL(getPath());
		return SUCCESS;
	}
	
	public String addProductAction(){
		try{
		errorMessage = null;
		ActionContext context = ActionContext.getContext();
		Map<String, Object> params = context.getParameters();
		productId = findParam(params, "productId");
		productName = findParam(params, "productName");
		sellPrice = findParam("sellPrice");
		groupProduct = findParam(params, "groupProduct");
		buyPrice = findParam(params, "buyPrice");
		inventory = findParam(params, "inventory");
		description = findParam(params, "description");
		String result = validateInput();
		if(SUCCESS.equals(result))
			createProduct();
		}catch (Exception e) {
			e.printStackTrace();
			errorMessage = "Có lỗi trong quá trình tạo mới sản phẩm!";
		}
		return SUCCESS;
	}
	
	public void createProduct(){
		Product product = new Product();
		product.setId(productId);
		product.setProductName(productName);
		CategoryProduct categoryProduct = lookupBean.getProductDao().getCategoryByName(groupProduct);
		if(null != categoryProduct)
			product.setGroupId(categoryProduct.getId());
		else product.setGroupId(0);
		product.setCreateDate(new Date());
		product.setPriceBuy(Long.parseLong(buyPrice));
		product.setStatus("open");
		product.setDescription(description);
		product.setAvatar(productId + "_0_thumb." + typeAvatar);
		product.setCategoryName(groupProduct);
		product.setPriceBuy(Long.parseLong(buyPrice));
		product.setPriceSell(Long.parseLong(sellPrice));
		lookupBean.getProductDao().createProduct(product, Integer.parseInt(inventory));
	}
	
	public List<CategoryProduct> getCategoryList() {
		categoryList = categoryProductController.getListCategoryActive();
		return categoryList;
	}

	public String validateInput(){
		productId = productId.trim();
		productName = productName.trim();
		sellPrice = sellPrice.trim().replaceAll(",", "");;
		buyPrice = buyPrice.trim().replaceAll(",", "");
		inventory = inventory.trim();
		
		if(productId.length() > 0 && null != lookupBean.getProductDao().getProduct(productId)){
			errorMessage = "Mã sản phẩm này đã tồn tại!";
			return ERROR;
		}
		
		if(productName.length() == 0){
			errorMessage = "Tên sản phẩm không được để trống!";
			return ERROR;
		}
		if(sellPrice.length() == 0){
			errorMessage = "Giá bán không được để trống!";
			return ERROR;
		}
		if(!isNumeric(sellPrice)){
			errorMessage = "Giá bán phải là số!";
			return ERROR;
		}
		if(buyPrice.length() == 0){
			errorMessage = "Giá vốn không được để trống!";
			return ERROR;
		}
		if(!isNumeric(buyPrice)){
			errorMessage = "Giá vốn phải là số!";
			return ERROR;
		}
		if(inventory.length() > 0 && !isNumeric(inventory)){
			errorMessage = "Tồn kho phải là số";
			return ERROR;
		}
		else if(inventory.length() == 0){
			errorMessage = "0";
		}
		return SUCCESS;
	}
	
	public UserUtil getUserUtil() {
		return userUtil;
	}

	public void setUserUtil(UserUtil userUtil) {
		this.userUtil = userUtil;
	}

	public LookupBean getLookupBean() {
		return lookupBean;
	}

	public void setLookupBean(LookupBean lookupBean) {
		this.lookupBean = lookupBean;
	}

	public String getProductId() {
		return productId;
	}

	public String getProductName() {
		return productName;
	}

	public String getGroupProduct() {
		return groupProduct;
	}

	public String getSellPrice() {
		return sellPrice;
	}

	public String getBuyPrice() {
		return buyPrice;
	}

	public String getInventory() {
		return inventory;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public void setGroupProduct(String groupProduct) {
		this.groupProduct = groupProduct;
	}

	public void setSellPrice(String sellPrice) {
		this.sellPrice = sellPrice;
	}

	public void setBuyPrice(String buyPrice) {
		this.buyPrice = buyPrice;
	}

	public void setInventory(String inventory) {
		this.inventory = inventory;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTypeAvatar() {
		return typeAvatar;
	}

	public void setTypeAvatar(String typeAvatar) {
		this.typeAvatar = typeAvatar;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public void setCategoryProductController(CategoryProductController categoryProductController) {
		this.categoryProductController = categoryProductController;
	}
	
	
}
