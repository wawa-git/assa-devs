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
import com.assaassociates.syraway.dao.IBusinessUnitDAO;
import com.assaassociates.syraway.model.BusinessUnit;

/**
 * @author waheb
 *
 */


@Repository(value="BusinessUnitDAO")
public class BusinessUnitDAOImpl implements IBusinessUnitDAO {

	@PersistenceContext
	EntityManager oEntityManager;
	
	static Logger logger = LoggerFactory.getLogger(BusinessUnitDAOImpl.class);
	
	@PostConstruct
	void initLogging(){
		logger.info("BusinessUnitDAOImpl Instanciated ...");
	}

	@Transactional(isolation=Isolation.READ_COMMITTED)
	public BusinessUnit getBusinessUnitById(int pId) {
		if(oEntityManager != null)
			return oEntityManager.find(BusinessUnit.class, pId);
		
		logger.info("EntityManager is null ... Cant't get Business Unit by Id");
		return null;
	}

	@Transactional(isolation=Isolation.DEFAULT)
	public void updateBusinessUnit(BusinessUnit pBu) {
		if(oEntityManager != null){
			oEntityManager.merge(pBu);
		}else
			logger.info("EntityManager is null ... Cant't update Business Unit.");
	}
	
	@Transactional(isolation=Isolation.DEFAULT)
	public void addBusinessUnit(int pBuId) {
		if(oEntityManager != null){
			BusinessUnit oBu = new BusinessUnit();
			oBu.setBuId(pBuId);
			oEntityManager.persist(oBu);
		}else
			logger.info("EntityManager is null ... Cant't add Business Unit.");
	}

	@Transactional(isolation=Isolation.DEFAULT)
	public void addBusinessUnit(BusinessUnit pBusinessUnit) {
		if(oEntityManager != null){
			oEntityManager.persist(pBusinessUnit);
		}else
			logger.info("EntityManager is null ... Cant't add Business Unit.");
	}

	@Transactional(isolation=Isolation.DEFAULT)
	public void removeBusinessUnit(int pBuId) {
		if(oEntityManager != null){
			BusinessUnit oBu = getBusinessUnitById(pBuId);
			if(oBu != null){
				oEntityManager.remove(oBu);
			}
		}else
			logger.info("EntityManager is null ... Cant't remove Business Unit.");
	}

	@Transactional(isolation=Isolation.DEFAULT)
	public void removeBusinessUnit(BusinessUnit pBusinessUnit) {
		if(oEntityManager != null){
			oEntityManager.remove(pBusinessUnit);
		}else
			logger.info("EntityManager is null ... Cant't remove Business Unit.");
	}

	@Transactional(isolation=Isolation.READ_COMMITTED)
	public List<BusinessUnit> getAllBusinessUnit() {
		if(oEntityManager != null){
			TypedQuery<BusinessUnit> oQuery = oEntityManager.createQuery("SELECT bu FROM BusinessUnit AS bu",BusinessUnit.class);
			return oQuery.getResultList();
		}
		return new ArrayList<BusinessUnit>();
	}

}