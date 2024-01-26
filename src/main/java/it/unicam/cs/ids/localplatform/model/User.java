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
    //private Roles role;

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

    /*
    public Roles getRole() {
        return role;
    }

     */

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
}
