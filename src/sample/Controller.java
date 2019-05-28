



package sample;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.nio.ByteBuffer;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


import javax.imageio.ImageIO;
import javax.swing.*;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField EnteryText;


    @FXML
    private Button EnteryButton;

    class DrawPane extends JPanel {
        @Override
        public void paint(Graphics g) {
            try {
                InputStream inputStream = clientSocket.getInputStream();
                byte[] sizeAr = new byte[4];
                inputStream.read(sizeAr);
                int size = ByteBuffer.wrap(sizeAr).asIntBuffer().get();

                byte[] imageAr = new byte[size];
                inputStream.read(imageAr);
                BufferedImage image = ImageIO.read(new ByteArrayInputStream(imageAr));
                g.drawImage(image, 0, 0,this);
            } catch (IOException e) {
            }
        }
    }

    private Socket clientSocket;
    public static BufferedReader reader;
    public static BufferedReader in;
    public static BufferedWriter out;
    void EnterActivMain(javafx.scene.input.KeyEvent event){

    }
    @FXML
    void keyPressed(MouseEvent event) throws IOException {
        if(EnteryText.getText().equals("AdminConsole")){
            EnteryButton.getScene().getWindow().hide();
            FXMLLoader fxmlLoader = new FXMLLoader();
            try {
                clientSocket = new Socket(new Main().ip, 9999);
                reader = new BufferedReader(new InputStreamReader(System.in));

                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            fxmlLoader.setLocation(getClass().getResource("/sample/AdminPanel.fxml"));
            try {
                fxmlLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = fxmlLoader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } else if (EnteryText.getText().equals("") || !EnteryText.getText().equals("AdminConsole")){
            String read = null;
            try {
                clientSocket = new Socket(new Main().ip, 9999);
                reader = new BufferedReader(new InputStreamReader(System.in));

                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
                String okeyString = "okey";
                out.write(okeyString + "\n");
                out.flush();
                String key2 = EnteryText.getText();
                out.write(key2 + "\n");
                out.flush();
                read = in.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (read.equals("true")) {
                new Main().FailNumber = 3;
                EnteryText.setText(null);
                JFrame homework = new JFrame();
                homework.setSize(1000, 1000);
                homework.setSize(new DrawPane().getWidth(), new DrawPane().getHeight());
                homework.setContentPane(new DrawPane());
                homework.setVisible(true);
                homework.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                homework.addKeyListener(new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                            homework.dispose();
                            try {
                                out.write("back" + "\n");
                                out.flush();
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });
                homework.setSize(1000, 700);
            } else {

                EnteryButton.getScene().getWindow().hide();
                FXMLLoader fxmlLoader = new FXMLLoader();

                fxmlLoader.setLocation(getClass().getResource("/sample/FailPanel.fxml"));
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
        }
    }
    @FXML
    void initialize() {

    }
}
