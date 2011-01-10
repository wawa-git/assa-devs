/**
 * 
 */
package com.assaassociates.syraway.dao;

import java.util.List;

import com.assaassociates.syraway.model.BusinessUnitEx;

/**
 * @author waheb
 *
 */
public interface IBusinessUnitExDAO {
	
	public BusinessUnitEx getBusinessUnitExById(int pId);
	public void addBusinessUnitEx(int pBuExId);
	public void addBusinessUnitEx(BusinessUnitEx pBusinessUnitEx);
	public void removeBusinessUnitEx(int pBuExId);
	public void removeBusinessUnitEx(BusinessUnitEx pBusinessUnitEx);
	public List<BusinessUnitEx> getAllBusinessUnitEx();	
}
