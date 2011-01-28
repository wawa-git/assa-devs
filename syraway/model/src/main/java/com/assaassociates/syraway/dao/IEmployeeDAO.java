/**
 * 
 */
package com.assaassociates.syraway.dao;

import java.util.List;
import com.assaassociates.syraway.model.Employee;

/**
 * @author waheb
 *
 */
public interface IEmployeeDAO {
	public Employee getEmployeeById(String pEmployeePK);
	public List<Employee> getEmployeeOfBuEx(String pBuExId);
	public List<Employee> getAllEmployee();
	public void addEmployee(Employee pEmployee);
	public void removeEmployee(Employee pEmployee);
	public void removeEmployee(String pEmploStringId);
	public Employee getEmployeeFromEmail(String pEmail);
}
