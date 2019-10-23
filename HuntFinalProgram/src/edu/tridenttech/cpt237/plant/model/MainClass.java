package edu.tridenttech.cpt237.plant.model;

import java.util.Scanner;

import edu.tridenttech.cpt237.plant.view.PlantWindow;
import javafx.application.Application;
import javafx.stage.Stage;

public class MainClass extends Application
{
	static Scanner console = new Scanner(System.in);

	@Override
	public void start(Stage primaryStage) throws Exception
	{
		//calls instance of plant and loads both files
		Plant p = Plant.getInstance();
	    p.loadStuds("studlist.csv");
	    p.loadDefects("defects.csv");
		
	    //opens Plant Window
		new PlantWindow(primaryStage).show();
		
		//closes scanner
		console.close();
	}
	
	public static void main(String[] args) 
	{
		// launch the application
		Application.launch(args);

	}	

}
