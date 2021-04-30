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

public void DeleteClassToList() {

    String create = Input_Field.getText() + ".txt";
    String Class = Input_Field.getText();

    File f1 = new File(create);

    boolean success = f1.delete();

    Class_List.setOnMouseClicked(event -> {
        String selectedItem = Class_List.getSelectionModel().getSelectedItem().toString();
        Input_Field.setText(selectedItem);
    });

    Class_List.getItems().remove(Class);

    if (!success) {
        System.out.println("Deletion Failed.");
    } else {
        System.out.println("File deleted.");

    }

    Input_Field.clear();
}

    public String AddClassToList() {

        String create = Input_Field.getText() + ".txt";
        String Class = Input_Field.getText();

        Class_List.setOnMouseClicked(event -> {
            String selectedItem = Class_List.getSelectionModel().getSelectedItem().toString();
            selectedIndex = Class_List.getSelectionModel().getSelectedIndex();
            Input_Field.setText(selectedItem);
        });

            try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(create,true)))){
                out.println();
            } catch(IOException ioe){
                ioe.printStackTrace();
            }

        Class_List.getItems().addAll(Class);


        Input_Field.clear();

      return create;
       }

       public void changeSceneButtonHOme(ActionEvent event) throws IOException {
        Parent MenuScreen = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        Scene Homescene = new Scene(MenuScreen);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(Homescene);
        window.show();
       }

}


