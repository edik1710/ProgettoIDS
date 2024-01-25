package it.unicam.cs.ids.localplatform;

import it.unicam.cs.ids.localplatform.command.CreatePOICommand;
import it.unicam.cs.ids.localplatform.model.Content;
import it.unicam.cs.ids.localplatform.model.Coordinates;
import it.unicam.cs.ids.localplatform.model.Curator;
import it.unicam.cs.ids.localplatform.model.POI;
import it.unicam.cs.ids.localplatform.singleton.CommandVerificationQueue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * This class tests the {@link Curator} class.
 */
public class CuratorTest {
    private MunicipalTerritory mt;
    private Curator curator;

    @BeforeEach
    public void setup() {
        mt = new MunicipalTerritory();
        curator = new Curator("Elia", "Toma", "elia.toma@studenti.unicam.it", "password", mt, "1234567890");
        CommandVerificationQueue.getInstance().getToBeVerified().clear();
    }

    /**
     * Tests the {@link Curator#viewNextCommand()} method.
     */
    @Test
    public void viewNextCommandTest() {
        // Create necessary instances for the test
        Coordinates coord = new Coordinates(1, 1);
        POI poi = new POI("POI", new Date(), curator, coord, "Description");
        CreatePOICommand command = new CreatePOICommand(poi, mt);
        CommandVerificationQueue.getInstance().getToBeVerified().add(command);

        // Execute the method
        curator.viewNextCommand();

        // Verify that the command has been retrieved
        assertEquals(command, Curator.getValidatingCommand());
    }

    /**
     * Tests the {@link Curator#authorizeCommandExecution()} method.
     */
    @Test
    public void authorizeCommandExecutionTest() {
        // Create necessary instances for the test
        Coordinates coord = new Coordinates(1, 1);
        POI poi = new POI("POI", new Date(), curator, coord, "Description");
        CreatePOICommand command = new CreatePOICommand(poi, mt);
        CommandVerificationQueue.getInstance().getToBeVerified().add(command);

        // Execute the method
        curator.viewNextCommand();
        curator.authorizeCommandExecution();

        // Verify that the command has been executed
        assertEquals(poi, mt.getPOIs().get(coord));
    }

    /**
     * Tests the {@link Curator#rejectCommandExecution()} method.
     */
    @Test
    public void rejectCommandExecutionTest() {
        // Create necessary instances for the test
        Coordinates coord = new Coordinates(1, 1);
        POI poi = new POI("POI", new Date(), curator, coord, "Description");
        CreatePOICommand command = new CreatePOICommand(poi, mt);
        CommandVerificationQueue.getInstance().getToBeVerified().add(command);

        // Execute the method
        curator.viewNextCommand();
        curator.rejectCommandExecution();

        // Verify that the command has been rejected
        assertEquals(0, mt.getPOIs().size());
    }

    /**
     * Tests the {@link Curator#removeReportedContents()} method.
     */
    @Test
    public void removeReportedContentsTest() {
        // Create necessary instances for the test
        Content content = new Content(new Date(), curator, "Description");
        for (int i = 0; i < 10; i++) {
            content.reportContent();
        }
        mt.getGeneralContents().add(content);

        // Execute the method
        curator.removeReportedContents();

        // Verify that the content has been removed
        assertFalse(mt.getGeneralContents().contains(content));
    }
}
