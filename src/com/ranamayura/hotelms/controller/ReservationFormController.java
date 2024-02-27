package com.ranamayura.hotelms.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ReservationFormController {

    public AnchorPane ReservationContext;
    public TextField txtId;
    public TextField txtTotalAmount;
    public TextField txtSearch;
    public Button btnSaveUpdate;
    public TableView tblRoom;
    public TableColumn colId;
    public TableColumn colRoomNumber;
    public TableColumn colInDate;
    public TableColumn colOutDate;
    public TableColumn colTotalAmount;
    public TableColumn colOption;
    public TextField txtRoomNumber;

    public void backToHomeOnAction(ActionEvent actionEvent) throws IOException {
        setUi("DashboardForm");
    }

    public void btnNewRoomOnAction(ActionEvent actionEvent) {

    }
    private void setUi(String location) throws IOException {
        Stage stage = (Stage) ReservationContext.getScene().getWindow();
        stage.setScene(
                new Scene(FXMLLoader.load(getClass().getResource("../view/" + location + ".fxml"))));
        stage.centerOnScreen();
    }

    public void btnSaveUpdateOnAction(ActionEvent actionEvent) {
    }
}
