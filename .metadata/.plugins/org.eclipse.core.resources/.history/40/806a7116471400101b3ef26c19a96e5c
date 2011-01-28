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
import com.assaasoociates.syraway.controller.IDepartmentController;
import com.assaassociates.syraway.model.BusinessUnitEx;
import com.assaassociates.syraway.model.Department;

@ManagedBean(name="department")
@SessionScoped
public class DepartmentServiceImpl implements IDepartmentService {

private static final long serialVersionUID = 6726658729223350876L;
	

	private IDepartmentController oDepartmentController;
	private IBusinessUnitExController oBuExController;
	private Department value;
	private List<Department> departmentList;
	private String buEx;
	private String deptId;
	
	public DepartmentServiceImpl(){
		super(); 
		ApplicationContext appContext = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
		this.oDepartmentController = appContext.getBean("deptController", IDepartmentController.class);
		this.oBuExController = appContext.getBean("buExController", IBusinessUnitExController.class);
		this.value = new Department();
		this.departmentList = new ArrayList<Department>();
	}
	
	public IDepartmentController getDepartmentController() {
		return oDepartmentController;
	}
	
	public void setDepartmentController(IDepartmentController pDepartmentController) {
		this.oDepartmentController = pDepartmentController;
	}

	public Department getValue() {
		return value;
	}

	public void setValue(Department oDepartment) {
		this.value = oDepartment;
	}

	public List<Department> getDepartmentList() {
		return departmentList;
	}

	public void setDepartmentList(List<Department> departmentList) {
		this.departmentList = departmentList;
	}

	public String getBuEx() {
		return buEx;
	}

	public void setBuEx(String buEx) {
		this.buEx = buEx;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String pDeptId) {
		this.deptId = pDeptId;
		if(this.deptId != null){
			if(getDepartmentController() != null){
				value = getDepartmentController().getDepartment(this.deptId);
				if(value != null){
					setBuEx(String.valueOf(value.getSwBuEx().getBuExId()));
				}
			}
		}
	}

	public IBusinessUnitExController getBuExController() {
		return oBuExController;
	}

	public void setBuExController(IBusinessUnitExController oBuExController) {
		this.oBuExController = oBuExController;
	}

	public void addDepartment(){
		if(this.oDepartmentController != null){
			if(getBuEx() != null){
				getBuExController().getBuEx(Integer.parseInt(getBuEx()));
				value.setSwBuEx(getBuExController().getBuEx(Integer.parseInt(getBuEx())));
				this.oDepartmentController.addDepartment(value);
			}
		}
	}

	@Override
	public void listDepartmentListener(AjaxBehaviorEvent event) {
		if(oDepartmentController != null){
			departmentList = oDepartmentController.getAllDepts();
		}
	}

	@Override
	public List<BusinessUnitEx> getAllBuEx() {
		if(oDepartmentController != null){
			return oDepartmentController.getAllBuEx();
		}
		return new ArrayList<BusinessUnitEx>();
	}
//	public List<SelectItem> getAllBuEx() {
//		List<SelectItem> oResult = new ArrayList<SelectItem>();
//		if(oDepartmentController != null){
//			List<BusinessUnitEx> oBuExList = oDepartmentController.getAllBuEx();
//			for (BusinessUnitEx oBuEx : oBuExList) {
//				String strValue = String.valueOf(oBuEx.getBuExId());
//				strValue = "000000".substring(strValue.length()) + strValue;
//				SelectItem oItem = new SelectItem(oBuEx,strValue,strValue);
//				oResult.add(oItem);
//			}
//		}
//		return oResult;
//	}

	
}
