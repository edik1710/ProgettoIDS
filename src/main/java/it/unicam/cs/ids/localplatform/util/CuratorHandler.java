package it.unicam.cs.ids.localplatform.util;

import it.unicam.cs.ids.localplatform.model.Curator;

import java.text.SimpleDateFormat;
import java.util.Scanner;

/**
 * This class represents a curator handler.
 */
public class CuratorHandler extends AuthorizedContributorHandler {
    private Curator curator;
    private Scanner scanner = new Scanner(System.in);
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    public CuratorHandler(Curator curator) {
        super(curator);
        this.curator = curator;
    }

    /**
     * This method allows the curator to view the next command to be validated.
     */
    public void retrieveNextCommandToValidate() {
        curator.viewNextCommand();
        System.out.println("Comando da validare:");
        System.out.println(Curator.getValidatingCommand());
        System.out.println("Vuoi autorizzare il comando? (S/N)");
        String choice = scanner.nextLine();
        if (choice.equalsIgnoreCase("S")) {
            curator.authorizeCommandExecution();
            System.out.println("Comando autorizzato.");
        } else if (choice.equalsIgnoreCase("N")) {
            curator.rejectCommandExecution();
            System.out.println("Comando non autorizzato.");
        } else {
            System.out.println("Scelta non valida.");
        }
    }

    /**
     * This method allows the curator to remove the reported contents.
     */
    public void removeReportedContents() {
        curator.removeReportedContents();
        System.out.println("Contenuti rimossi.");
    }
}
