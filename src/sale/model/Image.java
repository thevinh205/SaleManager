package sale.model;

import java.util.Date;

public class Image {
	private int id;
	private String url;
	private String parent;
	private String type;
	private int partyId;
	private Date createDate;
	
	public int getId() {
		return id;
	}
	public String getUrl() {
		return url;
	}
	public String getParent() {
		return parent;
	}
	public String getType() {
		return type;
	}
	public int getPartyId() {
		return partyId;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setPartyId(int partyId) {
		this.partyId = partyId;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
}
