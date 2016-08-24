package sale.dao;

import java.io.File;
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
import sale.model.Product;
import sale.util.URLUtil;


public class ImageDao extends BaseDao{
	private HashMap<String, Image> imageCache = new LinkedHashMap<>();
   
	
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
		 Session session = getSessionFactory().openSession();
		 Transaction tx = session.beginTransaction();
		 session.save(image);
		 tx.commit();
		 session.close();
	}
	
	public void deleteImageProduct(String productId){
		try{
			 deleteImageFromDisk(productId);
			 Session session = getSessionFactory().openSession();
			 Transaction tx = session.beginTransaction();
			 String sql = "delete from " + Image.class.getName() + " where parent=:parent";
			 Query query = session.createQuery(sql);
		     query.setString("parent", productId);
		     query.executeUpdate();
			 tx.commit();
			 session.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteImageProduct(String productId, String url, String urlThumb){
		 File fileDelete = new File(URLUtil.PATH_SAVE_DIR + URLUtil.TYPE_PRODUCT + url);
		 fileDelete.delete();
		 File fileThumbDelete = new File(URLUtil.PATH_SAVE_DIR + URLUtil.TYPE_PRODUCT + urlThumb);
		 fileThumbDelete.delete();
		 Session session = getSessionFactory().openSession();
		 Transaction tx = session.beginTransaction();
		 String sql = "delete from " + Image.class.getName() + " where parent=:parent and url = :url";
		 Query query = session.createQuery(sql);
	     query.setString("parent", productId);
	     query.setString("url", url);
	     query.executeUpdate();
		 tx.commit();
		 session.close();
	}
	
	public void deleteImageFromDisk(String productId){
		try{
			Product product = getProductDao().getProduct(productId);
			if(null != product && null != product.getImages()){
				for(Image image : product.getImages()){
					File fileDelete = new File(URLUtil.PATH_SAVE_DIR + URLUtil.TYPE_PRODUCT + image.getUrl());
					fileDelete.delete();
					
					File fileThumbDelete = new File(URLUtil.PATH_SAVE_DIR + URLUtil.TYPE_PRODUCT + image.getUrlThumb());
					fileThumbDelete.delete();
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
