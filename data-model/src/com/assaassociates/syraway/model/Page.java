package com.assaassociates.syraway.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the SW_PAGE database table.
 * 
 */
@Entity
@Table(name="SW_PAGE")
public class Page implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="PAGE_ID", unique=true, nullable=false)
	private byte pageId;

	@Column(name="MENU", nullable=false)
	private int menu;

	@Column(name="MENU_NAME", length=45)
	private String menuName;

	@Column(name="MENU_ORDER")
	private int menuOrder;

	@Column(name="NAME", nullable=false, length=45)
	private String name;

	@Column(name="URI", nullable=false, length=45)
	private String uri;

	//bi-directional many-to-one association to PageRole
    @ManyToOne
	@JoinColumn(name="PAGE_ID", referencedColumnName="PAGE_ID", nullable=false, insertable=false, updatable=false)
	private PageRole swPageRole;

    public Page() {
    }

	public byte getPageId() {
		return this.pageId;
	}

	public void setPageId(byte pageId) {
		this.pageId = pageId;
	}

	public int getMenu() {
		return this.menu;
	}

	public void setMenu(int menu) {
		this.menu = menu;
	}

	public String getMenuName() {
		return this.menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public int getMenuOrder() {
		return this.menuOrder;
	}

	public void setMenuOrder(int menuOrder) {
		this.menuOrder = menuOrder;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUri() {
		return this.uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public PageRole getSwPageRole() {
		return this.swPageRole;
	}

	public void setSwPageRole(PageRole swPageRole) {
		this.swPageRole = swPageRole;
	}
	
}