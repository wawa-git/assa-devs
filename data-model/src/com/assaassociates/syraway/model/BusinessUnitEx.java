package com.assaassociates.syraway.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the SW_BU_EX database table.
 * 
 */
@Entity
@Table(name="SW_BU_EX")
public class BusinessUnitEx implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="BU_EX_ID", unique=true, nullable=false)
	private int buExId;

	@Column(name="BSE_CUR", length=3)
	private String bseCur;

	@Column(name="CAL_ID", length=30)
	private String calId;

	@Column(name="EX_CHRONO")
	private int exChrono;

	@Column(name="STATUS", length=1)
	private String status;

	@Column(name="TIME_CHRONO")
	private int timeChrono;

	//bi-directional many-to-one association to BusinessUnit
	@OneToMany(mappedBy="swBuEx")
	private Set<BusinessUnit> swBus;

	//bi-directional many-to-one association to Employee
	@OneToMany(mappedBy="swBuEx")
	private Set<Employee> swEmployees;

	//bi-directional many-to-one association to Department
	@OneToMany(mappedBy="swBuEx")
	private Set<Department> swDepts;

    public BusinessUnitEx() {
    }

	public int getBuExId() {
		return this.buExId;
	}

	public void setBuExId(int buExId) {
		this.buExId = buExId;
	}

	public String getBseCur() {
		return this.bseCur;
	}

	public void setBseCur(String bseCur) {
		this.bseCur = bseCur;
	}

	public String getCalId() {
		return this.calId;
	}

	public void setCalId(String calId) {
		this.calId = calId;
	}

	public int getExChrono() {
		return this.exChrono;
	}

	public void setExChrono(int exChrono) {
		this.exChrono = exChrono;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getTimeChrono() {
		return this.timeChrono;
	}

	public void setTimeChrono(int timeChrono) {
		this.timeChrono = timeChrono;
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
	
	public Set<Department> getSwDepts() {
		return this.swDepts;
	}

	public void setSwDepts(Set<Department> swDepts) {
		this.swDepts = swDepts;
	}
	
}