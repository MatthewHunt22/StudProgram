package edu.tridenttech.cpt237.plant.view;

import edu.tridenttech.cpt237.plant.model.Plant;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AddStud 
{
	private Stage stage;
	
	public AddStud(String inputId)
	{
		GridPane pane = new GridPane();
		Scene scene = new Scene(pane);
		stage = new Stage();
		stage.setTitle("Add Stud");
		stage.setScene(scene);
		pane.setPadding(new Insets(10, 10, 10, 10));
		pane.setHgap(25);
		pane.setVgap(15);
		
		Label idLabel = new Label("Stud ID:");
		Label processLabel = new Label("Process:");
		Label nameLabel = new Label("Assembly Name:");
		
		Button addBtn = new Button("Add");
		Button cancelBtn = new Button("Cancel");
		
		HBox hb = new HBox();
	    hb.setSpacing(10);
	    hb.getChildren().addAll(addBtn, cancelBtn);
		
		Text id = new Text(inputId);
		TextField nameTF = new TextField();
		nameTF.setPrefWidth(50);
		
		ToggleGroup groupRB = new ToggleGroup();
		RadioButton autoRB = new RadioButton("Automatic"); 
		RadioButton manRB = new RadioButton("Manual"); 
		RadioButton naRB = new RadioButton("N/A"); 
		
		autoRB.setToggleGroup(groupRB);
		autoRB.setSelected(true);
		manRB.setToggleGroup(groupRB);
		naRB.setToggleGroup(groupRB);
		
		pane.addRow(0, idLabel, id);
		pane.addRow(1, processLabel, autoRB);
		pane.add(manRB, 1, 2);
		pane.add(naRB, 1, 3);
		pane.addRow(4, nameLabel, nameTF);
		pane.add(hb, 1, 5);		
		
		//if add button is clicked
		addBtn.setOnAction(e -> 
		{
			//if assembly name is filled out, continue
			if (nameTF.getText() != null)
			{
				String process;
				
				//selection structure that assigns value to process based on radio button selected
				if (autoRB.isSelected())
				{
					process = "Automatic";
				}
				else if (manRB.isSelected())
				{
					process = "Manual";
				}
				else
				{
					process = "N/A";
				}
				
				//if stud is added, close window
				if (Plant.getInstance().addStud(inputId, process, nameTF.getText())) 
				{
					stage.close();
				}
				//if stud could not be added, alert user
				else
				{
		    		Alert a = new Alert(AlertType.ERROR);
					a.setContentText("The stud could not be added.");
					a.show();
				}
			}
			//alert user name was not entered
			else 
			{
				Alert a = new Alert(AlertType.ERROR);
				a.setContentText("You must enter an assembly name for this stud!");
				a.show();
			}
		});
		
		//closes window if cancel is clicked
		cancelBtn.setOnAction(e ->
		{
			stage.close();
		});
		
	}
	
	//method that opens the window
	public void show()
	{		
		stage.show();
	}
	
	//method that brings window to front
	public void toFront()
	{
		stage.toFront();
	}
	
	//method that returns true if window is open
	public boolean isShowing()
	{
		return stage.isShowing();
	}
	
}
