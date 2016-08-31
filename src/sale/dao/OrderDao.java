package sale.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import sale.base.BaseDao;
import sale.table.Member;
import sale.table.OrderHeader;

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
		session.close();
		return listOrderHeader;
	}
}
