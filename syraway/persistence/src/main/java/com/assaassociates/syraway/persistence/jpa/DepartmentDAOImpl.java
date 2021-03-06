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
import com.assaassociates.syraway.dao.IDepartmentDAO;
import com.assaassociates.syraway.model.Department;

/**
 * @author waheb
 *
 */

@Repository(value="DepartmentDAO")
public class DepartmentDAOImpl implements IDepartmentDAO {

	@PersistenceContext
	EntityManager oEntityManager;
	
	static Logger logger = LoggerFactory.getLogger(DepartmentDAOImpl.class);

	@PostConstruct
	void initLogging(){
		logger.info("DepartmentDAOImpl Instanciated ...");
	}
	
	@Transactional(isolation=Isolation.READ_COMMITTED)
	public Department getDepartmentById(int pDepartmentPK) {
		if(oEntityManager != null){
			return oEntityManager.find(Department.class, pDepartmentPK);
		}
		logger.info("EntityManager is null ... Cant't get Department by Id");
		return null;
	}
	
	@Transactional(isolation=Isolation.READ_COMMITTED)
	public List<Department> getDepartmentOfBu(String pBuisnessUnitId) {
		if(oEntityManager != null){
			TypedQuery<Department> oQuery = oEntityManager.createQuery("SELECT e FROM Department e WHERE buId = :pBuId",Department.class);
			oQuery.setParameter("pBuId", pBuisnessUnitId);
			return oQuery.getResultList();
		}
		logger.info("EntityManager is null ... Can't get Department of BU");
		return new ArrayList<Department>();
	}

	@Transactional(isolation=Isolation.DEFAULT)
	public void addDepartment(Department pDepartment) {
		if(oEntityManager != null){
			oEntityManager.persist(pDepartment);
		}else
			logger.info("EntityManager is null ... Cant't add Department.");
	}

	@Transactional(isolation=Isolation.DEFAULT)
	public void removeDepartment(Department pDepartment) {
		if(oEntityManager != null){
			oEntityManager.remove(pDepartment);
		}else
			logger.info("EntityManager is null ... Cant't remove Department.");
	}

	@Transactional(isolation=Isolation.DEFAULT)
	public void removeDepartment(int pDepartmentPK) {
		if(oEntityManager != null){
			Department oDepartment = getDepartmentById(pDepartmentPK);
			if(oDepartment != null){
				oEntityManager.remove(oDepartment);
			}
		}else
			logger.info("EntityManager is null ... Cant't remove Department.");
	}

	@Transactional(isolation=Isolation.READ_COMMITTED)
	public List<Department> getAllDepts() {
		if(oEntityManager != null){
			TypedQuery<Department> oQuery = oEntityManager.createQuery("SELECT dept FROM Department AS dept",Department.class);
			return oQuery.getResultList();
		}
		return new ArrayList<Department>();
	}
}