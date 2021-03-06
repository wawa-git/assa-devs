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
import com.assaassociates.syraway.dao.IBusinessUnitPcDAO;
import com.assaassociates.syraway.model.BusinessUnitPc;

/**
 * @author waheb
 *
 */


@Repository(value="BusinessUnitPcDAO")
public class BusinessUnitPcDAOImpl implements IBusinessUnitPcDAO {

	@PersistenceContext
	EntityManager oEntityManager;
	
	static Logger logger = LoggerFactory.getLogger(BusinessUnitPcDAOImpl.class);
	
	@PostConstruct
	void initLogging(){
		logger.info("BusinessUnitPcDAOImpl Instanciated ...");
	}

	@Transactional(isolation=Isolation.READ_COMMITTED)
	public BusinessUnitPc getBusinessUnitPcById(int pId) {
		if(oEntityManager != null)
			return oEntityManager.find(BusinessUnitPc.class, pId);
		
		logger.info("EntityManager is null ... Cant't get Business Unit Pc by Id");
		return null;
	}

	@Transactional(isolation=Isolation.DEFAULT)
	public void addBusinessUnitPc(int pBuPcId) {
		if(oEntityManager != null){
			BusinessUnitPc oBu = new BusinessUnitPc();
			oBu.setBuPcId(pBuPcId);
			oEntityManager.persist(pBuPcId);
		}else
			logger.info("EntityManager is null ... Cant't add Business Unit Pc.");
	}

	@Transactional(isolation=Isolation.DEFAULT)
	public void addBusinessUnitPc(BusinessUnitPc pBusinessUnitPc) {
		if(oEntityManager != null){
			oEntityManager.persist(pBusinessUnitPc);
		}else
			logger.info("EntityManager is null ... Cant't add Business Unit Pc.");
	}

	@Transactional(isolation=Isolation.DEFAULT)
	public void removeBusinessUnitPc(int pBuPcId) {
		if(oEntityManager != null){
			BusinessUnitPc oBuPc = getBusinessUnitPcById(pBuPcId);
			if(oBuPc != null){
				oEntityManager.remove(oBuPc);
			}
		}else
			logger.info("EntityManager is null ... Cant't remove Business Unit Pc.");
	}

	@Transactional(isolation=Isolation.DEFAULT)
	public void removeBusinessUnitPc(BusinessUnitPc pBusinessUnitPc) {
		if(oEntityManager != null){
			oEntityManager.remove(pBusinessUnitPc);
		}else
			logger.info("EntityManager is null ... Cant't remove Business Unit Pc.");
	}

	@Transactional(isolation=Isolation.READ_COMMITTED)
	public List<BusinessUnitPc> getAllBusinessUnitPc() {
		if(oEntityManager != null){
			TypedQuery<BusinessUnitPc> oQuery = oEntityManager.createQuery("SELECT buPc FROM BusinessUnitPc AS buPc",BusinessUnitPc.class);
			return oQuery.getResultList();
		}
		return new ArrayList<BusinessUnitPc>();
	}

}