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

    public TouristHandler(Tourist tourist) {
        this.tourist = tourist;
    }

    /*
    public void reportContent() {

    }
    */
}
