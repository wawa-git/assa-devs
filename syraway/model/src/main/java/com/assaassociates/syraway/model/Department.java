package com.assaassociates.syraway.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the SW_DEPT database table.
 * 
 */
@Entity
@Table(name="SW_DEPT")
public class Department implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="DEPT_ID", unique=true, nullable=false)
	private int deptId;

	@Column(name="ACTIVE")
	private int active;

	@Column(name="DESCR", length=120)
	private String descr;

	//bi-directional many-to-many association to BusinessUnit
	@ManyToMany(mappedBy="swDepts")
	private Set<BusinessUnit> swBus;

	//bi-directional many-to-one association to Employee
	@OneToMany(mappedBy="swDept")
	private Set<Employee> swEmployees;

	//bi-directional many-to-one association to BusinessUnitEx
    @ManyToOne
	@JoinColumn(name="DEFAULT_BU_EX_ID", nullable=false)
	private BusinessUnitEx swBuEx;

    public Department() {
    }

	public int getDeptId() {
		return this.deptId;
	}

	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}

	public int getActive() {
		return this.active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public String getDescr() {
		return this.descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public Set<BusinessUnit> getSwBus() {
		return this.swBus;
	}

	public void setSwBus(Set<BusinessUnit> swBus) {
		this.swBus = swBus;
	}
	
	public Set<Employee> getSwEmployees() {
		return this.swEmployees;
	}

	public void setSwEmployees(Set<Employee> swEmployees) {
		this.swEmployees = swEmployees;
	}
	
	public BusinessUnitEx getSwBuEx() {
		return this.swBuEx;
	}

	public void setSwBuEx(BusinessUnitEx swBuEx) {
		System.err.println(swBuEx.getClass());
		this.swBuEx = swBuEx;
	}
	
	@Override
	public String toString(){
		return String.valueOf(getDeptId());
	}
	
}