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
	
	public BusinessUnit getBusinessUnitById(Integer pId);
	public void addBusinessUnit(Integer pBuId);
	public void addBusinessUnit(BusinessUnit pBu);
	public void updateBusinessUnit(BusinessUnit pBu);
	public void removeBusinessUnit(Integer pBuId);
	public void removeBusinessUnit(BusinessUnit pBusinessUnit);
	public List<BusinessUnit> getAllBusinessUnit();	
}
