package edu.tridenttech.cpt237.plant.view;

import edu.tridenttech.cpt237.plant.model.Stud;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class EditContainment 
{
	private Stage stage;
	
	public EditContainment(Stud stud)
	{
		GridPane pane = new GridPane();
		Scene scene = new Scene(pane);
		stage = new Stage();
		stage.setTitle("Edit Containment");
		stage.setScene(scene);
		pane.setPadding(new Insets(10, 10, 10, 10));
		pane.setHgap(25);
		pane.setVgap(15);
		
		//header for containment - includes the stud ID.
		Text header = new Text("Containment for Stud " + stud.getStudId());
		header.setStyle("-fx-font: 14 arial;");
		
		Label label = new Label("New Containment:");
		TextField conTF = new TextField();
		conTF.setPrefWidth(220);
		
		Button submit = new Button("Submit");
		Button cancel = new Button("Cancel");
		
		pane.add(header, 0, 0, 2, 1);
		GridPane.setHalignment(header, HPos.CENTER);
		
		pane.addRow(1, label, conTF);
		pane.add(submit, 1, 2);
		pane.add(cancel, 1, 2);
		GridPane.setHalignment(submit, HPos.CENTER);
		GridPane.setHalignment(cancel, HPos.RIGHT);
		
		//action if submit button is clicked
		submit.setOnAction(e -> 
		{
			//continues if text field for containment contains text
			if (conTF.getText() != null)
			{
				//edits the containment for specific stud
				stud.editContainment(conTF.getText());
				
				//tells user that the containment was updated
				Alert a = new Alert(AlertType.INFORMATION);
				a.setContentText("Containment updated.");
				a.show();
				
				//closes window when containment updated
				stage.close();
			}
			//alerts user no text was entered for containment
			else
			{
				Alert a = new Alert(AlertType.ERROR);
				a.setContentText("You did not enter a containment!");
				a.show();
			}
		});
		
		//closes window when cancel button is clicked
		cancel.setOnAction(e -> 
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
