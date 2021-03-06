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
import com.assaassociates.syraway.dao.IRoleDAO;
import com.assaassociates.syraway.model.Role;

/**
 * @author waheb
 *
 */

@Repository(value="RoleDAO")
public class RoleDAOImpl implements IRoleDAO {

	@PersistenceContext
	EntityManager oEntityManager;
	
	static Logger logger = LoggerFactory.getLogger(RoleDAOImpl.class);

	@PostConstruct
	void initLogging(){
		logger.info("RoleDAOImpl Instanciated ...");
	}
	
	@Transactional(isolation=Isolation.READ_COMMITTED)
	public Role getRoleById(Integer pRoleId) {
		if(oEntityManager != null){
			return oEntityManager.find(Role.class, pRoleId);
		}
		logger.info("EntityManager is null ... Cant't get Role by Id");
		return null;
	}

	@Transactional(isolation=Isolation.DEFAULT)
	public void addRole(Role pRole) {
		if(oEntityManager != null){
			oEntityManager.persist(pRole);
		}else
			logger.info("EntityManager is null ... Cant't add Role.");
	}
	
	@Transactional(isolation=Isolation.DEFAULT)
	public void updateRole(Role pRole) {
		if(oEntityManager != null){
			oEntityManager.merge(pRole);
		}else
			logger.info("EntityManager is null ... Cant't add Role.");
	}

	@Transactional(isolation=Isolation.DEFAULT)
	public void removeRole(Role pRole) {
		if(oEntityManager != null){
			oEntityManager.remove(pRole);
		}else
			logger.info("EntityManager is null ... Cant't remove Role.");
	}

	@Transactional(isolation=Isolation.DEFAULT)
	public void removeRole(Integer pRoleId) {
		if(oEntityManager != null){
			Role oRole = getRoleById(pRoleId);
			if(oRole != null){
				oEntityManager.remove(oRole);
			}
		}else
			logger.info("EntityManager is null ... Cant't remove Role.");
	}
	
	@Transactional(isolation=Isolation.READ_COMMITTED)
	public List<Role> getAllRoles() {
		if(oEntityManager != null){
			TypedQuery<Role> oQuery = oEntityManager.createQuery("SELECT r FROM Role As r",Role.class);
//			oQuery.setParameter("pEmployeeId", pEmployeeId);
			return oQuery.getResultList();
		}
		logger.info("EntityManager is null ... Can't get all Role");
		return new ArrayList<Role>();
	}
}