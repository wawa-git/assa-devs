package com.assaassociates.syraway.exts.mbeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import org.springframework.context.ApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;
import com.assaassociates.syraway.exts.ihm.ConfIhm;

@ManagedBean(name="mainPageIhm")
@SessionScoped
public class MainPageServiceImpl implements IMainPageService {

private static final long serialVersionUID = 6726658729223350876L;
	
	private ConfIhm oConfIhm;
	private String ihmValue;
	public MainPageServiceImpl(){
		super(); 
		ApplicationContext appContext = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
		this.oConfIhm = appContext.getBean("confIhm", ConfIhm.class);
	}
	
	public ConfIhm getConfIhm() {
		return oConfIhm;
	}

	public void setConfIhm(ConfIhm oConfIhm) {
		this.oConfIhm = oConfIhm;
	}

	public String getIhmValue() {
		return ihmValue;
	}

	public void setIhmValue(String ihmValue) {
		this.ihmValue = ihmValue;
	}

	@Override
	public String getContentFromParam() {
		FacesContext context = FacesContext.getCurrentInstance();
		String sectionParam = (String) context.getExternalContext().getRequestParameterMap().get("section");
		if(sectionParam != null){
			if(oConfIhm != null){
				return oConfIhm.getConfIhm().get(sectionParam);
			}
		}
		return "content.xhtml";
	}

	public void contentFromParamListener(AjaxBehaviorEvent pEvent){
		FacesContext context = FacesContext.getCurrentInstance();
	    String sectionParam = (String) context.getExternalContext().getRequestParameterMap().get("section");
		if(sectionParam != null){
			if(oConfIhm != null){
				ihmValue =  oConfIhm.getConfIhm().get(sectionParam);
			}
		}
	}
	
}
