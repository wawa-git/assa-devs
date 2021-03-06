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
import com.assaassociates.syraway.dao.ITimeDAO;
import com.assaassociates.syraway.model.Time;

/**
 * @author waheb
 *
 */

@Repository(value="TimeDAO")
public class TimeDAOImpl implements ITimeDAO {

	@PersistenceContext
	EntityManager oEntityManager;
	
	static Logger logger = LoggerFactory.getLogger(TimeDAOImpl.class);

	@PostConstruct
	void initLogging(){
		logger.info("TimeDAOImpl Instanciated ...");
	}
	
	@Transactional(isolation=Isolation.READ_COMMITTED)
	public Time getTimeById(Integer pTimeId) {
		if(oEntityManager != null){
			return oEntityManager.find(Time.class, pTimeId);
		}
		logger.info("EntityManager is null ... Cant't get Time by Id");
		return null;
	}
	
	@Transactional(isolation=Isolation.READ_COMMITTED)
	public List<Time> getTimeOfEmployee(Integer pEmployeeId) {
		if(oEntityManager != null){
//			TypedQuery<Time> oQuery = oEntityManager.createQuery("SELECT e FROM Project e WHERE buPcId = :pEmployeeId",Time.class);
//			oQuery.setParameter("pEmployeeId", pEmployeeId);
//			return oQuery.getResultList();
		}
		logger.info("EntityManager is null ... Can't get Time of Employee");
		return new ArrayList<Time>();
	}
	
	@Transactional(isolation=Isolation.READ_COMMITTED)
	public List<Time> getTimeOfTask(Integer pTaskId) {
		if(oEntityManager != null){
//			TypedQuery<Time> oQuery = oEntityManager.createQuery("SELECT e FROM Project e WHERE buPcId = :pTaskId",Time.class);
//			oQuery.setParameter("pTaskId", pTaskId);
//			return oQuery.getResultList();
		}
		logger.info("EntityManager is null ... Can't get Time of Task");
		return new ArrayList<Time>();
	}
	
	@Transactional(isolation=Isolation.READ_COMMITTED)
	public List<Time> getTimeOfTeam(Integer pTeamId) {
		if(oEntityManager != null){
//			TypedQuery<Time> oQuery = oEntityManager.createQuery("SELECT e FROM Project e WHERE buPcId = :pTeamId",Time.class);
//			oQuery.setParameter("pTeamId", pTeamId);
//			return oQuery.getResultList();
		}
		logger.info("EntityManager is null ... Can't get Time of Team");
		return new ArrayList<Time>();
	}
	
	@Transactional(isolation=Isolation.DEFAULT)
	public void addTime(Time pTime) {
		if(oEntityManager != null){
			oEntityManager.persist(pTime);
		}else
			logger.info("EntityManager is null ... Cant't add Project.");
	}
	
	@Transactional(isolation=Isolation.DEFAULT)
	public void updateTime(Time pTime) {
		if(oEntityManager != null){
			oEntityManager.merge(pTime);
		}else
			logger.info("EntityManager is null ... Cant't update Project.");
	}

	@Transactional(isolation=Isolation.DEFAULT)
	public void removeTime(Time pTime) {
		if(oEntityManager != null){
			oEntityManager.remove(pTime);
		}else
			logger.info("EntityManager is null ... Cant't remove Time.");
	}

	@Transactional(isolation=Isolation.DEFAULT)
	public void removeTime(Integer pTimeId) {
		if(oEntityManager != null){
			Time oTime = getTimeById(pTimeId);
			if(oTime != null){
				oEntityManager.remove(oTime);
			}
		}else
			logger.info("EntityManager is null ... Cant't remove Time.");
	}
	
	@Transactional(isolation=Isolation.READ_COMMITTED)
	public List<Time> getAllTimes() {
		if(oEntityManager != null){
			TypedQuery<Time> oQuery = oEntityManager.createQuery("SELECT t FROM Time As t",Time.class);
			return oQuery.getResultList();
		}
		logger.info("EntityManager is null ... Can't get all Time of Task");
		return new ArrayList<Time>();
	}
}