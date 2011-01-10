/**
 * 
 */
package com.assaasoociates.syraway.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.assaassociates.syraway.dao.IBusinessUnitPcDAO;
import com.assaassociates.syraway.model.BusinessUnitPc;

/**
 * @author waheb
 *
 */

@Component("buPcController")
public class BusinessUnitPcControllerImpl implements IBusinessUnitPcController {

	@Autowired
	private IBusinessUnitPcDAO businessUnitPcDAO;
	
	public void addBusinessUnitPc(BusinessUnitPc pBusinessUnitPc) {
		BusinessUnitPc oBuPc = pBusinessUnitPc;
		if(businessUnitPcDAO != null){
			businessUnitPcDAO.addBusinessUnitPc(oBuPc);
		}
	}
	
	public List<BusinessUnitPc> getAllBuPc() {
		if(businessUnitPcDAO != null){
			return businessUnitPcDAO.getAllBusinessUnitPc();
		}
		return null;
	}

	public IBusinessUnitPcDAO getBusinessUnitPcDAO() {
		return businessUnitPcDAO;
	}

	public void setBusinessUnitPcDAO(IBusinessUnitPcDAO pBusinessUnitPcDAO) {
		businessUnitPcDAO = pBusinessUnitPcDAO;
	}

	public BusinessUnitPc getBuPc(int pBuPc) {
		if(businessUnitPcDAO != null){
			return businessUnitPcDAO.getBusinessUnitPcById(pBuPc);
		}
		return new BusinessUnitPc();
	}
}