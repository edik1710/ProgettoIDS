package it.unicam.cs.ids.localplatform.util;

import it.unicam.cs.ids.localplatform.model.Tourist;

import java.text.SimpleDateFormat;
import java.util.Scanner;

/**
 * This class represents a tourist handler.
 */
public class TouristHandler {
    private Tourist tourist;
    private Scanner scanner = new Scanner(System.in);
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    private int counter = 0;

    public TouristHandler(Tourist tourist) {
        this.tourist = tourist;
    }

    /*
    Il campo curatorHandler non viene mai inizializzato, quindi è sempre null.
    Non credo sia concettualmente corretto inserire un handler all'interno di un altro handler.
     */
    /*
    private CuratorHandler curatorHandler;
    public void reportContent() {
        System.out.println("Inserisci l'ID del contenuto da segnalare:");
        String id = scanner.nextLine();
        System.out.println("Inserisci il motivo della segnalazione:");
        String reason = scanner.nextLine();
        counter ++;
        if (counter == 10) {
            curatorHandler.removeReportedContents();
        }
    }
    */
}
