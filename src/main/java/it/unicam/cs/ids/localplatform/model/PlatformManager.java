package it.unicam.cs.ids.localplatform.model;

import it.unicam.cs.ids.localplatform.MunicipalTerritory;

/**
 * This class represents a platform manager.<br><br>
 * Platform Managers manage all aspects of the platform, not least the acceptance of authorizations and accreditations on the platform itself.
 */
public class PlatformManager extends User {
    public PlatformManager(String name, String surname, String email, String password, MunicipalTerritory residence, String cf) {
        super(name, surname, email, password, residence, cf);
    }


}
