package com.assaassociates.syraway.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the SW_BU database table.
 * 
 */
@Entity
@Table(name="SW_BU")
public class BusinessUnit implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="BU_ID", unique=true, nullable=false)
	private int buId;

	//bi-directional many-to-many association to Department
    @ManyToMany
	@JoinTable(
		name="SW_BU_DEPT"
		, joinColumns={
			@JoinColumn(name="BU_ID", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="DEPT_ID", nullable=false)
			}
		)
	private Set<Department> swDepts;

	//bi-directional many-to-one association to BusinessUnitEx
    @ManyToOne
	@JoinColumn(name="BU_EX_ID", nullable=false)
	private BusinessUnitEx swBuEx;

	//bi-directional many-to-one association to BusinessUnitPc
    @ManyToOne
	@JoinColumn(name="BU_PC_ID", nullable=false)
	private BusinessUnitPc swBuPc;

	//bi-directional many-to-many association to Setid
	@ManyToMany(mappedBy="swBus")
	private Set<Setid> swSetids;

	//bi-directional many-to-one association to UserRole
    @ManyToOne
	@JoinColumn(name="BU_ID", referencedColumnName="BU_ID", nullable=false, insertable=false, updatable=false)
	private UserRole swUserRole;

    public BusinessUnit() {
    }

	public int getBuId() {
		return this.buId;
	}

	public void setBuId(int buId) {
		this.buId = buId;
	}

	public Set<Department> getSwDepts() {
		return this.swDepts;
	}

	public void setSwDepts(Set<Department> swDepts) {
		this.swDepts = swDepts;
	}
	
	public BusinessUnitEx getSwBuEx() {
		return this.swBuEx;
	}

	public void setSwBuEx(BusinessUnitEx swBuEx) {
		this.swBuEx = swBuEx;
	}
	
	public BusinessUnitPc getSwBuPc() {
		return this.swBuPc;
	}

	public void setSwBuPc(BusinessUnitPc swBuPc) {
		this.swBuPc = swBuPc;
	}
	
	public Set<Setid> getSwSetids() {
		return this.swSetids;
	}

	public void setSwSetids(Set<Setid> swSetids) {
		this.swSetids = swSetids;
	}
	
	public UserRole getSwUserRole() {
		return this.swUserRole;
	}

	public void setSwUserRole(UserRole swUserRole) {
		this.swUserRole = swUserRole;
	}
	
}