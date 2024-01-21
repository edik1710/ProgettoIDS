package it.unicam.cs.ids.localplatform;

import it.unicam.cs.ids.localplatform.command.CreatePOICommand;
import it.unicam.cs.ids.localplatform.model.Coordinates;
import it.unicam.cs.ids.localplatform.model.Curator;
import it.unicam.cs.ids.localplatform.model.POI;
import it.unicam.cs.ids.localplatform.singleton.CommandVerificationQueue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        CreatePOICommand command = new CreatePOICommand("POI", new Date(), curator, coord, mt);
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
        POI poi = new POI("POI", new Date(), curator, coord);
        CreatePOICommand command = new CreatePOICommand("POI", new Date(), curator, coord, mt);
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
        CreatePOICommand command = new CreatePOICommand("POI", new Date(), curator, coord, mt);
        CommandVerificationQueue.getInstance().getToBeVerified().add(command);

        // Execute the method
        curator.viewNextCommand();
        curator.rejectCommandExecution();

        // Verify that the command has been rejected
        assertEquals(0, mt.getPOIs().size());
    }
}
