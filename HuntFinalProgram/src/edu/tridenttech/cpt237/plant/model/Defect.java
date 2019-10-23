package edu.tridenttech.cpt237.plant.model;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

public class Defect 
{
	private String studId;
	private Date date;
	private int unitsAffected;
	private String type;
	
	//constructor for defect object
	public Defect(String studId, Date date, int unitsAffected, String type)
	{
		this.studId = studId;
		this.date = date;
		this.unitsAffected = unitsAffected;
		this.type = type;
	}
	
	//comparator that sorts date of defect objects. Newest date at the top
    public static class SortDate implements Comparator<Defect>
    {
		@Override
		public int compare(Defect d1, Defect d2) 
		{
			
			int relationship = d2.date.compareTo(d1.date);
	    	
			if (relationship == 0)
	    	{
				//if date is the same, order by studId
	    		relationship = d1.studId.compareTo(d2.studId);
	    	}
	    	
			return relationship;
		}
    	
    }
    
    //returns stud ID
	public String getStudId()
	{
		return studId;
	}
	
	//returns formatted string of date
	public String getDate()
	{ 
		//formats date and returns formatted date
		SimpleDateFormat dt = new SimpleDateFormat("MM/dd/yy"); 
		String formatDate = dt.format(date); 
		
		return formatDate;
	}
	
	//return units affected
	public int getUnitsAffected()
	{
		return unitsAffected;
	}
	
	//returns type (missing, broken, position
	public String getType()
	{
		return type;
	}
}
