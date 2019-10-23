package edu.tridenttech.cpt237.plant.view;

import edu.tridenttech.cpt237.plant.model.Defect;
import edu.tridenttech.cpt237.plant.model.Plant;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ShowDefects 
{
	private Stage stage;
	
	@SuppressWarnings("unchecked")
	public ShowDefects()
	{
		GridPane pane = new GridPane();
		Scene scene = new Scene(pane);
		stage = new Stage();
		stage.setTitle("Show Defects");
		stage.setScene(scene);
		stage.setWidth(425);
		pane.setPadding(new Insets(10, 10, 10, 10));
				
		//Tableview populated using defect arraylist
		TableView<Defect> tableView = new TableView<Defect>();
		ObservableList<Defect> data = FXCollections.observableArrayList(Plant.getInstance().getDefectList());
		
		TableColumn<Defect, String> c1 = new TableColumn<Defect, String>("Stud ID");
		c1.setCellValueFactory(new PropertyValueFactory<Defect, String>("studId"));
		c1.setMinWidth(100);
		
		TableColumn<Defect, String> c2 = new TableColumn<Defect, String>("Date");
		c2.setCellValueFactory(new PropertyValueFactory<Defect, String>("date"));
		c2.setMinWidth(100);
		
		TableColumn<Defect, String> c3 = new TableColumn<Defect, String>("Units Affected");
		c3.setCellValueFactory(new PropertyValueFactory<Defect, String>("unitsAffected"));
		c3.setMinWidth(100);
		
		TableColumn<Defect, String> c4 = new TableColumn<Defect, String>("Defect Type");
		c4.setCellValueFactory(new PropertyValueFactory<Defect, String>("type"));
		c4.setMinWidth(100);
		
		tableView.setItems(data);
		tableView.getColumns().addAll(c1, c2, c3, c4);
		
		//header above the tableview
		Text findingsHeader = new Text("All Findings:");
	    findingsHeader.setStyle("-fx-font: 24 arial;");
	    
	    pane.add(findingsHeader, 0, 0, 2, 1);
	    GridPane.setHalignment(findingsHeader, HPos.CENTER);
		pane.add(tableView, 0, 2);
		
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
