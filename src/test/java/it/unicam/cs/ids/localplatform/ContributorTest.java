package it.unicam.cs.ids.localplatform;

import it.unicam.cs.ids.localplatform.model.*;
import it.unicam.cs.ids.localplatform.singleton.CommandVerificationQueue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * This class tests the {@link Contributor} class.
 */
public class ContributorTest {
    private Contributor c;

    @BeforeEach
    public void setup() {
        MunicipalTerritory mt = new MunicipalTerritory();
        c = new Contributor("Elia", "Toma", "elia.toma@studenti.unicam.it", "password", mt, "1234567890");
        CommandVerificationQueue.getInstance().getToBeVerified().clear();
    }

    /**
     * Tests the {@link Contributor#submitPOI(String, Coordinates)} method.
     */
    @Test
    public void submitPOITest() {
        // Create necessary instances for the test
        Coordinates coord = new Coordinates(1, 1);
        String poiTitle = "POI";

        // Execute the method
        c.submitPOI(poiTitle, coord);

        // Verify that the command has been added to the queue
        assertFalse(CommandVerificationQueue.getInstance().getToBeVerified().isEmpty());
    }

    /**
     * Tests the {@link Contributor#submitGeneralContent(Content)} method.
     */
    @Test
    public void submitGeneralContentTest() {
        // Create necessary instances for the test
        Content content = new Content(new Date(), c, "Content");

        // Execute the method
        c.submitGeneralContent(content);

        // Verify that the command has been added to the queue
        assertFalse(CommandVerificationQueue.getInstance().getToBeVerified().isEmpty());
    }

    /**
     * Tests the {@link Contributor#submitItinerary(String, List)} method.
     */
    @Test
    public void submitItineraryTest() {
        // Create necessary instances for the test
        List<POI> POIs = new ArrayList<>();
        POIs.add(new POI("POI1", new Date(), c, new Coordinates(1, 1)));
        POIs.add(new POI("POI2", new Date(), c, new Coordinates(2, 2)));

        // Execute the method
        c.submitItinerary("Itinerary", POIs);

        // Verify that the command has been added to the queue
        assertFalse(CommandVerificationQueue.getInstance().getToBeVerified().isEmpty());
    }

    /**
     * Tests the {@link Contributor#submitPOIContent(POI, Content)} method.
     */
    @Test
    public void submitPOIContentTest() {
        // Create necessary instances for the test
        POI poi = new POI("POI", new Date(), c, new Coordinates(1, 1));
        Content content = new Content(new Date(), c, "Content");

        // Execute the method
        c.submitPOIContent(poi, content);

        // Verify that the command has been added to the queue
        assertFalse(CommandVerificationQueue.getInstance().getToBeVerified().isEmpty());
    }

    /**
     * Tests the {@link Contributor#submitChangesToPOI(POI, String)} method.
     */
    @Test
    public void submitChangesToPOITest() {
        // Create necessary instances for the test
        POI poi = new POI("POI", new Date(), c, new Coordinates(1, 1));
        String newTitle = "New POI";

        // Execute the method
        c.submitChangesToPOI(poi, newTitle);

        // Verify that the command has been added to the queue
        assertFalse(CommandVerificationQueue.getInstance().getToBeVerified().isEmpty());
    }

    /**
     * Tests the {@link Contributor#submitChangesToExistingContent(Content, String)} method.
     */
    @Test
    public void submitChangesToExistingContentTest() {
        // Create necessary instances for the test
        Content content = new Content(new Date(), c, "Content");
        String newText = "New Content";

        // Execute the method
        c.submitChangesToExistingContent(content, newText);

        // Verify that the command has been added to the queue
        assertFalse(CommandVerificationQueue.getInstance().getToBeVerified().isEmpty());
    }

    /**
     * Tests the {@link Contributor#submitChangesToItinerary(Itinerary, String)} method.
     */
    @Test
    public void submitChangesToItineraryTest() {
        // Create necessary instances for the test
        List<POI> POIs = new ArrayList<>();
        POIs.add(new POI("POI1", new Date(), c, new Coordinates(1, 1)));
        POIs.add(new POI("POI2", new Date(), c, new Coordinates(2, 2)));
        Itinerary itinerary = new Itinerary("Itinerary", new Date(), c, POIs);
        String newTitle = "New Itinerary";

        // Execute the method
        c.submitChangesToItinerary(itinerary, newTitle);

        // Verify that the command has been added to the queue
        assertFalse(CommandVerificationQueue.getInstance().getToBeVerified().isEmpty());
    }

    /**
     * Tests the {@link Contributor#submitPOIDeletion(POI)} method.
     */
    @Test
    public void submitPOIDeletionTest() {
        // Create necessary instances for the test
        POI poi = new POI("POI", new Date(), c, new Coordinates(1, 1));

        // Execute the method
        c.submitPOIDeletion(poi);

        // Verify that the command has been added to the queue
        assertFalse(CommandVerificationQueue.getInstance().getToBeVerified().isEmpty());
    }

    /**
     * Tests the {@link Contributor#submitGeneralContentDeletion(Content)} method.
     */
    @Test
    public void submitGeneralContentDeletionTest() {
        // Create necessary instances for the test
        Content content = new Content(new Date(), c, "Content");

        // Execute the method
        c.submitGeneralContentDeletion(content);

        // Verify that the command has been added to the queue
        assertFalse(CommandVerificationQueue.getInstance().getToBeVerified().isEmpty());
    }

    /**
     * Tests the {@link Contributor#submitPOIContentDeletion(POI, Content)} method.
     */
    @Test
    public void submitPOIContentDeletionTest() {
        // Create necessary instances for the test
        POI poi = new POI("POI", new Date(), c, new Coordinates(1, 1));
        Content content = new Content(new Date(), c, "Content");

        // Execute the method
        c.submitPOIContentDeletion(poi, content);

        // Verify that the command has been added to the queue
        assertFalse(CommandVerificationQueue.getInstance().getToBeVerified().isEmpty());
    }

    /**
     * Tests the {@link Contributor#submitItineraryDeletion(Itinerary)} method.
     */
    @Test
    public void submitItineraryDeletionTest() {
        // Create necessary instances for the test
        List<POI> POIs = new ArrayList<>();
        POIs.add(new POI("POI1", new Date(), c, new Coordinates(1, 1)));
        POIs.add(new POI("POI2", new Date(), c, new Coordinates(2, 2)));
        Itinerary itinerary = new Itinerary("Itinerary", new Date(), c, POIs);

        // Execute the method
        c.submitItineraryDeletion(itinerary);

        // Verify that the command has been added to the queue
        assertFalse(CommandVerificationQueue.getInstance().getToBeVerified().isEmpty());
    }
}
