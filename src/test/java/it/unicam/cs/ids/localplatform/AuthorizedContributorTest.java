package it.unicam.cs.ids.localplatform;

import it.unicam.cs.ids.localplatform.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class tests the {@link AuthorizedContributor} class.
 */
public class AuthorizedContributorTest {
    private MunicipalTerritory mt;
    private AuthorizedContributor ac;

    @BeforeEach
    public void setup() {
        mt = new MunicipalTerritory();
        ac = new AuthorizedContributor("Elia", "Toma", "elia.toma@studenti.unicam.it", "password", mt, "1234567890");
    }

    /**
     * Tests the {@link AuthorizedContributor#publishPOI(String, Coordinates)} method.
     */
    @Test
    public void publishPOITest() {
        // Create necessary instances for the test
        Coordinates coord = new Coordinates(1, 1);
        String poiTitle = "POI";

        // Execute the method
        ac.publishPOI(poiTitle, coord);

        // Verify that the POI has been created
        assertFalse(mt.getPOIs().isEmpty());
    }

    /**
     * Tests the {@link AuthorizedContributor#publishGeneralContent(Content)} method.
     */
    @Test
    public void publishGeneralContentTest() {
        // Create necessary instances for the test
        Content content = new TextualContent(new Date(), ac, "Content");

        // Execute the method
        ac.publishGeneralContent(content);

        // Verify that the content has been created
        assertFalse(mt.getGeneralContents().isEmpty());
    }

    /**
     * Tests the {@link AuthorizedContributor#publishItinerary(String, List)} method.
     */
    @Test
    public void publishItineraryTest() {
        // Create necessary instances for the test
        List<POI> POIs = new ArrayList<>();
        POIs.add(new POI("POI1", new Date(), ac, new Coordinates(1, 1)));
        POIs.add(new POI("POI2", new Date(), ac, new Coordinates(2, 2)));
        String itineraryTitle = "Itinerary";

        // Execute the method
        ac.publishItinerary(itineraryTitle, POIs);

        // Verify that the itinerary has been created
        assertFalse(mt.getItineraries().isEmpty());
    }

    /**
     * Tests the {@link AuthorizedContributor#publishPOIContent(POI, Content)} method.
     */
    @Test
    public void publishPOIContentTest() {
        // Create necessary instances for the test
        Coordinates coord = new Coordinates(1, 1);
        POI poi = new POI("POI", new Date(), ac, coord);
        Content content = new TextualContent(new Date(), ac, "Content");

        // Execute the method
        ac.publishPOIContent(poi, content);

        // Verify that the content has been created
        assertFalse(poi.getContents().isEmpty());
    }

    /**
     * Tests the {@link AuthorizedContributor#publishChangesToPOI(POI, String)} method.
     */
    @Test
    public void publishChangesToPOITest() {
        // Create necessary instances for the test
        Coordinates coord = new Coordinates(1, 1);
        POI poi = new POI("POI", new Date(), ac, coord);

        // Execute the method
        ac.publishChangesToPOI(poi, "New POI Title");

        // Verify that the content has been created
        assertEquals("New POI Title", poi.getTitle());
    }

    // Inserire il commento javaDoc dopo l'implementazione del metodo
    @Test
    public void publishChangesToExistingContentTest() {
        // TODO
    }

    /**
     * Tests the {@link AuthorizedContributor#publishChangesToItinerary(Itinerary, String)} method.
     */
    @Test
    public void publishChangesToItineraryTest() {
        // Create necessary instances for the test
        List<POI> POIs = new ArrayList<>();
        POIs.add(new POI("POI1", new Date(), ac, new Coordinates(1, 1)));
        POIs.add(new POI("POI2", new Date(), ac, new Coordinates(2, 2)));
        Itinerary itinerary = new Itinerary("Itinerary", new Date(), ac, POIs);

        // Execute the method
        ac.publishChangesToItinerary(itinerary, "New Itinerary Title");

        // Verify that the content has been created
        assertEquals("New Itinerary Title", itinerary.getTitle());
    }

    /**
     * Tests the {@link AuthorizedContributor#deletePOI(POI)} method.
     */
    @Test
    public void deletePOITest() {
        // Create necessary instances for the test
        Coordinates coord = new Coordinates(1, 1);
        POI poi = new POI("POI", new Date(), ac, coord);

        ac.publishPOI("POI", coord);
        assertFalse(mt.getPOIs().isEmpty());
        assertEquals(poi, mt.getPOIs().get(coord));

        // Execute the method
        ac.deletePOI(poi);

        // Verify that the content has been created
        assertFalse(mt.getPOIs().containsKey(coord));
        assertTrue(mt.getPOIs().isEmpty());
    }

    // Inserire il commento javaDoc dopo l'implementazione del metodo
    @Test
    public void deleteContentTest() {
        // TODO
    }

    /**
     * Tests the {@link AuthorizedContributor#deleteItinerary(Itinerary)} method.
     */
    @Test
    public void deleteItineraryTest() {
        // Create necessary instances for the test
        List<POI> POIs = new ArrayList<>();
        POIs.add(new POI("POI1", new Date(), ac, new Coordinates(1, 1)));
        POIs.add(new POI("POI2", new Date(), ac, new Coordinates(2, 2)));
        Itinerary itinerary = new Itinerary("Itinerary", new Date(), ac, POIs);

        ac.publishItinerary("Itinerary", POIs);
        assertFalse(mt.getItineraries().isEmpty());
        assertEquals(itinerary, mt.getItineraries().getFirst());

        // Execute the method
        ac.deleteItinerary(itinerary);

        // Verify that the content has been created
        assertFalse(mt.getItineraries().contains(itinerary));
        assertTrue(mt.getItineraries().isEmpty());
    }
}
