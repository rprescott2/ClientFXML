package sample;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class AdminActivity {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField AdminPassword;

    private Socket clientSocket;

    @FXML
    private TextField AdminLogin;

    @FXML
    private Button AdminOkeyButton;

    public static String AdminName = null;
    @FXML
    void OkeyButtonAdmin(MouseEvent event) {
        String read = null;
        try {
            new Controller().out.write("Admin" + "\n");
            new Controller().out.flush();
            new Controller().out.write(AdminLogin.getText() + "\n");
            new Controller().out.flush();
            new Controller().out.write(AdminPassword.getText() + "\n");
            new Controller().out.flush();
            read = new Controller().in.readLine();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        if (read.contentEquals("true")) {
            AdminName = AdminLogin.getText();
            AdminLogin.setText(null);
            AdminPassword.setText(null);
            AdminOkeyButton.getScene().getWindow().hide();
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/sample/AddPanel.fxml"));
            try {
                fxmlLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = fxmlLoader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } else {
            AdminOkeyButton.getScene().getWindow().hide();
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
        }
    }
    @FXML
    private Button AdminExitButton;
    @FXML
    void AdminExitActivity(MouseEvent event){
        AdminOkeyButton.getScene().getWindow().hide();
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
    }
    @FXML
    void initialize() {

    }
}
