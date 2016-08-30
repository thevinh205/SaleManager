package sale.converter;

 

import sale.table.Member;
import sale.util.LookupBean;

public class MemberConverter {
	private String type;
	private String memberUsername;
	private LookupBean lookupBean;
	private String memberName;
	
	public String getType() {
		return type;
	}
	
	public String getMemberUsername() {
		return memberUsername;
	}
	
	public String getMemberName(){
		if(null != memberUsername){
			Member member = lookupBean.getMemberDao().getMember(memberUsername);
			if(null != member)
				return member.getName();
		}
		return "";
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public void setMemberUsername(String memberUsername) {
		this.memberUsername = memberUsername;
	}
	
	public void setLookupBean(LookupBean lookupBean) {
		this.lookupBean = lookupBean;
	}
	

}
