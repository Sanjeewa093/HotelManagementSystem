package com.ranamayura.hotelms.view.tm;

import javafx.scene.control.ButtonBar;

public class CustomerTm {
    private int id;
    private String name;
    private String nic;
    private String address;
    private String contact;
    private boolean status;

    private ButtonBar buttonBar;

    public CustomerTm() {
    }

    public CustomerTm(int id, String name, String nic, String address, String contact, boolean status, ButtonBar buttonBar) {
        this.id = id;
        this.name = name;
        this.nic = nic;
        this.address = address;
        this.contact = contact;
        this.status = status;
        this.buttonBar = buttonBar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
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
        return "CustomerTm{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", nic='" + nic + '\'' +
                ", address='" + address + '\'' +
                ", contact='" + contact + '\'' +
                ", status=" + status +
                ", buttonBar=" + buttonBar +
                '}';
    }
}
