package sale.dao;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import sale.base.BaseDao;
import sale.model.Image;
import sale.model.Member;
import sale.model.mapper.MemberMapper;


public class ImageDao extends BaseDao{
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;
	private HashMap<String, Image> imageCache = new LinkedHashMap<>();
   
	public void setDataSource(DataSource dataSource) {
	  this.dataSource = dataSource;
	  this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}
	
	public List<Image> getListImageForProduct(String productId){
		 Session session = getSessionFactory().openSession();
		 Transaction tx = session.beginTransaction();
		 String sql = "select i from " + Image.class.getName() + " i where i.type='product' and i.parent='" + productId + "'";
		 List<Image> listImage = (List<Image>)session.createQuery(sql).list(); 
		 tx.commit();
		 session.close();
		 return listImage;
	}
	
	public void createImage(Image image){
		String sql = "insert into image "
				+ "(url, parent, type, party_id, create_date) "
				+ "values (:url, :parent, :type, :party_id, :create_date)";
		 Session session = getSessionFactory().openSession();
		 Transaction tx = session.beginTransaction();
		 
		 Query query = session.createQuery(sql);
		 query.setParameter("url", image.getUrl());
		 query.setParameter("parent", image.getParent());
		 query.setParameter("type", image.getType());
		 query.setParameter("party_id", image.getPartyId());
		 query.setParameter("create_date", image.getCreateDate());
		tx.commit();
		session.close();
	}
}
