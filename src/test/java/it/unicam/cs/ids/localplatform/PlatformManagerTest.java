package it.unicam.cs.ids.localplatform;

import it.unicam.cs.ids.localplatform.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

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
        /*
        User animator = new User("AnimatorName", "AnimatorSurname", "AnimatorEmail", "AnimatorPassword", mt, "AnimatorCF");
        User curator = new User("CuratorName", "CuratorSurname", "CuratorEmail", "CuratorPassword", mt, "CuratorCF");
        User platformManagerUser = new User("PlatformManagerName", "PlatformManagerSurname", "PlatformManagerEmail", "PlatformManagerPassword", mt, "PlatformManagerCF");
        User contributor = new User("ContributorName", "ContributorSurname", "ContributorEmail", "ContributorPassword", mt, "ContributorCF");
        */

        // Add users to pendingUsers
        /*
        platformManager.getPendingUsers().put(animator, Roles.ANIMATOR);
        platformManager.getPendingUsers().put(curator, Roles.CURATOR);
        platformManager.getPendingUsers().put(platformManagerUser, Roles.PLATFORM_MANAGER);
        platformManager.getPendingUsers().put(contributor, Roles.CONTRIBUTOR);
        */

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

    }

    /**
     * Tests the {@link PlatformManager#authorizeContributor(Contributor)} method.
     */
    @Test
    public void authorizeContributorTest() {

    }
}
