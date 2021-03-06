package com.assaassociates.syraway.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the SW_USER_ROLE database table.
 * 
 */
@Entity
@Table(name="SW_USER_ROLE")
public class UserRole implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="USER_ROLE_ID", unique=true, nullable=false)
	private int userRoleId;

	@Column(name="DESCRIPTION", length=100)
	private String description;

	@Column(name="NAME", length=100)
	private String name;

	//bi-directional many-to-one association to Employee
    @ManyToOne
	@JoinColumn(name="EMPL_ID", nullable=false)
	private Employee swEmployee;

	//bi-directional many-to-one association to Role
	@ManyToOne
	@JoinColumn(name="ROLE_ID", nullable=false)
	private Role swRole;

	//bi-directional many-to-one association to BusinessUnit
	@ManyToOne
	@JoinColumn(name="BU_ID", nullable=true)
	private BusinessUnit swBus;

    public UserRole() {
    }

	public int getUserRoleId() {
		return this.userRoleId;
	}

	public void setUserRoleId(int userRoleId) {
		this.userRoleId = userRoleId;
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

	public Employee getSwEmployee() {
		return this.swEmployee;
	}

	public void setSwEmployee(Employee swEmployee) {
		this.swEmployee = swEmployee;
	}
	
	public Role getSwRole() {
		return this.swRole;
	}

	public void setSwRole(Role pSwRole) {
		this.swRole = pSwRole;
	}
	
	public BusinessUnit getSwBus() {
		return this.swBus;
	}

	public void setSwBus(BusinessUnit pSwBus) {
		this.swBus = pSwBus;
	}
	
}