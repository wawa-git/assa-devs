package com.assaassociates.syraway.exts.mbeans;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;

import com.assaasoociates.syraway.controller.IBusinessUnitExController;
import com.assaasoociates.syraway.controller.IDepartmentController;
import com.assaasoociates.syraway.controller.IEmployeeController;
import com.assaasoociates.syraway.controller.IOpridController;
import com.assaassociates.syraway.model.BusinessUnitEx;
import com.assaassociates.syraway.model.Department;
import com.assaassociates.syraway.model.Employee;
import com.assaassociates.syraway.model.Oprid;

@ManagedBean(name="employee")
public class EmployeeServiceImpl implements IEmployeeService {

	private static final long serialVersionUID = 6726658729223350876L;
	private IEmployeeController oEmployeeController;
	private IOpridController oOpridController;
	private IDepartmentController oDepartmentController;
	private IBusinessUnitExController oBuExController;
	private Employee oValue;
	private Oprid oValuePwd;
	private List<Employee> oEmployeeList;
	private String emplId;
	private String buExId;
	private String deptId;
	private String managerId;
	private String pwd;
	private String confirmPwd;
	
	public EmployeeServiceImpl(){
		super(); 
		ApplicationContext appContext = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
		this.oEmployeeController = appContext.getBean("emplController", IEmployeeController.class);
		this.oOpridController = appContext.getBean("opridController", IOpridController.class);
		this.oDepartmentController = appContext.getBean("deptController", IDepartmentController.class);
		this.oBuExController = appContext.getBean("buExController", IBusinessUnitExController.class);
		this.oValue = new Employee();
		this.oValuePwd = new Oprid();
	}
	
	public Employee getValue() {
		return oValue;
	}

	public void setValue(Employee pEmployee) {
		this.oValue = pEmployee;
	}

	public Oprid getValuePwd() {
		return oValuePwd;
	}

	public void setValuePwd(Oprid oValuePwd) {
		this.oValuePwd = oValuePwd;
	}


	public IOpridController getOpridController() {
		return oOpridController;
	}


	public void setOpridController(IOpridController oOpridController) {
		this.oOpridController = oOpridController;
	}


	public IDepartmentController getDepartmentController() {
		return oDepartmentController;
	}

	public void setDepartmentController(IDepartmentController oDepartmentController) {
		this.oDepartmentController = oDepartmentController;
	}

	public IBusinessUnitExController getBuExController() {
		return oBuExController;
	}

	public void setBuExController(IBusinessUnitExController oBuExController) {
		this.oBuExController = oBuExController;
	}

	public String getPwd() {
		return pwd;
	}


	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getConfirmPwd() {
		return confirmPwd;
	}


	public void setConfirmPwd(String confirmPwd) {
		this.confirmPwd = confirmPwd;
	}

	public String getEmplId() {
		return emplId;
	}


	public void setEmplId(String pEmplId) {
		this.emplId = pEmplId;
		if(oEmployeeController != null){
			oValue = oEmployeeController.getEmployee(pEmplId);
			if(oValue != null){
				if(oValue.getSwDept() != null)
					setDeptId(String.valueOf(oValue.getSwDept().getDeptId()));
				if(oValue.getSwBuEx() != null)
					setBuExId(String.valueOf(oValue.getSwBuEx().getBuExId()));
				if(oValue.getSwEmployee() != null)
					setManagerId(String.valueOf(oValue.getSwEmployee().getEmplId()));
			}
		}
	}

	public void addEmployee(){
		if(oValuePwd.getPassword().equals(confirmPwd)){
			if(this.oOpridController != null){
				this.oOpridController.addOprid(oValuePwd);
			}
			if(this.oEmployeeController != null){
				this.oValue.setSwOprid(oValuePwd);
				if(oDepartmentController != null && deptId != null){
					this.oValue.setSwDept(oDepartmentController.getDepartment(deptId));
				}
				if(oEmployeeController != null && managerId != null){
					this.oValue.setSwEmployee(oEmployeeController.getEmployee(managerId));
				}
				if(oBuExController != null && buExId != null){
					this.oValue.setSwBuEx(oBuExController.getBuEx(Integer.valueOf(buExId)));
				}
				this.oEmployeeController.addEmployee(this.oValue);
			}
		}
	}
	
	public void listEmployeeListener(AjaxBehaviorEvent event){
		oEmployeeList = oEmployeeController.getAllEmpls();
	}
	
	public List<BusinessUnitEx> getAllBuEx(){
		List<BusinessUnitEx> oResult = new ArrayList<BusinessUnitEx>();
		if(this.oEmployeeController != null)
			oResult = this.oEmployeeController.getAllBuEx();
		return oResult;
	}
	
	public List<Department> getAllDepts(){
		List<Department> oResult = new ArrayList<Department>();
		if(this.oEmployeeController != null)
			oResult = this.oEmployeeController.getAllDepts();
		return oResult;
	}
	
	public List<Employee> getAllManager(){
		List<Employee> oResult = new ArrayList<Employee>();
		if(this.oEmployeeController != null)
			oResult = this.oEmployeeController.getAllEmpls();
		return oResult;
	}

	public List<Employee> getEmployeeList() {
		return oEmployeeList;
	}

	public void setEmployeeList(List<Employee> pEmployeeList) {
		this.oEmployeeList = pEmployeeList;
	}

	public String getBuExId() {
		return buExId;
	}

	public void setBuExId(String buExId) {
		this.buExId = buExId;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getManagerId() {
		return managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}


	public IEmployeeController getEmployeeController() {
		return oEmployeeController;
	}


	public void setEmployeeController(IEmployeeController oEmployeeController) {
		this.oEmployeeController = oEmployeeController;
	}
}
