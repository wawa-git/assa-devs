package com.assaassociates.syraway.exts.convertes;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.springframework.context.ApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;
import com.assaasoociates.syraway.controller.IDepartmentController;
import com.assaassociates.syraway.model.Department;

/**
 * @author waheb
 *
 */

@FacesConverter( value="DeptConverter" )
public class DeptConverter implements Converter {

	private IDepartmentController oDepartmentController;
	
	public DeptConverter(){
		super(); 
		ApplicationContext appContext = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
		this.oDepartmentController = appContext.getBean("deptController", IDepartmentController.class);
	}
	
	@Override
	public Object getAsObject(FacesContext pFacesContext, UIComponent pUIComponent, String pString) {
		Department oResult = new Department();
		if(this.oDepartmentController != null){
			oResult = this.oDepartmentController.getDepartment(pString);
		}
		return oResult;
	}

	@Override
	public String getAsString(FacesContext pFacesContext, UIComponent pUIComponent, Object pObject) {
		String oResult = "error";
		if(pObject instanceof Department){
			oResult = String.valueOf(((Department) pObject).getDeptId());
			oResult = "000000".substring(oResult.length()) + oResult;
		}
		return oResult;
	}

	public IDepartmentController getoDepartmentController() {
		return oDepartmentController;
	}

	public void setoDepartmentController(IDepartmentController oDepartmentController) {
		this.oDepartmentController = oDepartmentController;
	}
}
