package it.unicam.cs.ids.localplatform.model;

import it.unicam.cs.ids.localplatform.MunicipalTerritory;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents an authorized tourist.<br><br>
 * An Authorized Tourist has the ability to save information for future visits, upload photos on the routes (to be submitted for approval).
 */
public class AuthorizedTourist extends Tourist {
    private final List<Info> savedInfo;

    public AuthorizedTourist(String name, String surname, String email, String password, MunicipalTerritory residence, String cf) {
        super(name, surname, email, password, residence, cf);
        this.savedInfo = new ArrayList<>();
    }

    /**
     * This method allows the authorized tourist to save an information for future visits.
     *
     * @param info The information to be saved.
     */
    public void saveInfo(Info info) {
        this.savedInfo.add(info);
    }

    /**
     * This method allows the authorized tourist to remove an information from the saved ones.
     *
     * @param info The information to be removed.
     */
    public void removeInfo(Info info) {
        this.savedInfo.remove(info);
    }

    public List<Info> getSavedInfo() {
        return savedInfo;
    }
}
