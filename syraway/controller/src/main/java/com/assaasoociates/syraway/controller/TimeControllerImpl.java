/**
 * 
 */
package com.assaasoociates.syraway.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.assaassociates.syraway.dao.ITimeDAO;
import com.assaassociates.syraway.model.Time;

/**
 * @author waheb
 *
 */

@Component("timeController")
public class TimeControllerImpl implements ITimeController {

	@Autowired
	private ITimeDAO timeDAO;
	
	public ITimeDAO getTimeDAO() {
		return timeDAO;
	}

	public void setTimeDAO(ITimeDAO pTimeDAO) {
		this.timeDAO = pTimeDAO;
	}

	public void addTime(Time pTime) {
		if(timeDAO != null){
			timeDAO.addTime(pTime);
		}
	}

	public void updateTime(Time pTime) {
		if(timeDAO != null){
			timeDAO.updateTime(pTime);
		}
	}

	public Time getTime(Integer pTimeId) {
		Time result = null;
		if(timeDAO != null){
			result = timeDAO.getTimeById(pTimeId);
		}
		return result;
	}

	public List<Time> getAllTimes() {
		List<Time> result = new ArrayList<Time>();
		if(timeDAO != null)
			result = timeDAO.getAllTimes();
		return result;
	}
}
