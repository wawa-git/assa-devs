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
import com.assaassociates.syraway.dao.IUserRoleDAO;
import com.assaassociates.syraway.model.UserRole;

/**
 * @author waheb
 *
 */

@Repository(value="UserRoleDAO")
public class UserRoleDAOImpl implements IUserRoleDAO {

	@PersistenceContext
	EntityManager oEntityManager;
	
	static Logger logger = LoggerFactory.getLogger(UserRoleDAOImpl.class);

	@PostConstruct
	void initLogging(){
		logger.info("UserRoleDAOImpl Instanciated ...");
	}
	
	@Transactional(isolation=Isolation.READ_COMMITTED)
	public UserRole getUserRoleById(Integer pUserRoleId) {
		if(oEntityManager != null){
			return oEntityManager.find(UserRole.class, pUserRoleId);
		}
		logger.info("EntityManager is null ... Cant't get UserRole by Id");
		return null;
	}
	
	@Transactional(isolation=Isolation.READ_COMMITTED)
	public List<UserRole> getUserRoleOfEmployee(Integer pEmployeeId) {
		if(oEntityManager != null){
			TypedQuery<UserRole> oQuery = oEntityManager.createQuery("SELECT e FROM UserRole e WHERE swEmployee.emplId = :pEmployeeId",UserRole.class);
			oQuery.setParameter("pEmployeeId", pEmployeeId);
			return oQuery.getResultList();
		}
		logger.info("EntityManager is null ... Can't get UserRole of Employee");
		return new ArrayList<UserRole>();
	}
	
	@Transactional(isolation=Isolation.DEFAULT)
	public void addUserRole(UserRole pUserRole) {
		if(oEntityManager != null){
			oEntityManager.persist(pUserRole);
		}else
			logger.info("EntityManager is null ... Cant't add UserRole.");
	}
	
	@Transactional(isolation=Isolation.DEFAULT)
	public void updateUserRole(UserRole pUserRole) {
		if(oEntityManager != null){
			oEntityManager.merge(pUserRole);
		}else
			logger.info("EntityManager is null ... Cant't update UserRole.");
	}

	@Transactional(isolation=Isolation.DEFAULT)
	public void removeUserRole(UserRole pUserRole) {
		if(oEntityManager != null){
			oEntityManager.remove(pUserRole);
		}else
			logger.info("EntityManager is null ... Cant't remove Project.");
	}

	@Transactional(isolation=Isolation.DEFAULT)
	public void removeUserRole(Integer pUserRoleId) {
		if(oEntityManager != null){
			UserRole oUserRole = getUserRoleById(pUserRoleId);
			if(oUserRole != null){
				oEntityManager.remove(oUserRole);
			}
		}else
			logger.info("EntityManager is null ... Cant't remove UserRole.");
	}

	@Transactional(isolation=Isolation.READ_COMMITTED)
	public List<UserRole> getAllUserRoles() {
		if(oEntityManager != null){
			TypedQuery<UserRole> oQuery = oEntityManager.createQuery("SELECT ur FROM UserRole As ur",UserRole.class);
			return oQuery.getResultList();
		}
		logger.info("EntityManager is null ... Can't get UserRole of Employee");
		return new ArrayList<UserRole>();
	}
	
}