package com.example.session;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class HelloController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button logInButton;

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;


    @FXML
    void initialize() {

        logInButton.setOnAction(event -> {
            String loginText = loginField.getText().trim();
            String loginPassword = passwordField.getText().trim();
            if (!loginText.equals("") && !loginPassword.equals("")) {
                loginUser(loginText, loginPassword);
            }
        });
    }

    private void loginUser(String loginText, String loginPassword) {
        DButils dbManager = new DButils();
        String role = dbManager.authenticate(loginText, loginPassword);
        switch (role) {
            case "student" -> {
                Info.id = Integer.parseInt(dbManager.getId(loginText, loginPassword));
                Info.course = Integer.parseInt(dbManager.getcourse());
                Stage stage = (Stage) logInButton.getScene().getWindow();
                Parent root = null;
                try {
                    root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource
                            ("student-main.fxml")));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                stage.setTitle("Session");
                stage.setScene(new Scene(root));
                stage.show();
            }
            case "admin" -> {
                Stage stage = (Stage) logInButton.getScene().getWindow();
                Parent root = null;
                try {
                    root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource
                            ("admin-menu.fxml")));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                stage.setTitle("Session");
                stage.setScene(new Scene(root));
                stage.show();
            }
            case "deputy" -> {
                Stage stage = (Stage) logInButton.getScene().getWindow();
                Parent root = null;
                try {
                    root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource
                            ("zamDirector-menu.fxml")));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                stage.setTitle("Session");
                stage.setScene(new Scene(root));
                stage.show();
            }
            case "teacher" -> {
                Info.teacher_id = Integer.parseInt(dbManager.getId(loginText, loginPassword));
                Stage stage = (Stage) logInButton.getScene().getWindow();
                Parent root = null;
                try {
                    root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource
                            ("teacher-main.fxml")));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                stage.setTitle("Session");
                stage.setScene(new Scene(root));
                stage.show();
            }
            default -> System.out.println("wrong login or password");
        }
    }

}