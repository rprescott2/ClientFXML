package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;

public class Main extends Application {

    public static String ip = null;
    public  static int FailNumber = 3;
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/sample/sample.fxml"));
        primaryStage.setTitle("HomeWork");
        primaryStage.setScene(new Scene(root, 1000, 700));
        primaryStage.show();
    }

    public static void main(String[] args) {
            try {
                ip = new GetIpAdress().run2().replaceFirst("/","");
            } catch (Exception e) {
                e.printStackTrace();
            }
        launch(args);
    }
}
