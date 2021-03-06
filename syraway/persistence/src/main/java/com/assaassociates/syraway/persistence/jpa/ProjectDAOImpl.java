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
import com.assaassociates.syraway.dao.IProjectDAO;
import com.assaassociates.syraway.model.Project;

/**
 * @author waheb
 *
 */

@Repository(value="ProjectDAO")
public class ProjectDAOImpl implements IProjectDAO {

	@PersistenceContext
	EntityManager oEntityManager;
	
	static Logger logger = LoggerFactory.getLogger(ProjectDAOImpl.class);

	@PostConstruct
	void initLogging(){
		logger.info("ProjectDAOImpl Instanciated ...");
	}
	
	@Transactional(isolation=Isolation.READ_COMMITTED)
	public Project getProjectById(int pProjectId) {
		if(oEntityManager != null){
			return oEntityManager.find(Project.class, pProjectId);
		}
		logger.info("EntityManager is null ... Cant't get Project by Id");
		return null;
	}
	
	@Transactional(isolation=Isolation.READ_COMMITTED)
	public List<Project> getProjectOfBuPc(String pBuPcId) {
		if(oEntityManager != null){
			TypedQuery<Project> oQuery = oEntityManager.createQuery("SELECT e FROM Project e WHERE buPcId = :pBuPcId",Project.class);
			oQuery.setParameter("pBuPcId", pBuPcId);
			return oQuery.getResultList();
		}
		logger.info("EntityManager is null ... Can't get Project of BuPc");
		return new ArrayList<Project>();
	}
	
	@Transactional(isolation=Isolation.READ_COMMITTED)
	public List<Project> getProjectOfManager(String pEmployeeId) {
		if(oEntityManager != null){
			TypedQuery<Project> oQuery = oEntityManager.createQuery("SELECT e FROM Project e WHERE managerId = :pEmployeeId",Project.class);
			oQuery.setParameter("pEmployeeId", pEmployeeId);
			return oQuery.getResultList();
		}
		logger.info("EntityManager is null ... Can't get Project of Manager");
		return new ArrayList<Project>();
	}

	@Transactional(isolation=Isolation.DEFAULT)
	public void addProject(Project pProject) {
		if(oEntityManager != null){
			oEntityManager.persist(pProject);
		}else
			logger.info("EntityManager is null ... Cant't add Project.");
	}

	@Transactional(isolation=Isolation.DEFAULT)
	public void removeProject(Project pProject) {
		if(oEntityManager != null){
			oEntityManager.remove(pProject);
		}else
			logger.info("EntityManager is null ... Cant't remove Project.");
	}

	@Transactional(isolation=Isolation.DEFAULT)
	public void removeProject(int pProjectId) {
		if(oEntityManager != null){
			Project oProject = getProjectById(pProjectId);
			if(oProject != null){
				oEntityManager.remove(oProject);
			}
		}else
			logger.info("EntityManager is null ... Cant't remove Project.");
	}

	@Transactional(isolation=Isolation.READ_COMMITTED)
	public List<Project> getAllProjects() {
		if(oEntityManager != null){
			TypedQuery<Project> oQuery = oEntityManager.createQuery("SELECT p FROM Project AS p",Project.class);
			return oQuery.getResultList();
		}
		return new ArrayList<Project>();
	}
}