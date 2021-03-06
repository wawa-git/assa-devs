package com.assaassociates.syraway.exts.mbeans;

import java.io.Serializable;
import java.util.List;
import javax.faces.event.AjaxBehaviorEvent;
import com.assaassociates.syraway.model.BusinessUnitPc;
import com.assaassociates.syraway.model.Employee;

public interface IProjectService extends Serializable{

	public void addProject();
	public void listProjectListener(AjaxBehaviorEvent event);
	public List<BusinessUnitPc> getAllBuPc();
	public List<Employee> getAllEmployee();
	 
}
