package it.unicam.cs.ids.localplatform.model;

public class Animator extends User {
    private Contest contest;

    public Animator(String name, String surname, String email, String password, String address, String cf) {
        super(name, surname, email, password, address, cf);
    }
}
