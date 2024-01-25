package it.unicam.cs.ids.localplatform;

import it.unicam.cs.ids.localplatform.model.Animator;
import it.unicam.cs.ids.localplatform.model.Content;
import it.unicam.cs.ids.localplatform.model.Contest;
import it.unicam.cs.ids.localplatform.model.Contributor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This class tests the {@link Animator} class.
 */
public class AnimatorTest {
    private MunicipalTerritory mt;
    private Animator animator;
    private Date startDate;
    private Date endDate;
    private Contest contest;

    @BeforeEach
    public void setup() {
        mt = new MunicipalTerritory();
        animator = new Animator("Name", "Surname", "Email", "Password", mt, "CF");
        startDate = new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24); // 1 day from now
        endDate = new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 48); // 2 days from now
        contest = new Contest("Objective", "Title", startDate, endDate);
    }

    /**
     * Tests the {@link Animator#proposeContest(String, String, Date, Date)} method.
     */
    @Test
    public void proposeContestTest() {
        // Execute the method
        animator.proposeContest("Objective", "Title", startDate, endDate);

        // Verify that the contest has been proposed
        Contest proposedContest = mt.getContests().getLast(); // Get the last contest in the list
        assertEquals(contest, proposedContest);
    }

    /**
     * Tests the {@link Animator#viewContest(Contest)} method.
     */
    @Test
    public void viewContestTest() {
        // Create necessary instances for the test
        mt.addContest(contest);

        // Execute the method
        animator.viewContest(contest);

        // Verify that the contest has been viewed
        assertEquals(contest, Animator.getValidatingContest());
    }

    /**
     * Tests the {@link Animator#viewNextContestContent()} method.
     */
    @Test
    public void viewNextContestContentTest() {
        // Create necessary instances for the test
        mt.addContest(contest);
        Contributor contributor = new Contributor("Name", "Surname", "Email", "Password", mt, "CF");
        Content content = new Content(new Date(), contributor, "Description");
        contest.addContent(content);

        // Execute the method
        animator.viewContest(contest);
        animator.viewNextContestContent();

        // Verify that the content has been viewed
        assertEquals(content, Animator.getValidatingContent());
    }

    /**
     * Tests the {@link Animator#authorizeContent()} method.
     */
    @Test
    public void authorizeContentTest() {
        // Create necessary instances for the test
        mt.addContest(contest);
        Contributor contributor = new Contributor("Name", "Surname", "Email", "Password", mt, "CF");
        Content content = new Content(new Date(), contributor, "Description");
        contest.addContent(content);

        // Execute the method
        animator.viewContest(contest);
        animator.viewNextContestContent();
        animator.authorizeContent();

        // Verify that the content has been authorized
        assertEquals(content, mt.getGeneralContents().getLast());
    }

    /**
     * Tests the {@link Animator#rejectContent()} method.
     */
    @Test
    public void rejectContentTest() {
        // Create necessary instances for the test
        mt.addContest(contest);
        Contributor contributor = new Contributor("Name", "Surname", "Email", "Password", mt, "CF");
        Content content = new Content(new Date(), contributor, "Description");
        contest.addContent(content);

        // Execute the method
        animator.viewContest(contest);
        animator.viewNextContestContent();
        animator.rejectContent();

        // Verify that the content has been rejected
        assertEquals(0, contest.getContents().size());
    }
}
