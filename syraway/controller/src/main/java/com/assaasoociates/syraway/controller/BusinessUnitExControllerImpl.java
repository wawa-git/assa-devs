/**
 * 
 */
package com.assaasoociates.syraway.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.assaassociates.syraway.dao.IBusinessUnitExDAO;
import com.assaassociates.syraway.model.BusinessUnitEx;

/**
 * @author waheb
 *
 */

@Component("buExController")
public class BusinessUnitExControllerImpl implements IBusinessUnitExController {

	@Autowired
	private IBusinessUnitExDAO businessUnitExDAO;
	
	public void addBusinessUnitEx(BusinessUnitEx pBusinessUnitEx) {
		BusinessUnitEx oBuEx = pBusinessUnitEx;
		if(businessUnitExDAO != null){
			businessUnitExDAO.addBusinessUnitEx(oBuEx);
		}
	}
	
	public List<BusinessUnitEx> getAllBuEx() {
		if(businessUnitExDAO != null){
			return businessUnitExDAO.getAllBusinessUnitEx();
		}
		return null;
	}

	public IBusinessUnitExDAO getBusinessUnitExDAO() {
		return businessUnitExDAO;
	}

	public void setBusinessUnitDAO(IBusinessUnitExDAO pBusinessUnitExDAO) {
		businessUnitExDAO = pBusinessUnitExDAO;
	}

	public BusinessUnitEx getBuEx(int pBuEx) {
		if(businessUnitExDAO != null){
			return getBusinessUnitExDAO().getBusinessUnitExById(pBuEx);
		}
		return null;
	}
}
