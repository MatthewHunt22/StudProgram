package edu.tridenttech.cpt237.plant.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Optional;
import java.util.Scanner;

public class Plant
{
	//initializing instance of Plant - for singleton class
	private static Plant instance = new Plant();
	
	private ArrayList<Stud> studs = new ArrayList<>();
	private ArrayList<Defect> defects = new ArrayList<>();
	
	//returns instance of plant
	public static Plant getInstance()
	{
		return instance;
	}
	
	//reads in the list of studs csv
	public void loadStuds(String studFileName) throws FileNotFoundException
	{
		Scanner input = new Scanner(new File(studFileName));
		
		while (input.hasNext())
		{
			String line = input.nextLine();
	
			String[] fields = line.split(",");
			
			String studId = fields[0];
			String process = fields[1];
			String name = fields[2];
			
			//creates new stud object and adds it to the stud arraylist
			studs.add(new Stud(studId, process, name));
		}
		
		//implements comparable after the file is loaded. Sorts the stud objects by studId.
		Collections.sort(studs);
		
		//closes input resource
		input.close();
	}
	
	//reads in the list of defects csv
	public void loadDefects(String defectFileName) throws FileNotFoundException, ParseException
	{
		Scanner input = new Scanner(new File(defectFileName));
		
		while (input.hasNext())
		{
			String line = input.nextLine();
	
			String[] fields = line.split(",");
			
			String studId = fields[0];
			Date date = new SimpleDateFormat("MM/dd/yyyy").parse(fields[1]);
			int unitsAffected = Integer.parseInt(fields[2]);
			String type = fields[3];
			
			//creates new defect object and adds it to the defect array list
			defects.add(new Defect(studId, date, unitsAffected, type));
			
			//finds the stud object using studId from the defect record
			Stud stud = findStudById(studId);
			
			//selection structure that adds the defect to total # of units affected for that type & studId
			if (type.equals("Missing"))
			{
				stud.addMissing(unitsAffected);
			}
			else if (type.equals("Broken"))
			{
				stud.addBroken(unitsAffected);
			}
			else
			{
				stud.addPosition(unitsAffected);
			}
		}
		
		//uses comparator to sort the date of the defects scanned in
		Collections.sort(defects, new Defect.SortDate());
		
		//closes input resource
		input.close();
	}
	
	//method that adds a new missing defect, saves it to the file, and returns true if successfully added to defect arraylist
	public boolean addMissingFinding(String studId, Date date, int unitsAffected)
	{
		//create new missing defect
		Missing missing = new Missing(studId, date, unitsAffected);
		
		//calls method in SaveToFile class that saves new missing defect to defect file
		SaveToFile.saveDefect(studId, missing.getDate(), unitsAffected, missing.getType());
		
		//adds defect to array list
		return defects.add(missing);
	}
	
	//method that adds a new broken defect, saves it to the file, and returns true if successfully added to defect arraylist
	public boolean addBrokenFinding(String studId, Date date, int unitsAffected)
	{
		//create new broken defect
		Broken broken = new Broken(studId, date, unitsAffected);
		
		//calls method in SaveToFile class that saves new broken defect to defect file
		SaveToFile.saveDefect(studId, broken.getDate(), unitsAffected, broken.getType());
		
		//adds defect to array list
		return defects.add(broken);
	}
	
	//method that adds a new position defect, saves it to the file, and returns true if successfully added to defect arraylist
	public boolean addPositionFinding(String studId, Date date, int unitsAffected)
	{
		//create new position defect
		Position position = new Position(studId, date, unitsAffected);
		
		//calls method in SaveToFile class that saves new position defect to defect file
		SaveToFile.saveDefect(studId, position.getDate(), unitsAffected, position.getType());
		
		//adds defect to array list
		return defects.add(position);
	}
	
	//method that adds a new stud, saves it to the file, and returns true if successfully added to stud arraylist
	public boolean addStud(String studId, String process, String assemblyName)
	{
		//create new stud
		Stud stud = new Stud(studId, process, assemblyName);
		
		//calls method in SaveToFile class that saves new stud to studlist file
		SaveToFile.saveStud(studId, process, assemblyName);
		
		//adds stud to stud array list
		return studs.add(stud);
	}
	
	//method that finds and returns stud object based on the studId 
	public Stud findStudById(String studId)
	{
		//returns null if stud is not found
		Stud stud = null;
		Optional<Stud> match = studs.stream().filter(e -> e.getStudId().equals(studId)).findFirst();
		if (match.isPresent()) 
		{
			stud = match.get();
		}
		
		return stud;
	}
	
	//returns stud arraylist
	public ArrayList<Stud> getStudList()
	{
		//Calls comparable to sort Stud objects based on studId when method called
		Collections.sort(studs);
		
		return studs; 
	}
	
	//returns defect arraylist
	public ArrayList<Defect> getDefectList()
	{
		//calls comparator to sort defect objects based on date when method called
		Collections.sort(defects, new Defect.SortDate());
		
		return defects;
	}
}
