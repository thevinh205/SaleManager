package sale.converter;

import sale.base.BaseSale;
import sale.table.Shipment;
import sale.table.Status;
import sale.util.LookupBean;

public class ShipmentConverter extends BaseSale{
	private LookupBean lookupBean;
	private String shipmentName;
	private String shipmentId;
	
	public String getShipmentName() {
		try{
			if(!isBlankOrNull(shipmentId)){
				Shipment shipment = lookupBean.getShipmentDao().getShipment(Integer.parseInt(shipmentId));
				if(null != shipment)
					shipmentName = shipment.getName();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return shipmentName;
	}

	public String getShipmentId() {
		return shipmentId;
	}

	public void setShipmentId(String shipmentId) {
		this.shipmentId = shipmentId;
	}

	public void setLookupBean(LookupBean lookupBean) {
		this.lookupBean = lookupBean;
	}
}
