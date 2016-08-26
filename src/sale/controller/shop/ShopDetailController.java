package sale.controller.shop;

import java.util.LinkedList;
import java.util.List;

import sale.base.BaseSale;
import sale.model.CategoryProduct;
import sale.model.Product;
import sale.model.Shop;
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
	
	public String shopDetail(){
		try{
			if(null == userUtil.getMember())
				return ERROR;
			String shopId = findParam("shopId");
			if(!isBlankOrNull(shopId) && (null == shopView || shopView.getId() != Integer.parseInt(shopId))){
				shopView = lookupBean.getShopDao().getShop(Integer.parseInt(shopId));
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
	
	
}
