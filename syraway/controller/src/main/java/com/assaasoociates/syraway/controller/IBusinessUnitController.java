package com.assaasoociates.syraway.controller;

import java.util.List;

import com.assaassociates.syraway.model.BusinessUnit;
import com.assaassociates.syraway.model.BusinessUnitEx;
import com.assaassociates.syraway.model.BusinessUnitPc;

public interface IBusinessUnitController {

	public void addBusinessUnit(String pBuExId, String pBuPcId);
	
	public void updateBusinessUnit(BusinessUnit pBu);
	
	public BusinessUnit getBusinessUnit(String pBuId); 
	
	public List<BusinessUnit> getAllBu();
	
	public List<BusinessUnitEx> getAllBuEx();

	public List<BusinessUnitPc> getAllBuPc();
}