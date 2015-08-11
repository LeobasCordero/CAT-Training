package com.example.leobas.cardviewjson;

/**
 * Created by Leobas on 04/08/2015.
 */
public class Contact {

    private String first_name;
    private String surname;
    private String address;
    private String phone_number;
    private String email;
    private String id;
    private String createdAt;
    private String updatedAt;

    public Contact(){

    }

    public Contact (String first_name, String surname, String address, String phone_number,
                    String email, String id, String createdAt, String updatedAt){
        this.first_name = first_name;
        this.surname = surname;
        this.address = address;
        this.phone_number = phone_number;
        this.email = email;
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
