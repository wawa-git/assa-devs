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
import com.assaassociates.syraway.dao.IBusinessUnitExDAO;
import com.assaassociates.syraway.model.BusinessUnitEx;

/**
 * @author waheb
 *
 */


@Repository(value="BusinessUnitExDAO")
public class BusinessUnitExDAOImpl implements IBusinessUnitExDAO {

	@PersistenceContext
	EntityManager oEntityManager;
	
	static Logger logger = LoggerFactory.getLogger(BusinessUnitExDAOImpl.class);
	
	@PostConstruct
	void initLogging(){
		logger.info("BusinessUnitDAOImpl Instanciated ...");
	}

	@Transactional(isolation=Isolation.READ_COMMITTED)
	public BusinessUnitEx getBusinessUnitExById(int pId) {
		if(oEntityManager != null)
			return oEntityManager.find(BusinessUnitEx.class, pId);
		
		logger.info("EntityManager is null ... Can't get Business Unit Ex by Id");
		return null;
	}

	@Transactional(isolation=Isolation.DEFAULT)
	public void addBusinessUnitEx(int pBuExId) {
		if(oEntityManager != null){
			BusinessUnitEx oBu = new BusinessUnitEx();
			oBu.setBuExId(pBuExId);
			oEntityManager.persist(pBuExId);
		}else
			logger.info("EntityManager is null ... Can't add Business Unit Ex.");
	}

	@Transactional(isolation=Isolation.DEFAULT)
	public void addBusinessUnitEx(BusinessUnitEx pBusinessUnitEx) {
		if(oEntityManager != null){
			oEntityManager.persist(pBusinessUnitEx);
		}else
			logger.info("EntityManager is null ... Can't add Business Unit Ex.");
	}

	@Transactional(isolation=Isolation.DEFAULT)
	public void removeBusinessUnitEx(int pBuExId) {
		if(oEntityManager != null){
			BusinessUnitEx oBuEx = getBusinessUnitExById(pBuExId);
			if(oBuEx != null){
				oEntityManager.remove(oBuEx);
			}
		}else
			logger.info("EntityManager is null ... Can't remove Business Unit Ex.");
	}

	@Transactional(isolation=Isolation.DEFAULT)
	public void removeBusinessUnitEx(BusinessUnitEx pBusinessUnitEx) {
		if(oEntityManager != null){
			oEntityManager.remove(pBusinessUnitEx);
		}else
			logger.info("EntityManager is null ... Can't remove Business Unit Ex.");
	}

	@Transactional(isolation=Isolation.READ_COMMITTED)
	public List<BusinessUnitEx> getAllBusinessUnitEx() {
		if(oEntityManager != null){
			TypedQuery<BusinessUnitEx> oQuery = oEntityManager.createQuery("SELECT buEx FROM BusinessUnitEx AS buEx",BusinessUnitEx.class);
			return oQuery.getResultList();
		}
		return new ArrayList<BusinessUnitEx>();
	}

}