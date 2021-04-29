package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;

public class ChooseClassController {

    private Button Add_Grade;

    @FXML
    private Button Select_Class;

    @FXML
    private TextField Input_Field;

    @FXML
    private ListView<String> Class_List;


    public void AddClassToList(ActionEvent event) {
        String create = Input_Field.getText() + ".txt";
        String Class = Input_Field.getText();


            try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(create,true)))){
                out.println(Input_Field.getText());
            } catch(IOException ioe){
                ioe.printStackTrace();
            }

        Class_List.getItems().addAll(Class);
        Input_Field.clear();
      Class_List.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
       }


       public void changeSceneButtonHOme(ActionEvent event) throws IOException {
        Parent MenuScreen = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        Scene Homescene = new Scene(MenuScreen);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(Homescene);
        window.show();
       }




    }
