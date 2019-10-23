package edu.tridenttech.cpt237.plant.model;

import java.util.Date;

//child class of Defect class, inherits all defect methods
public class Missing extends Defect
{
	//constant type for missing child class - type always missing
	private final static String type = "Missing";
	
	public Missing(String studId, Date date, int unitsAffected)
	{
		//super constructor for Defect parent class, passes type
		super(studId, date, unitsAffected, type);
	}
}
