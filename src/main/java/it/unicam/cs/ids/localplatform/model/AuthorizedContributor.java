package it.unicam.cs.ids.localplatform.model;

import it.unicam.cs.ids.localplatform.MunicipalTerritory;

/**
 * This class represents an authorized contributor.<br><br>
 * Authorized Contributors are special contributors whose reliability has been verified and for whom the uploaded contents
 * are not subject to validation by the {@link Curator}.
 */
public class AuthorizedContributor extends User {

    public AuthorizedContributor(String name, String surname, String email, String password, MunicipalTerritory residence, String cf) {
        super(name, surname, email, password, residence, cf);
    }
}
