package it.unicam.cs.ids.localplatform.model;

import it.unicam.cs.ids.localplatform.MunicipalTerritory;

/**
 * This class is a model of a generic user.
 */
public abstract class User {
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
}
