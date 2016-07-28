package sale.model.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

import sale.model.Image;

public class ImageMapper implements RowMapper<Image> {
	   public Image mapRow(ResultSet rs, int rowNum) throws SQLException {
		   	  Image image = new Image();
		   	  image.setId(rs.getInt("id"));
		   	  image.setUrl(rs.getString("url"));
		   	  image.setParent(rs.getString("parent"));
		   	  image.setPartyId(rs.getInt("party_id"));
		   	  image.setType(rs.getString("type"));
		      if(null != rs.getTimestamp("create_date"))
		    	  image.setCreateDate(new Date(rs.getTimestamp("create_date").getTime()));
		      return image;
		   }
		}
