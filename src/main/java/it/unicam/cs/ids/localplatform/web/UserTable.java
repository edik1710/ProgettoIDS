package it.unicam.cs.ids.localplatform.web;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

/**
 * This class represents the UserTable entity in the database.
 * It contains the following fields:
 * - name: the name of the user
 * - surname: the surname of the user
 * - email: the email of the user
 * - password: the password of the user
 * - residence: the residence of the user
 * - cf: the fiscal code of the user
 * - role: the role of the user
 * - pending: a boolean that indicates if the user is pending
 * <p>
 * The class also contains the getters and setters for the fields.
 */
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
