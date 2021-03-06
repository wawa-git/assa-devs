/**
 * 
 */
package com.assaassociates.syraway.persistence.jpa;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import com.assaassociates.syraway.dao.IEmployeeDAO;
import com.assaassociates.syraway.model.Employee;

/**
 * @author waheb
 *
 */


@Repository(value="EmployeeDAO")
public class EmployeeDAOImpl implements IEmployeeDAO {

	@PersistenceContext
	EntityManager oEntityManager;
	
	static Logger logger = LoggerFactory.getLogger(EmployeeDAOImpl.class);
	
	@PostConstruct
	void initLogging(){
		logger.info("EmployeeDAOImpl Instanciated ...");
	}

	@Transactional(isolation=Isolation.READ_COMMITTED)
	public Employee getEmployeeById(String pEmployeePK) {
		if(oEntityManager != null){
			return oEntityManager.find(Employee.class, Integer.valueOf(pEmployeePK));
		}
		logger.info("EntityManager is null ... Cant't get Employee by Id");
		return null;
	}

	@Transactional(isolation=Isolation.READ_COMMITTED)
	public List<Employee> getEmployeeOfBuEx(String pBuExId) {
		if(oEntityManager != null){
			TypedQuery<Employee> oQuery = oEntityManager.createQuery("SELECT e FROM Employee e WHERE buExId = :pBuExId",Employee.class);
			oQuery.setParameter("pBuExId", pBuExId);
			return oQuery.getResultList();
		}
		logger.info("EntityManager is null ... Can't get Employee of BU_EX");
		return new ArrayList<Employee>();
	}

	@Transactional(isolation=Isolation.DEFAULT)
	public void addEmployee(Employee pEmployee) {
		if(oEntityManager != null){
			oEntityManager.persist(pEmployee);
		}else
			logger.info("EntityManager is null ... Cant't add Employee.");
	}
	
	@Transactional(isolation=Isolation.DEFAULT)
	public void removeEmployee(String pEmployeePK) {
		if(oEntityManager != null){
			Employee oEmployee = getEmployeeById(pEmployeePK);
			oEntityManager.remove(oEmployee);
		}else
			logger.info("EntityManager is null ... Cant't remove Employee.");
	}

	@Transactional(isolation=Isolation.DEFAULT)
	public void removeEmployee(Employee pEmployee) {
		if(oEntityManager != null){
			oEntityManager.remove(pEmployee);
		}else
			logger.info("EntityManager is null ... Cant't remove Employee.");
	}

	@Transactional(isolation=Isolation.READ_COMMITTED)
	public List<Employee> getAllEmployee() {
		if(oEntityManager != null){
			TypedQuery<Employee> oQuery = oEntityManager.createQuery("SELECT empl FROM Employee As empl",Employee.class);
			return oQuery.getResultList();
		}
		return new ArrayList<Employee>();
	}

}
