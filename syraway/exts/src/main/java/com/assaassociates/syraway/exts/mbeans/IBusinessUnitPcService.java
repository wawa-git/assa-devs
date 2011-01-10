package com.assaassociates.syraway.exts.mbeans;

import java.io.Serializable;
import java.util.List;
import javax.faces.event.AjaxBehaviorEvent;
import com.assaassociates.syraway.model.Project;

public interface IBusinessUnitPcService extends Serializable{

	public void addBusinessUnitPc();
	public void listBusinessUnitPcListener(AjaxBehaviorEvent event);
	public List<Project> getAllProject();
	 
}
