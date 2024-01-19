package it.unicam.cs.ids.localplatform.model;

import it.unicam.cs.ids.localplatform.MunicipalTerritory;

/**
 * This class represents an animator.<br><br>
 * An Animator can propose themes for possible "contribution contests" of contents.
 * Animators will be responsible for defining the objective and then validating the proposed contents.
 */
public class Animator extends User {
    private Contest contest;

    public Animator(String name, String surname, String email, String password, MunicipalTerritory residence, String cf) {
        super(name, surname, email, password, residence, cf);
    }
}
