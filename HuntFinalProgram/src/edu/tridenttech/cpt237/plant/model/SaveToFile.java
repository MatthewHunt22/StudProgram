package edu.tridenttech.cpt237.plant.model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class SaveToFile 
{
	//constants for file names
	private static final String STUD_FILE = "studlist.csv";
	private static final String DEFECT_FILE = "defects.csv";
	
	//method that saves stud to file
	public static void saveStud(String studId, String process, String assemblyName)
	{
		try
		{
			FileWriter fw = new FileWriter(STUD_FILE, true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			
			//adds studId, process, and assemblyName to csv file with comma delimiters
			pw.println(studId + "," + process + "," + assemblyName);
			
			//flushes and closes print writer
			pw.flush();
			pw.close();
			
			//alerts user that stud was successfully added to the file
			Alert a = new Alert(AlertType.INFORMATION);
			a.setContentText("The stud was successfully added to the file.");
			a.show();
		}
		catch(Exception e)
		{
			//alerts user that stud was not added to file
			Alert a = new Alert(AlertType.ERROR);
			a.setContentText("There was an error adding the stud to the file.");
			a.show();
		}
	}
	
	//method that saves defect to defect file
	public static void saveDefect(String studId, String date, int unitsAffected, String type)
	{
		try
		{
			FileWriter fw = new FileWriter(DEFECT_FILE, true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			
			//adds studId, date, unitsaffecte, and type to csv file with comma delimiters
			pw.println(studId + "," + date + "," + unitsAffected + "," + type);
			
			//flushes and closes print writer
			pw.flush();
			pw.close();
			
			//alerts user defect was successfully added to file
			Alert a = new Alert(AlertType.INFORMATION);
			a.setContentText("The defect was successfully added to the file.");
			a.show();
		}
		catch(Exception e)
		{
			//alerts user that defect was not added to file
			Alert a = new Alert(AlertType.ERROR);
			a.setContentText("There was an error adding the defect to the file.");
			a.show();
		}
		
	}
}
