package sale.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import sale.dao.ImageDao;
import sale.dao.MemberDao;
import sale.dao.OrderDao;
import sale.dao.ProductDao;
import sale.dao.ShopDao;
import sale.dao.StatusDao;

public class LookupBean {
	private ApplicationContext context;
	private MemberDao memberDao;
	private ProductDao productDao;
	private ImageDao imageDao;
	private ShopDao shopDao;
	private OrderDao orderDao;
	private StatusDao statusDao;
	
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

	public ShopDao getShopDao() {
		if(null == shopDao)
			shopDao = (ShopDao)getContext().getBean("shopDao");
		return shopDao;
	}

	public OrderDao getOrderDao() {
		if(null == orderDao)
			orderDao = (OrderDao)getContext().getBean("orderDao");
		return orderDao;
	}

	public StatusDao getStatusDao() {
		if(null == statusDao)
			statusDao = (StatusDao)getContext().getBean("statusDao");
		return statusDao;
	}
}
