package com.assaassosiates.syraway.security.authentication;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import com.assaasoociates.syraway.controller.IPageController;
import com.assaassociates.syraway.model.Employee;
import com.assaassosiates.syraway.security.SywApplicationContext;

@Component
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class UserProfile implements IUserProfile {

	private static final long serialVersionUID = 1L;
	
	private boolean oTransient = true;
	private Employee oEmpl;
	private List<GrantedAuthority> oListAuthorities;

	public boolean isTransient() {
		return oTransient;
	}

	public void setTransient(boolean pTransient) {
		this.oTransient = pTransient;
	}

	public Employee getEmpl() {
		return oEmpl;
	}

	public void setEmpl(Employee oEmpl) {
		this.oEmpl = oEmpl;
	}

	public List<GrantedAuthority> getListAuthorities() {
		return oListAuthorities;
	}

	public void setListAuthorities(List<GrantedAuthority> pListAuthorities) {
		this.oListAuthorities = pListAuthorities;
	}
	
	public List<String> getListAuthoritiesName(){
		List<String> result = null;
		if(oListAuthorities != null){
			result = new ArrayList<String>();
			for (GrantedAuthority oGrantedAuthority : oListAuthorities) {
				result.add(oGrantedAuthority.getAuthority());
				System.out.println("LIST AUTHOTIRY : " + oGrantedAuthority.getAuthority());
			}
		}
		return result;
	}
	
	public boolean isAuthorized(String pAuthority){
		List<String> oList = getListAuthoritiesName(); 
		if(oList != null){
			return oList.contains(pAuthority);
		}
		return false;
	}

	public boolean isAuthorizedUri(String pUri) {
		Collection<String> oList = getAuthoritiesForPage(pUri);
		boolean result = false;
		for (String authority : oList) {
			if (isAuthorized(authority)) {
                result = true;
                break;
            }
		}
		return result;
	}
	
	private Collection<String> getAuthoritiesForPage(String pPageUri){
		
		
		Collection<String> result = new HashSet<String>();
		
		ApplicationContext ctx = SywApplicationContext.getCtx();
		IPageController oPageController = ctx.getBean("pageController", IPageController.class);

		if(oPageController != null){
			result = oPageController.getAuthoritiesForPage(pPageUri);
		}
		return result;
	}
	
}