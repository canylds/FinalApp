package com.example.finalapp.models;

public class UserModel {
    String firstName;
    String lastName;
    String Email;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public UserModel(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        Email = email;
    }

    public UserModel() {
    }
}
