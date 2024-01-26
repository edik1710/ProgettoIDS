package it.unicam.cs.ids.localplatform.util;

import it.unicam.cs.ids.localplatform.MunicipalTerritory;
import it.unicam.cs.ids.localplatform.model.PlatformManager;
import it.unicam.cs.ids.localplatform.model.Roles;
import it.unicam.cs.ids.localplatform.model.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.Scanner;

/**
 * The Controller class is responsible for managing the interactions between the user and the application.
 * This class may include methods to handle user requests, update the application model,
 * and update the view based on changes in the model.
 *
 * @author EliaToma, EdoardoDottori
 * @version 1.0
 */
public class Controller {
    private MunicipalTerritory municipalTerritory;
    private PlatformManager platformManager;
    private Scanner scanner = new Scanner(System.in);
    private User currentUser;

    /**
     * The default constructor.
     */
    public Controller() {
        this.municipalTerritory = new MunicipalTerritory("Camerino");
        this.platformManager = new PlatformManager("Elia", "Toma", "elia.toma@studenti.unicam.it", "12345678", this.municipalTerritory, "cf");
    }

    /**
     * This method initializes the controller.
     */
    public void initialize(){
        System.out.println("Software di valorizzazione del territorio comunale di " + this.municipalTerritory.getMunicipalName());
        System.out.print("Scegli un'azione da eseguire:\n" +
                "1. Crea nuovo utente.\n" +
                "2. Visualizza info sul territorio.\n" +
                "3. Accedi.\n\n" +
                "Digita il numero corrispondente e premi invio per selezionare l'azione da eseguire --> ");

        String input = scanner.nextLine();
        if(!Objects.equals(input, "1") && !Objects.equals(input, "2")){
            System.out.println("Hai inserito un valore non valido devi inserire o 1 o 2, hai inserito: "+ input+ " riprova");
            initialize();
        } else {
            switch (input) {
                case "1":
                    newUser();
                    break;
                case "2":
                    retrieveInfo();
                    break;
                case "3":
                    login();
                    break;
            }
        }
    }

    public void newUser() {
        System.out.print("Inserisci i tuoi dati:\n" + "Nome: ");
        String name = scanner.nextLine();
        System.out.print("Cognome: ");
        String surname = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        System.out.print("Residenza: ");
        String residence = scanner.nextLine();
        System.out.print("Codice fiscale: ");
        String cf = scanner.nextLine();

        Roles role = askForRole();

        User user = new User(name, surname, email, password, new MunicipalTerritory(residence), cf);
        this.platformManager.getPendingUsers().put(user, role);
        System.out.println("Utente inserito correttamente☻.\n In attesa che il manager della piattaforma assegni il ruolo.");
        initialize();
    }

    private Roles askForRole() {
        System.out.print("Seleziona un ruolo da scegliere: "+ "\n"
                + "1. Animator" + "\n"
                + "2. Contributor" + "\n"
                + "3. Curator" + "\n"
                + "4. Platform Manager" + "\n\n"+
                "Digita il numero corrispondente e premi invio per selezionare l'azione da eseguire --> ");
        String role = scanner.nextLine();
        switch (role) {
            case "1":
                return Roles.ANIMATOR;
            case "2":
                return Roles.CONTRIBUTOR;
            case "3":
                return Roles.CURATOR;
            case "4":
                return Roles.PLATFORM_MANAGER;
            default:
                System.out.println("Hai inserito un valore non valido devi inserire o 1 o 2 o 3 o 4, hai inserito: "+ role+ " riprova");
                askForRole();
        }
        return null;
    }

    public void retrieveInfo() {
        System.out.println("Numero di contenuti generali: " + this.municipalTerritory.getGeneralContents().size());
        System.out.println("Numero di punti di interesse: " + this.municipalTerritory.getPOIs().size());
        System.out.println("Numero di itinerari: " + this.municipalTerritory.getItineraries().size());

        System.out.println("Seleziona l'informazione che vuoi vedere "+ "\n"
                + "1. Contenuti Generali" + "\n"
                + "2. Punti d'Interesse" + "\n"
                + "3. Itinerari" + "\n\n"+
                "Digita il numero corrispondente e premi invio per selezionare l'azione da eseguire --> ");
        String info = scanner.nextLine();
        switch (info) {
            case "1":
                displayGeneralContents();
                break;
            case "2":
                displayPOIs();
                break;
            case "3":
                displayItineraries();
                break;
            default:
                System.out.println("Hai inserito un valore non valido devi inserire o 1 o 2 o 3, hai inserito: "+ info+ " riprova");
                retrieveInfo();
        }
    }

    private void displayGeneralContents() {
        this.municipalTerritory.getGeneralContents().toString();
    }

    private void displayPOIs() {
        this.municipalTerritory.getPOIs().toString();
    }

    private void displayItineraries() {
        this.municipalTerritory.getItineraries().toString();
    }

    public void login() {
        System.out.print("Inserisci i tuoi dati:\n" + "Email: ");
        String loginEmail = scanner.nextLine();
        System.out.print("Password: ");
        String loginPassword = scanner.nextLine();
        User user = new User("", "", loginEmail, loginPassword, null, "");

        if (this.municipalTerritory.getUsers().contains(user)) {
            this.currentUser = this.municipalTerritory.getUsers().get(this.municipalTerritory.getUsers().indexOf(user));
        } else {
            System.out.println("Email o password errati, riprova.");
            login();
        }
    }

    /**
     * This method updates the view based on changes in the model.
     */
    public void updateView() { // Forse private nono non credo

    }
}