package application;
	
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.*;
import javafx.event.*;


public class Main extends Application {
	
	public void start(Stage stage)  {
		stage.setTitle("Assignment 2");
		
		//create labels, textfields and comboboxes
		//traveler name
		Label nameLabel = new Label("Traveller's Name: ");
		TextField nameInput = new TextField();
		
		//city
		Label cityLabel = new Label("City: ");
		ComboBox<String> cityBox = new ComboBox<String>();
		
		//populate city combo box
		cityBox.getItems().add("Thunder Bay");
		cityBox.getItems().add("Toronto");
		cityBox.getItems().add("Montreal");
		cityBox.getItems().add("Vancouver");
		
		//default
		cityBox.setValue("Thunder Bay");
				
		//email
		Label emailLabel = new Label("Email: ");
		TextField emailInput = new TextField();
				
		//mobile number
		Label mobileLabel = new Label("Mobile No.: ");
		TextField mobileInput = new TextField();
				
		//occupation
		Label occupationLabel = new Label("Occupation: ");
		ComboBox<String> occupationBox = new ComboBox<String>();
		
		//populate occupation combo box
		occupationBox.getItems().add("Engineer");
		occupationBox.getItems().add("Programmer");
		occupationBox.getItems().add("Doctor");
		
		//default
		occupationBox.setValue("Programmer");
				
		//Buttons
		//save
		Button save = new Button("Save");
		//close
		Button close = new Button("Close");
		//search
		Button search = new Button("Search");
		
		//create alert
		Alert a = new Alert(AlertType.NONE);
		
		//save event handler
		class SaveButtonHandler implements EventHandler<ActionEvent>{
			
			public void handle(ActionEvent event) {
				
				//create file
				
				try {
					FileWriter fw = new FileWriter("TravellerInfo.txt", true);
					PrintWriter output = new PrintWriter(fw);
					
					//pull inputs
					String name = nameInput.getText();
					String city = cityBox.getValue();
					String email = emailInput.getText();
					String mobile = mobileInput.getText();
					String occupation = occupationBox.getValue();
					
					//output
					output.println(name);
					output.println(city);
					output.println(email);
					output.println(mobile);
					output.println(occupation);
					output.println();
					
					output.close();
				
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
				
				//alert box
				a.setAlertType(AlertType.INFORMATION);
				a.setContentText("Successfully saved the details");
				a.show();
			}
		}
		
		class CloseButtonHandler implements EventHandler<ActionEvent>{
			
			public void handle(ActionEvent event) {
				System.exit(0);
			}
		}
		
		class SearchButtonHandler implements EventHandler<ActionEvent>{
			
			public void handle(ActionEvent event) {
				try {
				//read file
				File file = new File("TravellerInfo.txt");
				Scanner fileRead = new Scanner(file);
				String mobile = mobileInput.getText();
				
				//create hashset
				HashSet<String> hset = new HashSet<String>();
				
				//add contents of txt file to  hash set
				while(fileRead.hasNext()) {
					String str = fileRead.nextLine();
					hset.add(str);
				}
				
				if(hset.contains(mobile)) {		
					a.setAlertType(AlertType.INFORMATION);
					a.setContentText("A traveler exists with that number");
					a.show();
				}else {
					a.setAlertType(AlertType.INFORMATION);
					a.setContentText("Traveler is not found");
					a.show();
				}
				fileRead.close();
			}catch(IOException e) {
				System.out.println(e.getMessage());
			}
		 }
		}

		//set button actions
		save.setOnAction(new SaveButtonHandler());
		search.setOnAction(new SearchButtonHandler());
		close.setOnAction(new CloseButtonHandler());
		
		//gridpane
		GridPane gpane = new GridPane(); 
		gpane.setHgap(15);
		gpane.setVgap(25);
		gpane.setPadding(new Insets(10));
		
		//populate grid
		gpane.add(nameLabel, 0, 0);
		gpane.add(nameInput, 1, 0);
		gpane.add(cityLabel, 2, 0);
		gpane.add(cityBox, 3, 0);
		gpane.add(emailLabel, 0, 1);
		gpane.add(emailInput, 1, 1);
		gpane.add(mobileLabel, 2, 1);
		gpane.add(mobileInput, 3, 1);
		gpane.add(occupationLabel, 0, 2);
		gpane.add(occupationBox, 1, 2);
		
		gpane.add(save, 0, 3);
		gpane.add(close, 1, 3);
		gpane.add(search, 2, 3);
		
		//hbox for buttons
		HBox hbox = new HBox(15,save,close,search);
		hbox.setAlignment(Pos.CENTER);
		
		//vbox
		VBox vbox = new VBox(15,gpane, hbox);
		vbox.setPadding(new Insets(10));
		
		//set scene
		Scene scene = new Scene(vbox);
		stage.setScene(scene);
		
		stage.show();
			
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
