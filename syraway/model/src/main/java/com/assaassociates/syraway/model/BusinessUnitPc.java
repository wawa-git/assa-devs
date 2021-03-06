package com.assaassociates.syraway.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the SW_BU_PC database table.
 * 
 */
@Entity
@Table(name="SW_BU_PC")
public class BusinessUnitPc implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="BU_PC_ID", unique=true, nullable=false)
	private int buPcId;

	@Column(name="DESCR", length=45)
	private String descr;

	@Column(name="PROJECT_CHRONO")
	private int projectChrono;

	@Column(name="STATUS", length=1)
	private String status;

	//bi-directional many-to-one association to BusinessUnit
	@OneToMany(mappedBy="swBuPc")
	private Set<BusinessUnit> swBus;

	//bi-directional many-to-one association to Project
	@OneToMany(mappedBy="swBuPc")
	private Set<Project> swProjects;

    public BusinessUnitPc() {
    }

	public int getBuPcId() {
		return this.buPcId;
	}

	public void setBuPcId(int buPcId) {
		this.buPcId = buPcId;
	}

	public String getDescr() {
		return this.descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public int getProjectChrono() {
		return this.projectChrono;
	}

	public void setProjectChrono(int projectChrono) {
		this.projectChrono = projectChrono;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Set<BusinessUnit> getSwBus() {
		return this.swBus;
	}

	public void setSwBus(Set<BusinessUnit> swBus) {
		this.swBus = swBus;
	}
	
	public Set<Project> getSwProjects() {
		return this.swProjects;
	}

	public void setSwProjects(Set<Project> swProjects) {
		this.swProjects = swProjects;
	}
	
	@Override
	public String toString(){
		//return "000000".substring(String.valueOf(buPcId).length()) + buPcId;
		return String.valueOf(buPcId);
	}
}