package sale.base;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import sale.dao.ImageDao;
import sale.dao.ProductDao;

public class BaseDao {
	private SessionFactory factory;
	private ImageDao imageDao;
	private ApplicationContext context; 
	private ProductDao productDao;
	
	public ApplicationContext getContext(){
		if(null == context)
			context = new ClassPathXmlApplicationContext("Beans.xml");
		return context;
	}
	
	public SessionFactory getSessionFactory(){
		if(null == factory){
			factory = new AnnotationConfiguration().configure().buildSessionFactory();
		}
		return factory;
	}
	
	public ImageDao getImageDao(){
		if(null == imageDao){
			imageDao = (ImageDao)getContext().getBean("imageDao");
		}
		return imageDao;
	}
	
	public ProductDao getProductDao(){
		if(null == productDao)
			productDao = (ProductDao)getContext().getBean("productDao");
		return productDao;
	}

}
