package com.assaassociates.syraway.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the SW_TEAM database table.
 * 
 */
@Entity
@Table(name="SW_TEAM")
public class Team implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="TEAM_ID", unique=true, nullable=false)
	private int teamId;

	@Column(name="ACTIVE")
	private int active;

	@Column(name="DESCRIPTION", length=200)
	private String description;

	@Column(name="NAME", length=45)
	private String name;

	//bi-directional many-to-many association to Employee
	@ManyToMany(mappedBy="swTeams1")
	private Set<Employee> swEmployees;

	//bi-directional many-to-one association to Employee
    @ManyToOne
	@JoinColumn(name="MANAGER_ID", nullable=false)
	private Employee swEmployee;

	//bi-directional many-to-one association to Time
	@OneToMany(mappedBy="swTeam")
	private Set<Time> swTimes;

    public Team() {
    }

	public int getTeamId() {
		return this.teamId;
	}

	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}

	public int getActive() {
		return this.active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Employee> getSwEmployees() {
		return this.swEmployees;
	}

	public void setSwEmployees(Set<Employee> swEmployees) {
		this.swEmployees = swEmployees;
	}
	
	public Employee getSwEmployee() {
		return this.swEmployee;
	}

	public void setSwEmployee(Employee swEmployee) {
		this.swEmployee = swEmployee;
	}
	
	public Set<Time> getSwTimes() {
		return this.swTimes;
	}

	public void setSwTimes(Set<Time> swTimes) {
		this.swTimes = swTimes;
	}
	
	@Override
	public String toString(){
		return String.valueOf(this.teamId);
	}
}