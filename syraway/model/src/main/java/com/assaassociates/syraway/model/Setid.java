package com.assaassociates.syraway.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the SW_SETID database table.
 * 
 */
@Entity
@Table(name="SW_SETID")
public class Setid implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="SETID_ID", unique=true, nullable=false)
	private int setidId;

	@Column(name="ACTIVE")
	private int active;

	@Column(name="DESCR", length=120)
	private String descr;

	//bi-directional many-to-many association to BusinessUnit
    @ManyToMany
	@JoinTable(
		name="SW_SETID_BU"
		, joinColumns={
			@JoinColumn(name="SETID_ID", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="BU_ID", nullable=false)
			}
		)
	private Set<BusinessUnit> swBus;

    public Setid() {
    }

	public int getSetidId() {
		return this.setidId;
	}

	public void setSetidId(int setidId) {
		this.setidId = setidId;
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
	
}