package com.assaassociates.syraway.exts.mbeans;

import java.io.Serializable;
import java.util.List;
import javax.faces.event.AjaxBehaviorEvent;
import com.assaassociates.syraway.model.BusinessUnitEx;

public interface IDepartmentService extends Serializable{

	public void addDepartment();
	public void listDepartmentListener(AjaxBehaviorEvent event);
	public List<BusinessUnitEx> getAllBuEx();
	 
}
