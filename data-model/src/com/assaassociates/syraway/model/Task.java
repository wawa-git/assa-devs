package com.assaassociates.syraway.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the SW_TASK database table.
 * 
 */
@Entity
@Table(name="SW_TASK")
public class Task implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="TASK_ID", unique=true, nullable=false)
	private int taskId;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="BEGIN_DT")
	private Date beginDt;

	@Column(name="DESCR", length=45)
	private String descr;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="END_DT")
	private Date endDt;

	@Column(name="STATUS")
	private int status;

	//bi-directional many-to-one association to Project
    @ManyToOne
	@JoinColumn(name="PROJECT_ID", nullable=false)
	private Project swProject;

	//bi-directional many-to-one association to Employee
    @ManyToOne
	@JoinColumn(name="EMPL_ID", nullable=false)
	private Employee swEmployee;

	//bi-directional many-to-one association to Time
	@OneToMany(mappedBy="swTask")
	private Set<Time> swTimes;

    public Task() {
    }

	public int getTaskId() {
		return this.taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
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

	public Project getSwProject() {
		return this.swProject;
	}

	public void setSwProject(Project swProject) {
		this.swProject = swProject;
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
	
}