package sale.dao;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.sun.org.apache.bcel.internal.generic.ISUB;

import sale.base.BaseDao;
import sale.model.Image;
import sale.model.Product;
import sale.model.ProductTable;
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
	public List<ProductTable> getListProductOfShop(int shopId){
		Session session = getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String sql =  "select p from " + ProductTable.class.getName() + " p" + 
					  "where p.id in (select rl.productId from " + ShopPartyRelationship.class.getName() +" rl " +
		       						  "where rl.type=:type and rl.shopId=:shopId)";
		Query query = session.createQuery(sql);
		query.setParameter("type", "product");
		query.setParameter("shopId", shopId);
		List<ProductTable> listProduct = (List<ProductTable>)query.list(); 
		tx.commit();
		session.close();
		return listProduct;
	}
	
	/*
	 * Search product of shop
	 * Search by page index
	 */
	public List<Product> searchProductOfShop(int shopId, String productId, String productName, String type, int startIndex, int count){
		Session session = getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String sql = "select rl.count, p from " + ProductTable.class.getName() + " p, " + ShopPartyRelationship.class.getName() + " rl where 1=1";
		if(null == productId || productId.trim().length() == 0)
			sql += " and p.id in (select rl.productId from " +
					" rl where rl.type=:type and rl.shopId=:shopId)";
		else sql += " and p.id in (select rl.productId from " + 
					" rl where rl.type=:type and rl.shopId=:shopId and rl.productId like :productId)"; 
		if(null != productName && productName.trim().length() != 0)
			sql += " and p.name like :productName ";
		if(null != type && type.trim().length() != 0)
			sql += " and p.categoryName = :type";
		Query query = session.createQuery(sql);		
		query.setParameter("type", "product");
		query.setParameter("shopId", shopId);
		if(null != productId && productId.trim().length() != 0)
			query.setParameter("productId", "%" + productId + "%");
		if(null != productName && productName.trim().length() != 0)
			query.setParameter("productName", "%" + productName + "%");
		if(null != type && type.trim().length() != 0)
			query.setParameter("type", type);
		query.setFirstResult(startIndex);
		query.setMaxResults(count);
		List<Object[]> listObject = (List<Object[]>)query.list(); 
		tx.commit();
		session.close();
		List<Product> listProduct = new LinkedList<>();
		if(null != listObject && listObject.size()>0){
			for(Object[] object : listObject){
				Product productView = converToProductView((ProductTable)object[1]);
				productView.setCount((Integer)object[0]);
				listProduct.add(productView);
			}
		}
		return listProduct;
	}
}
