package it.unicam.cs.ids.localplatform.model;

import it.unicam.cs.ids.localplatform.MunicipalTerritory;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents an authorized tourist.<br><br>
 * An Authorized Tourist has the ability to save information for future visits, upload photos on the routes (to be submitted for approval).
 */
public class AuthorizedTourist extends Tourist {
    private List<Info> savedInfo;
    //private List<MultimediaContent> uploadedPhotos;

    public AuthorizedTourist(String name, String surname, String email, String password, MunicipalTerritory residence, String cf) {
        super(name, surname, email, password, residence, cf);
        this.savedInfo = new ArrayList<>();
        //this.uploadedPhotos = new ArrayList<>();
    }
}
