package com.ranamayura.hotelms.controller;

import com.ranamayura.hotelms.model.Customer;
import com.ranamayura.hotelms.model.Room;
import com.ranamayura.hotelms.util.Globalvar;
import com.ranamayura.hotelms.view.tm.CustomerTm;
import com.ranamayura.hotelms.view.tm.RoomTm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.Optional;

public class RoomsFormController {
    public AnchorPane roomContext;
    public TextField txtId;
    public TextField txtType;
    public TextField txtPrice;
    public TextField txtSearch;
    public Button btnSaveUpdate;
    public TextField txtBed;
    public TableColumn colType;
    public TableColumn colBed;
    public TableColumn colStatus;
    public TableColumn colPrice;
    public TableColumn colOption;
    public TableView <RoomTm>tblRoom;
    public TableColumn colId;
    private String searchText = "";

    public void backToHomeOnAction(ActionEvent actionEvent) throws IOException {
        setUi("DashboardForm");
    }

    public void btnNewRoomOnAction(ActionEvent actionEvent) {
        clearFields();
        txtId=null;
        btnSaveUpdate.setText("Save Room");
    }

    public void btnSaveUpdateOnAction(ActionEvent actionEvent) {

        Room room = new Room(
                txtId.getId(),
                txtType.getText(),
                txtBed.getText(),
                txtPrice.getText(),
                true
        );


        if (btnSaveUpdate.getText().equalsIgnoreCase("save Room")) {
            try {
                // step 01
                Class.forName("com.mysql.cj.jdbc.Driver");

                //step 02

                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelManagementSystem",
                        "root", "1234");

                //step 03

                String query = "INSERT INTO room(room_number, room_type, bed, ammount,status,user_email)" +
                        "VALUES (?,?,?,?,?,?)";

                //step 04

                PreparedStatement preparedStatement = connection.prepareStatement(query);

                //step 05
                preparedStatement.setInt(1, room.getRoomId());
                preparedStatement.setString(2, room.getRoomType());
                preparedStatement.setString(3, room.getBed());
                preparedStatement.setString(4, room.getPrice());
                preparedStatement.setBoolean(5, room.isStatus());
                preparedStatement.setString(6, Globalvar.userEmail);

                if (preparedStatement.executeUpdate() > 0) {
                    new Alert(Alert.AlertType.INFORMATION, "Room was Saved!").show();
                    clearFields();
                    loadRoom(searchText);

                } else {
                    new Alert(Alert.AlertType.WARNING, "Try again").show();
                }

            } catch (ClassNotFoundException | SQLException e) {
                new Alert(Alert.AlertType.ERROR, "Something went wrong").show();
                e.printStackTrace();

            }
        } else {
            if (room.getRoomId() == 0) {
                new Alert(Alert.AlertType.ERROR, "Please verify the Customer Id").show();
                return;
            }
            try {
                // step 01
                Class.forName("com.mysql.cj.jdbc.Driver");

                //step 02

                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelManagementSystem",
                        "root", "1234");

                //step 03

                String query = "UPDATE room SET room_number =?, room_type=?, bed=?, ammount=?";

                //step 04

                PreparedStatement preparedStatement = connection.prepareStatement(query);

                //step 05
                preparedStatement.setInt(1, room.getRoomId());
                preparedStatement.setString(2, room.getRoomType());
                preparedStatement.setString(3, room.getBed());
                preparedStatement.setString(4, room.getPrice());


                if (preparedStatement.executeUpdate() > 0) {
                    new Alert(Alert.AlertType.INFORMATION, "Room was Updated!").show();
                    clearFields();
                    loadRoom(searchText);
                    btnSaveUpdate.setText("Save Room");

                } else {
                    new Alert(Alert.AlertType.WARNING, "Try again").show();
                }

            } catch (ClassNotFoundException | SQLException e) {
                new Alert(Alert.AlertType.ERROR, "Something went wrong").show();
                e.printStackTrace();
            }
        }
    }

    private void loadRoom(String searchText) {

        searchText = searchText + "%";
        try {
            // step 01
            Class.forName("com.mysql.cj.jdbc.Driver");

            //step 02

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelManagementSystem",
                    "root", "1234");

            //step 03

            String query = "SELECT * FROM room WHERE room_number LIKE ? OR type LIKE ?";

            //step 04

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            //step 05
            preparedStatement.setString(1, searchText);
            preparedStatement.setString(2, searchText);

            ResultSet resultSet = preparedStatement.executeQuery();
            ObservableList<RoomTm> tms = FXCollections.observableArrayList();

            while (resultSet.next()) {
                Button deleteButton = new Button("Delete");
                Button updateButton = new Button("Update");

                ButtonBar buttonBar = new ButtonBar();
                buttonBar.getButtons().addAll(deleteButton, updateButton);

                RoomTm roomTm = new RoomTm(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getBoolean(5),
                        buttonBar

                );

                updateButton.setOnAction(e -> {
                    txtId.setId(roomTm.getId());
                    txtType.setText(roomTm.getRoomType());
                    txtBed.setText(roomTm.getBed());
                    txtPrice.setText(roomTm.getPrice());
                    btnSaveUpdate.setText("Update Customer");
                });
                deleteButton.setOnAction(e -> {

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?",
                            ButtonType.YES, ButtonType.NO);
                    Optional<ButtonType> buttonType = alert.showAndWait();

                    if (buttonType.get() == ButtonType.YES) {
                        try {
                            Class.forName("com.mysql.cj.jdbc.Driver");

                            Connection connection1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelManagementSystem",
                                    "root", "1234");


                            String query1 = "DELETE FROM customer WHERE customer_id =? ";


                            PreparedStatement preparedStatement1 = connection1.prepareStatement(query1);



                            if (preparedStatement1.executeUpdate() > 0) {
                                new Alert(Alert.AlertType.INFORMATION, "Customer was Deleted!").show();
                                loadRoom("");

                            } else {
                                new Alert(Alert.AlertType.WARNING, "Try again").show();
                            }
                        } catch (SQLException | ClassNotFoundException ex) {
                            ex.printStackTrace();
                        }

                    }
                });
            }

            tblRoom.setItems(tms);

        } catch (ClassNotFoundException | SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Something went wrong").show();
            e.printStackTrace();
        }
    }
    private void setUi(String location) throws IOException {
        Stage stage = (Stage) roomContext.getScene().getWindow();
        stage.setScene(
                new Scene(FXMLLoader.load(getClass().getResource("../view/" + location + ".fxml"))));
        stage.centerOnScreen();
    }
    private void clearFields() {
        txtId.clear();
        txtType.clear();
        txtBed.clear();
        txtPrice.clear();
    }
}
