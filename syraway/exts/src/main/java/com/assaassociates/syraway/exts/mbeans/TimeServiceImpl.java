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
import com.assaasoociates.syraway.controller.ITaskController;
import com.assaasoociates.syraway.controller.ITeamController;
import com.assaasoociates.syraway.controller.ITimeController;
import com.assaassociates.syraway.model.Employee;
import com.assaassociates.syraway.model.Task;
import com.assaassociates.syraway.model.Team;
import com.assaassociates.syraway.model.Time;

@ManagedBean(name="time")
@SessionScoped
public class TimeServiceImpl implements ITimeService {

private static final long serialVersionUID = 6726658729223350876L;
	
	private ITimeController oTimeController;
	private ITaskController oTaskController;
	private ITeamController oTeamController;
	private IEmployeeController oEmployeeController;
	private Time value;
	private List<Time> timeList;
	private String emplId;
	private String taskId;
	private String teamId;
	private String timeId;
	
	public TimeServiceImpl(){
		super(); 
		ApplicationContext appContext = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
		this.oTimeController = appContext.getBean("timeController", ITimeController.class);
		this.oTaskController = appContext.getBean("taskController", ITaskController.class);
		this.oTeamController = appContext.getBean("teamController", ITeamController.class);
		this.oEmployeeController = appContext.getBean("emplController", IEmployeeController.class);
		this.value = new Time();
		this.timeList = new ArrayList<Time>();
	}

	public ITimeController getTimeController() {
		return oTimeController;
	}

	public void setTimeController(ITimeController pTimeController) {
		this.oTimeController = pTimeController;
	}

	public ITaskController getTaskController() {
		return oTaskController;
	}

	public void setTaskController(ITaskController pTaskController) {
		this.oTaskController = pTaskController;
	}

	public ITeamController getTeamController() {
		return oTeamController;
	}

	public void setTeamController(ITeamController oTeamController) {
		this.oTeamController = oTeamController;
	}

	public Time getValue() {
		return value;
	}

	public void setValue(Time pValue) {
		this.value = pValue;
	}

	public IEmployeeController getEmployeeController() {
		return oEmployeeController;
	}

	public void setEmployeeController(IEmployeeController pEmployeeController) {
		this.oEmployeeController = pEmployeeController;
	}

	public List<Time> getTimeList() {
		return timeList;
	}

	public void setTimeList(List<Time> pTimeList) {
		this.timeList = pTimeList;
	}

	public String getEmplId() {
		return emplId;
	}

	public void setEmplId(String emplId) {
		this.emplId = emplId;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getTeamId() {
		return teamId;
	}

	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}

	public String getTimeId() {
		return timeId;
	}

	public void setTimeId(String pTimeId) {
		this.timeId = pTimeId;
		if(this.timeId != null){
			if(getTimeController() != null){
				try {
					value = getTimeController().getTime(Integer.valueOf(this.timeId));
					if(value != null){
						setEmplId(String.valueOf(value.getSwEmployee().getEmplId()));
						setTeamId(String.valueOf(value.getSwTeam().getTeamId()));
						setTaskId(String.valueOf(value.getSwTask().getTaskId()));
					}
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void addTime(){
		if(this.oTimeController != null){
			if(getEmplId() != null && !"".equals(getEmplId().trim())){
				value.setSwEmployee(getEmployeeController().getEmployee(getEmplId()));
			}
			if(getTeamId() != null && !"".equals(getTeamId().trim())){
				value.setSwTeam(getTeamController().getTeam(Integer.valueOf(getTeamId())));
			}
			if(getTaskId() != null && !"".equals(getTaskId().trim())){
				value.setSwTask(getTaskController().getTask(Integer.valueOf(getTaskId())));
			}
			this.oTimeController.addTime(value);
		}
	}

	@Override
	public void listTimeListener(AjaxBehaviorEvent event) {
		if(oTimeController != null){
			timeList = oTimeController.getAllTimes();
		}
	}

	@Override
	public List<Team> getAllTeam() {
		if(oTeamController != null){
			return oTeamController.getAllTeams();
		}
		return new ArrayList<Team>();
	}
	
	@Override
	public List<Task> getAllTask() {
		if(oTaskController != null){
			return oTaskController.getAllTasks();
		}
		return new ArrayList<Task>();
	}
	
	@Override
	public List<Employee> getAllEmployee() {
		if(oEmployeeController != null){
			return oEmployeeController.getAllEmpls();
		}
		return new ArrayList<Employee>();
	}
}
