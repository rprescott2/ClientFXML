package sample;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class AddActivity {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button AddOkeyButton;
    @FXML
    void AddOkeyActivity(MouseEvent event){
        try {
            new Controller().out.write("Add" + "\n");
            new Controller().out.flush();
            new Controller().out.write(AddText.getText() + "\n");
            new Controller().out.flush();
            AddText.setText(null);
        }catch (IOException e1){
        }
    }
    @FXML
    private Button AddExitButton;
    @FXML
    void AddExitActivity(MouseEvent event){
        AddExitButton.getScene().getWindow().hide();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/sample/sample.fxml"));
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = fxmlLoader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
    @FXML
    private TextField AddText;

    @FXML
    private Label AddLabel;

    @FXML
    void initialize() {
        Platform.runLater(()->{
            AddLabel.setText("Привет " + new AdminActivity().AdminName + "!!!");
        });
    }
}
