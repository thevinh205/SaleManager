package sale.dao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import sale.base.BaseDao;
import sale.model.CategoryProduct;
import sale.model.Image;
import sale.model.Product;
import sale.model.mapper.CategoryProductMapper;
import sale.model.mapper.ProductMapper;
import sale.util.LookupBean;


public class ProductDao extends BaseDao{
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;
	private HashMap<String, Product> productCache = new LinkedHashMap<>();
	private List<CategoryProduct> listcategory;
   
	public void setDataSource(DataSource dataSource) {
	  this.dataSource = dataSource;
	  this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}
	
	public Product getProduct(String productId){
		Product product = productCache.get(productId);
		if(null == product){
			product = getProductDB(productId);
			if(null != product)
				productCache.put(productId, product);
		}
		return product;
	}
	
	public void reloadProduct(String productId){
		if(productCache.containsKey(productId))
			productCache.remove(productId);
		Product product = getProductDB(productId);
		if(null != product){
			productCache.put(productId, product);
		}
	}
	
	public Product getProductDB(String productId){
		try{
			String sql = "select * from product where id = " + productId;
			Product product = jdbcTemplateObject.queryForObject(sql, new ProductMapper());
			if(null != product){
				List<Image> listImage = getImageDao().getListImageForProduct(productId);
				if(null != listImage)
					product.setImages(listImage);
			}
			return product;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	public void createProduct(Product product, int inventory){
		String sql = "insert into product "
				+ "(id, name, group_id, description, create_date, status,category_name, avatar, price_buy, price_sell) "
				+ "values ('"
				+ product.getId() + "','"
				+ product.getProductName() + "','"
				+ product.getGroupId() + "','"
				+ product.getDescription() 
				+ "',?, '"
				+ product.getStatus() + "','"
				+ product.getCategoryName() + "','"
				+ product.getAvatar() + "','" 
				+ product.getPriceBuy() + "','" 
				+ product.getPriceSell()
				+ "')";
		Timestamp createDate = new Timestamp(product.getCreateDate().getTime());
		jdbcTemplateObject.update(sql, new Object[]{createDate});
	}
	
	public List<Product> searchProduct(String productId, String productName, int category){
		String sql = "select * from product where 1= 1 ";
		if(null != productId && productId.length() > 0)
			sql += " and id = '" + productId + "' ";
		if(null != productName && productName.length() > 0)
			sql += " and name like '%" + productName + "%' ";
		if(category != 0)
			sql += " and group_id = '" + category + "' ";
		List<Product> result = jdbcTemplateObject.query(sql, new ProductMapper());
		return result;
	} 
	
	public List<Product> searchProduct(String productId, String productName, int category, int limit, int ofset){
		String sql = "select * from product where 1= 1 ";
		if(null != productId && productId.length() > 0)
			sql += " and id = '" + productId + "' ";
		if(null != productName && productName.length() > 0)
			sql += " and name like '%" + productName + "%' ";
		if(category != 0)
			sql += " and group_id = '" + category + "' ";
		sql += " limit " + limit + " offset " + ofset;
		List<Product> result = jdbcTemplateObject.query(sql, new ProductMapper());
		return result;
	} 
	
	public void updateProduct(Product product, int inventory){
		String sql = "update product set ";
		sql += " name = '" + product.getProductName()+ "'";
		sql += ", description = '" + product.getDescription() + "'";
		sql += ", group_id = " + product.getGroupId();
		sql += ", category_name = '" + product.getCategoryName()+ "'";
		sql += ", price_buy = " + product.getPriceBuy();
		sql += ", price_sell = " + product.getPriceSell();
		sql += ", avatar = '" + product.getAvatar() + "'";
		sql += " where id = '" + product.getId() + "'" ;
		jdbcTemplateObject.update(sql);
		reloadProduct(product.getId());
	}
	
	public void deleteProduct(String productId){
		getImageDao().deleteImageProduct(productId);
		String sql = "delete from product where id=" + productId;
		jdbcTemplateObject.update(sql);
		reloadProduct(productId);
	}
	
	public CategoryProduct getCategoryByName(String categoryName){
		if(null != getListCategoryProduct()){
			for(CategoryProduct category : listcategory){
				if(category.getName().equals(categoryName))
					return category;
			}
		}
		return null;
	}
	
	public int getCountProduct(){
		String sql = "select count(*) from product";
		int result = jdbcTemplateObject.queryForObject(sql, Integer.class);
		return result;
	}
	
	public List<CategoryProduct> getListCategoryProduct(){
		if(null == listcategory){
			reloadCategoryProduct();
		}
		return listcategory;
	}
	
	public void reloadCategoryProduct(){
		String sql = "select * from category_product";
		listcategory = jdbcTemplateObject.query(sql, new CategoryProductMapper());		
	}
	
	public void addCategory(CategoryProduct categoryProduct){
		String sql = "insert into category_product "
				+ "(name, status, create_date, description) "
				+ "values ('"
				+ categoryProduct.getName() + "','"
				+ categoryProduct.getStatus()+ "',"
				+ "?, '"
				+ categoryProduct.getDescription()
				+ "')";
		Timestamp createDate = new Timestamp(new Date().getTime());
		jdbcTemplateObject.update(sql, new Object[]{createDate});
		reloadCategoryProduct();
	}
	
	public void deleteCategory(int categoryId){
		String sql = "delete from category_product where id = " + categoryId;
		jdbcTemplateObject.update(sql);
		
		//reload list category
		if(null != listcategory){
			for(CategoryProduct categoryProduct : listcategory){
				if(categoryProduct.getId() == categoryId){
					listcategory.remove(categoryProduct);
					return;
				}
			}
		}
	}
	
	public void updateCategory(CategoryProduct categoryProduct){
		String sql = "update category_product set ";
		sql += " name = '" + categoryProduct.getName() + "'";
		sql += " ,description = '" + categoryProduct.getDescription() + "'";
		sql += " ,status = '" + categoryProduct.getStatus() + "'";
		sql += " where id=" + categoryProduct.getId();
		jdbcTemplateObject.update(sql);
		reloadCategoryProduct();
	}
}
