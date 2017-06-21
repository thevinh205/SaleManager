package sale.base;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import sale.dao.ImageDao;
import sale.dao.MemberDao;
import sale.dao.OrderDao;
import sale.dao.ProductDao;
import sale.dao.ShopDao;
import sale.model.Product;
import sale.table.ProductTable;

public class BaseDao {
	private SessionFactory factory;
	private ImageDao imageDao;
	private ApplicationContext context; 
	private ProductDao productDao;
	private MemberDao memberDao;
	private OrderDao orderDao;
	private ShopDao shopDao;
	
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

	public MemberDao getMemberDao() {
		if(null == memberDao)
			memberDao = (MemberDao)getContext().getBean("memberDao");
		return memberDao;
	}
	
	public OrderDao getOrderDao() {
		if(null == orderDao)
			orderDao = (OrderDao)getContext().getBean("orderDao");
		return orderDao;
	}
	
	public ShopDao getShopDao() {
		if(null == shopDao)
			shopDao = (ShopDao)getContext().getBean("shopDao");
		return shopDao;
	}

	public Product converToProductView(ProductTable productTable){
		Product product = new Product();
		product.setId(productTable.getId());
		product.setProductName(productTable.getProductName());
		product.setGroupId(productTable.getGroupId());
		product.setDescription(productTable.getDescription());
		product.setCreateDate(productTable.getCreateDate());
		product.setType(productTable.getType());
		product.setColor(productTable.getColor());
		product.setSize(productTable.getSize());
		product.setWeight(productTable.getWeight());
		product.setStyle(productTable.getStyle());
		product.setAvatar(productTable.getAvatar());
		product.setStatus(productTable.getStatus());
		product.setCategoryName(productTable.getCategoryName());
		product.setPriceBuy(productTable.getPriceBuy());
		product.setPriceSell(productTable.getPriceSell());
		return product;
	}
}
