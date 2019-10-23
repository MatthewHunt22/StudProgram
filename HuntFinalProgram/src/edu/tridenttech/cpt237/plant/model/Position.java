package edu.tridenttech.cpt237.plant.model;

import java.util.Date;

//child class of Defect class, inherits all defect methods
public class Position extends Defect
{
	//constant type for position child class - type is always position
	private final static String type = "Position";
	
	public Position(String studId, Date date, int unitsAffected)
	{
		//super constructor for Defect parent class, passes type
		super(studId, date, unitsAffected, type);
	}
}
