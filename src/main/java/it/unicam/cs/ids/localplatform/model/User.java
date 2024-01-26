package it.unicam.cs.ids.localplatform.model;

import it.unicam.cs.ids.localplatform.MunicipalTerritory;
import org.apache.tomcat.util.digester.Rules;

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
    private Rules role;

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

    public Rules getRole() {
        return role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User that = (User) o;
        return this.email.equals(that.email) && this.password.equals(that.password);
    }
}
