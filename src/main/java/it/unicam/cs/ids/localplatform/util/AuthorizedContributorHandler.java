package it.unicam.cs.ids.localplatform.util;

import it.unicam.cs.ids.localplatform.model.AuthorizedContributor;
import it.unicam.cs.ids.localplatform.model.Content;
import it.unicam.cs.ids.localplatform.model.Coordinates;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * This class represents an authorized contributor handler.
 */
public class AuthorizedContributorHandler {
    private AuthorizedContributor authorizedContributor;
    private Scanner scanner = new Scanner(System.in);
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    public AuthorizedContributorHandler(AuthorizedContributor authorizedContributor) {
        this.authorizedContributor = authorizedContributor;
    }

    /**
     * This method allows the authorized contributor to upload a new point of interest to the platform.
     */
    public void uploadPOI() {
        System.out.println("Inserisci il titolo del POI:");
        String title = scanner.nextLine();
        System.out.println("Inserisci la latitudine delle coordinate del POI:");
        String latitude = scanner.nextLine();
        System.out.println("Inserisci la longitudine delle coordinate del POI:");
        String longitude = scanner.nextLine();
        System.out.println("Inserisci la descrizione del POI:");
        String description = scanner.nextLine();

        try {
            authorizedContributor.publishPOI(title, new Coordinates(Double.parseDouble(latitude), Double.parseDouble(longitude)), description);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This method allows the authorized contributor to upload a new general content to the platform.
     */
    public void uploadGeneralContent() {
        System.out.println("Inserisci la descrizione del contenuto:");
        String description = scanner.nextLine();

        Content content = new Content(new Date(), authorizedContributor, description);

        try {
            authorizedContributor.publishGeneralContent(content);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This method allows the authorized contributor to upload a new itinerary to the platform.
     */
    public void uploadItinerary() {
        System.out.println("Inserisci il titolo dell'itinerario:");
        String title = scanner.nextLine();
        // Inserire POIs
        //Itinerary itinerary = new Itinerary(title, new Date(), authorizedContributor, /*POIs*/);
    }


}
