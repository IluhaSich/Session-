package com.example.session;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class TeacherMain implements Initializable {
    ObservableList<Exams> ListT;

    @FXML
    private Button btn_fullList;

    @FXML
    private Button btn_grade;

    @FXML
    private TableColumn<Exams, Integer> c_attempt;

    @FXML
    private TableColumn<Exams, Integer> c_examId;

    @FXML
    private TableColumn<Exams, Integer> c_student;

    @FXML
    private TableColumn<Exams, Integer> c_subject;


    @FXML
    private TableColumn<Exams, Integer> c_grade;
    @FXML
    private TextField t_grade;

    @FXML
    private TableView<Exams> tview_exams;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        c_examId.setCellValueFactory(new PropertyValueFactory<Exams, Integer>("exam_details_id"));
        c_subject.setCellValueFactory(new PropertyValueFactory<Exams, Integer>("subject_id"));
        c_student.setCellValueFactory(new PropertyValueFactory<Exams, Integer>("student_id"));
        c_grade.setCellValueFactory(new PropertyValueFactory<Exams, Integer>("grade"));
        c_attempt.setCellValueFactory(new PropertyValueFactory<Exams, Integer>("attempt_num"));
        ListT = DButils.getDataExams();
        tview_exams.setItems(ListT);
    }

    int index = -1;

    @FXML
    void getSelected(MouseEvent event) {
        index = tview_exams.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }


    }

    public void UpdateTable() {
        c_examId.setCellValueFactory(new PropertyValueFactory<Exams, Integer>("exam_details_id"));
        c_subject.setCellValueFactory(new PropertyValueFactory<Exams, Integer>("subject_id"));
        c_student.setCellValueFactory(new PropertyValueFactory<Exams, Integer>("student_id"));
        c_grade.setCellValueFactory(new PropertyValueFactory<Exams, Integer>("grade"));
        c_attempt.setCellValueFactory(new PropertyValueFactory<Exams, Integer>("attempt_num"));

        ListT = DButils.getDataExams();
        tview_exams.setItems(ListT);
    }

}
