package com.assaassociates.syraway.exts.mbeans;

import java.io.Serializable;
import java.util.List;
import javax.faces.event.AjaxBehaviorEvent;
import com.assaassociates.syraway.model.Employee;
import com.assaassociates.syraway.model.Task;
import com.assaassociates.syraway.model.Team;

public interface ITimeService extends Serializable{

	public void addTime();
	public void listTimeListener(AjaxBehaviorEvent event);
	public List<Team> getAllTeam();
	public List<Task> getAllTask();
	public List<Employee> getAllEmployee();
	 
}
