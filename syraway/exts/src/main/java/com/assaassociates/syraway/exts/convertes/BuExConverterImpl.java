package com.assaassociates.syraway.exts.convertes;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.springframework.context.ApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;
import com.assaasoociates.syraway.controller.IBusinessUnitExController;
import com.assaassociates.syraway.model.BusinessUnitEx;

/**
 * @author waheb
 *
 */

@FacesConverter( value="BuExConverter" )
public class BuExConverterImpl implements Converter {

	private IBusinessUnitExController oBusinessUnitExController;
	
	public BuExConverterImpl(){
		super(); 
		ApplicationContext appContext = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
		this.oBusinessUnitExController = appContext.getBean("buExController", IBusinessUnitExController.class);
	}
	
	@Override
	public Object getAsObject(FacesContext pFacesContext, UIComponent pUIComponent, String pString) {
		BusinessUnitEx oResult = null;
		System.out.println("I'm HERE ... " + pString);
		if(this.oBusinessUnitExController != null){
			try {
				oResult = this.oBusinessUnitExController.getBuEx(Integer.parseInt(pString));
			} catch (NumberFormatException e) {
				System.err.println("ERROR" + e.getStackTrace());
				// TODO Auto-generated catch block
			}
			System.out.println(oResult);
		}
		return oResult;
	}

	@Override
	public String getAsString(FacesContext pFacesContext, UIComponent pUIComponent, Object pObject) {
		String oResult = "error";
		if(pObject instanceof BusinessUnitEx){
			oResult = String.valueOf(((BusinessUnitEx) pObject).getBuExId());
			//oResult = "000000".substring(oResult.length()) + oResult;
		}
		System.out.println(oResult);
		return oResult;
	}

	public IBusinessUnitExController getBusinessUnitExController() {
		return oBusinessUnitExController;
	}

	public void setBusinessUnitExController(
			IBusinessUnitExController pBusinessUnitExController) {
		this.oBusinessUnitExController = pBusinessUnitExController;
	}
}
