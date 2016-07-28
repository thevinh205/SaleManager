package sale.controller.product;

import java.util.LinkedList;
import java.util.List;

import sale.base.BaseSale;
import sale.model.CategoryProduct;
import sale.model.Product;
import sale.util.LookupBean;
import sale.util.UserUtil;

public class ProductListController extends BaseSale{
	private UserUtil userUtil;
	private LookupBean lookupBean;
	private List<CategoryProduct> categoryList;
	private List<Product> productList;
	private String errorMessage;
	private String idProdSearch;
	private String nameProdSearch;
	private String groupProduct;
	
	public String productList(){
		if(null == userUtil.getMember())
			return ERROR;
		setActionURL(getPath());
		return SUCCESS;
	}
	
	public String searchProductList(){
		idProdSearch = idProdSearch.trim();
		nameProdSearch = nameProdSearch.trim();
		int groupId = 0;
		if(null != groupProduct){
			CategoryProduct categoryProduct = lookupBean.getProductDao().getCategoryByName(groupProduct);
			if(null != categoryProduct)
				groupId = categoryProduct.getId();
		}
		productList = lookupBean.getProductDao().searchProduct(idProdSearch, nameProdSearch, groupId);
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

	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
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

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
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
	
	

}
