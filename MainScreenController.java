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
