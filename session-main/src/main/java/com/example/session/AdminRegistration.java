package com.example.session;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class AdminRegistration {
    @FXML
    private Button regButton;
    @FXML
    private TextField login;
    @FXML
    private TextField course_id;
    @FXML
    private TextField subject_id;
    @FXML
    private TextField password;
    @FXML
    private RadioButton student;
    @FXML
    private RadioButton teacher;
    @FXML
    private RadioButton zamDirector;
    @FXML
    private Button button_logout;
    @FXML
    void goBack(ActionEvent event) throws IOException { // GO BACK!!!
        Stage stage = (Stage) button_logout.getScene().getWindow();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("admin-menu.fxml")));
        stage.setTitle("Session");
        stage.setScene(new Scene(root));
        stage.show();
    }
    @FXML
    void initialize() {
        regButton.setOnAction(event2->{
            String username = login.getText().trim();
            String password1 = password.getText().trim();
            String role;
            DButils manager = new DButils();
            if (student.isSelected()) {
                role = "student";
                if (!username.isEmpty() && !password1.isEmpty()) {
                    int course_Id = Integer.parseInt(course_id.getText().trim());
                    manager.user_Registration(username, password1, role);
                    manager.student_Registration(course_Id, manager.getLastUserId());
                }
            } else if (teacher.isSelected()) {
                role = "teacher";
                if (!username.isEmpty() && !password1.isEmpty()) {
                    int subject_Id = Integer.parseInt(subject_id.getText().trim());
                    manager.user_Registration(username, password1, role);
                    manager.student_Registration(subject_Id, manager.getLastUserId());
                }
            } else if (zamDirector.isSelected()) {
                    role = "deputy";
                if (!username.isEmpty() && !password1.isEmpty()) {
                    manager.user_Registration(username, password1, role);
                    System.out.println("Пользователь добавлен.");
                }
            }
        });
    }

}
