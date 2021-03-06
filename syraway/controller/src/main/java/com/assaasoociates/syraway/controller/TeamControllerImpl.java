/**
 * 
 */
package com.assaasoociates.syraway.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.assaassociates.syraway.dao.ITeamDAO;
import com.assaassociates.syraway.model.Team;

/**
 * @author waheb
 *
 */

@Component("teamController")
public class TeamControllerImpl implements ITeamController {

	@Autowired
	private ITeamDAO teamDAO;
	
	public ITeamDAO getTeamDAO() {
		return teamDAO;
	}

	public void setTeamDAO(ITeamDAO pTeamDAO) {
		this.teamDAO = pTeamDAO;
	}
	
	public void addTeam(Team pTeam) {
		if(teamDAO != null){
			teamDAO.addTeam(pTeam);
		}
	}

	public void updateTeam(Team pTeam) {
		if(teamDAO != null){
			teamDAO.updateTeam(pTeam);
		}
	}

	public Team getTeam(Integer pTeamId) {
		Team result = null;
		if(teamDAO != null){
			result = teamDAO.getTeamById(pTeamId);
		}
		return result;
	}

	public List<Team> getAllTeams() {
		List<Team> result = new ArrayList<Team>();
		if(teamDAO != null)
			result = teamDAO.getAllTeams();
		return result;
	}
}
