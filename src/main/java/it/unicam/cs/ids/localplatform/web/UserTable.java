package it.unicam.cs.ids.localplatform.web;

import it.unicam.cs.ids.localplatform.MunicipalTerritory;
import it.unicam.cs.ids.localplatform.model.User;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class UserTable {

    private String cf;
    private String name;
    private String surname;
    private String residence;

    public String getCf() {
        return cf;
    }

    public void setCf(String cf) {
        this.cf = cf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getResidence() {
        return residence;
    }

    public void setResidence(String residence) {
        this.residence = residence;
    }

    @Id
    private String email;
    private String password;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
