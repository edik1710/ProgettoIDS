package it.unicam.cs.ids.localplatform.web;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class UserTable {
    private String name;

    private String surname;

    @Id
    private String email;

    private String password;

    private String residence;

    private String cf;

    private String role;

    private boolean pending;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isPending() {
        return pending;
    }

    public void setPending(boolean pending) {
        this.pending = pending;
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

    public String getResidence() {
        return residence;
    }

    public void setResidence(String residence) {
        this.residence = residence;
    }

    public String getCf() {
        return cf;
    }

    public void setCf(String cf) {
        this.cf = cf;
    }

    @Override
    public String toString() {
        return "name=" + name +
                ", surname=" + surname +
                ", email=" + email +
                ", password=" + password +
                ", residence=" + residence +
                ", cf=" + cf +
                ", role=" + role +
                ", pending=" + pending;
    }
}
