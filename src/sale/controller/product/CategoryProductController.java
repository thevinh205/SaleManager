package sale.controller.product;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;

import sale.base.BaseSale;
import sale.model.CategoryProduct;
import sale.model.Product;
import sale.util.LookupBean;
import sale.util.UserUtil;

public class CategoryProductController extends BaseSale{
	private UserUtil userUtil;
	private LookupBean lookupBean;
	private String errorMessage;
	private List<CategoryProduct> categoryProducts;
	private List<CategoryProduct> listCategoryActive; 
	public String listCategory(){
		if(null == userUtil.getMember())
			return ERROR;
		setActionURL(getPath());
		return SUCCESS;
	}
	
	/**
	 * add category product
	 * @return
	 */
	public String addCategory(){
		try{
			if(null == userUtil.getMember())
				return ERROR;	
			ActionContext context = ActionContext.getContext();
			Map<String, Object> params = context.getParameters();
			String categoryName = findParam(params, "categoryName");
			String groupStatus = findParam(params, "groupStatus");
			String description = findParam(params, "description");
			if(null != categoryName && categoryName.length() > 0){
				CategoryProduct categoryProduct = new CategoryProduct();
				categoryProduct.setName(categoryName);
				if(null != description)
					categoryProduct.setStatus(groupStatus);
				if(null != description)
					categoryProduct.setDescription(description);
				lookupBean.getProductDao().addCategory(categoryProduct);
				categoryProducts = lookupBean.getProductDao().getListCategoryProduct();
				listCategoryActive = null;
			}
			else return ERROR;
		}catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
	}
	
	public String deleteCategory(){
		try{
			if(null == userUtil.getMember())
				return ERROR;	
			String idDelete = findParam("idDelete");
			if(!isBlankOrNull(idDelete)){
				lookupBean.getProductDao().deleteCategory(Integer.parseInt(idDelete));
				categoryProducts = lookupBean.getProductDao().getListCategoryProduct();
				listCategoryActive = null;
			}
		}catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
	}
	
	public String updateCategory(){
		try{
			if(null == userUtil.getMember())
				return ERROR;	
			ActionContext context = ActionContext.getContext();
			Map<String, Object> params = context.getParameters();
			String categoryName = findParam(params, "name");
			String status = findParam(params, "status");
			String description = findParam(params, "description");
			String categoryId = findParam(params, "categoryId");
			if(!isBlankOrNull(categoryName) && !isBlankOrNull(categoryId)){
				CategoryProduct categoryProduct = new CategoryProduct();
				categoryProduct.setId(Integer.parseInt(categoryId));
				categoryProduct.setName(categoryName);
				categoryProduct.setDescription(description);
				categoryProduct.setStatus(status);
				lookupBean.getProductDao().updateCategory(categoryProduct);
				categoryProducts = lookupBean.getProductDao().getListCategoryProduct();
				listCategoryActive = null;
			}
		}catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
	}
	
	public List<CategoryProduct> getCategoryProducts() {
		if(null == categoryProducts)
			categoryProducts = lookupBean.getProductDao().getListCategoryProduct();
		return categoryProducts;
	}

	public void setCategoryProducts(List<CategoryProduct> categoryProducts) {
		this.categoryProducts = categoryProducts;
	}
	
	public List<CategoryProduct> getListCategoryActive() {
		if(null == listCategoryActive){
			listCategoryActive = new LinkedList<CategoryProduct>();
			for(CategoryProduct categoryProduct : getCategoryProducts()){
				if(categoryProduct.getStatus().equals("open"))
					listCategoryActive.add(categoryProduct);
			}
		}
		return listCategoryActive;
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
}
