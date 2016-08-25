package sale.dao;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import sale.base.BaseDao;
import sale.model.Image;
import sale.model.Product;
import sale.model.Shop;
import sale.model.ShopPartyRelationship;
import sale.model.ShopView;

public class ShopDao extends BaseDao{
	private HashMap<Integer, ShopView> shopCache;
	
	/**
	 * Get list shop from db and check to put to shop cache
	 * @return all shop
	 */
	public List<Shop> getListShop(){
		Session session = getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String sql = "select s from " + Shop.class.getName() + " s";
		List<Shop> listShop = (List<Shop>)session.createQuery(sql).list(); 
		if(null != listShop && null == shopCache){
			shopCache = new LinkedHashMap<>();
			for(Shop shop : listShop){
				if(!shopCache.containsKey(shop.getId())){
					ShopView shopView = parseShopView(shop);
					shopCache.put(shop.getId(), shopView);
				}
			}
		}
		tx.commit();
		session.close();
		return listShop;
	}
	
	/**
	 * parser from shop to shopview
	 * @param shop
	 * @return shopview
	 */
	public ShopView parseShopView(Shop shop){
		if(null != shop){
			ShopView shopView = new ShopView();
			shopView.setId(shop.getId());
			shopView.setShop(shop);
			return shopView;
		}
		return null;
	}
	
	/**
	 * Get shop view from cache
	 * if not exit, get shop from database
	 * then parser to shopView and put to cache
	 * @param shopId
	 * @return
	 */
	public ShopView getShop(int shopId){
		ShopView shopView = shopCache.get(shopId);
		if(null == shopView){
			Shop shop = getShopFromDb(shopId);
			if(null != shop){
				shopView = parseShopView(shop);
				shopCache.put(shopId, shopView);
			}
		}
		return shopView;
	}
	
	/**
	 * Get shop from database
	 */
	public Shop getShopFromDb(int shopId){
		try{
			Session session = getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			String sql = "select s from " + Shop.class.getName() + " s where s.id=:id";
			Query query = session.createQuery(sql);
			Shop shop = (Shop)query.uniqueResult();
			tx.commit();
			session.close();
			return shop;
		}catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * Get all product of shop
	 * @param shopId
	 * @return
	 */
	public List<Product> getListProductOfShop(int shopId){
		Session session = getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String sql =  "select p from " + Product.class.getName() + " p";
		       sql += "where p.id in (select rl.productId from " + ShopPartyRelationship.class.getName() +" rl "
		       							+ "where rl.type=:type and rl.shopId=:shopId)";
		Query query = session.createQuery(sql);
		query.setParameter("type", "product");
		query.setParameter("shopId", shopId);
		List<Product> listProduct = (List<Product>)query.list(); 
		tx.commit();
		session.close();
		return listProduct;
	}
}
