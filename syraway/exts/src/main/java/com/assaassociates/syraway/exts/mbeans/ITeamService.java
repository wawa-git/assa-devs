package com.assaassociates.syraway.exts.mbeans;

import java.io.Serializable;
import java.util.List;
import javax.faces.event.AjaxBehaviorEvent;
import com.assaassociates.syraway.model.Employee;

public interface ITeamService extends Serializable{

	public void addTeam();
	public void listTeamListener(AjaxBehaviorEvent event);
	public List<Employee> getAllEmployee();
	 
}
