package it.unicam.cs.ids.localplatform.model;

import it.unicam.cs.ids.localplatform.MunicipalTerritory;

import java.util.Objects;

/**
 * This class is a model of a generic user.
 */
public class User {
    private String name;
    private String surname;
    private String email;
    private String password;
    private MunicipalTerritory residence;
    private String cf;

    public User(String name, String surname, String email, String password, MunicipalTerritory residence, String cf) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.residence = residence;
        this.cf = cf;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public MunicipalTerritory getResidence() {
        return residence;
    }

    public String getCf() {
        return cf;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setResidence(MunicipalTerritory residence) {
        this.residence = residence;
    }

    public void setCf(String cf) {
        this.cf = cf;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return email.equals(user.email) && password.equals(user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, password);
    }

    @Override
    public String toString() {
        return "name=" + name +
                ", surname=" + surname +
                ", email=" + email +
                ", password=" + password +
                ", residence=" + residence.getMunicipalName() +
                ", cf=" + cf;
    }
}