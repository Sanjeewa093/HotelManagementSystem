package com.ranamayura.hotelms.controller;

import com.ranamayura.hotelms.model.User;
import com.ranamayura.hotelms.util.Globalvar;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SignupFormController {

    public void initialize() {
        txtFirstName.requestFocus();
    }


    public AnchorPane context;
    public TextField txtFirstName;
    public TextField txtEmail;
    public TextField txtLastName;
    public TextField txtPassword;
    public Button btnSignup;

    public void signUpOnAction(ActionEvent actionEvent) throws IOException {

        User user = new User(
                txtFirstName.getText(),
                txtLastName.getText(),
                txtEmail.getText(),
                txtPassword.getText()
        );

        try {
            // step 01
            Class.forName("com.mysql.cj.jdbc.Driver");

            //step 02

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelManagementSystem",
                    "root", "1234");

            //step 03

            String query = "INSERT INTO user VALUES (?,?,?,?,?)";

            //step 04

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            //step 05
            preparedStatement.setString(1, user.getRootEmail());
            preparedStatement.setString(2, user.getFirstName());
            preparedStatement.setString(3, user.getLastName());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setBoolean(5, true);

            if (preparedStatement.executeUpdate() > 0) {
                new Alert(Alert.AlertType.INFORMATION, "User was Saved!").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Try again").show();
            }

        } catch (ClassNotFoundException | SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Something went wrong").show();
            e.printStackTrace();
        }

        Globalvar.userEmail = user.getRootEmail();
        setUi("DashboardForm");
    }

    public void alreadyHaveAccountOnAction(ActionEvent actionEvent) throws IOException {

        setUi("loginForm");
    }

    private void setUi(String location) throws IOException {
        Stage stage = (Stage) context.getScene().getWindow();
        stage.setScene(
                new Scene(FXMLLoader.load(getClass().getResource("../view/" + location + ".fxml"))));
        stage.centerOnScreen();
    }

    public void fNameNextOnAction(ActionEvent actionEvent) {
        txtFirstName.requestFocus();
    }

    public void lNameNextOnAction(ActionEvent actionEvent) {
        txtEmail.requestFocus();
    }

    public void emailNextOnAction(ActionEvent actionEvent) {
        txtPassword.requestFocus();
    }

    public void passwordNextOnAction(ActionEvent actionEvent) {
        btnSignup.requestFocus();
    }
}
