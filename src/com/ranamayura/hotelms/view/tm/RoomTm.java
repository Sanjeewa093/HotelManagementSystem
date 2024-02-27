package com.ranamayura.hotelms.view.tm;

import javafx.scene.control.ButtonBar;

public class RoomTm {

    private int id;
    private String roomType;
    private String bed;
    private String price;
    private boolean status;
    private ButtonBar buttonBar;

    public RoomTm() {
    }

    public RoomTm(int id, String roomType, String bed, String price, boolean status, ButtonBar buttonBar) {
        this.id = id;
        this.roomType = roomType;
        this.bed = bed;
        this.price = price;
        this.status = status;
        this.buttonBar = buttonBar;
    }

    public String getId() {
        return String.valueOf(id);
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getBed() {
        return bed;
    }

    public void setBed(String bed) {
        this.bed = bed;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public ButtonBar getButtonBar() {
        return buttonBar;
    }

    public void setButtonBar(ButtonBar buttonBar) {
        this.buttonBar = buttonBar;
    }

    @Override
    public String toString() {
        return "RoomTm{" +
                "id=" + id +
                ", roomType='" + roomType + '\'' +
                ", bed='" + bed + '\'' +
                ", price='" + price + '\'' +
                ", status=" + status +
                ", buttonBar=" + buttonBar +
                '}';
    }
}
