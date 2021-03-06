package com.assaassociates.syraway.exts.mbeans;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;
import com.assaasoociates.syraway.controller.IBusinessUnitPcController;
import com.assaassociates.syraway.model.BusinessUnitPc;
import com.assaassociates.syraway.model.Project;

@ManagedBean(name="businessUnitPc")
@SessionScoped
public class BusinessUnitPcServiceImpl implements IBusinessUnitPcService {

private static final long serialVersionUID = 6726658729223350876L;
	

	private IBusinessUnitPcController oBusinessUnitPcController;
	private BusinessUnitPc oBusinessUnitPc;
	private List<BusinessUnitPc> oBusinessUnitPcList;
	private String buPcId;
	
	public BusinessUnitPcServiceImpl(){
		super(); 
		ApplicationContext appContext = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
		this.oBusinessUnitPcController = appContext.getBean("buPcController", IBusinessUnitPcController.class);
		this.oBusinessUnitPcList = new ArrayList<BusinessUnitPc>();
		this.oBusinessUnitPc = new BusinessUnitPc();
	}
	
	public String getBuPcId() {
		return buPcId;
	}

	public void setBuPcId(String pBuPcId) {
		this.buPcId = pBuPcId;
		if(this.buPcId != null){
			try {
				this.oBusinessUnitPc = this.oBusinessUnitPcController.getBuPc(Integer.valueOf(pBuPcId));
			} catch (NumberFormatException e) {

			}
		}
	}

	public IBusinessUnitPcController getBusinessUnitPcController() {
		return oBusinessUnitPcController;
	}

	public void setBusinessUnitPcController(IBusinessUnitPcController pBuPcController) {
		this.oBusinessUnitPcController = pBuPcController;
	}

	public BusinessUnitPc getBusinessUnitPc() {
		return oBusinessUnitPc;
	}

	public void setBusinessUnit(BusinessUnitPc oBusinessUnitPc) {
		this.oBusinessUnitPc = oBusinessUnitPc;
	}

	@Override
	public void addBusinessUnitPc(){
		if(this.oBusinessUnitPcController != null){
			this.oBusinessUnitPcController.addBusinessUnitPc(oBusinessUnitPc);
		}
	}
	
	@Override
	public void listBusinessUnitPcListener(AjaxBehaviorEvent event){
		oBusinessUnitPcList = oBusinessUnitPcController.getAllBuPc();
	}
	
	public List<BusinessUnitPc> getBusinessUnitPcList() {
		return oBusinessUnitPcList;
	}

	public void setBusinessUnitList(List<BusinessUnitPc> pBusinessUnitPcList) {
		this.oBusinessUnitPcList = pBusinessUnitPcList;
	}

	@Override
	public List<Project> getAllProject() {
		List<Project> result = new ArrayList<Project>();
		return result;
	}
	
	public String processBuPcList(){
		System.out.println("testttttttttttt");
		return "success";
	}
}
