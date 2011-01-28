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
import com.assaassociates.syraway.dao.IPageRoleDAO;
import com.assaassociates.syraway.model.PageRole;

/**
 * @author waheb
 *
 */

@Repository(value="PageRoleDAO")
public class PageRoleDAOImpl implements IPageRoleDAO {

	@PersistenceContext
	EntityManager oEntityManager;
	
	static Logger logger = LoggerFactory.getLogger(PageRoleDAOImpl.class);

	@PostConstruct
	void initLogging(){
		logger.info("PageRoleDAOImpl Instanciated ...");
	}
	
	@Transactional(isolation=Isolation.READ_COMMITTED)
	public PageRole getPageRoleById(Integer pPageRoleId) {
		if(oEntityManager != null){
			return oEntityManager.find(PageRole.class, pPageRoleId);
		}
		logger.info("EntityManager is null ... Cant't get PageRole by Id");
		return null;
	}
	
	@Transactional(isolation=Isolation.READ_COMMITTED)
	public List<PageRole> getPageRoleOfPage(Integer pPageId) {
		if(oEntityManager != null){
			TypedQuery<PageRole> oQuery = oEntityManager.createQuery("SELECT e FROM PageRole e WHERE swPage.pageId = :pPageId",PageRole.class);
			oQuery.setParameter("pPageId", pPageId);
			return oQuery.getResultList();
		}
		logger.info("EntityManager is null ... Can't get PageRole of Page");
		return new ArrayList<PageRole>();
	}
	
	@Transactional(isolation=Isolation.DEFAULT)
	public void addPageRole(PageRole pPageRole) {
		if(oEntityManager != null){
			oEntityManager.persist(pPageRole);
		}else
			logger.info("EntityManager is null ... Cant't add PageRole.");
	}
	
	@Transactional(isolation=Isolation.DEFAULT)
	public void updatePageRole(PageRole pPageRole) {
		if(oEntityManager != null){
			oEntityManager.merge(pPageRole);
		}else
			logger.info("EntityManager is null ... Cant't update PageRole.");
	}

	@Transactional(isolation=Isolation.DEFAULT)
	public void removePageRole(PageRole pPageRole) {
		if(oEntityManager != null){
			oEntityManager.remove(pPageRole);
		}else
			logger.info("EntityManager is null ... Cant't remove Project.");
	}

	@Transactional(isolation=Isolation.DEFAULT)
	public void removePageRole(Integer pPageRoleId) {
		if(oEntityManager != null){
			PageRole oPageRole = getPageRoleById(pPageRoleId);
			if(oPageRole != null){
				oEntityManager.remove(oPageRole);
			}
		}else
			logger.info("EntityManager is null ... Cant't remove PageRole.");
	}

	@Transactional(isolation=Isolation.READ_COMMITTED)
	public List<PageRole> getAllPageRoles() {
		if(oEntityManager != null){
			TypedQuery<PageRole> oQuery = oEntityManager.createQuery("SELECT ur FROM PageRole As ur",PageRole.class);
			return oQuery.getResultList();
		}
		logger.info("EntityManager is null ... Can't get PageRole");
		return new ArrayList<PageRole>();
	}
	
}