package it.unicam.cs.ids.localplatform.model;

public abstract class User {
    private String name;
    private String surname;
    private String email;
    private String password;
    private String address;
    private String cf;

    public User(String name, String surname, String email, String password, String address, String cf) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.address = address;
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

    public String getAddress() {
        return address;
    }

    public String getCf() {
        return cf;
    }
}
