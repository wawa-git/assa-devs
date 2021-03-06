package com.assaassociates.syraway.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the SW_OPRID database table.
 * 
 */
@Entity
@Table(name="SW_OPRID")
public class Oprid implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="OPRID_ID", unique=true, nullable=false)
	private int opridId;

	@Column(name="PASSWORD", nullable=false, length=10)
	private String password;

	//bi-directional one-to-one association to Employee
	@OneToOne(mappedBy="swOprid")
	private Employee swEmployee;

    public Oprid() {
    }

	public int getOpridId() {
		return this.opridId;
	}

	public void setOpridId(int opridId) {
		this.opridId = opridId;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Employee getSwEmployee() {
		return this.swEmployee;
	}

	public void setSwEmployee(Employee swEmployee) {
		this.swEmployee = swEmployee;
	}
	
}