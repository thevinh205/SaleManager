package sale.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import sale.base.BaseDao;
import sale.model.Product;
import sale.table.Member;
import sale.table.OrderHeader;
import sale.table.OrderPartyRelationship;

public class OrderDao extends BaseDao{
	
	public List<OrderHeader> searchOrderHeader(int shopId, int orderId, String cusName, String empName, Date startDate, Date endDate){
		Session session = getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String sql = "select oh from " + OrderHeader.class.getName() + " oh where oh.shopId=:shopId ";
		
		if(orderId != 0){
			sql += " and oh.id = :orderId";
		}
		
		if(null != cusName  && cusName.length() > 0){
			sql += " and oh.customerUsername in (select m.userName from " 
					+ Member.class.getName() + " m where LOWER(m.name) like :cusName and m.role='customer')";
		}
			
		if(null != empName && empName.length()>0){
			sql += " and oh.employeeUsername in (select m.userName from " 
					+ Member.class.getName() + " m where LOWER(m.name) like :empName and m.role='employee')";
		}
		
		if(null != startDate){
			sql += " and  oh.createDate >= :startDate";
		}
		
		if(null != endDate){
			sql += " and oh.createDate <= :endDate";
		}
		
		Query query = session.createQuery(sql);
		query.setParameter("shopId", shopId);
		
		if(orderId != 0)
			query.setParameter("orderId", orderId);
		
		if(null != cusName  && cusName.length() > 0)
			query.setParameter("cusName", "%" + cusName.toLowerCase() + "%");
		
		if(null != empName && empName.length() > 0)
			query.setParameter("empName", "%" + empName.toLowerCase() + "%");
		
		if(null != startDate)
			query.setParameter("startDate", startDate);
		
		if(null != endDate)
			query.setParameter("endDate", endDate);
		
		List<OrderHeader> listOrderHeader = query.list();
		tx.commit();
		session.close();
		return listOrderHeader;
	}
	
	public void createOrder(OrderHeader orderHeader, List<OrderPartyRelationship> productList){
		Session session = getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try{
			//create orderHeader
			session.save(orderHeader);
			
			//create orderPartyRelationship
			for(OrderPartyRelationship OrderPartyRelationship : productList){
				session.save(OrderPartyRelationship);
			}
			tx.commit();
		}catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}
		session.close();
	}
	
	public int getMaxOrderId(){
		try{
			Session session = getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			String sql = "select oh.id from " + OrderHeader.class.getName() + " oh order by oh.id desc"; 
			Query query = session.createQuery(sql);
			query.setMaxResults(1);
			int orderIdMax = (Integer)query.uniqueResult();
			tx.commit();
			session.close();
			return orderIdMax;
		}catch (Exception e) {
			return 0;
		}
	}
}
