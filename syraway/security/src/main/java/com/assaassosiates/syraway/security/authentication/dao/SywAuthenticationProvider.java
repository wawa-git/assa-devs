package com.assaassosiates.syraway.security.authentication.dao;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

/**
 * @author waheb
 *
 */
public class SywAuthenticationProvider implements AuthenticationProvider {

	/**
	 * 
	 */
	public SywAuthenticationProvider() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.authentication.AuthenticationProvider#authenticate(org.springframework.security.core.Authentication)
	 */
	public Authentication authenticate(Authentication arg0)
			throws AuthenticationException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.authentication.AuthenticationProvider#supports(java.lang.Class)
	 */
	public boolean supports(Class<? extends Object> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

}