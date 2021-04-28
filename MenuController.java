package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;

public class MenuController {
    @FXML
    private Button AddGradeButton;
    private Button ViewGradeButton;
    public void changeSceneButtonHOme(ActionEvent event) throws IOException {
        Parent MenuScreen = FXMLLoader.load(getClass().getResource("ViewGrade.fxml"));
        Scene Homescene = new Scene(MenuScreen);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(Homescene);
        window.show();
    }
}
