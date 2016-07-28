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
	private List<String> listPage;
	private String indexPage;
	
	public String productList(){
		if(null == userUtil.getMember())
			return ERROR;
		String page = findParam("page");
		if(null != page && !page.trim().equals(indexPage)){
			getProductListByPage(page.trim());
		}
		setActionURL(getPath());
		return SUCCESS;
	}
	
	public void getProductListByPage(String index){
		try{
			indexPage = index;
			int countProduct = lookupBean.getProductDao().getCountProduct();
			int total = countProduct/10;
			if(countProduct%10 > 0) total += 1;
			listPage = getListPage(Integer.parseInt(indexPage), total);
			int groupId = getGroupId();
			productList = lookupBean.getProductDao().searchProduct(idProdSearch, nameProdSearch, groupId, 10, (Integer.parseInt(index)-1)*10);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public String searchProductList(){
		try{
			idProdSearch = idProdSearch.trim();
			nameProdSearch = nameProdSearch.trim();
			int groupId = getGroupId();
			productList = lookupBean.getProductDao().searchProduct(idProdSearch, nameProdSearch, groupId, 10, 0);
			indexPage = "1";
			int countProduct = lookupBean.getProductDao().getCountProduct();
			int total = countProduct/10;
			if(countProduct%10 > 0) total += 1;
			listPage = getListPage(Integer.parseInt(indexPage), 7);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public int getGroupId(){
		int groupId = 0;
		if(null != groupProduct){
			CategoryProduct categoryProduct = lookupBean.getProductDao().getCategoryByName(groupProduct);
			if(null != categoryProduct)
				groupId = categoryProduct.getId();
		}
		return groupId;
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

	public List<String> getListPage() {
		return listPage;
	}

	public void setListPage(List<String> listPage) {
		this.listPage = listPage;
	}

	public String getIndexPage() {
		return indexPage;
	}

	public void setIndexPage(String indexPage) {
		this.indexPage = indexPage;
	}
	
}
