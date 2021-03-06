package com.assaassociates.syraway.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;


/**
 * The persistent class for the SW_ROLE database table.
 * 
 */
@Entity
@Table(name="SW_ROLE")
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ROLE_ID", unique=true, nullable=false)
	private int roleId;

	@Column(name="DESCRIPTION", length=100)
	private String description;

	@Column(name="NAME", length=100)
	private String name;

	//bi-directional one-to-many association to UserRole
	@OneToMany(mappedBy="swRole")
	private Set<UserRole> swUserRole;

    public Role() {
    }

	public int getRoleId() {
		return this.roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
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

	public Set<UserRole> getSwUserRole() {
		return this.swUserRole;
	}

	public void setSwUserRole(Set<UserRole> swUserRole) {
		this.swUserRole = swUserRole;
	}
	
	@Override
	public String toString(){
		return String.valueOf(this.getRoleId());
	}
}