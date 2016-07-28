package sale.model.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

import sale.model.CategoryProduct;

public class CategoryProductMapper implements RowMapper<CategoryProduct> {
   public CategoryProduct mapRow(ResultSet rs, int rowNum) throws SQLException {
	  CategoryProduct categoryProduct = new CategoryProduct();
	  categoryProduct.setId(rs.getInt("id"));
	  categoryProduct.setName(rs.getString("name"));
	  categoryProduct.setDescription(rs.getString("description"));
	  categoryProduct.setStatus(rs.getString("status"));
	  if(null != rs.getTimestamp("create_date"))
		  categoryProduct.setCreateDate(new Date(rs.getTimestamp("create_date").getTime()));
      return categoryProduct;
   }
}
