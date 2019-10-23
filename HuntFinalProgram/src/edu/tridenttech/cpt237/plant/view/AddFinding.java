package edu.tridenttech.cpt237.plant.view;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import edu.tridenttech.cpt237.plant.model.Plant;
import edu.tridenttech.cpt237.plant.model.Stud;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AddFinding 
{
	private Stage stage;
	private Date date;
	
	public AddFinding(String studId)
	{
		GridPane pane = new GridPane();
		Scene scene = new Scene(pane);
		stage = new Stage();
		stage.setTitle("Add Finding");
		stage.setScene(scene);
		stage.setWidth(350);
		pane.setPadding(new Insets(10, 10, 25, 10));
		pane.setHgap(25);
		pane.setVgap(15);
				
		Label idLabel = new Label("Stud ID:");
		Label defLabel = new Label("Defect Type:");
		Label numLabel = new Label("Units Affected:");
		Label dateLabel = new Label("Date:");
		DatePicker datePicker = new DatePicker();
		
		//adds a calendar for the user to select a date, assigns to date variable
		datePicker.setOnAction(e -> 
		{
			LocalDate localDate = datePicker.getValue();
			date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		});
		
		Button addBtn = new Button("Add");
		Button cancelBtn = new Button("Cancel");
		
		HBox hb = new HBox();
	    hb.setSpacing(10);
	    hb.getChildren().addAll(addBtn, cancelBtn);
		
		Text id = new Text(studId);
		TextField numTF = new TextField();
		numTF.setPrefWidth(50);
		
		ToggleGroup groupRB = new ToggleGroup();
		RadioButton missingRB = new RadioButton("Missing"); 
		RadioButton brokenRB = new RadioButton("Broken"); 
		RadioButton posRB = new RadioButton("Position"); 
		
		missingRB.setToggleGroup(groupRB);
		missingRB.setSelected(true);
		brokenRB.setToggleGroup(groupRB);
		posRB.setToggleGroup(groupRB);
		
		pane.addRow(0, idLabel, id);
		pane.addRow(1, defLabel, missingRB);
		pane.add(brokenRB, 1, 2);
		pane.add(posRB, 1, 3);
		pane.addRow(4, dateLabel, datePicker);
		pane.addRow(5, numLabel, numTF);
		pane.add(hb, 1, 6);		
		
		//action if add finding button is clicked
		addBtn.setOnAction(e -> 
		{
			//continues if number input is numeric && positive number
			if (isNumeric(numTF.getText()))
			{
				//continues if date selected
				if (date != null)
				{		
					//calls plant instance and stud object
					Plant p = Plant.getInstance();
					Stud stud = p.findStudById(studId);
					
					//selection structure that creates child defect object based on radio button selected. Closes window when successful
					if (missingRB.isSelected())
					{	
						if (p.addMissingFinding(studId, date, Integer.parseInt(numTF.getText())))
						{
							//adds new units affected to total units affected for the specific stud
							stud.addMissing(Integer.parseInt(numTF.getText()));
							stage.close();
						}
						else
						{
				    		Alert a = new Alert(AlertType.ERROR);
							a.setContentText("The finding could not be added.");
							a.show();
						}
					}
					else if (brokenRB.isSelected())
					{
						if (p.addBrokenFinding(studId, date, Integer.parseInt(numTF.getText())))
						{
							//adds new units affected to total units affected for the specific stud
							stud.addBroken(Integer.parseInt(numTF.getText()));
							stage.close();
						}
						else
						{
				    		Alert a = new Alert(AlertType.ERROR);
							a.setContentText("The finding could not be added.");
							a.show();
						}
					}
					else
					{
						if (p.addPositionFinding(studId, date, Integer.parseInt(numTF.getText())))
						{
							//adds new units affected to total units affected for the specific stud
							stud.addPosition(Integer.parseInt(numTF.getText()));
							stage.close();
						}
						else
						{
				    		Alert a = new Alert(AlertType.ERROR);
							a.setContentText("The finding could not be added.");
							a.show();
						}
					}
				}
				//alerts user that date was not selected
				else 
				{
					Alert a = new Alert(AlertType.ERROR);
					a.setContentText("You must select a date!");
					a.show();
				}
				
			}
			//alerts user number was empty / < 1
			else
			{
				Alert a = new Alert(AlertType.ERROR);
				a.setContentText("You must enter a positive number!");
				a.show();
			}
			
		});
		
		//closes window if cancel button is clicked
		cancelBtn.setOnAction(e -> 
		{
			stage.close();
		});
		
	}
	
	//method that tests if user input is a valid number
	public static boolean isNumeric(String numInput)
	{
		try
		{
			//tries to parse numInput
			Integer.parseInt(numInput);
			
			//if numInput is 0 or less, return false
			if (Integer.parseInt(numInput) < 1)
			{
				return false;
			}
		}
		catch (NumberFormatException nfe)
		{
			return false;
		}

		return true;
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
