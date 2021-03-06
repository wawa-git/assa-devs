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
import com.assaasoociates.syraway.controller.IEmployeeController;
import com.assaasoociates.syraway.controller.IProjectController;
import com.assaassociates.syraway.model.BusinessUnitPc;
import com.assaassociates.syraway.model.Employee;
import com.assaassociates.syraway.model.Project;

@ManagedBean(name="project")
@SessionScoped
public class ProjectServiceImpl implements IProjectService {

private static final long serialVersionUID = 6726658729223350876L;
	
	private IProjectController oProjectController;
	private IBusinessUnitPcController oBuPcController;
	private IEmployeeController oEmployeeController;
	private Project value;
	private List<Project> projectList;
	private String buPcId;
	private String managerId;
	private String projectId;
	
	public ProjectServiceImpl(){
		super(); 
		ApplicationContext appContext = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
		this.oProjectController = appContext.getBean("projectController", IProjectController.class);
		this.oBuPcController = appContext.getBean("buPcController", IBusinessUnitPcController.class);
		this.oEmployeeController = appContext.getBean("emplController", IEmployeeController.class);
		this.value = new Project();
		this.projectList = new ArrayList<Project>();
	}

	public IProjectController getProjectController() {
		return oProjectController;
	}

	public void setProjectController(IProjectController pProjectController) {
		this.oProjectController = pProjectController;
	}

	public IBusinessUnitPcController getBuPcController() {
		return oBuPcController;
	}

	public void setBuPcController(IBusinessUnitPcController pBuPcController) {
		this.oBuPcController = pBuPcController;
	}

	public Project getValue() {
		return value;
	}

	public void setValue(Project value) {
		this.value = value;
	}

	public IEmployeeController getEmployeeController() {
		return oEmployeeController;
	}

	public void setEmployeeController(IEmployeeController pEmployeeController) {
		this.oEmployeeController = pEmployeeController;
	}

	public List<Project> getProjectList() {
		return projectList;
	}

	public void setProjectList(List<Project> pProjectList) {
		this.projectList = pProjectList;
	}

	public String getBuPcId() {
		return buPcId;
	}

	public void setBuPcId(String pBuPcId) {
		this.buPcId = pBuPcId;
	}

	public String getManagerId() {
		return managerId;
	}

	public void setManagerId(String pManagerId) {
		this.managerId = pManagerId;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String pProjectId) {
		this.projectId = pProjectId;
		if(this.projectId != null){
			if(getProjectController() != null){
				value = getProjectController().getProject(this.projectId);
				if(value != null){
					setBuPcId(String.valueOf(value.getSwBuPc().getBuPcId()));
					setManagerId(String.valueOf(value.getSwEmployee().getEmplId()));
				}
			}
		}
	}

	@Override
	public void addProject(){
		if(this.oProjectController != null){
			if(getBuPcId() != null && !"".equals(getBuPcId().trim())){
				value.setSwBuPc(getBuPcController().getBuPc(Integer.parseInt(getBuPcId())));
			}
			if(getManagerId() != null && !"".equals(getManagerId().trim())){
				value.setSwEmployee(getEmployeeController().getEmployee(getManagerId()));
			}
			this.oProjectController.addProject(value);
		}
	}

	@Override
	public void listProjectListener(AjaxBehaviorEvent event) {
		if(oProjectController != null){
			projectList = oProjectController.getAllProjects();
		}
	}

	@Override
	public List<BusinessUnitPc> getAllBuPc() {
		if(oProjectController != null){
			return oProjectController.getAllBuPc();
		}
		return new ArrayList<BusinessUnitPc>();
	}
	
	@Override
	public List<Employee> getAllEmployee() {
		if(oProjectController != null){
			return oProjectController.getAllEmpls();
		}
		return new ArrayList<Employee>();
	}
}
