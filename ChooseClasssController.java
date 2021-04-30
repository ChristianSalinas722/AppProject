package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;

public class ChooseClassController {

    //Creating the IDs for all of the functions in the scene
    @FXML
    private Button Add_Grade;

    @FXML
    private Button Delete_Grade;

    @FXML
    private Button Select_Class;

    @FXML
    private TextField Input_Field;

    @FXML
    private ListView<String> Class_List;

    private int selectedIndex = -1;

    //Fuction to delete a class off of the ListView
public void DeleteClassToList() {

    //Creating simple variables for the Input Field (Where the user inputs data)
    String create = Input_Field.getText() + ".txt";
    String Class = Input_Field.getText();

    File f1 = new File(create);

    boolean success = f1.delete();

    //Setting up so when you click a class on the List View it shows up on the Text Field
    Class_List.setOnMouseClicked(event -> {
        String selectedItem = Class_List.getSelectionModel().getSelectedItem().toString();
        Input_Field.setText(selectedItem);
    });

    //Removing the Class thats in the Text Field
    Class_List.getItems().remove(Class);

    if (!success) {
        System.out.println("Deletion Failed.");
    } else {
        System.out.println("File deleted.");

    }
//Clearing the Text Field
    Input_Field.clear();
}

    //Function to Add Classes to the View List
    public String AddClassToList() {

        //Creating simple variables for the Input Field (Where the user inputs data)
        String create = Input_Field.getText() + ".txt";
        String Class = Input_Field.getText();

        //Setting up so when you click a class on the List View it shows up on the Text Field
        Class_List.setOnMouseClicked(event -> {
            String selectedItem = Class_List.getSelectionModel().getSelectedItem().toString();
            selectedIndex = Class_List.getSelectionModel().getSelectedIndex();
            Input_Field.setText(selectedItem);
        });
        //Making a File Name with what the User inputs 
            try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(create,true)))){
                out.println("\n");
            } catch(IOException ioe){
                ioe.printStackTrace();
            }

        //Adding what is in the Text Field to the View List
        Class_List.getItems().addAll(Class);

//Clearing
        Input_Field.clear();

      return create;
       }

    //Function to change scene to the next screen
       public void changeSceneButtonHOme(ActionEvent event) throws IOException {
        Parent MenuScreen = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        Scene Homescene = new Scene(MenuScreen);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(Homescene);
        window.show();
       }

}

