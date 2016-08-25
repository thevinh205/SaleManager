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

public class ShopDao extends BaseDao{
	private HashMap<Integer, Shop> shopCache = new LinkedHashMap<>();
	
	/**
	 * Get list shop from db and check to put to shop cache
	 * @return all shop
	 */
	public List<Shop> getListShop(){
		Session session = getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String sql = "select s from " + Shop.class.getName() + " i";
		List<Shop> listShop = (List<Shop>)session.createQuery(sql).list(); 
		if(null != listShop){
			for(Shop shop : listShop){
				if(!shopCache.containsKey(shop.getId())){
					shopCache.put(shop.getId(), shop);
				}
			}
		}
		tx.commit();
		session.close();
		return listShop;
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
		       sql += "where p.id in (select rl.productId from shop_party_relationship rl "
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
