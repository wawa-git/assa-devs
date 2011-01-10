/**
 * 
 */
package com.assaassociates.syraway.dao;

import java.util.List;
import com.assaassociates.syraway.model.Team;

/**
 * @author waheb
 *
 */
public interface ITeamDAO {
	public Team getTeamById(Integer pTeamId);
	public List<Team> getTeamOfEmployee(Integer pEmployeeId);
	public void addTeam(Team oTeam);
	public void updateTeam(Team oTeam);
	public void removeTeam(Team pTeam);
	public void removeTeam(Integer pTeamId);
	public List<Team> getAllTeams();
}
