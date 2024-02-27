package com.ranamayura.hotelms.model;

public class Customer {
    private int customerId;
    private String customerName;
    private String nic;
    private String address;
    private String contact;
    private boolean status;


    public Customer() {
    }

    public Customer(int customerId, String customerName, String nic, String address, String contact, boolean status) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.nic = nic;
        this.address = address;
        this.contact = contact;
        this.status = status;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
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

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", customerName='" + customerName + '\'' +
                ", nic='" + nic + '\'' +
                ", address='" + address + '\'' +
                ", contact='" + contact + '\'' +
                ", status=" + status +
                '}';
    }
}