package sale.controller.product;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionContext;

import sale.base.BaseSale;
import sale.model.CategoryProduct;
import sale.model.Image;
import sale.model.Price;
import sale.model.Product;
import sale.util.LookupBean;
import sale.util.UserUtil;

public class EditProductController extends BaseSale{
	private UserUtil userUtil;
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
	private List<Image> listImage;
	private ProductListController productListController;
	private String totalFile;
	private CategoryProductController categoryProductController;
	private List<CategoryProduct> categoryList;
	
	
	public String editProduct(){
		if(null == userUtil.getMember())
			return ERROR;
		loadContentEdit();
		setActionURL(getPath());
		return SUCCESS;
	}
	
	public void loadContentEdit(){
		productId = findParam("id");
		if(null != productId){
			Product product = lookupBean.getProductDao().getProduct(productId);
			if(null != product){
				productName = product.getProductName();
				description = product.getDescription();
				listImage = product.getImages();
				sellPrice = product.getPriceSell().toString();
				buyPrice = product.getPriceBuy().toString();
			}
		}
	}
	
	public String editProductAction(){
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
		if(null == inventory || inventory.trim().length() == 0)
			inventory = "0";
		description = findParam(params, "description");
		String result = validateInput();
		if(SUCCESS.equals(result))
			updateProduct();
		}catch (Exception e) {
			e.printStackTrace();
			errorMessage = "Có lỗi trong quá trình tạo mới sản phẩm!";
		}
		return SUCCESS;
	}
	
	public void updateProduct(){
		try{
			checkDeleteImage(productId, Integer.parseInt(totalFile));
			Product product = new Product();
			product.setId(productId);
			product.setProductName(productName);
			CategoryProduct categoryProduct = lookupBean.getProductDao().getCategoryByName(groupProduct);
			if(null != categoryProduct)
				product.setGroupId(categoryProduct.getId());
			else product.setGroupId(0);
			product.setPriceBuy(Long.parseLong(buyPrice));
			product.setPriceSell(Long.parseLong(sellPrice));
			product.setStatus("open");
			product.setDescription(description);
			product.setAvatar(productId + "_0_thumb." + typeAvatar);
			product.setCategoryName(groupProduct);
			lookupBean.getProductDao().updateProduct(product, Integer.parseInt(inventory));
			reloadListProduct(product);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void checkDeleteImage(String productId, int countFile){
		Product product = lookupBean.getProductDao().getProduct(productId);
		if(null != product && null != product.getImages()){
			if(countFile < product.getImages().size()){
				for(int i=countFile; i< product.getImages().size(); i++){
					lookupBean.getImageDao().deleteImageProduct(productId, 
																product.getImages().get(i).getUrl(),
																product.getImages().get(i).getUrlThumb());
				}
			}
		}
	}
	
	public void reloadListProduct(Product productUpdate){
		if(null != productUpdate){
			List<Product> listProduct = productListController.getProductList();
			if(null != listProduct){
				for(Product product : listProduct){
					if(product.getId().equals(productUpdate.getId())){
						product.setProductName(productUpdate.getProductName());
						product.setGroupId(productUpdate.getGroupId());
						product.setPriceBuy(productUpdate.getPriceBuy());
						product.setPriceSell(productUpdate.getPriceSell());
						product.setStatus(productUpdate.getStatus());
						product.setDescription(productUpdate.getDescription());
						product.setAvatar(productUpdate.getAvatar());
						product.setCategoryName(productUpdate.getCategoryName());
						break;
					}
				}
			}
		}
	}
	
	public List<CategoryProduct> getCategoryList() {
		categoryList = categoryProductController.getListCategoryActive();
		return categoryList;
	}

	public String validateInput(){
		productId = productId.trim();
		productName = productName.trim();
		sellPrice = sellPrice.trim().replaceAll(",", "");
		buyPrice = buyPrice.trim().replaceAll(",", "");
		inventory = inventory.trim();
		
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

	public List<Image> getListImage() {
		return listImage;
	}

	public void setListImage(List<Image> listImage) {
		this.listImage = listImage;
	}

	public void setProductListController(ProductListController productListController) {
		this.productListController = productListController;
	}

	public String getTotalFile() {
		return totalFile;
	}

	public void setTotalFile(String totalFile) {
		this.totalFile = totalFile;
	}
	
	public void setCategoryProductController(CategoryProductController categoryProductController) {
		this.categoryProductController = categoryProductController;
	}
}
