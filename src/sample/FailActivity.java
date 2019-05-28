package sample;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.InputMethodEvent;
import javafx.stage.Stage;

public class FailActivity {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button BackButtonFaill;

    @FXML
    public Label FailLabel;

    @FXML
    void initialize() {
        Platform.runLater(()->{
            new Main().FailNumber--;
            if(new Main().FailNumber == 2) {
                FailLabel.setText("У вас осталось " + String.valueOf(new Main().FailNumber) + " попытки");
            } else if(new Main().FailNumber == 1){
                FailLabel.setText("У вас осталось " + String.valueOf(new Main().FailNumber) + " попытка");
            } else {
                FailLabel.setText("Доступ заблокирован");
                BackButtonFaill.setOnAction(event -> {

                BackButtonFaill.getScene().getWindow().hide();
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("/sample/FailPanel.fxml"));
                    try {
                        fxmlLoader.load();
                    }catch (IOException e) {
                        e.printStackTrace();
                    }
                    Parent root = fxmlLoader.getRoot();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.close();

            });
            }
        });
        BackButtonFaill.setOnAction(event -> {

            BackButtonFaill.getScene().getWindow().hide();
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/sample/sample.fxml"));
            try {
                fxmlLoader.load();

            new Controller().out.write("back" + "\n");
            new Controller().out.flush();
            }catch (IOException e) {
            e.printStackTrace();
        }
            Parent root = fxmlLoader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

        });

    }
}
