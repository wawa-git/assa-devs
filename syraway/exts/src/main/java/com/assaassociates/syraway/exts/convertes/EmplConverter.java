package com.assaassociates.syraway.exts.convertes;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.springframework.context.ApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;
import com.assaasoociates.syraway.controller.IEmployeeController;
import com.assaassociates.syraway.model.Employee;

/**
 * @author waheb
 *
 */

@FacesConverter( value="DeptConverter" )
public class EmplConverter implements Converter {

	private IEmployeeController oEmplController;
	
	public EmplConverter(){
		super(); 
		ApplicationContext appContext = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
		this.oEmplController = appContext.getBean("emplController", IEmployeeController.class);
	}
	
	@Override
	public Object getAsObject(FacesContext pFacesContext, UIComponent pUIComponent, String pString) {
		Employee oResult = new Employee();
		if(this.oEmplController != null){
			oResult = this.oEmplController.getEmployee(pString);
		}
		return oResult;
	}

	@Override
	public String getAsString(FacesContext pFacesContext, UIComponent pUIComponent, Object pObject) {
		String oResult = "error";
		if(pObject instanceof Employee){
			oResult = String.valueOf(((Employee) pObject).getEmplId());
			oResult = "000000".substring(oResult.length()) + oResult;
		}
		return oResult;
	}

	public IEmployeeController getEmplController() {
		return oEmplController;
	}

	public void setEmplController(IEmployeeController oEmplController) {
		this.oEmplController = oEmplController;
	}
}
