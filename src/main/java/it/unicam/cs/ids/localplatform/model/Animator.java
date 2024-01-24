package it.unicam.cs.ids.localplatform.model;

import it.unicam.cs.ids.localplatform.MunicipalTerritory;

import java.util.Date;

/**
 * This class represents an animator.<br><br>
 * An Animator can propose themes for possible "contribution contests" of contents.
 * Animators will be responsible for defining the objective and then validating the proposed contents.
 */
public class Animator extends User {

    public Animator(String name, String surname, String email, String password, MunicipalTerritory residence, String cf) {
        super(name, surname, email, password, residence, cf);
    }

    /**
     * This method allows the animator to propose a contest.
     *
     * @param objective The objective of the contest.
     * @param title     The title of the contest.
     * @param startDate The start date of the contest.
     * @param endDate   The end date of the contest.
     */
    public void proposeContest(String objective, String title, Date startDate, Date endDate) {
        this.getResidence().addContest(new Contest(objective, title, startDate, endDate));
    }

    /**
     * This method allows the curator to view the next command to be verified.
     */
    /*
    public void viewNextContestCommand() {
        if (CommandVerificationQueue.getInstance().getToBeVerified().isEmpty()) return;

        validatingCommand = CommandVerificationQueue.getInstance().getToBeVerified().peek();
    }

     */

    /**
     * This method allows the curator to authorize the execution of the command.
     */
    /*
    public void authorizeCommandExecution() {
        validatingCommand.execute();
        validatingCommand = null;
        CommandVerificationQueue.getInstance().getToBeVerified().remove();
    }

     */

    /**
     * This method allows the curator to reject the execution of the command.
     */
    /*
    public void rejectCommandExecution() {
        validatingCommand = null;
        CommandVerificationQueue.getInstance().getToBeVerified().remove();
    }

     */
}