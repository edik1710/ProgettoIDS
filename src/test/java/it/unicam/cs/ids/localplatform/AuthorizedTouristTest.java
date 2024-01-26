package it.unicam.cs.ids.localplatform;

import it.unicam.cs.ids.localplatform.model.AuthorizedTourist;
import it.unicam.cs.ids.localplatform.model.Content;
import it.unicam.cs.ids.localplatform.model.Contributor;
import it.unicam.cs.ids.localplatform.model.Info;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This class tests the {@link AuthorizedTourist} class.
 */
public class AuthorizedTouristTest {
    private AuthorizedTourist tourist;
    private Contributor contributor;

    @BeforeEach
    public void setup() {
        MunicipalTerritory mt = new MunicipalTerritory("Camerino");
        tourist = new AuthorizedTourist("Name", "Surname", "Email", "Password", mt, "CF");
        contributor = new Contributor("Name", "Surname", "Email", "Password", mt, "CF");
    }

    /**
     * Tests the {@link AuthorizedTourist#saveInfo(Info)} method.
     */
    @Test
    public void saveInfoTest() {
        // Create necessary instances for the test
        Info info = new Content(new Date(), contributor, "Description");

        // Execute the method
        tourist.saveInfo(info);

        // Verify that the info has been saved
        assertEquals(1, tourist.getSavedInfo().size());
    }

    /**
     * Tests the {@link AuthorizedTourist#removeInfo(Info)} method.
     */
    @Test
    public void removeInfoTest() {
        // Create necessary instances for the test
        Info info = new Content(new Date(), contributor, "Description");
        tourist.saveInfo(info);

        // Execute the method
        tourist.removeInfo(info);

        // Verify that the info has been removed
        assertEquals(0, tourist.getSavedInfo().size());
    }
}
