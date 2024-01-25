package it.unicam.cs.ids.localplatform.model;

import it.unicam.cs.ids.localplatform.MunicipalTerritory;

import java.util.Date;

/**
 * This class represents an animator.<br><br>
 * An Animator can propose themes for possible "contribution contests" of contents.
 * Animators will be responsible for defining the objective and then validating the proposed contents.
 */
public class Animator extends User {
    private static Contest validatingContest;
    private static Content validatingContent;

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
     * This method allows the animator to view a contest.
     *
     * @param contest The contest to be viewed.
     */
    public void viewContest(Contest contest) {
        if (!this.getResidence().getContests().contains(contest)) return;

        validatingContest = this.getResidence().getContests().get(this.getResidence().getContests().indexOf(contest));
    }

    /**
     * This method allows the animator to view the next content to be validated.
     */
    public void viewNextContestContent() {
        if (validatingContest.getContents().isEmpty()) return;

        validatingContent = validatingContest.getContents().getFirst();
    }

    /**
     * This method allows the animator to authorize the content.
     */
    public void authorizeContent() {
        this.getResidence().addGeneralContent(validatingContent);
        validatingContent = null;
    }

    /**
     * This method allows the animator to reject the content.
     */
    public void rejectContent() {
        validatingContest.getContents().remove(validatingContent);
        validatingContent = null;
    }

    public static Contest getValidatingContest() {
        return validatingContest;
    }

    public static Content getValidatingContent() {
        return validatingContent;
    }
}