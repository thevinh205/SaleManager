package sale.controller;

import java.util.*;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.opensymphony.xwork2.util.ValueStack;

import sale.base.BaseSale;
import sale.table.Member;
import sale.util.LookupBean;
import sale.util.UserUtil;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class LoginController extends BaseSale{
	private String userName;
	private String password;
	private String errorMessage;
	private UserUtil userUtil;
	private LookupBean lookupBean;
	
	/**
	 * access to page login
	 * @return
	 */
	public String login(){
		if(null != userUtil.getMember())
			return ERROR;
		return SUCCESS;
	}
	
	/**
	 * submit butoon login on page login
	 * @return
	 */
	public String loginAction(){
		if(null == userName || userName.trim().equals("")){
			errorMessage = "UserName không được để trống!";
			return ERROR;
		}
		if(null == password || password.trim().equals("")){
			errorMessage = "Password không được để trống!";
			return ERROR;
		}
		Member member = lookupBean.getMemberDao().getMemberDB(userName, password);
		if(null == member){
			errorMessage = "UserName hoặc password không chính xác!";
			return ERROR;
		}
		else{
			Map session = ActionContext.getContext().getSession();
			userUtil.setMember(member);
			errorMessage = "";
		}
		return SUCCESS;
	}
	
	public String logoutAction(){
		userUtil.setMember(null);
		return SUCCESS;
	}

	
	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public UserUtil getUserUtil() {
		return userUtil;
	}

	public void setUserUtil(UserUtil userUtil) {
		this.userUtil = userUtil;
	}

	public void setLookupBean(LookupBean lookupBean) {
		this.lookupBean = lookupBean;
	}
	
}