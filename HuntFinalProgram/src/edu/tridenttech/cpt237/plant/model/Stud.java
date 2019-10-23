package edu.tridenttech.cpt237.plant.model;

public class Stud implements Comparable<Stud>
{
	private final String studId;
	private String process;
	private String assemblyName;
	private String containment = "TBD";
	private int missing = 0;
	private int broken = 0;
	private int position = 0;
	
	//constructor for stud object
	public Stud(String studId, String process, String asmbName)
	{
		this.studId = studId;
		this.process = process;
		this.assemblyName = asmbName;
	}
	
	//comparable that orders stud objects by stud id
	 @Override
	public int compareTo(Stud s) {
    int relationship = this.studId.compareTo(s.studId);
   	if (relationship == 0)
   	{
   		relationship = this.assemblyName.compareTo(s.assemblyName);
   	}
   	
		return relationship;
	}
	 
	//method that adds the new missing defects to the total missing defects of this stud
	public void addMissing(int newMissing)
	{
		missing += newMissing;
	}
	
	//method that adds the new broken defects to the total broken defects of this stud
	public void addBroken(int newBroken)
	{
		broken += newBroken;
	}
	
	//method that adds the new position defects to the total position defects of this stud
	public void addPosition(int newPosition)
	{
		position += newPosition;
	}
	
	//method that edits this stud's containment
	public void editContainment(String newContainment)
	{
		containment = newContainment;
	}
	
	//returns stud id
	public String getStudId()
	{
		return studId;
	}
	
	//returns process
	public String getProcess()
	{
		return process;
	}
	
	//returns assemblyName
	public String getAssemblyName()
	{
		return assemblyName;
	}
	
	//returns missing
	public int getMissing()
	{
		return missing;
	}
	
	//returns broken
	public int getBroken()
	{
		return broken;
	}
	
	//returns position
	public int getPosition()
	{
		return position;
	}
	
	//returns containment
	public String getContainment()
	{
		return containment;
	}
}
