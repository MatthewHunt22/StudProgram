package edu.tridenttech.cpt237.plant.view;

import java.util.ArrayList;
import java.util.Collections;

import edu.tridenttech.cpt237.plant.model.Defect;
import edu.tridenttech.cpt237.plant.model.Plant;
import edu.tridenttech.cpt237.plant.model.Stud;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class FindStud 
{
	private Stage stage;
	private Stud stud;
	private ArrayList<Defect> recentDefects = new ArrayList<>();
	
	@SuppressWarnings("unchecked")
	public FindStud() 
	{
		GridPane pane = new GridPane();
		Scene scene = new Scene(pane);
		stage = new Stage();
		stage.setTitle("Find Stud");
		stage.setScene(scene);
		stage.setHeight(150);
		stage.setWidth(275);
		pane.setPadding(new Insets(10, 10, 10, 10));
		pane.setHgap(25);
		pane.setVgap(15);
		
		Button searchBtn = new Button("Search");
		Button doneBtn = new Button("Done");
		Button addBtn = new Button("Add Findings");
		Button studBtn = new Button("Add Stud");
		Button containBtn = new Button("Edit Containment");
		
		Label idLabel = new Label("Stud ID:");
		Label idLabel2 = new Label("Stud ID:");
		TextField idTF = new TextField();
		idTF.setPrefWidth(50);
		
		Label processLbl = new Label("Process:");
		Label nameLbl = new Label("Assembly Name:");
		Label missingLbl = new Label("Missing:");
		Label brokenLbl = new Label("Broken:");
		Label posLbl = new Label("Position:");
		Label conLbl = new Label("Containment:");
		
		Text id = new Text();
		Text process = new Text();
		Text name = new Text();
		Text missing = new Text();
		Text broken = new Text();
		Text pos = new Text();
		Text con = new Text();
		
		HBox hb = new HBox();
	    hb.setSpacing(10);
	    hb.getChildren().addAll(searchBtn, doneBtn);
	    
	    VBox vb1 = new VBox();
	    vb1.setSpacing(15);
	    vb1.getChildren().addAll(idLabel2, processLbl, nameLbl, missingLbl, brokenLbl, posLbl, conLbl);
	    
	    VBox vb2 = new VBox();
	    vb2.setSpacing(16);
	    vb2.getChildren().addAll(id, process, name, missing, broken, pos, con);
	    
	    pane.add(vb1, 0, 3);
	    pane.add(vb2, 1, 3);
	    pane.add(addBtn, 0, 4);
	    pane.add(containBtn, 1, 4);	
	    
	    //sets buttons to invisible initially
	    vb1.setVisible(false);
	    vb2.setVisible(false);
	    addBtn.setVisible(false);
	    containBtn.setVisible(false);
	    studBtn.setVisible(false);
	    
	    //action if search button is clicked
	    searchBtn.setOnAction(e -> 
	    {
	    	//stud = value returned from searching ID input by user
	    	stud = Plant.getInstance().findStudById(idTF.getText());
	    	
	    	//continue if stud is found
	    	if (stud != null)
	    	{
	    		//calls load array method.
	    		loadArray();
	    		
	    		//new height and width settings
	    		stage.setHeight(425);
		    	stage.setWidth(875);
		    	idTF.setMaxWidth(125);
		    	
		    	//turns all buttons/labels to visible and enables other buttons
		    	vb1.setVisible(true);
			    vb2.setVisible(true);
			    addBtn.setVisible(true);
			    containBtn.setVisible(true);
			    studBtn.setVisible(false);
			    addBtn.setDisable(false);
			    containBtn.setDisable(false);
			    
			    //setting value of text nodes by calling getters for stud object properties
			    id.setText(stud.getStudId());
			    process.setText(stud.getProcess());
			    name.setText(stud.getAssemblyName());
			    con.setText(stud.getContainment());
			    missing.setText(Integer.toString(stud.getMissing()));
			    broken.setText(Integer.toString(stud.getBroken()));
			    pos.setText(Integer.toString(stud.getPosition()));
			    
			    //Header for tableview
			    Text findingsHeader = new Text("Recent Findings:");
			    findingsHeader.setStyle("-fx-font: 24 arial;");
			    
			    pane.add(findingsHeader, 2, 0, 2, 1);
			    GridPane.setHalignment(findingsHeader, HPos.CENTER);
			    
			    //populates tableview with recentdefects filled by loadarray method
				TableView<Defect> tableView = new TableView<Defect>();
				ObservableList<Defect> data = FXCollections.observableArrayList(recentDefects);
				
				TableColumn<Defect, String> c1 = new TableColumn<Defect, String>("Stud ID");
				c1.setCellValueFactory(new PropertyValueFactory<Defect, String>("studId"));
				c1.setMinWidth(90);
				
				TableColumn<Defect, String> c2 = new TableColumn<Defect, String>("Date");
				c2.setCellValueFactory(new PropertyValueFactory<Defect, String>("date"));
				c2.setMinWidth(80);
				
				TableColumn<Defect, String> c3 = new TableColumn<Defect, String>("Units Affected");
				c3.setCellValueFactory(new PropertyValueFactory<Defect, String>("unitsAffected"));
				c3.setMinWidth(110);
				
				TableColumn<Defect, String> c4 = new TableColumn<Defect, String>("Defect Type");
				c4.setCellValueFactory(new PropertyValueFactory<Defect, String>("type"));
				c4.setMinWidth(90);
				
				tableView.setItems(data);
				tableView.getColumns().addAll(c1, c2, c3, c4);
				
				pane.add(tableView, 2, 1, 1, 4);
	    	}
	    	//if stud is not found
	    	else
	    	{	    		
	    		//tells user no stud exists with that stud ID
	    		Alert a = new Alert(AlertType.ERROR);
				a.setContentText("There have not been any findings logged with that stud ID.");
				a.show();
				
				//Resets text values for the stud to Not Found/empty
				id.setText("Not Found");
			    process.setText("");
			    name.setText("");
			    con.setText("");
			    missing.setText("");
			    broken.setText("");
			    pos.setText("");
			    
			    //When stud is not found, an add stud button becomes visible. Add finding and Edit containment become disabled
				studBtn.setVisible(true);
				addBtn.setDisable(true);
				containBtn.setDisable(true);
	    	}
	    });
	    //action if containment button is clicked
	    containBtn.setOnAction (e ->
	    {
	    	//new instance of containment view class and assigns to ec
	    	EditContainment ec = new EditContainment(stud);
	    	
			//opens add finding window if not opened.
			if (!ec.isShowing()) 
			{
				ec.show();
			} 
			//if opened, brings add finding window to front.
			else 
			{
				ec.toFront();
			}
	    });
	    
	    //closes window if done button is clicked
	    doneBtn.setOnAction(e -> 
	    {
	    	stage.close();
	    });
		
	    //action if add buton is clicked
	    addBtn.setOnAction(e -> 
	    {
	    	AddFinding addFinding = new AddFinding(stud.getStudId());
	    	
			//opens add finding window if not opened.
			if (!addFinding.isShowing()) 
			{
				addFinding.show();
			} 
			//if opened, brings add finding window to front.
			else 
			{
				addFinding.toFront();
			}
			
		});
	    
	    //action if addStud button is clicked
	    studBtn.setOnAction(e -> 
	    {
	    	AddStud addStud = new AddStud(idTF.getText());
	    	
			//opens add stud window if not opened.
			if (!addStud.isShowing()) 
			{
				addStud.show();
			} 
			//if opened, brings add stud window to front.
			else 
			{
				addStud.toFront();
			}
			
			//closes find stud window when add stud window opens
			stage.close();
		});
	    
		pane.addRow(0, idLabel, idTF);
	    pane.add(hb, 1, 1);
	    pane.add(studBtn, 1, 2);
	    
	}
	
	//method that loads recent defects array
	public void loadArray()
	{
		//clears the objects loaded in array
		recentDefects.clear();
		
		//gets defect arraylist from plant and assigns it to local array list
		ArrayList<Defect> defects = Plant.getInstance().getDefectList();
		
		//cycles through defect array list and adds each defect with matching stud ID to recent defects arraylist
		for (Defect d : defects)
		{
			if (d.getStudId().equals(stud.getStudId()))
			{
				recentDefects.add(d);
			}
		}
		
		//sorts the recent defects array by date using comparator
		Collections.sort(recentDefects, new Defect.SortDate());
		
	}
	
	//returns stud id
	public String getStudId()
	{
		return stud.getStudId();
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
