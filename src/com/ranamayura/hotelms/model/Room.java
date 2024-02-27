package com.ranamayura.hotelms.model;

public class Room {
    private int roomId;
    private String roomType;
    private String bed;
    private String price;
    private boolean status;

    public Room(String id, String text, String txtBedText, String txtPriceText, boolean status) {
    }

    public Room(int roomId, String roomType, String bed, String price, boolean status) {
        this.roomId = roomId;
        this.roomType = roomType;
        this.bed = bed;
        this.price = price;
        this.status = status;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
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

    @Override
    public String toString() {
        return "Room{" +
                "roomId=" + roomId +
                ", roomType='" + roomType + '\'' +
                ", bed='" + bed + '\'' +
                ", price='" + price + '\'' +
                ", status=" + status +
                '}';
    }
}
