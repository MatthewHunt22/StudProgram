package edu.tridenttech.cpt237.plant.model;

import java.util.Date;

//child class of Defect class, inherits all Defect methods
public class Broken extends Defect
{
	//constant for type - broken
	private final static String type = "Broken";
	
	public Broken(String studId, Date date, int unitsAffected)
	{
		//super constructor for Defect parent class, passes type
		super(studId, date, unitsAffected, type);
	}
}
