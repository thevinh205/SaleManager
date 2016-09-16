package sale.controller.product;

import java.util.List;

import sale.base.BaseSale;
import sale.controller.shop.ShopDetailController;
import sale.model.Product;
import sale.model.ShopView;
import sale.util.LookupBean;
import sale.util.UserUtil;

public class SearchProductController extends BaseSale{
	private UserUtil userUtil;
	private LookupBean lookupBean;
	private List<Product> listProductSearch;
	private ShopDetailController shopDetailController;
	private List<String> listPageProd;
	private String indexPage = "1";
	private int totalPage;
	
	public String listProductOrderSearch(){
		if(null == userUtil.getMember())
			return ERROR;
		String page = findParam("page");
		indexPage = findParam("pageCurrent");
		if(isBlankOrNull(indexPage))
			indexPage = "1";
		if(!isBlankOrNull(page)){
			indexPage = page.trim();
			searchProductOrder();
		}
		return SUCCESS;
	}

	public String searchProductOrder(){
		try{
			if(null == userUtil.getMember())
				return ERROR;
			ShopView shopView = shopDetailController.getShopView();
			String idProdSearch = findParam("idProdSearch");
			String nameProdSearch = findParam("nameProdSearch");
			String groupProduct = findParam("groupProductSearch");
			if(null != shopView){
				listProductSearch = lookupBean.getShopDao().searchProductOfShop(shopView.getId(), idProdSearch, 
										nameProdSearch, groupProduct, (Integer.parseInt(indexPage)-1)*5, 5);
				int countProduct = lookupBean.getShopDao().getCountProductOfShop(shopView.getId(), 
						idProdSearch, nameProdSearch, groupProduct).intValue();
				totalPage = countProduct/5;
				if(countProduct % 5 > 0) totalPage += 1;
				listPageProd = getListPage(Integer.parseInt(indexPage), totalPage);
			}
		}catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
	}
	
	public List<String> getListPageProd() {
		return listPageProd;
	}

	public void setListPageProd(List<String> listPageProd) {
		this.listPageProd = listPageProd;
	}
	
	public String getIndexPage() {
		return indexPage;
	}

	public int getTotalPage() {
		return totalPage;
	}
	
	public List<Product> getListProductSearch() {
		return listProductSearch;
	}

	public void setShopDetailController(ShopDetailController shopDetailController) {
		this.shopDetailController = shopDetailController;
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
}
