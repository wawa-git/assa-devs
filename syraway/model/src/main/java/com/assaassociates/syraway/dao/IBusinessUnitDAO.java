/**
 * 
 */
package com.assaassociates.syraway.dao;

import java.util.List;
import com.assaassociates.syraway.model.BusinessUnit;

/**
 * @author waheb
 *
 */
public interface IBusinessUnitDAO {
	
	public BusinessUnit getBusinessUnitById(int pId);
	public void addBusinessUnit(int pBuId);
	public void addBusinessUnit(BusinessUnit pBu);
	public void updateBusinessUnit(BusinessUnit pBu);
	public void removeBusinessUnit(int pBuId);
	public void removeBusinessUnit(BusinessUnit pBusinessUnit);
	public List<BusinessUnit> getAllBusinessUnit();	
}
