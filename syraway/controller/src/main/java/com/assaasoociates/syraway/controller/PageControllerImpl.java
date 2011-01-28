/**
 * 
 */
package com.assaasoociates.syraway.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import com.assaassociates.syraway.dao.IPageDAO;
import com.assaassociates.syraway.model.Page;
import com.assaassociates.syraway.model.PageRole;
import com.assaassociates.syraway.model.Role;

/**
 * @author waheb
 *
 */

@Component("pageController")
public class PageControllerImpl implements IPageController {

	@Autowired
	private IPageDAO pageDAO;
	
	public IPageDAO getPageDAO() {
		return pageDAO;
	}

	public void setPageDAO(IPageDAO roleDAO) {
		this.pageDAO = roleDAO;
	}

	public void addPage(Page pPage) {
		if(pageDAO != null){
			pageDAO.addPage(pPage);
		}
	}

	public void updatePage(Page pPage) {
		if(pageDAO != null){
			pageDAO.updatePage(pPage);
		}
	}

	public Page getPage(Integer pPageId) {
		Page result = null;
		if(pageDAO != null){
			result = pageDAO.getPageById(pPageId);
		}
		return result;
	}

	public List<Page> getAllPages() {
		List<Page> result = new ArrayList<Page>();
		if(pageDAO != null)
			result = pageDAO.getAllPages();
		return result;
	}

	@Transactional(isolation=Isolation.READ_COMMITTED)
	public List<Page> getAllMenus() {
		List<Page> oPages = new ArrayList<Page>();
		List<Page> oResult = new ArrayList<Page>();
		if(pageDAO != null)
			oPages = pageDAO.getAllPages();
		
		for (Page oPage : oPages) {
			if(oPage.isMenu()){
				oResult.add(oPage);
			}
		}
		return oResult;
	}

	@Transactional(isolation=Isolation.READ_COMMITTED)
	public Collection<String> getAuthoritiesForPage(String pPageUri){
		Collection<String> result = new HashSet<String>();
		
		Page oPage = null;
		if(pageDAO != null)
			oPage = pageDAO.getPageByUri(pPageUri);
		
		if(oPage != null){
			Collection<PageRole> oPageRoles = oPage.getSwPages();
			if(oPageRoles != null){
				for (PageRole oPageRole : oPageRoles) {
					Role oRole = oPageRole.getSwRole();
					result.add(oRole.getName());
				}
			}
		}
		return result;
	}
}
