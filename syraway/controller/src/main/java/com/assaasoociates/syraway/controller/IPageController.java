package com.assaasoociates.syraway.controller;

import java.util.Collection;
import java.util.List;

import com.assaassociates.syraway.model.Page;

public interface IPageController {

	public void addPage(Page oPage);
	public void updatePage(Page oPage);
	public Page getPage(Integer pPageId);
	public List<Page> getAllPages();
	public List<Page> getAllMenus();
	public Collection<String> getAuthoritiesForPage(String pPageUri);
}
