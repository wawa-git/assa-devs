package com.assaassociates.syraway.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the SW_TIME database table.
 * 
 */
@Entity
@Table(name="SW_TIME")
public class Time implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="TIME_ID", unique=true, nullable=false)
	private int timeId;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="BEGIN_DT", nullable=false)
	private Date beginDt;

	@Column(name="DESCR", length=200)
	private String descr;

    @Lob()
	@Column(name="DURATION")
	private String duration;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="END_DT")
	private Date endDt;

	@Column(name="STATUS")
	private int status;

	//bi-directional many-to-one association to Task
    @ManyToOne
	@JoinColumn(name="TASK_ID", nullable=false)
	private Task swTask;

	//bi-directional many-to-one association to Employee
    @ManyToOne
	@JoinColumn(name="EMPL_ID", nullable=false)
	private Employee swEmployee;

	//bi-directional many-to-one association to Team
    @ManyToOne
	@JoinColumn(name="TEAM_ID", nullable=false)
	private Team swTeam;

    public Time() {
    }

	public int getTimeId() {
		return this.timeId;
	}

	public void setTimeId(int timeId) {
		this.timeId = timeId;
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

	public String getDuration() {
		return this.duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
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

	public Task getSwTask() {
		return this.swTask;
	}

	public void setSwTask(Task swTask) {
		this.swTask = swTask;
	}
	
	public Employee getSwEmployee() {
		return this.swEmployee;
	}

	public void setSwEmployee(Employee swEmployee) {
		this.swEmployee = swEmployee;
	}
	
	public Team getSwTeam() {
		return this.swTeam;
	}

	public void setSwTeam(Team swTeam) {
		this.swTeam = swTeam;
	}
	
}