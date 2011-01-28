/**
 * 
 */
package com.assaassociates.syraway.dao;

import java.util.List;

import com.assaassociates.syraway.model.Page;

/**
 * @author waheb
 *
 */
public interface IPageDAO {
	public Page getPageById(Integer pPageId);
	public Page getPageByUri(String pPageName);
	public void addPage(Page pPage);
	public void updatePage(Page pPage);
	public void removePage(Page pPage);
	public void removePage(Integer pPageId);
	public List<Page> getAllPages();
}
