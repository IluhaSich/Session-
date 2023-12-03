package com.example.session;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class StudentMain implements Initializable {
    ObservableList<Subjects> ListS;

    @FXML
    private Button btn_back;

    @FXML
    private Button btn_exam;

    @FXML
    private TableColumn<Subjects, Integer> t_id;

    @FXML
    private TableColumn<Subjects, String> t_subject;

    @FXML
    private TableView<Subjects> tbl_subjects;

    @FXML
    void goBack(ActionEvent event) throws IOException {
        Stage stage = (Stage) btn_back.getScene().getWindow();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("login.fxml")));
        stage.setTitle("Session");
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void pass_Exam(ActionEvent event) {
//        String id = (t_id.getCellData(index).toString());
        DButils db = new DButils();
        if (t_subject.getCellData(index).isEmpty()) {
            return;
        }
        else Info.attempt_num = Integer.parseInt(db.getAttempt()) + 1;
        Info.subject = (t_subject.getCellData(index));
        Info.subject_id = (t_id.getCellData(index));
        Info.teacher_id = 1;

        if (!(Info.attempt_num >= 4)) {
            db.createExam();
        }
        Stage stage = (Stage) btn_exam.getScene().getWindow();
        Parent root = null;
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource
                    ("exam.fxml")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.setTitle("Session");
        stage.setScene(new Scene(root));
        stage.show();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        t_id.setCellValueFactory(new PropertyValueFactory<Subjects, Integer>("subjects_id"));
        t_subject.setCellValueFactory(new PropertyValueFactory<Subjects, String>("subject"));

        ListS = DButils.getDataSubjects();
        tbl_subjects.setItems(ListS);
    }

    int index = -1;

    @FXML
    void getSelected(MouseEvent event) {
        index = tbl_subjects.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }


    }

    public void UpdateTable() {
        t_id.setCellValueFactory(new PropertyValueFactory<Subjects, Integer>("subjects_id"));
        t_subject.setCellValueFactory(new PropertyValueFactory<Subjects, String>("subject"));

        ListS = DButils.getDataSubjects();
        tbl_subjects.setItems(ListS);
    }

}