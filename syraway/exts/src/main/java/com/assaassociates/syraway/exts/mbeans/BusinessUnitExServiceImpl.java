package com.assaassociates.syraway.exts.mbeans;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;
import com.assaasoociates.syraway.controller.IBusinessUnitExController;
import com.assaassociates.syraway.model.BusinessUnit;
import com.assaassociates.syraway.model.BusinessUnitEx;

@ManagedBean(name="businessUnitEx")
@SessionScoped
public class BusinessUnitExServiceImpl implements IBusinessUnitExService {

private static final long serialVersionUID = 6726658729223350876L;
	
	private IBusinessUnitExController oBusinessUnitExController;
	private BusinessUnitEx oBusinessUnitEx;
	private List<BusinessUnitEx> oBusinessUnitExList;
	private String buExId;
	
	public BusinessUnitExServiceImpl(){
		super(); 
		ApplicationContext appContext = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
		this.oBusinessUnitExController = appContext.getBean("buExController", IBusinessUnitExController.class);
		this.oBusinessUnitExList = new ArrayList<BusinessUnitEx>();
		this.oBusinessUnitEx = new BusinessUnitEx();
	}
	
	public String getBuExId() {
		return buExId;
	}

	public void setBuExId(String buExId) {
		this.buExId = buExId;
		if(this.buExId != null){
			try {
				this.oBusinessUnitEx = this.oBusinessUnitExController.getBuEx(Integer.valueOf(buExId));
			} catch (NumberFormatException e) {

			}
		}
	}

	public IBusinessUnitExController getBusinessUnitExController() {
		return oBusinessUnitExController;
	}

	public void setBusinessUnitExController(IBusinessUnitExController oBuExController) {
		this.oBusinessUnitExController = oBuExController;
	}

	public BusinessUnitEx getBusinessUnitEx() {
		return oBusinessUnitEx;
	}

	public void setBusinessUnitEx(BusinessUnitEx oBusinessUnitEx) {
		this.oBusinessUnitEx = oBusinessUnitEx;
	}

	public void addBusinessUnitEx(){
		if(this.oBusinessUnitExController != null){
			this.oBusinessUnitExController.addBusinessUnitEx(oBusinessUnitEx);
		}
	}
	
	public void listBusinessUnitExListener(AjaxBehaviorEvent event){
		oBusinessUnitExList = oBusinessUnitExController.getAllBuEx();
	}

	public List<BusinessUnitEx> getBusinessUnitExList() {
		return oBusinessUnitExList;
	}

	public void setBusinessUnitExList(List<BusinessUnitEx> pBusinessUnitExList) {
		this.oBusinessUnitExList = pBusinessUnitExList;
	}

	@Override
	public List<BusinessUnit> getAllBu() {
		List<BusinessUnit> result = new ArrayList<BusinessUnit>();
		
		return result;
	}
	
	public String processBuExList(){
		System.out.println("testttttttttttt");
		return "success";
	}
}
