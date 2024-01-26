package it.unicam.cs.ids.localplatform;

import it.unicam.cs.ids.localplatform.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * This class tests the {@link PlatformManager} class.
 */
public class PlatformManagerTest {
    private MunicipalTerritory mt;
    private PlatformManager platformManager;

    @BeforeEach
    public void setup() {
        mt = new MunicipalTerritory();
        platformManager = new PlatformManager("Name", "Surname", "Email", "Password", mt, "CF");
    }

    /**
     * Tests the {@link PlatformManager#assignRoles()} method.
     */
    @Test
    public void assignRolesTest() {
        // Create necessary instances for the test
        User animator = new User("AnimatorName", "AnimatorSurname", "AnimatorEmail", "AnimatorPassword", mt, "AnimatorCF");
        User curator = new User("CuratorName", "CuratorSurname", "CuratorEmail", "CuratorPassword", mt, "CuratorCF");
        User platformManagerUser = new User("PlatformManagerName", "PlatformManagerSurname", "PlatformManagerEmail", "PlatformManagerPassword", mt, "PlatformManagerCF");
        User contributor = new User("ContributorName", "ContributorSurname", "ContributorEmail", "ContributorPassword", mt, "ContributorCF");

        // Add users to pendingUsers
        platformManager.getPendingUsers().put(animator, Roles.ANIMATOR);
        platformManager.getPendingUsers().put(curator, Roles.CURATOR);
        platformManager.getPendingUsers().put(platformManagerUser, Roles.PLATFORM_MANAGER);
        platformManager.getPendingUsers().put(contributor, Roles.CONTRIBUTOR);

        // Execute the method
        platformManager.assignRoles();

        // Verify that the roles have been assigned
        List<User> users = mt.getUsers();
        assertTrue(users.stream().anyMatch(user -> user instanceof Animator && user.getName().equals("AnimatorName")));
        assertTrue(users.stream().anyMatch(user -> user instanceof Curator && user.getName().equals("CuratorName")));
        assertTrue(users.stream().anyMatch(user -> user instanceof PlatformManager && user.getName().equals("PlatformManagerName")));
        assertTrue(users.stream().anyMatch(user -> user instanceof Contributor && user.getName().equals("ContributorName")));
    }

    /**
     * Tests the {@link PlatformManager#authorizeTourist(Tourist)} method.
     */
    @Test
    public void authorizeTouristTest() {
        // Create a Tourist instance
        Tourist tourist = new Tourist("TouristName", "TouristSurname", "TouristEmail", "TouristPassword", mt, "TouristCF");
        mt.getUsers().add(tourist);

        // Set the number of reports of the tourist to a value greater than or equal to 10
        tourist.setNumReports(10);

        // Call the authorizeTourist method on the platformManager passing the tourist
        platformManager.authorizeTourist(tourist);

        // Verify that the user has been removed from the municipal territory's user list
        assertFalse(mt.getUsers().contains(tourist));

        // Verify that an AuthorizedTourist with the same information as the original user has been added to the municipal territory's user list
        assertTrue(mt.getUsers().stream().anyMatch(user -> user instanceof AuthorizedTourist && user.getName().equals("TouristName")));
    }

    /**
     * Tests the {@link PlatformManager#authorizeContributor(Contributor)} method.
     */
    @Test
    public void authorizeContributorTest() {
        // Create a Contributor instance
        Contributor contributor = new Contributor("ContributorName", "ContributorSurname", "ContributorEmail", "ContributorPassword", mt, "ContributorCF");
        mt.getUsers().add(contributor);

        // Set the number of submitted info of the contributor to a value greater than or equal to 10
        for (int i = 0; i < 10; i++) {
            contributor.submitPOI("POI" + i, new Coordinates(0, 0), "Description" + i);
        }

        // Call the authorizeContributor method on the platformManager passing the contributor
        platformManager.authorizeContributor(contributor);

        // Verify that the user has been removed from the municipal territory's user list
        assertFalse(mt.getUsers().contains(contributor));

        // Verify that an AuthorizedContributor with the same information as the original user has been added to the municipal territory's user list
        assertTrue(mt.getUsers().stream().anyMatch(user -> user instanceof AuthorizedContributor && user.getName().equals("ContributorName")));
    }
}
