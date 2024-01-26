package it.unicam.cs.ids.localplatform.util;

import it.unicam.cs.ids.localplatform.model.Animator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

/**
 * This class represents an animator handler.
 */
public class AnimatorHandler {
    private Animator animator;
    private Scanner scanner = new Scanner(System.in);
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    public AnimatorHandler(Animator animator) {
        this.animator = animator;
    }

    /**
     * This method allows the animator to create a contest.
     */
    public void createContest() {
        System.out.println("Inserisci l'obiettivo del contest:");
        String objective = scanner.nextLine();
        System.out.println("Inserisci il titolo del contest:");
        String title = scanner.nextLine();
        System.out.println("Inserisci la data di inizio del contest nel formato dd-MM-yyyy:");
        String startDate = scanner.nextLine();
        System.out.println("Inserisci la data di fine del contest nel formato dd-MM-yyyy:");
        String endDate = scanner.nextLine();

        try {
            animator.proposeContest(objective, title, dateFormat.parse(startDate), dateFormat.parse(endDate));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This method allows the animator to view the next content to be validated.
     * The animator can then choose whether to authorize or reject the content.
     */
    public void retrieveNextContestContent() {
        animator.viewNextContestContent();
        System.out.println("Contenuto da validare:");
        System.out.println(Animator.getValidatingContent());
        System.out.println("Vuoi autorizzare il contenuto? (S/N)");
        String choice = scanner.nextLine();
        if (choice.equalsIgnoreCase("S")) {
            animator.authorizeContent();
            System.out.println("Contenuto autorizzato.");
        } else if (choice.equalsIgnoreCase("N")) {
            animator.rejectContent();
            System.out.println("Contenuto non autorizzato.");
        } else {
            System.out.println("Scelta non valida.");
        }
    }
}
