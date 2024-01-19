package it.unicam.cs.ids.localplatform;

import it.unicam.cs.ids.localplatform.command.*;
import it.unicam.cs.ids.localplatform.model.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * This class tests the commands of the application.
 */
public class CommandsTest {

    /**
     * Tests the {@link CreatePOICommand} class.
     */
    @Test
    public void CreatePOICommandTest() {
        // Create necessary instances for the test
        MunicipalTerritory mt = new MunicipalTerritory();
        User u = new Contributor("Elia", "Toma", "elia.toma@studenti.unicam.it", "password", mt, "1234567890");
        Coordinates coord = new Coordinates(1, 1);
        Command c = new CreatePOICommand("POI", new Date(), u, coord, mt);
        POI poi = new POI("POI", new Date(), u, coord);

        // Execute the command
        c.execute();

        // Verify that the POI has been created
        assertFalse(mt.getPOIs().isEmpty());
        assertEquals(mt.getPOIs().get(coord), poi);
    }

    /**
     * Tests the {@link CreatePOIContentCommand} class.
     */
    @Test
    public void CreatePOIContentCommandTest() {

    }

    /**
     * Tests the {@link CreateGeneralContentCommand} class.
     */
    @Test
    public void CreateGeneralContentCommandTest() {
        // Create necessary instances for the test
        MunicipalTerritory mt = new MunicipalTerritory();
        User u = new Contributor("Elia", "Toma", "elia.toma@studenti.unicam.it", "password", mt, "1234567890");
        Content expectedContent = new TextualContent(new Date(), u, "Content");
        Command c = new CreateGeneralContentCommand(expectedContent, mt);

        // Execute the command
        c.execute();

        // Verify that the content has been created
        assertFalse(mt.getGeneralContents().isEmpty());
        assertEquals(expectedContent, mt.getGeneralContents().getFirst());
    }

    /**
     * Tests the {@link CreateItineraryCommand} class.
     */
    @Test
    public void CreateItineraryCommandTest() {
        // Create necessary instances for the test
        MunicipalTerritory mt = new MunicipalTerritory();
        User u = new Contributor("Elia", "Toma", "elia.toma@studenti.unicam.it", "password", mt, "1234567890");
        List<POI> POIs = new ArrayList<>();
        POIs.add(new POI("POI1", new Date(), u, new Coordinates(1, 1)));
        POIs.add(new POI("POI2", new Date(), u, new Coordinates(2, 2)));
        Command c = new CreateItineraryCommand("Itinerary", new Date(), u, POIs, mt);

        // Execute the command
        c.execute();

        // Verify that the itinerary has been created
        assertFalse(mt.getItineraries().isEmpty());
        Itinerary expectedItinerary = new Itinerary("Itinerary", new Date(), u, POIs);
        assertEquals(expectedItinerary, mt.getItineraries().getFirst());
    }

    /**
     * Tests the {@link ChangePOICommand} class.
     */
    @Test
    public void ChangePOICommandTest() {

    }

    /**
     * Tests the {@link ChangeExistingContentCommand} class.
     */
    @Test
    public void ChangeExistingContentTest() {

    }

    /**
     * Tests the {@link ChangeItineraryCommand} class.
     */
    @Test
    public void ChangeItineraryCommandTest() {

    }

    /**
     * Tests the {@link DeletePOICommand} class.
     */
    @Test
    public void DeletePOICommandTest() {

    }

    /**
     * Tests the {@link DeleteContentCommand} class.
     */
    @Test
    public void DeleteContentCommandTest() {

    }

    /**
     * Tests the {@link DeleteItineraryCommand} class.
     */
    @Test
    public void DeleteItineraryCommandTest() {

    }
}
