package com.assaassociates.syraway.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the SW_PAGE_ROLE database table.
 * 
 */
@Entity
@Table(name="SW_PAGE_ROLE")
public class PageRole implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="PAGE_ROLE_ID", unique=true, nullable=false)
	private byte pageRoleId;

	@Column(name="BU_ID")
	private byte buId;

	@Column(name="DESCRIPTION", length=100)
	private String description;

	@Column(name="NAME", length=100)
	private String name;

	@Column(name="ROLE_ID", nullable=false)
	private byte roleId;

	//bi-directional many-to-one association to Page
	@OneToMany(mappedBy="swPageRole")
	private List<Page> swPages;

    public PageRole() {
    }

	public byte getPageRoleId() {
		return this.pageRoleId;
	}

	public void setPageRoleId(byte pageRoleId) {
		this.pageRoleId = pageRoleId;
	}

	public byte getBuId() {
		return this.buId;
	}

	public void setBuId(byte buId) {
		this.buId = buId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte getRoleId() {
		return this.roleId;
	}

	public void setRoleId(byte roleId) {
		this.roleId = roleId;
	}

	public List<Page> getSwPages() {
		return this.swPages;
	}

	public void setSwPages(List<Page> swPages) {
		this.swPages = swPages;
	}
	
}