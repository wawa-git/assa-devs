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
import com.assaassociates.syraway.dao.ITeamDAO;
import com.assaassociates.syraway.model.Team;

/**
 * @author waheb
 *
 */

@Repository(value="TeamDAO")
public class TeamDAOImpl implements ITeamDAO {

	@PersistenceContext
	EntityManager oEntityManager;
	
	static Logger logger = LoggerFactory.getLogger(TeamDAOImpl.class);

	@PostConstruct
	void initLogging(){
		logger.info("TeamDAOImpl Instanciated ...");
	}
	
	@Transactional(isolation=Isolation.READ_COMMITTED)
	public Team getTeamById(Integer pTeamId) {
		if(oEntityManager != null){
			return oEntityManager.find(Team.class, pTeamId);
		}
		logger.info("EntityManager is null ... Cant't get Team by Id");
		return null;
	}
	
	@Transactional(isolation=Isolation.READ_COMMITTED)
	public List<Team> getTeamOfEmployee(Integer pEmployeeId) {
		if(oEntityManager != null){
//			TypedQuery<Team> oQuery = oEntityManager.createQuery("SELECT e FROM Project e WHERE buPcId = :pEmployeeId",Team.class);
//			oQuery.setParameter("pEmployeeId", pEmployeeId);
//			return oQuery.getResultList();
		}
		logger.info("EntityManager is null ... Can't get Project of Team");
		return new ArrayList<Team>();
	}

	@Transactional(isolation=Isolation.DEFAULT)
	public void addTeam(Team pTeam) {
		if(oEntityManager != null){
			oEntityManager.persist(pTeam);
		}else
			logger.info("EntityManager is null ... Cant't add Team.");
	}
	
	@Transactional(isolation=Isolation.DEFAULT)
	public void updateTeam(Team pTeam) {
		if(oEntityManager != null){
			oEntityManager.merge(pTeam);
		}else
			logger.info("EntityManager is null ... Cant't update Team.");
	}

	@Transactional(isolation=Isolation.DEFAULT)
	public void removeTeam(Team pTeam) {
		if(oEntityManager != null){
			oEntityManager.remove(pTeam);
		}else
			logger.info("EntityManager is null ... Cant't remove Team.");
	}

	@Transactional(isolation=Isolation.DEFAULT)
	public void removeTeam(Integer pTeamId) {
		if(oEntityManager != null){
			Team oTeam = getTeamById(pTeamId);
			if(oTeam != null){
				oEntityManager.remove(oTeam);
			}
		}else
			logger.info("EntityManager is null ... Cant't remove team.");
	}

	@Transactional(isolation=Isolation.READ_COMMITTED)
	public List<Team> getAllTeams() {
		if(oEntityManager != null){
			TypedQuery<Team> oQuery = oEntityManager.createQuery("SELECT tm FROM Team As tm",Team.class);
//			oQuery.setParameter("pEmployeeId", pEmployeeId);
			return oQuery.getResultList();
		}
		logger.info("EntityManager is null ... Can't get all Team");
		return new ArrayList<Team>();
	}

}