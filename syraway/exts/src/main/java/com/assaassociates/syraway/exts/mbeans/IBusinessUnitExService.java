package com.assaassociates.syraway.exts.mbeans;

import java.io.Serializable;
import java.util.List;
import javax.faces.event.AjaxBehaviorEvent;
import com.assaassociates.syraway.model.BusinessUnit;

public interface IBusinessUnitExService extends Serializable{

	public void addBusinessUnitEx();
	public void listBusinessUnitExListener(AjaxBehaviorEvent event);
	public List<BusinessUnit> getAllBu();
	 
}
