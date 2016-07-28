package sale.dao;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import sale.model.CategoryProduct;
import sale.model.Product;
import sale.model.mapper.CategoryProductMapper;
import sale.model.mapper.ProductMapper;


public class ProductDao {
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
	
	public Product getProductDB(String productId){
		try{
			String sql = "select * from product where id = " + productId;
			Product product = jdbcTemplateObject.queryForObject(sql, new ProductMapper());
			return product;
		}catch (Exception e) {
			
		}
		return null;
		
	}
	
	public void createProduct(Product product, int inventory){
		String sql = "insert into product "
				+ "(id, name, group_id, description, create_date, status,category_name, avatar) "
				+ "values ('"
				+ product.getId() + "','"
				+ product.getProductName() + "','"
				+ product.getGroupId() + "','"
				+ product.getDescription() 
				+ "',?, '"
				+ product.getStatus() + "','"
				+ product.getCategoryName() + "','"
				+ product.getAvatar() 
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
}
