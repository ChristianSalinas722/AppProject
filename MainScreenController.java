package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;



public class MainScreenController {
    @FXML
    private Button MenuButton;

    public void changeSceneButtonHOme(ActionEvent event) throws IOException {
        Parent MenuScreen = FXMLLoader.load(getClass().getResource("ChooseClass.fxml"));
        Scene Homescene = new Scene(MenuScreen);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(Homescene);
        window.show();
    }

}
