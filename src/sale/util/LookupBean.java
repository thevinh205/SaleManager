package sale.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import sale.dao.ImageDao;
import sale.dao.MemberDao;
import sale.dao.ProductDao;

public class LookupBean {
	private ApplicationContext context;
	private MemberDao memberDao;
	private ProductDao productDao;
	private ImageDao imageDao;
	
	public ApplicationContext getContext(){
		if(null == context)
			context = new ClassPathXmlApplicationContext("Beans.xml");
		return context;
	}
	
	public MemberDao getMemberDao() {
		if(null == memberDao)
			memberDao = (MemberDao)getContext().getBean("memberDAO");
		return memberDao;
	}
	
	public ProductDao getProductDao(){
		if(null == productDao)
			productDao = (ProductDao)getContext().getBean("productDao");
		return productDao;
	}

	public ImageDao getImageDao() {
		if(null == imageDao)
			imageDao = (ImageDao)getContext().getBean("imageDao");
		return imageDao;
	}

	
}