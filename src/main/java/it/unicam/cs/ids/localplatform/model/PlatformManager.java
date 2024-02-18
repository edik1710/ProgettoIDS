package it.unicam.cs.ids.localplatform.model;

import it.unicam.cs.ids.localplatform.MunicipalTerritory;

import java.util.HashMap;
import java.util.Map;

/**
 * This class represents a platform manager.<br><br>
 * Platform Managers manage all aspects of the platform, not least the acceptance of authorizations and accreditations on the platform itself.
 */
public class PlatformManager extends User {
    private final Map<User, Roles> pendingUsers = new HashMap<>();

    public PlatformManager(String name, String surname, String email, String password, MunicipalTerritory residence, String cf) {
        super(name, surname, email, password, residence, cf);
    }

    /**
     * This method allows the platform manager to assign roles to users.
     */
    public void assignRoles() {
        for (Map.Entry<User, Roles> entry : pendingUsers.entrySet()) {
            User user = entry.getKey();
            Roles role = entry.getValue();

            if (!user.getResidence().equals(this.getResidence())) {
                createTourist(user);
            } else {
                switch (role) {
                    case ANIMATOR:
                        Animator animator = new Animator(user.getName(), user.getSurname(), user.getEmail(), user.getPassword(), user.getResidence(), user.getCf());
                        this.getResidence().getUsers().add(animator);
                        break;
                    case CURATOR:
                        Curator curator = new Curator(user.getName(), user.getSurname(), user.getEmail(), user.getPassword(), user.getResidence(), user.getCf());
                        this.getResidence().getUsers().add(curator);
                        break;
                    case PLATFORM_MANAGER:
                        PlatformManager platformManager = new PlatformManager(user.getName(), user.getSurname(), user.getEmail(), user.getPassword(), user.getResidence(), user.getCf());
                        this.getResidence().getUsers().add(platformManager);
                        break;
                    case CONTRIBUTOR:
                        Contributor contributor = new Contributor(user.getName(), user.getSurname(), user.getEmail(), user.getPassword(), user.getResidence(), user.getCf());
                        this.getResidence().getUsers().add(contributor);
                        break;
                }
            }
        }
    }

    /**
     * This method allows the platform manager to create a new tourist.
     *
     * @param user The name of the tourist.
     */
    private void createTourist(User user) {
        Tourist tourist = new Tourist(user.getName(), user.getSurname(), user.getEmail(), user.getPassword(), user.getResidence(), user.getCf());
        this.getResidence().getUsers().add(tourist);
    }

    /**
     * This method allows the platform manager to authorize a tourist after 10 correct reports.
     *
     * @param tourist The tourist to be authorized.
     */
    public void authorizeTourist(Tourist tourist) {
        if (tourist.getNumReports() >= 10) {
            AuthorizedTourist authorizedTourist = new AuthorizedTourist(tourist.getName(), tourist.getSurname(), tourist.getEmail(), tourist.getPassword(), tourist.getResidence(), tourist.getCf());
            this.getResidence().getUsers().remove(tourist);
            this.getResidence().getUsers().add(authorizedTourist);
        }
    }

    /**
     * This method allows the platform manager to authorize a contributor.
     *
     * @param contributor The contributor to be authorized.
     */
    public void authorizeContributor(Contributor contributor) {
        if (contributor.getSubmittedInfo() >= 10) {
            AuthorizedContributor authorizedContributor = new AuthorizedContributor(contributor.getName(), contributor.getSurname(), contributor.getEmail(), contributor.getPassword(), contributor.getResidence(), contributor.getCf());
            this.getResidence().getUsers().remove(contributor);
            this.getResidence().getUsers().add(authorizedContributor);
        }
    }

    /**
     * This method allows the platform manager to add a pending user.
     *
     * @param user The user.
     * @param role The role requested by the user.
     */
    public void addPendingUser(User user, String role) {
        Roles roles = Roles.valueOf(role);
        pendingUsers.put(user, roles);
    }

    public Map<User, Roles> getPendingUsers() {
        return pendingUsers;
    }
}