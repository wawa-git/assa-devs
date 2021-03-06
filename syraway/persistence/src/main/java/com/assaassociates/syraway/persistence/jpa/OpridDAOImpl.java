/**
 * 
 */
package com.assaassociates.syraway.persistence.jpa;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import com.assaassociates.syraway.dao.IOprIdDAO;
import com.assaassociates.syraway.model.Oprid;

/**
 * @author waheb
 *
 */


@Repository(value="opridDAO")
public class OpridDAOImpl implements IOprIdDAO {

	@PersistenceContext
	EntityManager oEntityManager;
	
	static Logger logger = LoggerFactory.getLogger(OpridDAOImpl.class);
	
	@PostConstruct
	void initLogging(){
		logger.info("OpridDAOImpl Instanciated ...");
	}

	@Transactional(isolation=Isolation.READ_COMMITTED)
	public Oprid getOpridById(String pOprid) {
		if(oEntityManager != null){
			return oEntityManager.find(Oprid.class, pOprid);
		}
		logger.info("EntityManager is null ... Cant't get Oprid by Id");
		return null;
	}

	@Transactional(isolation=Isolation.READ_COMMITTED)
	public Oprid getOpridOfEmployee(String pEmployeeId) {
		if(oEntityManager != null){
			TypedQuery<Oprid> oQuery = oEntityManager.createQuery("SELECT op FROM Oprid op WHERE emplId = :pEmployeeId",Oprid.class);
			oQuery.setParameter("pEmployeeId", pEmployeeId);
			return oQuery.getSingleResult();
		}
		logger.info("EntityManager is null ... Can't get Oprid of Employee");
		return new Oprid();
	}

	@Transactional(isolation=Isolation.DEFAULT)
	public void addOprid(Oprid oOprid) {
		if(oEntityManager != null){
			oEntityManager.persist(oOprid);
		}else
			logger.info("EntityManager is null ... Cant't add Oprid.");
	}
	
	@Transactional(isolation=Isolation.DEFAULT)
	public void updateOprid(Oprid oOprid) {
		if(oEntityManager != null){
			oEntityManager.merge(oOprid);
		}else
			logger.info("EntityManager is null ... Cant't update Oprid.");
	}
	
	@Transactional(isolation=Isolation.DEFAULT)
	public void removeOprid(String pOpridId) {
		if(oEntityManager != null){
			Oprid oOprid = getOpridById(pOpridId);
			oEntityManager.remove(oOprid);
		}else
			logger.info("EntityManager is null ... Cant't remove Oprid.");
	}

	@Transactional(isolation=Isolation.DEFAULT)
	public void removeOprid(Oprid pOprid) {
		if(oEntityManager != null){
			oEntityManager.remove(pOprid);
		}else
			logger.info("EntityManager is null ... Cant't remove Oprid.");
	}

}
