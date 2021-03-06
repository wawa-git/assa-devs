package com.assaassociates.syraway.exts.mbeans;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;
import com.assaasoociates.syraway.controller.IBusinessUnitController;
import com.assaassociates.syraway.model.BusinessUnit;
import com.assaassociates.syraway.model.BusinessUnitEx;
import com.assaassociates.syraway.model.BusinessUnitPc;

@ManagedBean(name="businessUnit")
public class BusinessUnitServiceImpl implements IBusinessUnitService {

private static final long serialVersionUID = 6726658729223350876L;
	

	private IBusinessUnitController oBusinessUnitController;
	
	private BusinessUnit oBusinessUnit;
	
	private String buExId;
	private String buPcId;
	private String buId;

	private List<BusinessUnit> oBusinessUnitList;
	
	public BusinessUnitServiceImpl(){
		super(); 
		ApplicationContext appContext = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
		this.oBusinessUnitController = appContext.getBean("buController", IBusinessUnitController.class);
		this.oBusinessUnitList = new ArrayList<BusinessUnit>();
	}
	
	public String getBuId() {
		return buId;
	}

	public void setBuId(String buId) {
		this.buId = buId;
		if(this.buId != null){
			if(getBusinessUnitController() != null){
				oBusinessUnit = getBusinessUnitController().getBusinessUnit(this.buId);
				if(oBusinessUnit != null){
					setBuExId(String.valueOf(oBusinessUnit.getSwBuEx().getBuExId()));
					setBuPcId(String.valueOf(oBusinessUnit.getSwBuPc().getBuPcId()));
				}
			}
		}
		System.out.println("================================== " + getBuExId() + "  " + getBuPcId());
	}

	public IBusinessUnitController getBusinessUnitController() {
		return oBusinessUnitController;
	}

	public void setBusinessUnitController(IBusinessUnitController oBuController) {
		this.oBusinessUnitController = oBuController;
	}

	public BusinessUnit getBusinessUnit() {
		return oBusinessUnit;
	}

	public void setBusinessUnit(BusinessUnit oBusinessUnit) {
		this.oBusinessUnit = oBusinessUnit;
	}

	public void addBusinessUnit(){
		if(this.oBusinessUnitController != null){
			if(getBuId() != null){
				this.oBusinessUnitController.updateBusinessUnit(oBusinessUnit);
			}else{
				this.oBusinessUnitController.addBusinessUnit(buExId, buPcId);
			}
		}
	}
	
	public void listBusinessUnitListener(AjaxBehaviorEvent event){
		oBusinessUnitList = oBusinessUnitController.getAllBu();
	}
	
	public void addBusinessUnitExListener(AjaxBehaviorEvent event){
		
	}
	
	public List<BusinessUnitPc> getAllBuPc(){
		List<BusinessUnitPc> result = new ArrayList<BusinessUnitPc>();
		if(this.oBusinessUnitController != null){
			result = this.oBusinessUnitController.getAllBuPc();
		}
		return result;
	}
	
	public List<BusinessUnitEx> getAllBuEx(){
		List<BusinessUnitEx> result = new ArrayList<BusinessUnitEx>();
		if(this.oBusinessUnitController != null){
			result = this.oBusinessUnitController.getAllBuEx();
		}
		return result;
	}

	public List<BusinessUnit> getBusinessUnitList() {
		return oBusinessUnitList;
	}

	public void setBusinessUnitList(List<BusinessUnit> pBusinessUnitList) {
		this.oBusinessUnitList = pBusinessUnitList;
	}

	public String getBuExId() {
		return buExId;
	}

	public void setBuExId(String pBuExId) {
		this.buExId = pBuExId;
	}

	public String getBuPcId() {
		return buPcId;
	}

	public void setBuPcId(String pBuPcId) {
		this.buPcId = pBuPcId;
	}
	
}
