package it.unicam.cs.ids.localplatform.util;

import it.unicam.cs.ids.localplatform.model.AuthorizedTourist;

import java.text.SimpleDateFormat;
import java.util.Scanner;

/**
 * This class represents a handler for an authorized tourist.
 */
public class AuthorizedTouristHandler extends TouristHandler {
    private AuthorizedTourist authorizedTourist;
    private Scanner scanner = new Scanner(System.in);
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    public AuthorizedTouristHandler(AuthorizedTourist authorizedTourist) {
        super(authorizedTourist);
        this.authorizedTourist = authorizedTourist;
    }

    /**
     * This method allows the authorized tourist to save an information for future visits.
     */
    public void saveInfo() {
        // TODO: implement
    }

    /**
     * This method allows the authorized tourist to remove an information from the saved ones.
     */
    public void removeInfo() {
        // TODO: implement
    }
}
