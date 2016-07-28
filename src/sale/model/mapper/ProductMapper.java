package sale.model.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;
import sale.model.Product;

public class ProductMapper implements RowMapper<Product> {
   public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
	  Product product = new Product();
	  product.setId(rs.getString("id"));
	  product.setProductName(rs.getString("name"));
	  product.setGroupId(rs.getInt("group_id"));
	  product.setDescription(rs.getString("description"));
	  if(null != rs.getTimestamp("create_date"))
		  product.setCreateDate(new Date(rs.getTimestamp("create_date").getTime()));
	  product.setType(rs.getString("type"));
	  product.setColor(rs.getString("color"));
	  product.setSize(rs.getString("size"));
	  product.setWeight(rs.getInt("weight"));
	  product.setStyle(rs.getString("style"));
	  product.setAvatar(rs.getString("avatar"));
	  product.setStatus(rs.getString("status"));
	  product.setCategoryName(rs.getString("category_name"));
      return product;
   }
}
