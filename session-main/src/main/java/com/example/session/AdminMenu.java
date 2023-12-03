package com.example.session;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class AdminMenu implements Initializable {

    @FXML
    private Button button_logout;
    @FXML
    private Button button_list_of_users;
    @FXML
    private Button button_reg;
    @FXML
    void goBack(ActionEvent event) throws IOException { // GO BACK!!!
        Stage stage = (Stage) button_logout.getScene().getWindow();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("login.fxml")));
        stage.setTitle("Session");
        stage.setScene(new Scene(root));
        stage.show();
    }


    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        button_reg.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Stage stage = (Stage) button_reg.getScene().getWindow();
                Parent root = null;
                try {
                    root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource
                            ("admin-registration.fxml")));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                stage.setTitle("Session");
                stage.setScene(new Scene(root));
                stage.show();
            }
        });

        button_list_of_users.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage = (Stage) button_list_of_users.getScene().getWindow();
                Parent root = null;
                try {
                    root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource
                            ("list-of-users.fxml")));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                stage.setTitle("Session");
                stage.setScene(new Scene(root));
                stage.show();
            }
        });
    }



}
