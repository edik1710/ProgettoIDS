package it.unicam.cs.ids.localplatform;

import it.unicam.cs.ids.localplatform.model.Content;
import it.unicam.cs.ids.localplatform.model.Contributor;
import it.unicam.cs.ids.localplatform.model.Tourist;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This class tests the {@link Tourist} class.
 */
public class TouristTest {

    /**
     * Tests the {@link Tourist#reportContent(Content)} method.
     */
    @Test
    public void reportContentTest() {
        // Create necessary instances for the test
        MunicipalTerritory mt = new MunicipalTerritory();
        Tourist tourist = new Tourist("Name", "Surname", "Email", "Password", mt, "CF");
        Contributor contributor = new Contributor("Name", "Surname", "Email", "Password", mt, "CF");
        Content content = new Content(new Date(), contributor, "Description");

        // Execute the method
        tourist.reportContent(content);

        // Verify that the content has been reported
        assertEquals(1, content.getReports());

        // Verify that the tourist has reported a content
        assertEquals(1, tourist.getNumReports());
    }
}
