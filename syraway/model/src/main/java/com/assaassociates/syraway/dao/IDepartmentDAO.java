/**
 * 
 */
package com.assaassociates.syraway.dao;

import java.util.List;
import com.assaassociates.syraway.model.Department;

/**
 * @author waheb
 *
 */
public interface IDepartmentDAO {
	
	public Department getDepartmentById(int pId);
	public List<Department> getDepartmentOfBu(String pBuisnessUnitId);
	public List<Department> getAllDepts();
	public void addDepartment(Department pDepartment);
	public void removeDepartment(Department pDepartment);
	public void removeDepartment(int pDepartmentPk);
}
