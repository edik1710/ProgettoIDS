package it.unicam.cs.ids.localplatform.model;

import java.util.ArrayList;
import java.util.List;

public class AuthorizedTourist extends Tourist {
    private List<Info> savedInfo;
    private List<MultimediaContent> uploadedPhotos;

    public AuthorizedTourist(String name, String surname, String email, String password, String address, String cf) {
        super(name, surname, email, password, address, cf);
        this.savedInfo = new ArrayList<>();
        this.uploadedPhotos = new ArrayList<>();
    }
}
