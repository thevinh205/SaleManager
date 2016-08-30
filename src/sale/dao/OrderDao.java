package sale.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import sale.base.BaseDao;
import sale.table.OrderHeader;

public class OrderDao extends BaseDao{
	
	public List<OrderHeader> getListOrderHeader(int shopId){
		Session session = getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String sql = "select oh from " + OrderHeader.class.getName() + " oh where shopId=:shopId";
		Query query = session.createQuery(sql);
		query.setParameter("shopId", shopId);
		List<OrderHeader> listOrderHeader = query.list();
		tx.commit();
		session.close();
		return listOrderHeader;
	}
	
	public List<OrderHeader> searchOrderHeader(int shopId, int orderId, String cusName, String empName, Date startDate, Date endDate){
		Session session = getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String sql = "select oh from " + OrderHeader.class.getName() + " oh where shopId=:shopId";
		Query query = session.createQuery(sql);
		query.setParameter("shopId", shopId);
		List<OrderHeader> listOrderHeader = query.list();
		session.close();
		return listOrderHeader;
	}
}
