package sale.dao;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.collections.map.HashedMap;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import sale.base.BaseDao;
import sale.table.Shipment;

public class ShipmentDao extends BaseDao{
	private List<Shipment> listShipment;
	private HashMap<Integer, Shipment> shipmentHashmap = new LinkedHashMap<>();
	
	public Shipment getShipment(int shipmentId){
		Shipment shipment = shipmentHashmap.get(shipmentId);
		if(null == shipment){
			shipment = getShipmentDb(shipmentId);
			if(null != shipment){
				shipmentHashmap.put(shipmentId, shipment);
			}
		}
		return shipment;
	}
	
	public Shipment getShipmentDb(int shipmentId){
		try{
			Session session = getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			String sql = "select sm from " + Shipment.class.getName() + " sm where id=:shipmentId";
			Query query = session.createQuery(sql);
			query.setParameter("shipmentId", shipmentId);
			Shipment shipment = (Shipment)query.uniqueResult();
			tx.commit();
			session.close();
			return shipment;
		}catch (Exception e) {
			return null;
		}
	}
	
	public List<Shipment> getListShipment(){
		if(null == listShipment){
			listShipment = getListShipmentDb();
			for(Shipment shipment : listShipment){
				if(!shipmentHashmap.containsKey(shipment.getId())){
					shipmentHashmap.put(shipment.getId(), shipment);
				}
			}
		}
		return listShipment;
	}
	
	public List<Shipment> getListShipmentDb(){
		Session session = getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String sql = "select sm from " + Shipment.class.getName() + " sm ";
		List<Shipment> listShipment = session.createQuery(sql).list();
		tx.commit();
		session.close();
		return listShipment;
	}
}
