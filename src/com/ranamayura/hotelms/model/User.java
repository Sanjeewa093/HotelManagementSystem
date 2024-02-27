package com.ranamayura.hotelms.model;

public class User {
    private String firstName;
    private String LastName;
    private String rootEmail;
    private String password;

    public User() {
    }

    public User(String firstName, String lastName, String rootEmail, String password) {
        this.firstName = firstName;
        LastName = lastName;
        this.rootEmail = rootEmail;
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getRootEmail() {
        return rootEmail;
    }

    public void setRootEmail(String rootEmail) {
        this.rootEmail = rootEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", LastName='" + LastName + '\'' +
                ", rootEmail='" + rootEmail + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
