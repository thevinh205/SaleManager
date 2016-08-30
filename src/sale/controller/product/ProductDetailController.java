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

public class ProductDetailController extends BaseSale{
	private UserUtil userUtil;
	private LookupBean lookupBean;
	private Product product;
	
	public String productDetail(){
		if(null == userUtil.getMember())
			return ERROR;
		if(null == product){
			String productId = findParam("productId");
			if(null != productId)
				product = lookupBean.getProductDao().getProduct(productId);
		}
		return SUCCESS;
	}	

	public String getImageShow(){
		if(null != product && null != product.getImages() && product.getImages().size()>0){
			return product.getImages().get(0).getUrl();
		}
		return "";
	}
	
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public void setUserUtil(UserUtil userUtil) {
		this.userUtil = userUtil;
	}

	public void setLookupBean(LookupBean lookupBean) {
		this.lookupBean = lookupBean;
	}
	
}
