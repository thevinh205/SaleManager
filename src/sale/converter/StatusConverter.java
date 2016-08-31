package sale.converter;

import sale.base.BaseSale;
import sale.table.Status;
import sale.util.LookupBean;

public class StatusConverter extends BaseSale{
	private LookupBean lookupBean;
	private String statusKey;
	private String statusName;
	private String type;

	public String getStatusName() {
		if(!isBlankOrNull(statusKey) && "order".equals(type)){
			Status status = lookupBean.getStatusDao().getStatusOrder(statusKey);
			if(null != status)
				statusName = status.getName();
		}
		return statusName;
	}

	public void setStatusKey(String statusKey) {
		this.statusKey = statusKey;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setLookupBean(LookupBean lookupBean) {
		this.lookupBean = lookupBean;
	}
}
