package sale.base;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.ActionSupport;

import sale.controller.member.CustomerController;
import sale.dao.MemberDao;
import sale.model.Member;

public class BaseSale extends ActionSupport implements Serializable{
	private String actionURL;
	
	public String findParam(String key) {
		ActionContext context = ActionContext.getContext();
		Map<String, Object> params = context.getParameters();
		Object obj = params.get(key);
		if(obj != null) {
			String[] values = (String[])obj;
			return values.length > 0 ? values[0] : null;
		} 
		return null;
	}
	
	public String findParam(Map<String, Object> params, String key) {
		Object obj = params.get(key);
		if(obj != null) {
			String[] values = (String[])obj;
			return values.length > 0 ? values[0] : null;
		} 
		return null;
	}
	
	public static boolean isNumeric(String str){
	    return str.matches("-?\\d+(.\\d+)?");
	}
	
	public String getPath(){
	  ActionProxy proxy = ActionContext.getContext().getActionInvocation().getProxy();
	  String namespace =  proxy.getNamespace();
	  String name = proxy.getActionName();
	  return name;
	}

	public String getActionURL() {
		return actionURL;
	}

	public void setActionURL(String actionURL) {
		this.actionURL = actionURL;
	}
	
	public List<String> getListPage(int index, int total){
		List<String> result = new LinkedList<>();
		if(total <= 5){
			for(int i=0; i<=5; i++){
				result.add(String.valueOf(i));
			}
		}
		else{
			if(index <=2){
				result.add("1");
				result.add("2");
				result.add("3");
				result.add("...");
				result.add(String.valueOf(total));
			}
			else if(index <= total-1)
		}
		return result;
	}
	
}
