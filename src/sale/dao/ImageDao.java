package sale.dao;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import sale.model.Image;
import sale.model.Member;
import sale.model.mapper.ImageMapper;
import sale.model.mapper.MemberMapper;


public class ImageDao {
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;
	private HashMap<String, Image> imageCache = new LinkedHashMap<>();
   
	public void setDataSource(DataSource dataSource) {
	  this.dataSource = dataSource;
	  this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}
	
	public List<Image> getListImageForProduct(String productId){
		String sql = "select * from image where type='product' and parent=" + productId;
		List<Image> listImage = jdbcTemplateObject.query(sql, new ImageMapper()); 
		return listImage;
	}
	
	public void createImage(Image image){
		String sql = "insert into image "
				+ "(url, parent, type, party_id, create_date) "
				+ "values (?, ?, ?, ?, ?)";
		jdbcTemplateObject.update( sql, image.getUrl(), 
										image.getParent(), 
										image.getType(),
										image.getPartyId(),
										new Timestamp(image.getCreateDate().getTime()));
	}
}
