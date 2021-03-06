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
import com.assaassociates.syraway.dao.ITaskDAO;
import com.assaassociates.syraway.model.Task;

/**
 * @author waheb
 *
 */

@Repository(value="TaskDAO")
public class TaskDAOImpl implements ITaskDAO {

	@PersistenceContext
	EntityManager oEntityManager;
	
	static Logger logger = LoggerFactory.getLogger(TaskDAOImpl.class);

	@PostConstruct
	void initLogging(){
		logger.info("TaskDAOImpl Instanciated ...");
	}
	
	@Transactional(isolation=Isolation.READ_COMMITTED)
	public Task getTaskById(Integer pTaskId) {
		if(oEntityManager != null){
			return oEntityManager.find(Task.class, pTaskId);
		}
		logger.info("EntityManager is null ... Cant't get Task by Id");
		return null;
	}
	
	@Transactional(isolation=Isolation.READ_COMMITTED)
	public List<Task> getTaskOfProject(Integer pProjectId) {
		if(oEntityManager != null){
//			TypedQuery<Task> oQuery = oEntityManager.createQuery("SELECT e FROM Task e WHERE pProjectId = :pProjectId",Task.class);
//			oQuery.setParameter("pProjectId", pProjectId);
//			return oQuery.getResultList();
		}
		logger.info("EntityManager is null ... Can't get Task of Project");
		return new ArrayList<Task>();
	}
	
	@Transactional(isolation=Isolation.READ_COMMITTED)
	public List<Task> getTaskOfEmployee(Integer pEmployeeId) {
		if(oEntityManager != null){
			TypedQuery<Task> oQuery = oEntityManager.createQuery("SELECT e FROM Project e WHERE managerId = :pEmployeeId",Task.class);
			oQuery.setParameter("pEmployeeId", pEmployeeId);
			return oQuery.getResultList();
		}
		logger.info("EntityManager is null ... Can't get Task of Employee");
		return new ArrayList<Task>();
	}

	@Transactional(isolation=Isolation.DEFAULT)
	public void addTask(Task pTask) {
		if(oEntityManager != null){
			oEntityManager.persist(pTask);
		}else
			logger.info("EntityManager is null ... Cant't add Task.");
	}
	
	@Transactional(isolation=Isolation.DEFAULT)
	public void updateTask(Task pTask) {
		if(oEntityManager != null){
			oEntityManager.merge(pTask);
		}else
			logger.info("EntityManager is null ... Cant't update Task.");
	}

	@Transactional(isolation=Isolation.DEFAULT)
	public void removeTask(Task pTask) {
		if(oEntityManager != null){
			oEntityManager.remove(pTask);
		}else
			logger.info("EntityManager is null ... Cant't remove Task.");
	}

	@Transactional(isolation=Isolation.DEFAULT)
	public void removeTask(Integer pTaskId) {
		if(oEntityManager != null){
			Task oTask = getTaskById(pTaskId);
			if(oTask != null){
				oEntityManager.remove(oTask);
			}
		}else
			logger.info("EntityManager is null ... Cant't remove Task.");
	}
	
	@Transactional(isolation=Isolation.READ_COMMITTED)
	public List<Task> getAllTasks() {
		if(oEntityManager != null){
			TypedQuery<Task> oQuery = oEntityManager.createQuery("SELECT tsk FROM Task As tsk",Task.class);
//			oQuery.setParameter("pEmployeeId", pEmployeeId);
			return oQuery.getResultList();
		}
		logger.info("EntityManager is null ... Can't get all Task");
		return new ArrayList<Task>();
	}
}