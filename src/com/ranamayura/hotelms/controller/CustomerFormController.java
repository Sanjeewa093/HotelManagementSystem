package com.ranamayura.hotelms.controller;

import com.ranamayura.hotelms.model.Customer;
import com.ranamayura.hotelms.util.Globalvar;
import com.ranamayura.hotelms.view.tm.CustomerTm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.Optional;

public class CustomerFormController {
    public AnchorPane customerFormContext;
    public TextField txtName;
    public TextField txtNic;
    public TextField txtAddress;
    public TextField txtContact;
    public Button btnSaveUpdate;
    public TextField txtSearch;
    public TableView<CustomerTm> tblCustomer;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colNic;
    public TableColumn colAddress;
    public TableColumn colContact;
    public TableColumn colStatus;
    public TableColumn colOption;

    private String searchText = "";

    private int selectedCustomerId = 0;

    public void initialize() {

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colNic.setCellValueFactory(new PropertyValueFactory<>("nic"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("buttonBar"));

        loadCustomers(searchText);

        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            searchText = newValue;
            if (newValue != null) {
                loadCustomers(searchText);
            }
        });
    }

    public void backToHomeOnAction(ActionEvent actionEvent) throws IOException {
        setUi("DashboardForm");
    }

    public void btnSaveUpdateOnAction(ActionEvent actionEvent) {

        Customer customer = new Customer(
                0,
                txtName.getText(),
                txtNic.getText(),
                txtAddress.getText(),
                txtContact.getText(),
                true
        );


        if (btnSaveUpdate.getText().equalsIgnoreCase("save customer")) {
            try {
                // step 01
                Class.forName("com.mysql.cj.jdbc.Driver");

                //step 02

                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelManagementSystem",
                        "root", "1234");

                //step 03

                String query = "INSERT INTO customer(customer_name, nic, address, contact,status,user_email)" +
                        "VALUES (?,?,?,?,?,?)";

                //step 04

                PreparedStatement preparedStatement = connection.prepareStatement(query);

                //step 05
                preparedStatement.setString(1, customer.getCustomerName());
                preparedStatement.setString(2, customer.getNic());
                preparedStatement.setString(3, customer.getAddress());
                preparedStatement.setString(4, customer.getContact());
                preparedStatement.setBoolean(5, customer.isStatus());
                preparedStatement.setString(6, Globalvar.userEmail);

                if (preparedStatement.executeUpdate() > 0) {
                    new Alert(Alert.AlertType.INFORMATION, "Customer was Saved!").show();
                    clearFields();
                    loadCustomers(searchText);

                } else {
                    new Alert(Alert.AlertType.WARNING, "Try again").show();
                }

            } catch (ClassNotFoundException | SQLException e) {
                new Alert(Alert.AlertType.ERROR, "Something went wrong").show();
                e.printStackTrace();

            }
        } else {
            if (selectedCustomerId == 0) {
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

                String query = "UPDATE customer SET customer_name =?, nic=?, address=?, contact=?" +
                        "WHERE customer_id =?";

                //step 04

                PreparedStatement preparedStatement = connection.prepareStatement(query);

                //step 05
                preparedStatement.setString(1, customer.getCustomerName());
                preparedStatement.setString(2, customer.getNic());
                preparedStatement.setString(3, customer.getAddress());
                preparedStatement.setString(4, customer.getContact());
                preparedStatement.setInt(5, selectedCustomerId);

                if (preparedStatement.executeUpdate() > 0) {
                    new Alert(Alert.AlertType.INFORMATION, "Customer was Updated!").show();
                    clearFields();
                    loadCustomers(searchText);
                    btnSaveUpdate.setText("Save Customer");

                } else {
                    new Alert(Alert.AlertType.WARNING, "Try again").show();
                }

            } catch (ClassNotFoundException | SQLException e) {
                new Alert(Alert.AlertType.ERROR, "Something went wrong").show();
                e.printStackTrace();
            }
        }
    }

    private void clearFields() {
        txtName.clear();
        txtNic.clear();
        txtAddress.clear();
        txtContact.clear();
    }

    private void loadCustomers(String searchText) {

        searchText = searchText + "%";
        try {
            // step 01
            Class.forName("com.mysql.cj.jdbc.Driver");

            //step 02

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelManagementSystem",
                    "root", "1234");

            //step 03

            String query = "SELECT * FROM customer WHERE customer_name LIKE ? OR nic LIKE ?";

            //step 04

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            //step 05
            preparedStatement.setString(1, searchText);
            preparedStatement.setString(2, searchText);

            ResultSet resultSet = preparedStatement.executeQuery();
            ObservableList<CustomerTm> tms = FXCollections.observableArrayList();

            while (resultSet.next()) {
                Button deleteButton = new Button("Delete");
                Button updateButton = new Button("Update");

                ButtonBar buttonBar = new ButtonBar();
                buttonBar.getButtons().addAll(deleteButton, updateButton);

                CustomerTm customerTm = new CustomerTm(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getBoolean(6),
                        buttonBar

                );
                tms.add(customerTm);

                updateButton.setOnAction(e -> {
                    txtName.setText(customerTm.getName());
                    txtNic.setText(customerTm.getNic());
                    txtAddress.setText(customerTm.getAddress());
                    txtContact.setText(customerTm.getContact());
                    selectedCustomerId = customerTm.getId();
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

                            preparedStatement1.setInt(1, customerTm.getId());


                            if (preparedStatement1.executeUpdate() > 0) {
                                new Alert(Alert.AlertType.INFORMATION, "Customer was Deleted!").show();
                                loadCustomers("");

                            } else {
                                new Alert(Alert.AlertType.WARNING, "Try again").show();
                            }
                        } catch (SQLException | ClassNotFoundException ex) {
                            ex.printStackTrace();
                        }

                    }
                });
            }

            tblCustomer.setItems(tms);

        } catch (ClassNotFoundException | SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Something went wrong").show();
            e.printStackTrace();
        }
    }

    private void setUi(String location) throws IOException {
        Stage stage = (Stage) customerFormContext.getScene().getWindow();
        stage.setScene(
                new Scene(FXMLLoader.load(getClass().getResource("../view/" + location + ".fxml"))));
        stage.centerOnScreen();
    }

    public void btnNewCustomerOnAction(ActionEvent actionEvent) {
        clearFields();
        selectedCustomerId = 0;
        btnSaveUpdate.setText("Save Customer");
    }

}
