package sale.dao;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.collections.map.HashedMap;
import org.hibernate.Session;
import org.hibernate.Transaction;

import sale.base.BaseDao;
import sale.table.Status;

public class ShipmentDao extends BaseDao{
	private List<Status> listStatusOrder;
	private HashMap<String, Status> statusOrderHasmap;	
	
	public Status getStatusOrder(String statusKey){
		if(null == statusOrderHasmap){
			statusOrderHasmap = new LinkedHashMap<>();
			for(Status status : getStatusListOrder()){
				if(!statusOrderHasmap.containsKey(status.getKey()))
					statusOrderHasmap.put(status.getKey(), status);
			}
		}
		return statusOrderHasmap.get(statusKey);
	}
	
	public List<Status> getStatusListOrder(){
		if(null == listStatusOrder){
			listStatusOrder = getStatusListOrderDb();
		}
		return listStatusOrder;
	}
	
	public List<Status> getStatusListOrderDb(){
		Session session = getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String sql = "select st from " + Status.class.getName() + " st where type='order'";
		List<Status> listStatus = session.createQuery(sql).list();
		tx.commit();
		session.close();
		return listStatus;
	}
}
