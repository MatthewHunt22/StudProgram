package edu.tridenttech.cpt237.plant.view;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class PlantWindow 
{
	private Stage myStage;
	private Button findBtn = new Button("Find Stud");
    private Button defectBtn = new Button("Show Findings");
    private Button showBtn = new Button("Show Studs");
    private Button doneBtn = new Button("Done");
	
	public PlantWindow(Stage stage) 
	{
		GridPane pane = new GridPane();
		Scene scene = new Scene(pane);
		myStage = stage;
		myStage.setTitle("Plant");
		myStage.setScene(scene);
		pane.setPadding(new Insets(10, 10, 10, 10));
		pane.setHgap(25);
		pane.setVgap(15);
		
		//action if find button clicked
		findBtn.setOnAction(e -> 
		{
			FindStud findStud = new FindStud();

			//opens find stud window if not opened.
			if (!findStud.isShowing()) 
			{
				findStud.show();
			} 
			//if opened, brings find stud window to front.
			else 
			{
				findStud.toFront();
			}
			
		});
		
		//action if show defects button is clicked
		defectBtn.setOnAction(e -> 
		{
			ShowDefects showDefects = new ShowDefects();
			
			//opens show defects window if not opened.
			if (!showDefects.isShowing()) 
			{
				showDefects.show();
			} 
			//if opened, brings show defects window to front.
			else 
			{
				showDefects.toFront();
			}
			
		});
		
		//action if show studs button is clicked
		showBtn.setOnAction(e -> 
		{
			ShowStuds showStud = new ShowStuds();

			//opens show stud window if not opened.
			if (!showStud.isShowing()) 
			{
				showStud.show();
			} 
			//if opened, brings show stud window to front.
			else 
			{
				showStud.toFront();
			}
			
		});
		
		//closes window if done button is clicked
		doneBtn.setOnAction(e -> 
		{
	    	stage.close();
	    });
		
		pane.add(findBtn, 0, 0);
		pane.add(defectBtn, 0, 1);
		pane.add(showBtn, 0, 2);
		pane.add(doneBtn, 0, 3);	
		GridPane.setHalignment(findBtn, HPos.CENTER);
		GridPane.setHalignment(defectBtn, HPos.CENTER);
		GridPane.setHalignment(showBtn, HPos.CENTER);
		GridPane.setHalignment(doneBtn, HPos.CENTER);
	}
	
	//method that shows main window
	public void show()
    {
        myStage.show();
    }
}
