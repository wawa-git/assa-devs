package com.assaassociates.syraway.exts.mbeans;

import java.io.Serializable;
import java.util.List;

import com.assaassociates.syraway.model.Page;

public interface IMenuService extends Serializable{

	public List<Page> getAllMenus();
	public List<Page> getAllAuthorizedMenus();
	public void invalidateMenusCache();
}
