package com.assaasoociates.syraway.controller;

import java.util.List;

import com.assaassociates.syraway.model.Time;

public interface ITimeController {

	public void addTime(Time oTime);
	public void updateTime(Time oTime);
	public Time getTime(Integer pTimeId); 
	public List<Time> getAllTimes();
	
}
