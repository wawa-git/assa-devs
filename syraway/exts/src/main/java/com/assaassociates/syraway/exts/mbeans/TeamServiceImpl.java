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
import com.assaasoociates.syraway.controller.ITeamController;
import com.assaassociates.syraway.model.Employee;
import com.assaassociates.syraway.model.Team;

@ManagedBean(name="team")
@SessionScoped
public class TeamServiceImpl implements ITeamService {

private static final long serialVersionUID = 6726658729223350876L;
	
	private ITeamController oTeamController;
	private IEmployeeController oEmployeeController;
	private Team value;
	private List<Team> teamList;
	private String managerId;
	private String teamId;
	
	public TeamServiceImpl(){
		super(); 
		ApplicationContext appContext = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
		this.oTeamController = appContext.getBean("teamController", ITeamController.class);
		this.oEmployeeController = appContext.getBean("emplController", IEmployeeController.class);
		this.value = new Team();
		this.teamList = new ArrayList<Team>();
	}


	public void setTeamController(ITeamController pTeamController) {
		this.oTeamController = pTeamController;
	}

	public ITeamController getTeamController() {
		return oTeamController;
	}

	public Team getValue() {
		return value;
	}

	public void setValue(Team pValue) {
		this.value = pValue;
	}

	public IEmployeeController getEmployeeController() {
		return oEmployeeController;
	}

	public void setEmployeeController(IEmployeeController pEmployeeController) {
		this.oEmployeeController = pEmployeeController;
	}

	public List<Team> getTeamList() {
		return teamList;
	}

	public void setTeamList(List<Team> pTeamList) {
		this.teamList = pTeamList;
	}

	public String getManagerId() {
		return managerId;
	}

	public void setManagerId(String pManagerId) {
		this.managerId = pManagerId;
	}

	public String getTeamId() {
		return teamId;
	}

	public void setTeamId(String pTeamId) {
		this.teamId = pTeamId;
		if(this.teamId != null){
			if(getTeamController() != null){
				try {
					value = getTeamController().getTeam(Integer.valueOf(this.teamId));
					if(value != null){
						setManagerId(String.valueOf(value.getSwEmployee().getEmplId()));
					}
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void addTeam(){
		if(this.oTeamController != null){
			if(getManagerId() != null && !"".equals(getManagerId().trim())){
				value.setSwEmployee(getEmployeeController().getEmployee(getManagerId()));
			}
			this.oTeamController.addTeam(value);
		}
	}

	@Override
	public void listTeamListener(AjaxBehaviorEvent event) {
		if(oTeamController != null){
			teamList = oTeamController.getAllTeams();
		}
	}

	@Override
	public List<Employee> getAllEmployee() {
		if(oEmployeeController != null){
			return oEmployeeController.getAllEmpls();
		}
		return new ArrayList<Employee>();
	}
}
