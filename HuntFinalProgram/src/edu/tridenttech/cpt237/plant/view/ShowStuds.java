package edu.tridenttech.cpt237.plant.view;

import edu.tridenttech.cpt237.plant.model.Plant;
import edu.tridenttech.cpt237.plant.model.Stud;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ShowStuds 
{
	private Stage stage;
	
	@SuppressWarnings("unchecked")
	public ShowStuds()
	{
		GridPane pane = new GridPane();
		Scene scene = new Scene(pane);
		stage = new Stage();
		stage.setTitle("Show Studs");
		stage.setScene(scene);
		stage.setWidth(525);
		pane.setPadding(new Insets(10, 10, 10, 10));
				
		//tableview populated using stud array list
		TableView<Stud> tableView = new TableView<Stud>();
		ObservableList<Stud> data = FXCollections.observableArrayList(Plant.getInstance().getStudList());
		
		TableColumn<Stud, String> c1 = new TableColumn<Stud, String>("Stud ID");
		c1.setCellValueFactory(new PropertyValueFactory<Stud, String>("studId"));
		
		TableColumn<Stud, String> c2 = new TableColumn<Stud, String>("Process");
		c2.setCellValueFactory(new PropertyValueFactory<Stud, String>("process"));
		
		TableColumn<Stud, String> c3 = new TableColumn<Stud, String>("Assembly Name");
		c3.setMinWidth(325);
		c3.setCellValueFactory(new PropertyValueFactory<Stud, String>("assemblyName"));
		
		tableView.setItems(data);
		tableView.getColumns().addAll(c1, c2, c3);
		
		pane.add(tableView, 0, 0);
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
