package com.assaassociates.syraway.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the SW_PROJECT database table.
 * 
 */
@Entity
@Table(name="SW_PROJECT")
public class Project implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="PROJECT_ID", unique=true, nullable=false)
	private int projectId;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="BEGIN_DT")
	private Date beginDt;

	@Column(name="DESCR", length=300)
	private String descr;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="END_DT")
	private Date endDt;

	@Column(name="STATUS")
	private int status;

	//bi-directional many-to-one association to BusinessUnitPc
    @ManyToOne
	@JoinColumn(name="BU_PC_ID", nullable=false)
	private BusinessUnitPc swBuPc;

	//bi-directional many-to-one association to Employee
    @ManyToOne
	@JoinColumn(name="MANAGER_ID", nullable=false)
	private Employee swEmployee;

	//bi-directional many-to-one association to Task
	@OneToMany(mappedBy="swProject")
	private Set<Task> swTasks;

    public Project() {
    }

	public int getProjectId() {
		return this.projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public Date getBeginDt() {
		return this.beginDt;
	}

	public void setBeginDt(Date beginDt) {
		this.beginDt = beginDt;
	}

	public String getDescr() {
		return this.descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public Date getEndDt() {
		return this.endDt;
	}

	public void setEndDt(Date endDt) {
		this.endDt = endDt;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public BusinessUnitPc getSwBuPc() {
		return this.swBuPc;
	}

	public void setSwBuPc(BusinessUnitPc swBuPc) {
		this.swBuPc = swBuPc;
	}
	
	public Employee getSwEmployee() {
		return this.swEmployee;
	}

	public void setSwEmployee(Employee swEmployee) {
		this.swEmployee = swEmployee;
	}
	
	public Set<Task> getSwTasks() {
		return this.swTasks;
	}

	public void setSwTasks(Set<Task> swTasks) {
		this.swTasks = swTasks;
	}
	
}