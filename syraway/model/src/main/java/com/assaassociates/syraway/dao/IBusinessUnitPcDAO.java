/**
 * 
 */
package com.assaassociates.syraway.dao;

import java.util.List;
import com.assaassociates.syraway.model.BusinessUnitPc;

/**
 * @author waheb
 *
 */
public interface IBusinessUnitPcDAO {
	
	public BusinessUnitPc getBusinessUnitPcById(int pId);
	public void addBusinessUnitPc(int pBuPcId);
	public void addBusinessUnitPc(BusinessUnitPc pBusinessUnitPc);
	public void removeBusinessUnitPc(int pBuPcId);
	public void removeBusinessUnitPc(BusinessUnitPc pBusinessUnitPc);
	public List<BusinessUnitPc> getAllBusinessUnitPc();	
}
