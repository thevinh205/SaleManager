package sale.controller;

import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.opensymphony.xwork2.ActionContext;
import com.sun.jmx.snmp.SnmpUnknownSecModelException;

import sale.base.BaseSale;
import sale.util.UserUtil;

public class WebController extends BaseSale {
	private UserUtil userUtil; 
	
	public String index(){
		if(null != userUtil.getMember())
			return SUCCESS;
		return ERROR;
	}

	public void setUserUtil(UserUtil userUtil) {
		this.userUtil = userUtil;
	}

	public UserUtil getUserUtil() {
		return userUtil;
	}
	
	
}
