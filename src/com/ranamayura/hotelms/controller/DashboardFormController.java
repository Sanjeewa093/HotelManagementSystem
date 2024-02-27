package com.ranamayura.hotelms.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DashboardFormController {
    public AnchorPane context;
    public Label lblTime;
    public Label lblDate;

    public void initialize(){
        setData();
    }

    public void customerClickOnAction(MouseEvent mouseEvent) throws IOException {
        setUi("CustomerForm");
    }

    private void setUi(String location) throws IOException{
        Stage stage = (Stage) context.getScene().getWindow();
        stage.setScene(
                new Scene(FXMLLoader.load(getClass().getResource("../view/" + location + ".fxml"))));
        stage.centerOnScreen();
    }
    private void setData() {
/*        Date date =new Date();
        SimpleDateFormat dateFormat =new SimpleDateFormat("yyyy-MM-dd");
       String textDate =dateFormat.format(date);
       lblDate.setText(textDate);*/

        lblDate.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        //lblTime.setText(new SimpleDateFormat("HH:mm:ss").format(new Date()));

        Timeline timeline=new Timeline(
                new KeyFrame(Duration.seconds(0),
                        e->{
                            DateTimeFormatter dateTimeFormatter=
                                    DateTimeFormatter.ofPattern("hh:mm:ss");
                            lblTime.setText(LocalTime.now().format(dateTimeFormatter));
                        }
                ),
                new KeyFrame(Duration.seconds(1))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

    }

    public void roomsClickOnAction(MouseEvent mouseEvent) throws IOException {
        setUi("RoomsForm");
    }

    public void reservationClickOnAction(MouseEvent mouseEvent) throws IOException {
        setUi("ReservationForm");
    }

    public void userClickOnAction(MouseEvent mouseEvent) throws IOException {
        setUi("SignupForm");
    }
}
