package com.ranamayura.hotelms.controller;

import com.ranamayura.hotelms.util.Globalvar;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class LoginFormController {
    public AnchorPane context;
    public TextField txtEmail;
    public TextField txtPassword;

    public void signInOnAction(ActionEvent actionEvent) throws IOException {

        try {
            // step 01
            Class.forName("com.mysql.cj.jdbc.Driver");

            //step 02

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelManagementSystem",
                    "root", "1234");

            //step 03

            String query = "SELECT email FROM user WHERE email=? AND password=?";

            //step 04

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            //step 05
            preparedStatement.setString(1, txtEmail.getText());
            preparedStatement.setString(2, txtPassword.getText());

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Globalvar.userEmail = resultSet.getString(1);
                setUi("DashboardForm");
                return;
            }
            new Alert(Alert.AlertType.INFORMATION, "Password or email wrong").show();

        } catch (ClassNotFoundException | SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Something went wrong").show();
            e.printStackTrace();
        }
    }

    public void createAccountOnAction(ActionEvent actionEvent) throws IOException {
        setUi("SignupForm");
    }

    private void setUi(String location) throws IOException {
        Stage stage = (Stage) context.getScene().getWindow();
        stage.setScene(
                new Scene(FXMLLoader.load(getClass().getResource("../view/" + location + ".fxml"))));
        stage.centerOnScreen();
    }
}
