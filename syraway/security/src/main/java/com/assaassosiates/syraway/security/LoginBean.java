package com.assaassosiates.syraway.security;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

@ManagedBean(name="loginBean")
@RequestScoped
public class LoginBean {
	
//	private AuthenticationManager oAuthenticationManager;
	
	private String userName = "admin"; 
	private String password = "admin";
	
//	public LoginBean(){
//		super();
//		ApplicationContext appContext = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
//		this.oAuthenticationManager = appContext.getBean("org.springframework.security.authenticationManager", AuthenticationManager.class);
//	}
	
	public String login() throws IOException, ServletException
	{
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
		RequestDispatcher dispatcher = ((ServletRequest)context.getRequest()).getRequestDispatcher("/j_spring_security_check");
		dispatcher.forward((ServletRequest) context.getRequest(), (ServletResponse) context.getResponse());
		FacesContext.getCurrentInstance().responseComplete();
		return "/index.xhtml";
	}

//	public String login() {
//		if(oAuthenticationManager != null){
//			try {
//	            Authentication result = oAuthenticationManager.authenticate(new UsernamePasswordAuthenticationToken(this.getUserName(), this.getPassword()));
//	            SecurityContextHolder.getContext().setAuthentication(result);
//	            System.out.println("Successfully authenticated. Security context contains: " + 
//	            		SecurityContextHolder.getContext().getAuthentication());
//	        } catch (AuthenticationException e) {
//	            //String loginFailedMessage = FacesUtils.getBundleKey("msg", "login.failed");
//	            //FacesUtils.addErrorMessage(loginFailedMessage);
//	            System.err.println("error logIn " + e);
//	        }
//		}else{
//			System.err.println("oAuthenticationManager is null");
//		}
//		return "/index.xhtml";
//	}

	public String logout() throws IOException, ServletException
	{
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
		RequestDispatcher dispatcher = ((ServletRequest)context.getRequest()).getRequestDispatcher("/j_spring_security_logout");
		dispatcher.forward((ServletRequest) context.getRequest(), (ServletResponse) context.getResponse());
		FacesContext.getCurrentInstance().responseComplete();
		return "/index.xhtml";
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
