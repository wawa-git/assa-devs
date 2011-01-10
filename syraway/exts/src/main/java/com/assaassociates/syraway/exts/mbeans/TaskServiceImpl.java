package com.assaassociates.syraway.exts.mbeans;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;
import com.assaasoociates.syraway.controller.IEmployeeController;
import com.assaasoociates.syraway.controller.IProjectController;
import com.assaasoociates.syraway.controller.ITaskController;
import com.assaassociates.syraway.model.Employee;
import com.assaassociates.syraway.model.Project;
import com.assaassociates.syraway.model.Task;

@ManagedBean(name="task")
@SessionScoped
public class TaskServiceImpl implements ITaskService {

private static final long serialVersionUID = 6726658729223350876L;
	
	private IProjectController oProjectController;
	private ITaskController oTaskController;
	private IEmployeeController oEmployeeController;
	private Task value;
	private List<Task> taskList;
	private String taskId;
	private String emplId;
	private String projectId;
	
	public TaskServiceImpl(){
		super(); 
		ApplicationContext appContext = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
		this.oProjectController = appContext.getBean("projectController", IProjectController.class);
		this.oTaskController = appContext.getBean("taskController", ITaskController.class);
		this.oEmployeeController = appContext.getBean("emplController", IEmployeeController.class);
		this.value = new Task();
		this.taskList = new ArrayList<Task>();
	}

	public IProjectController getProjectController() {
		return oProjectController;
	}

	public void setProjectController(IProjectController pProjectController) {
		this.oProjectController = pProjectController;
	}

	public ITaskController getTaskController() {
		return oTaskController;
	}

	public void setTaskController(ITaskController pTaskController) {
		this.oTaskController = pTaskController;
	}

	public Task getValue() {
		return value;
	}

	public void setValue(Task value) {
		this.value = value;
	}

	public IEmployeeController getEmployeeController() {
		return oEmployeeController;
	}

	public void setEmployeeController(IEmployeeController pEmployeeController) {
		this.oEmployeeController = pEmployeeController;
	}

	public List<Task> getTaskList() {
		return taskList;
	}

	public void setTaskList(List<Task> pTaskList) {
		this.taskList = pTaskList;
	}

	public String getProjectId() {
		return this.projectId;
	}

	public void setProjectId(String pProjectId) {
		this.projectId = pProjectId;
	}

	public String getEmplId() {
		return emplId;
	}

	public void setEmplId(String pEmplId) {
		this.emplId = pEmplId;
	}

	public String getTaskId() {
		return projectId;
	}

	public void setTaskId(String pTaskId) {
		this.taskId = pTaskId;
		if(this.taskId != null){
			if(getTaskController() != null){
				try {
					value = getTaskController().getTask(Integer.valueOf(this.taskId));
					if(value != null){
						setProjectId(String.valueOf(value.getSwProject().getProjectId()));
						setEmplId(String.valueOf(value.getSwEmployee().getEmplId()));
					}
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void addTask(){
		if(this.oProjectController != null){
			if(getProjectId() != null && !"".equals(getProjectId().trim())){
				value.setSwProject(getProjectController().getProject(getProjectId()));
			}
			if(getEmplId() != null && !"".equals(getEmplId().trim())){
				value.setSwEmployee(getEmployeeController().getEmployee(getEmplId()));
			}
			this.oTaskController.addTask(value);
		}
	}

	@Override
	public void listTaskListener(AjaxBehaviorEvent event) {
		if(oTaskController != null){
			taskList = oTaskController.getAllTasks();
		}
	}

	@Override
	public List<Project> getAllProject() {
		if(oProjectController != null){
			return oProjectController.getAllProjects();
		}
		return new ArrayList<Project>();
	}
	
	@Override
	public List<Employee> getAllEmployee() {
		if(oProjectController != null){
			return oProjectController.getAllEmpls();
		}
		return new ArrayList<Employee>();
	}
}
