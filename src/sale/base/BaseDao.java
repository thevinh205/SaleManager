package sale.base;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class BaseDao {
	SessionFactory factory;
	
	public SessionFactory getSessionFactory(){
		if(null == factory){
			factory = new AnnotationConfiguration().configure().buildSessionFactory();
		}
		return factory;
	}

}
