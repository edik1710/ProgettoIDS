package it.unicam.cs.ids.localplatform;

import it.unicam.cs.ids.localplatform.command.*;
import it.unicam.cs.ids.localplatform.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class tests the commands of the application.
 */
public class CommandsTest {
    private MunicipalTerritory mt;
    private User u;

    @BeforeEach
    public void setup() {
        this.mt = new MunicipalTerritory();
        this.u = new Contributor("Elia", "Toma", "elia.toma@studenti.unicam.it", "password", mt, "1234567890");
    }

    /**
     * Tests the {@link CreatePOICommand} class.
     */
    @Test
    public void CreatePOICommandTest() {
        // Create necessary instances for the test
        Coordinates coord = new Coordinates(1, 1);
        Command c = new CreatePOICommand("POI", new Date(), u, coord, mt, "Description");
        POI poi = new POI("POI", new Date(), u, coord, "Description");

        // Check NullPointerException
        assertThrows(NullPointerException.class, () -> new CreatePOICommand(null, new Date(), u, coord, mt, "Description"));
        assertThrows(NullPointerException.class, () -> new CreatePOICommand("POI", null, u, coord, mt, "Description"));
        assertThrows(NullPointerException.class, () -> new CreatePOICommand("POI", new Date(), null, coord, mt, "Description"));
        assertThrows(NullPointerException.class, () -> new CreatePOICommand("POI", new Date(), u, null, mt, "Description"));
        assertThrows(NullPointerException.class, () -> new CreatePOICommand("POI", new Date(), u, coord, null, "Description"));
        assertThrows(NullPointerException.class, () -> new CreatePOICommand("POI", new Date(), u, coord, mt, null));

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
        // Create necessary instances for the test
        Coordinates coord = new Coordinates(1, 1);
        POI poi = new POI("POI", new Date(), u, coord, "another description");
        Content expectedContent = new Content(new Date(), u, "Content");
        Command c = new CreatePOIContentCommand(poi, expectedContent);

        // Check NullPointerException
        assertThrows(NullPointerException.class, () -> new CreatePOIContentCommand(null, expectedContent));
        assertThrows(NullPointerException.class, () -> new CreatePOIContentCommand(poi, null));

        // Execute the command
        c.execute();

        // Verify that the content has been created
        assertFalse(poi.getContents().isEmpty());
        assertEquals(expectedContent, poi.getContents().getFirst());
    }

    /**
     * Tests the {@link CreateGeneralContentCommand} class.
     */
    @Test
    public void CreateGeneralContentCommandTest() {
        // Create necessary instances for the test
        Content expectedContent = new Content(new Date(), u, "Content");
        Command c = new CreateGeneralContentCommand(expectedContent, mt);

        // Check NullPointerException
        assertThrows(NullPointerException.class, () -> new CreateGeneralContentCommand(null, mt));
        assertThrows(NullPointerException.class, () -> new CreateGeneralContentCommand(expectedContent, null));

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
        List<POI> POIs = new ArrayList<>();
        POIs.add(new POI("POI1", new Date(), u, new Coordinates(1, 1), "Itinerary Description 1"));
        POIs.add(new POI("POI2", new Date(), u, new Coordinates(2, 2), "Itinerary Description 2"));
        Command c = new CreateItineraryCommand("Itinerary", new Date(), u, POIs, mt, "Description");

        // Check NullPointerException
        assertThrows(NullPointerException.class, () -> new CreateItineraryCommand(null, new Date(), u, POIs, mt, "Description"));
        assertThrows(NullPointerException.class, () -> new CreateItineraryCommand("Itinerary", null, u, POIs, mt, "Description"));
        assertThrows(NullPointerException.class, () -> new CreateItineraryCommand("Itinerary", new Date(), null, POIs, mt, "Description"));
        assertThrows(NullPointerException.class, () -> new CreateItineraryCommand("Itinerary", new Date(), u, null, mt, "Description"));
        assertThrows(NullPointerException.class, () -> new CreateItineraryCommand("Itinerary", new Date(), u, POIs, null, "Description"));
        assertThrows(NullPointerException.class, () -> new CreateItineraryCommand("Itinerary", new Date(), u, POIs, mt, null));

        // Execute the command
        c.execute();

        // Verify that the itinerary has been created
        assertFalse(mt.getItineraries().isEmpty());
        Itinerary expectedItinerary = new Itinerary("Itinerary", new Date(), u, POIs, "Description");
        assertEquals(expectedItinerary, mt.getItineraries().getFirst());
    }

    /**
     * Tests the {@link ChangePOICommand} class.
     */
    @Test
    public void ChangePOICommandTest() {
        // Create necessary instances for the test
        Coordinates coord = new Coordinates(1, 1);
        POI poi = new POI("POI", new Date(), u, coord, "Description");
        Command c = new ChangePOICommand(poi, "New POI");

        // Check NullPointerException
        assertThrows(NullPointerException.class, () -> new ChangePOICommand(null, "New POI"));
        assertThrows(NullPointerException.class, () -> new ChangePOICommand(poi, null));

        // Execute the command
        c.execute();

        // Verify that the POI has been changed
        assertEquals("New POI", poi.getTitle());
    }

    /**
     * Tests the {@link ChangeContentCommand} class.
     */
    @Test
    public void ChangeContentTest() {
        // Create necessary instances for the test
        Content content = new Content(new Date(), u, "Content");
        Command c = new ChangeContentCommand(content, "New Content");

        // Check NullPointerException
        assertThrows(NullPointerException.class, () -> new ChangeContentCommand(null, "New Content"));
        assertThrows(NullPointerException.class, () -> new ChangeContentCommand(content, null));

        // Execute the command
        c.execute();

        // Verify that the content has been changed
        assertEquals("New Content", content.getText());
    }

    /**
     * Tests the {@link ChangeItineraryCommand} class.
     */
    @Test
    public void ChangeItineraryCommandTest() {
        // Create necessary instances for the test
        List<POI> POIs = new ArrayList<>();
        POIs.add(new POI("POI1", new Date(), u, new Coordinates(1, 1), "Description"));
        POIs.add(new POI("POI2", new Date(), u, new Coordinates(2, 2), "Description"));
        Itinerary itinerary = new Itinerary("Itinerary", new Date(), u, POIs, "Description");
        Command c = new ChangeItineraryCommand(itinerary, "New Itinerary");

        // Check NullPointerException
        assertThrows(NullPointerException.class, () -> new ChangeItineraryCommand(null, "New Itinerary"));
        assertThrows(NullPointerException.class, () -> new ChangeItineraryCommand(itinerary, null));

        // Execute the command
        c.execute();

        // Verify that the itinerary has been changed
        assertEquals("New Itinerary", itinerary.getTitle());
    }

    /**
     * Tests the {@link DeletePOICommand} class.
     */
    @Test
    public void DeletePOICommandTest() {
        // Create necessary instances for the test
        Coordinates coord = new Coordinates(1, 1);
        mt.addPOI("POI", new Date(), u, coord, "Description");
        Command c = new DeletePOICommand(mt, coord);

        // Check NullPointerException
        assertThrows(NullPointerException.class, () -> new DeletePOICommand(null, coord));
        assertThrows(NullPointerException.class, () -> new DeletePOICommand(mt, null));

        // Execute the command
        c.execute();

        // Verify that the POI has been deleted
        assertTrue(mt.getPOIs().isEmpty());
    }

    /**
     * Tests the {@link DeleteGeneralContentCommand} class.
     */
    @Test
    public void DeleteContentCommandTest() {
        // Create necessary instances for the test
        Content content = new Content(new Date(), u, "Content");
        mt.addGeneralContent(content);
        Command c = new DeleteGeneralContentCommand(mt, content);

        // Check NullPointerException
        assertThrows(NullPointerException.class, () -> new DeleteGeneralContentCommand(null, content));
        assertThrows(NullPointerException.class, () -> new DeleteGeneralContentCommand(mt, null));

        // Execute the command
        c.execute();

        // Verify that the content has been deleted
        assertTrue(mt.getGeneralContents().isEmpty());
    }

    /**
     * Tests the {@link DeletePOIContentCommand} class.
     */
    @Test
    public void DeletePOIContentCommandTest() {
        // Create necessary instances for the test
        Coordinates coord = new Coordinates(1, 1);
        POI poi = new POI("POI", new Date(), u, coord, "Description");
        Content content = new Content(new Date(), u, "Content");
        poi.addContent(content);
        Command c = new DeletePOIContentCommand(poi, content);

        // Check NullPointerException
        assertThrows(NullPointerException.class, () -> new DeletePOIContentCommand(null, content));
        assertThrows(NullPointerException.class, () -> new DeletePOIContentCommand(poi, null));

        // Execute the command
        c.execute();

        // Verify that the content has been deleted
        assertTrue(poi.getContents().isEmpty());
    }

    /**
     * Tests the {@link DeleteItineraryCommand} class.
     */
    @Test
    public void DeleteItineraryCommandTest() {
        // Create necessary instances for the test
        List<POI> POIs = new ArrayList<>();
        POIs.add(new POI("POI1", new Date(), u, new Coordinates(1, 1), "Description"));
        POIs.add(new POI("POI2", new Date(), u, new Coordinates(2, 2), "Description"));
        Itinerary itinerary = new Itinerary("Itinerary", new Date(), u, POIs, "Description");
        mt.addItinerary("Itinerary", new Date(), u, POIs, "Description");
        Command c = new DeleteItineraryCommand(mt, itinerary);

        // Check NullPointerException
        assertThrows(NullPointerException.class, () -> new DeleteItineraryCommand(null, itinerary));
        assertThrows(NullPointerException.class, () -> new DeleteItineraryCommand(mt, null));

        // Execute the command
        c.execute();

        // Verify that the itinerary has been deleted
        assertTrue(mt.getItineraries().isEmpty());
    }
}
