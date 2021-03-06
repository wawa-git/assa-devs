/**
 * 
 */
package com.assaasoociates.syraway.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.assaassociates.syraway.dao.IBusinessUnitDAO;
import com.assaassociates.syraway.dao.IBusinessUnitExDAO;
import com.assaassociates.syraway.dao.IBusinessUnitPcDAO;
import com.assaassociates.syraway.model.BusinessUnit;
import com.assaassociates.syraway.model.BusinessUnitEx;
import com.assaassociates.syraway.model.BusinessUnitPc;

/**
 * @author waheb
 *
 */

@Component("buController")
public class BusinessUnitControllerImpl implements IBusinessUnitController {

	@Autowired
	private IBusinessUnitDAO businessUnitDAO;
	
	@Autowired
	private IBusinessUnitExDAO businessUnitExDAO;
	
	@Autowired
	private IBusinessUnitPcDAO businessUnitPcDAO;
	
	public IBusinessUnitPcDAO getBusinessUnitPcDAO() {
		return businessUnitPcDAO;
	}

	public void setBusinessUnitPcDAO(IBusinessUnitPcDAO businessUnitPcDAO) {
		this.businessUnitPcDAO = businessUnitPcDAO;
	}

	public void updateBusinessUnit(BusinessUnit pBu) {
		if(businessUnitDAO != null){
			businessUnitDAO.updateBusinessUnit(pBu);
		}
		
	}
	
	public void addBusinessUnit(String pBuExId, String pBuPcId) {
		BusinessUnit oBu = new BusinessUnit();
		oBu.setSwBuEx(getBuEx(pBuExId));
		oBu.setSwBuPc(getBuPc(pBuPcId));

		if(businessUnitDAO != null){
			businessUnitDAO.addBusinessUnit(oBu);
		}
		
	}
	
	public BusinessUnit getBusinessUnit(String pBuId){
		BusinessUnit result = null;
		if(businessUnitDAO != null){
			try {
				result = businessUnitDAO.getBusinessUnitById(Integer.valueOf(pBuId));
			} catch (NumberFormatException e) {
			}
		}
		return result;
	}
	
	public List<BusinessUnit> getAllBu() {
		if(businessUnitDAO != null){
			return businessUnitDAO.getAllBusinessUnit();
		}
		return null;
	}

	public IBusinessUnitExDAO getBusinessUnitExDAO() {
		return businessUnitExDAO;
	}

	public void setBusinessUnitExDAO(IBusinessUnitExDAO businessUnitExDAO) {
		this.businessUnitExDAO = businessUnitExDAO;
	}

	private BusinessUnitPc getBuPc(String pBuPcId){
		BusinessUnitPc result = null;
		if(pBuPcId != null){
			try {
				result = this.businessUnitPcDAO.getBusinessUnitPcById(Integer.parseInt(pBuPcId));
			} catch (NumberFormatException e) {
			}
			return result;
		}
		return result;
	}
	
	private BusinessUnitEx getBuEx(String pBuExId){
		BusinessUnitEx result = null;
		if(pBuExId != null){
			try {
				result = this.businessUnitExDAO.getBusinessUnitExById(Integer.parseInt(pBuExId));
			} catch (NumberFormatException e) {
			}
			return result;
		}
		return result;
	}

	public IBusinessUnitDAO getBusinessUnitDAO() {
		return businessUnitDAO;
	}

	public void setBusinessUnitDAO(IBusinessUnitDAO pBusinessUnitDAO) {
		businessUnitDAO = pBusinessUnitDAO;
	}

	public List<BusinessUnitEx> getAllBuEx() {
		List<BusinessUnitEx> result = new ArrayList<BusinessUnitEx>();
		if(businessUnitExDAO != null)
			result = businessUnitExDAO.getAllBusinessUnitEx();
		return result;
	}

	public List<BusinessUnitPc> getAllBuPc() {
		List<BusinessUnitPc> result = new ArrayList<BusinessUnitPc>();
		if(businessUnitPcDAO != null)
			result = businessUnitPcDAO.getAllBusinessUnitPc();
		return result;
	}
}
