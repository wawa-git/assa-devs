package com.assaasoociates.syraway.controller;

import java.util.List;
import com.assaassociates.syraway.model.BusinessUnitEx;

public interface IBusinessUnitExController {

	public void addBusinessUnitEx(BusinessUnitEx pBusinessUnitEx);
	
	public BusinessUnitEx getBuEx(int pBuEx);
	
	public List<BusinessUnitEx> getAllBuEx();
}
