package com.assaassociates.syraway.model;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the SW_EMPLOYEES database table.
 * 
 */
@Entity
@Table(name="SW_EMPLOYEES")
public class Employee implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="EMPL_ID", unique=true, nullable=false)
	private int emplId;

	@Column(name="ACTIVE")
	private int active;

	@Column(name="ADDRESS1", length=30)
	private String address1;

	@Column(name="ADDRESS2", length=30)
	private String address2;

    @Temporal( TemporalType.DATE)
	@Column(name="BIRTHDAY")
	private Date birthday;

	@Column(name="CITY", length=30)
	private String city;

	@Column(name="CITY_CD", length=10)
	private String cityCd;

	@Column(name="COSTRATE")
	private int costrate;

	@Column(name="COUNTRY", length=30)
	private String country;

	@Column(name="EMAIL", length=45)
	private String email;

    @Temporal( TemporalType.DATE)
	@Column(name="ENTRY_DT")
	private Date entryDt;

    @Temporal( TemporalType.DATE)
	@Column(name="EXIT_DT")
	private Date exitDt;

	@Column(name="NAME", length=45)
	private String name;

	@Column(name="PHONENUMBER", length=30)
	private String phonenumber;

	@Column(name="RATE")
	private int rate;

	@Column(name="SURNAME", length=45)
	private String surname;

	//bi-directional many-to-one association to Department
    @ManyToOne
	@JoinColumn(name="DEPT_ID")
	private Department swDept;

	//bi-directional many-to-one association to BusinessUnitEx
    @ManyToOne
	@JoinColumn(name="BU_EX_ID")
	private BusinessUnitEx swBuEx;

	//bi-directional many-to-one association to Employee
    @ManyToOne
	@JoinColumn(name="MANAGER_ID")
	private Employee swEmployee;

	//bi-directional many-to-one association to Employee
	@OneToMany(mappedBy="swEmployee")
	private Set<Employee> swEmployees;

	//bi-directional many-to-many association to Team
    @ManyToMany
	@JoinTable(
		name="SW_TEAM_EMPLOYEES"
		, joinColumns={
			@JoinColumn(name="EMPL_ID", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="TEAM_ID", nullable=false)
			}
		)
	private Set<Team> swTeams1;

	//bi-directional many-to-one association to Project
	@OneToMany(mappedBy="swEmployee")
	private Set<Project> swProjects;

	//bi-directional many-to-one association to Team
	@OneToMany(mappedBy="swEmployee")
	private Set<Team> swTeams2;

	//bi-directional many-to-one association to Task
	@OneToMany(mappedBy="swEmployee")
	private Set<Task> swTasks;

	//bi-directional many-to-one association to Time
	@OneToMany(mappedBy="swEmployee")
	private Set<Time> swTimes;

	//bi-directional many-to-one association to UserRole
	@OneToMany(mappedBy="swEmployee")
	private Set<UserRole> swUserRoles;

	//bi-directional one-to-one association to Oprid
	@OneToOne
	@JoinColumn(name="OPRID_ID", referencedColumnName="OPRID_ID", nullable=false)
	private Oprid swOprid;

    public Employee() {
    }

	public int getEmplId() {
		return this.emplId;
	}

	public void setEmplId(int emplId) {
		this.emplId = emplId;
	}

	public int getActive() {
		return this.active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public String getAddress1() {
		return this.address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return this.address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCityCd() {
		return this.cityCd;
	}

	public void setCityCd(String cityCd) {
		this.cityCd = cityCd;
	}

	public int getCostrate() {
		return this.costrate;
	}

	public void setCostrate(int costrate) {
		this.costrate = costrate;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getEntryDt() {
		return this.entryDt;
	}

	public void setEntryDt(Date entryDt) {
		this.entryDt = entryDt;
	}

	public Date getExitDt() {
		return this.exitDt;
	}

	public void setExitDt(Date exitDt) {
		this.exitDt = exitDt;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhonenumber() {
		return this.phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public int getRate() {
		return this.rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Department getSwDept() {
		return this.swDept;
	}

	public void setSwDept(Department swDept) {
		this.swDept = swDept;
	}
	
	public BusinessUnitEx getSwBuEx() {
		return this.swBuEx;
	}

	public void setSwBuEx(BusinessUnitEx swBuEx) {
		this.swBuEx = swBuEx;
	}
	
	public Employee getSwEmployee() {
		return this.swEmployee;
	}

	public void setSwEmployee(Employee swEmployee) {
		this.swEmployee = swEmployee;
	}
	
	public Set<Employee> getSwEmployees() {
		return this.swEmployees;
	}

	public void setSwEmployees(Set<Employee> swEmployees) {
		this.swEmployees = swEmployees;
	}
	
	public Set<Team> getSwTeams1() {
		return this.swTeams1;
	}

	public void setSwTeams1(Set<Team> swTeams1) {
		this.swTeams1 = swTeams1;
	}
	
	public Set<Project> getSwProjects() {
		return this.swProjects;
	}

	public void setSwProjects(Set<Project> swProjects) {
		this.swProjects = swProjects;
	}
	
	public Set<Team> getSwTeams2() {
		return this.swTeams2;
	}

	public void setSwTeams2(Set<Team> swTeams2) {
		this.swTeams2 = swTeams2;
	}
	
	public Set<Task> getSwTasks() {
		return this.swTasks;
	}

	public void setSwTasks(Set<Task> swTasks) {
		this.swTasks = swTasks;
	}
	
	public Set<Time> getSwTimes() {
		return this.swTimes;
	}

	public void setSwTimes(Set<Time> swTimes) {
		this.swTimes = swTimes;
	}
	
	public Set<UserRole> getSwUserRoles() {
		return this.swUserRoles;
	}

	public void setSwUserRoles(Set<UserRole> swUserRoles) {
		this.swUserRoles = swUserRoles;
	}
	
	public Oprid getSwOprid() {
		return this.swOprid;
	}

	public void setSwOprid(Oprid swOprid) {
		this.swOprid = swOprid;
	}
	
	@Override
	public String toString(){
		return String.valueOf(getEmplId());
	}
	
}