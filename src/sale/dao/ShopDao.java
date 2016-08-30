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
import sale.table.Image;
import sale.table.Member;
import sale.model.Product;
import sale.table.ProductTable;
import sale.table.Shop;
import sale.table.ShopPartyRelationship;
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
	
	/*
	 * Search product of shop
	 * Search by page index
	 */
	public List<Product> searchProductOfShop(int shopId, String productId, String productName, String type, int startIndex, int count){
		Session session = getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String sql = "select  rl.count, p from " + ProductTable.class.getName() + " p, " + ShopPartyRelationship.class.getName() + " rl where p.id = rl.productId";
		if(null == productId || productId.trim().length() == 0)
			sql += " and rl.type=:type and rl.shopId=:shopId";
		else sql += " and rl.type=:type and rl.shopId=:shopId and rl.productId like :productId"; 
		if(null != productName && productName.trim().length() != 0)
			sql += " and p.name like :productName ";
		if(null != type && type.trim().length() != 0)
			sql += " and p.categoryName = :groupName";
		Query query = session.createQuery(sql);		
		query.setParameter("type", "product");
		query.setParameter("shopId", shopId);
		if(null != productId && productId.trim().length() != 0)
			query.setParameter("productId", "%" + productId + "%");
		if(null != productName && productName.trim().length() != 0)
			query.setParameter("productName", "%" + productName + "%");
		if(null != type && type.trim().length() != 0)
			query.setParameter("groupName", type);
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
	
	/*
	 * get count product of shop
	 * get count by field productId, productName, type
	 */
	public Long getCountProductOfShop(int shopId, String productId, String productName, String type){
		try{
			Session session = getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			String sql = "select  count(p) from " + ProductTable.class.getName() + " p, " + ShopPartyRelationship.class.getName() + " rl where p.id = rl.productId";
			if(null == productId || productId.trim().length() == 0)
				sql += " and rl.type=:type and rl.shopId=:shopId";
			else sql += " and rl.type=:type and rl.shopId=:shopId and rl.productId like :productId"; 
			if(null != productName && productName.trim().length() != 0)
				sql += " and p.name like :productName ";
			if(null != type && type.trim().length() != 0)
				sql += " and p.categoryName = :groupName";
			Query query = session.createQuery(sql);		
			query.setParameter("type", "product");
			query.setParameter("shopId", shopId);
			if(null != productId && productId.trim().length() != 0)
				query.setParameter("productId", "%" + productId + "%");
			if(null != productName && productName.trim().length() != 0)
				query.setParameter("productName", "%" + productName + "%");
			if(null != type && type.trim().length() != 0)
				query.setParameter("groupName", type);
			Long count = (Long)query.uniqueResult();
			tx.commit();
			session.close();
			return count;
		}catch (Exception e) {
			return 0L;
		}
	}
	
	public List<Member> getlistEmployeeOfShop(int shopId){
		Session session = getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String sql = "select rl.position, m from " + Member.class.getName() + " m, " 
				+ ShopPartyRelationship.class.getName() + " rl where m.userName=rl.memberUserName and rl.type=:type and rl.shopId=:shopId";
		Query query = session.createQuery(sql);
		query.setParameter("type", "employee");
		query.setParameter("shopId", shopId);
		List<Object[]> listObject = (List<Object[]>)query.list();
		tx.commit();
		session.close();
		List<Member> listEmployee = new LinkedList<>();
		if(null != listObject){
			for(Object[] object : listObject){
				Member member = new Member();
				member.setId(((Member)object[1]).getId());
				member.setPosition((String)object[0]);
				member.setAddress(((Member)object[1]).getAddress());
				member.setName(((Member)object[1]).getName());
				member.setEmail(((Member)object[1]).getEmail());
				member.setPhoneNumber(((Member)object[1]).getPhoneNumber());
				member.setLevel(((Member)object[1]).getLevel());
				member.setGender(((Member)object[1]).getGender());
				member.setUserName(((Member)object[1]).getUserName());
				listEmployee.add(member);
			}
		}
		return listEmployee;
	}
	
	public Boolean checkEmployeeInShop(int shopId, String employeeUserName, String position){
		try{
			Session session = getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			String sql = "select rl from " + ShopPartyRelationship.class.getName() + 
					" rl where type='employee' and shopId=:shopId and memberUserName=:employeeUserName and position=:position";
			Query query = session.createQuery(sql);
			query.setParameter("shopId", shopId);
			query.setParameter("employeeUserName", employeeUserName);
			query.setParameter("position", position);
			if(null == query.uniqueResult())
				return false;
			tx.commit();
			session.close();
		}catch (Exception e) {
			return false;
		}
		return true;
	}
	
	public Boolean checkProductInShop(int shopId, String productId){
		try{
			Session session = getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			String sql = "select rl from " + ShopPartyRelationship.class.getName() + 
					" rl where type='product' and shopId=:shopId and productId=:productId";
			Query query = session.createQuery(sql);
			query.setParameter("shopId", shopId);
			query.setParameter("productId", productId);
			if(null == query.uniqueResult())
				return false;
			tx.commit();
			session.close();
		}catch (Exception e) {
			return false;
		}
		return true;
	}
	
	public void saveShopParyRelationship(ShopPartyRelationship shopPartyRelationship){
		Session session = getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try{
			session.save(shopPartyRelationship);
		}catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}
		tx.commit();
		session.close();
	}
	
	public void deleteEmployeeInShop(int shopId, String empUsername, String position){
		Session session = getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String sql = "delete from " + ShopPartyRelationship.class.getName() + 
				" where shopId=:shopId and memberUserName=:empUsername and position=:position";
		Query query = session.createQuery(sql);
		query.setParameter("empUsername", empUsername);
		query.setParameter("shopId", shopId);
		query.setParameter("position", position);
		query.executeUpdate();
		tx.commit();
		session.close();
	}
	
	public void deleteProductInShop(int shopId, String productId){
		Session session = getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String sql = "delete from " + ShopPartyRelationship.class.getName() + 
				" where shopId=:shopId and productId=:productId";
		Query query = session.createQuery(sql);
		query.setParameter("shopId", shopId);
		query.setParameter("productId", productId);
		query.executeUpdate();
		tx.commit();
		session.close();
	}
}
