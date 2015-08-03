package com.example.leobas.weekendassignamentone;

import java.util.Date;

/**
 * Created by Leobas on 02/08/2015.
 */
public class Contact {
    String name;
    int nin;
    int passport;
    String password;
    boolean genre;
    Date birthdate;
    int country;

    public Contact (String name, int nin, String password, int passport, boolean genre, Date birthdate, int country){
        this.name = name;
        this.nin = nin;
        this.password = password;
        this.passport = passport;
        this.genre = genre;
        this.birthdate = birthdate;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNin() {
        return nin;
    }

    public void setNin(int nin) {
        this.nin = nin;
    }

    public int getPassport() {
        return passport;
    }

    public void setPassport(int passport) {
        this.passport = passport;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isGenre() {
        return genre;
    }

    public void setGenre(boolean genre) {
        this.genre = genre;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public int getCountry() {
        return country;
    }

    public void setCountry(int country) {
        this.country = country;
    }
}
