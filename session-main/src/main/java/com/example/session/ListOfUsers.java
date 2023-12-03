package com.example.session;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class ListOfUsers implements Initializable {
    @FXML
    private TableView<Users> table_users;

    @FXML
    private TableColumn<Users, Integer> col_id;

    @FXML
    private TableColumn<Users, String> col_username;

    @FXML
    private TableColumn<Users, String> col_password;

    @FXML
    private TableColumn<Users, String> col_role;

    ObservableList<Users> listM;
    @FXML
    private TextField txt_username;

    @FXML
    private TextField txt_password;

    @FXML
    private RadioButton student;
    @FXML
    private RadioButton teacher;
    @FXML
    private RadioButton zamDirector;
    @FXML
    private Button button_logout;

    int index = -1;

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    @FXML
    void getSelected(MouseEvent event) {
        index = table_users.getSelectionModel().getSelectedIndex();
        if (index <= -1) {

            return;
        }
        String role;
        txt_username.setText(col_username.getCellData(index));
        txt_password.setText(col_password.getCellData(index));
        role = (col_role.getCellData(index));
        if (role.equals("student")) {
            student.setSelected(true);
        } else if (role.equals("teacher")) {
            teacher.setSelected(true);
        } else if (role.equals("zamDirector")) {
            zamDirector.setSelected(true);
        }
    }

    @FXML
    void goBack(ActionEvent event) throws IOException { // GO BACK!!!
        Stage stage = (Stage) button_logout.getScene().getWindow();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("admin-menu.fxml")));
        stage.setTitle("Session");
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private TextField subject_id;

    @FXML
    private TextField couse_id;

    public void Edit() {
        try {
            conn = DButils.ConnectDb();
            String login = txt_username.getText();
            String password = txt_password.getText();
            String course = couse_id.getText();
            String subject = subject_id.getText();
            String role;
            String sql;

            String id = (col_id.getCellData(index).toString());
            if (student.isSelected() && !course.isEmpty()) {
                role = "student";
                sql = "insert into students("+course+","+id+")";
            } else if (teacher.isSelected()  && !subject.isEmpty()) {
                role = "teacher";
                sql = "insert into students("+subject+","+id+")";
            } else if (zamDirector.isSelected()) {
                role = "deputy";
                sql = "insert into deputys("+id+")";
            } else throw new NullPointerException();
//            String sql2 = "update users set login= ? ,password= ?,role= ? where id= ?";
            String sql2 = "update users set login="+login+",password= "+
                    password+",role= "+role+" where user_id="+id;
//            String sql2 = "update users set login= '"+login+"',password= '"+
//                    password+"',role= '"+role+"'"+"' where id='"+id+"'";
            String sql3 = "SELECT role FROM users WHERE user_id = ?";
            PreparedStatement statement = conn.prepareStatement(sql3);
            statement.setString(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            if (resultSet.getString("role").equals("student")) {
                deleteStudent();
            } else if (resultSet.getString("role").equals("teacher")) {
                deleteTeacher();

            } else if (resultSet.getString("role").equals("deputy")) {
                deleteDeputy();
            }
            pst = conn.prepareStatement(sql2);
            pst.execute();
            pst = conn.prepareStatement(sql);
            pst.execute();
            UpdateTable();

            txt_username.setText("");
            txt_password.setText("");
            couse_id.setText("");
            subject_id.setText("");
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, e);
            e.printStackTrace();
        }

    }

    public void UpdateTable() {
        col_id.setCellValueFactory(new PropertyValueFactory<Users, Integer>("user_id"));
        col_username.setCellValueFactory(new PropertyValueFactory<Users, String>("login"));
        col_password.setCellValueFactory(new PropertyValueFactory<Users, String>("password"));
        col_role.setCellValueFactory(new PropertyValueFactory<Users, String>("role"));

        listM = DButils.getDatausers();
        table_users.setItems(listM);
    }

    @FXML
    void deleteSelected() {
        if (index > -1) {
            try {
                conn = DButils.ConnectDb();
                String id = col_id.getCellData(index).toString();


                String query = "SELECT role FROM users WHERE user_id = ?";
                PreparedStatement statement = conn.prepareStatement(query);
                statement.setString(1, id);
                ResultSet resultSet = statement.executeQuery();
                resultSet.next();
                if (resultSet.getString("role").equals("student")) {
                    deleteStudent();
                } else if (resultSet.getString("role").equals("teacher")) {
                    deleteTeacher();
                } else if (resultSet.getString("role").equals("deputy")) {
                    deleteDeputy();
                }
                String sql = "DELETE FROM users WHERE user_id = ?";
                pst = conn.prepareStatement(sql);
                pst.setString(1, id);
                pst.executeUpdate();
                UpdateTable();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    void deleteStudent() throws SQLException {
        conn = DButils.ConnectDb();
        String id = col_id.getCellData(index).toString();
        String sql = "DELETE FROM students WHERE user_id = ?";
        pst = conn.prepareStatement(sql);
        pst.setString(1, id);
        pst.executeUpdate();
        UpdateTable();
    }
    void deleteTeacher() throws SQLException {
        conn = DButils.ConnectDb();
        String id = col_id.getCellData(index).toString();
        String sql = "DELETE FROM teachers WHERE user_id = ?";
        pst = conn.prepareStatement(sql);
        pst.setString(1, id);
        pst.executeUpdate();
        UpdateTable();
    }
    void deleteDeputy() throws SQLException {
        conn = DButils.ConnectDb();
        String id = col_id.getCellData(index).toString();
        String sql = "DELETE FROM deputys WHERE user_id = ?";
        pst = conn.prepareStatement(sql);
        pst.setString(1, id);
        pst.executeUpdate();
        UpdateTable();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        col_id.setCellValueFactory(new PropertyValueFactory<Users, Integer>("user_id"));
        col_username.setCellValueFactory(new PropertyValueFactory<Users, String>("login"));
        col_password.setCellValueFactory(new PropertyValueFactory<Users, String>("password"));
        col_role.setCellValueFactory(new PropertyValueFactory<Users, String>("role"));

        listM = DButils.getDatausers();
        table_users.setItems(listM);
    }
}
