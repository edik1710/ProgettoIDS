package it.unicam.cs.ids.localplatform.util;

import it.unicam.cs.ids.localplatform.model.Content;
import it.unicam.cs.ids.localplatform.model.Contributor;
import it.unicam.cs.ids.localplatform.model.Coordinates;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * This class represents a contributor handler.
 */
public class ContributorHandler {
    private Contributor contributor;
    private Scanner scanner = new Scanner(System.in);
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    public ContributorHandler(Contributor contributor) {
        this.contributor = contributor;
    }

    /**
     * This method allows the contributor to send a new point of interest to the platform.
     */
    public void sendPOI() {
        System.out.println("Inserisci il titolo del POI:");
        String title = scanner.nextLine();
        System.out.println("Inserisci la latitudine delle coordinate del POI:");
        String latitude = scanner.nextLine();
        System.out.println("Inserisci la longitudine delle coordinate del POI:");
        String longitude = scanner.nextLine();
        System.out.println("Inserisci la descrizione del POI:");
        String description = scanner.nextLine();

        try {
            contributor.submitPOI(title, new Coordinates(Double.parseDouble(latitude), Double.parseDouble(longitude)), description);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This method allows the contributor to send a new general content to the platform.
     */
    public void sendGeneralContent() {
        System.out.println("Inserisci la descrizione del contenuto:");
        String description = scanner.nextLine();

        Content content = new Content(new Date(), contributor, description);

        try {
            contributor.submitGeneralContent(content);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /*
    public void sendItinerary() {
        System.out.println("Inserisci il titolo dell'itinerario:");
        String title = scanner.nextLine();
        System.out.println("Inserisci la descrizione dell'itinerario:");
        String description = scanner.nextLine();

        try {
            contributor.submitItinerary(title, description);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    */
}
