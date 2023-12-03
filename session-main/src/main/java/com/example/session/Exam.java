package com.example.session;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class Exam implements Initializable {

    @FXML
    private Label lbl_StudentId;

    @FXML
    private Label lbl_attemptNum;

    @FXML
    private Label lbl_main;

    @FXML
    private Label lbl_subject;

    @FXML
    private Label lbl_teacher;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(Info.attempt_num >= 4){
            lbl_main.setText("Вы исчерпали все свои попытки");
            lbl_main.setTextFill(Color.color(1,0,0));
            lbl_StudentId.setText("");
            lbl_attemptNum.setText("");
            lbl_subject.setText("");
            lbl_teacher.setText("");
            return;
        }
        lbl_StudentId.setText(lbl_StudentId.getText() + " " + Info.id);
        lbl_attemptNum.setText(lbl_attemptNum.getText() + " " + Info.attempt_num);
        lbl_subject.setText(lbl_subject.getText() + " " + Info.subject);
        lbl_teacher.setText(lbl_teacher.getText() + " " + Info.teacher_id);

    }
}
