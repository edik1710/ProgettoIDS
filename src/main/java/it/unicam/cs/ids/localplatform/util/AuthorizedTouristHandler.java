package it.unicam.cs.ids.localplatform.util;

import it.unicam.cs.ids.localplatform.model.AuthorizedTourist;

/**
 * This class represents a handler for an authorized tourist.
 */
public class AuthorizedTouristHandler {
    private final AuthorizedTourist authorizedTourist;

    public AuthorizedTouristHandler(AuthorizedTourist authorizedTourist) {
        this.authorizedTourist = (AuthorizedTourist) LoginController.getCurrentUser();
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
