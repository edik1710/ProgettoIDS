package it.unicam.cs.ids.localplatform.util;

import it.unicam.cs.ids.localplatform.MunicipalTerritory;
import it.unicam.cs.ids.localplatform.model.*;

import java.text.SimpleDateFormat;
import java.util.Objects;
import java.util.OptionalInt;
import java.util.Scanner;
import java.util.stream.IntStream;

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
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

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
    public void initialize() {
        System.out.println("Software di valorizzazione del territorio comunale di " + this.municipalTerritory.getMunicipalName());
        System.out.print("Scegli un'azione da eseguire:\n" + "1. Crea nuovo utente.\n" + "2. Visualizza info sul territorio.\n" + "3. Accedi.\n\n" + "Digita il numero corrispondente e premi invio per selezionare l'azione da eseguire --> ");

        String input = scanner.nextLine();
        if (!Objects.equals(input, "1") && !Objects.equals(input, "2") && !Objects.equals(input, "3")) {
            System.out.println("Hai inserito un valore non valido devi inserire o 1 o 2 o 3, hai inserito: " + input + " riprova");
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

    private void newUser() {
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

        if(residence.equals(municipalTerritory.getMunicipalName())) {
            System.out.println("Sei un cittadino di " + municipalTerritory.getMunicipalName() + " e puoi iscriverti alla piattaforma.");
            Roles role = askForRole();
            User user = new User(name, surname, email, password, new MunicipalTerritory(residence), cf);
            this.platformManager.getPendingUsers().put(user, role);
            System.out.println("Utente inserito correttamente☻.\nIn attesa che il manager della piattaforma assegni il ruolo.");
            this.platformManager.assignRoles();
        } else {
            System.out.println("Non sei un cittadino di " + municipalTerritory.getMunicipalName() + " sarai considerato turista");
            showTouristOptions();
        }
        initialize();
    }

    private Roles askForRole() {
        System.out.print("Seleziona un ruolo da scegliere: " + "\n" + "1. Animator" + "\n" + "2. Contributor" + "\n" + "3. Curator" + "\n" + "4. Platform Manager" + "\n\n" + "Digita il numero corrispondente e premi invio per selezionare l'azione da eseguire --> ");
        String role = scanner.nextLine();
        switch (role) {
            case "1":
                return Roles.ANIMATOR;
            case "2":
                return Roles.CONTRIBUTOR;
            case "3":
                return Roles.CURATOR;
            default:
                System.out.println("Hai inserito un valore non valido devi inserire o 1 o 2 o 3, hai inserito: " + role + " riprova");
                askForRole();
        }
        return null;
    }

    private void retrieveInfo() {
        System.out.println("Numero di contenuti generali: " + this.municipalTerritory.getGeneralContents().size());
        System.out.println("Numero di punti di interesse: " + this.municipalTerritory.getPOIs().size());
        System.out.println("Numero di itinerari: " + this.municipalTerritory.getItineraries().size());
        System.out.println("Numero di concorsi: " + this.municipalTerritory.getContests().size());

        System.out.print("\nSeleziona l'informazione che vuoi vedere " + "\n" + "1. Contenuti Generali" + "\n" + "2. Punti d'Interesse" + "\n" + "3. Itinerari" + "\n" + "4. Concorsi" + "\n\n" + "Digita il numero corrispondente e premi invio per selezionare l'azione da eseguire --> ");
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
            case "4":
                displayContests();
                break;
            default:
                System.out.println("Hai inserito un valore non valido devi inserire o 1 o 2 o 3, hai inserito: " + info + " riprova");
                retrieveInfo();
        }
    }

    private void displayGeneralContents() {
        if (this.municipalTerritory.getGeneralContents().isEmpty()) {
            System.out.println("Non ci sono contenuti generali da visualizzare.");
            initialize();
        } else {
            for (Content content : this.municipalTerritory.getGeneralContents())
                System.out.println(content);
        }
    }

    private void displayPOIs() {
        if (this.municipalTerritory.getPOIs().isEmpty()) {
            System.out.println("Non ci sono punti di interesse da visualizzare.");
            initialize();
        } else {
            for (POI poi : this.municipalTerritory.getPOIs().values())
                System.out.println(poi);
        }
    }

    private void displayItineraries() {
        if (this.municipalTerritory.getItineraries().isEmpty()) {
            System.out.println("Non ci sono itinerari da visualizzare.");
            initialize();
        } else {
            for (Itinerary itinerary : this.municipalTerritory.getItineraries())
                System.out.println(itinerary);
        }
    }

    private void displayContests() {
        if (this.municipalTerritory.getContests().isEmpty()) {
            System.out.println("Non ci sono concorsi da visualizzare.");
            initialize();
        } else {
            for (Contest contest : this.municipalTerritory.getContests())
                System.out.println(contest);
        }
    }

    private void login() {
        System.out.print("Inserisci i tuoi dati:\n" + "Email: ");
        String loginEmail = scanner.nextLine();
        System.out.print("Password: ");
        String loginPassword = scanner.nextLine();
        User user = new User("", "", loginEmail, loginPassword, null, "");

        if (isUserInList(user)) {
            this.currentUser = this.municipalTerritory.getUsers().get(getUserIndex(user).getAsInt());
            displayUserInfo();
        } else {
            System.out.println("Email o password errati, riprova.");
            login();
        }
    }

    private boolean isUserInList(User userToFind) {
        return this.municipalTerritory.getUsers().stream().anyMatch(user -> user.getEmail().equals(userToFind.getEmail()) && user.getPassword().equals(userToFind.getPassword()));
    }

    private OptionalInt getUserIndex(User userToFind) {
        return IntStream.range(0, this.municipalTerritory.getUsers().size()).filter(i -> this.municipalTerritory.getUsers().get(i).getEmail().equals(userToFind.getEmail()) && this.municipalTerritory.getUsers().get(i).getPassword().equals(userToFind.getPassword())).findFirst();
    }

    private void displayUserInfo() {
        System.out.println("Informazioni utente: ");
        System.out.println("Nome: " + this.currentUser.getName());
        System.out.println("Cognome: " + this.currentUser.getSurname());
        System.out.println("Email: " + this.currentUser.getEmail());
        System.out.println("Residenza: " + this.currentUser.getResidence());
        System.out.println("Codice fiscale: " + this.currentUser.getCf());
        String role = this.currentUser.getClass().getSimpleName();
        System.out.println("Il tuo ruolo è: " + role);
        showRoleOptions(role);
    }

    private void showRoleOptions(String role) {
        switch (role) {
            case "Animator":
                showAnimatorOptions();
                break;
            case "Contributor":
                showContributorOptions();
                break;
            case "Curator":
                showCuratorOptions();
                break;
            case "AuthorizedContributor":
                showAuthorizedContributorOptions();
                break;
            case "AuthorizedTourist":
                showAuthorizedTouristOptions();
                break;
            case "Tourist":
                showTouristOptions();
                break;
        }
    }

    private void showAnimatorOptions() {
        Animator animator = (Animator) this.currentUser;
        AnimatorHandler animatorHandler = new AnimatorHandler(animator);

        System.out.println("Seleziona l'azione da eseguire: " + "\n" + "1. Proporre un concorso" + "\n" + "2. Visualizzare il prossimo contenuto da validare" + "\n" + "Digita il numero corrispondente e premi invio per selezionare l'azione da eseguire --> ");
        String action = scanner.nextLine();
        switch (action) {
            case "1":
                animatorHandler.createContest();
                break;
            case "2":
                animatorHandler.retrieveNextContestContent();
                break;
            default:
                System.out.println("Hai inserito un valore non valido devi inserire o 1 o 2, hai inserito: " + action + " riprova");
                showAnimatorOptions();
        }
    }

    private void showContributorOptions() {
        Contributor contributor = (Contributor) this.currentUser;
        ContributorHandler contributorHandler = new ContributorHandler(contributor);

        System.out.println("Seleziona l'azione da eseguire: " + "\n" + "1. Proporre un contenuto generale" + "\n" + "2. Proporre un punto di interesse" + "\n" + "3. Proporre un itinerario" + "\n" + "Digita il numero corrispondente e premi invio per selezionare l'azione da eseguire --> ");
        String action = scanner.nextLine();
        switch (action) {
            case "1":
                contributorHandler.sendGeneralContent();
                break;
            case "2":
                contributorHandler.sendPOI();
                break;
            case "3":
                //contributorHandler.sendItinerary();
                break;
            default:
                System.out.println("Hai inserito un valore non valido devi inserire o 1 o 2 o 3, hai inserito: " + action + " riprova");
                showContributorOptions();
        }
    }

    private void showCuratorOptions() {
        Curator curator = (Curator) this.currentUser;
        CuratorHandler curatorHandler = new CuratorHandler(curator);

        System.out.println("Seleziona l'azione da eseguire: " + "\n" + "1. Visualizzare il prossimo comando da validare" + "\n" + "2. Rimuovere i contenuti segnalati" + "\n" + "Digita il numero corrispondente e premi invio per selezionare l'azione da eseguire --> ");
        String action = scanner.nextLine();
        switch (action) {
            case "1":
                curatorHandler.retrieveNextCommandToValidate();
                break;
            case "2":
                curatorHandler.removeReportedContents();
                break;
            default:
                System.out.println("Hai inserito un valore non valido devi inserire o 1 o 2, hai inserito: " + action + " riprova");
                showCuratorOptions();
        }
    }

    private void showAuthorizedContributorOptions() {
        AuthorizedContributor authorizedContributor = (AuthorizedContributor) this.currentUser;
        AuthorizedContributorHandler authorizedContributorHandler = new AuthorizedContributorHandler(authorizedContributor);

        System.out.println("Seleziona l'azione da eseguire: " + "\n" + "1. Carica un contenuto generale" + "\n" + "2. Carica un punto di interesse" + "\n" + "3. Carica un itinerario" + "\n" + "Digita il numero corrispondente e premi invio per selezionare l'azione da eseguire --> ");
        String action = scanner.nextLine();
        switch (action) {
            case "1":
                authorizedContributorHandler.uploadGeneralContent();
                break;
            case "2":
                authorizedContributorHandler.uploadPOI();
                break;
            case "3":
                //authorizedContributorHandler.uploadItinerary();
                break;
            default:
                System.out.println("Hai inserito un valore non valido devi inserire o 1 o 2 o 3, hai inserito: " + action + " riprova");
                showAuthorizedContributorOptions();
        }
    }

    private void showAuthorizedTouristOptions() {
        AuthorizedTourist authorizedTourist = (AuthorizedTourist) this.currentUser;
        AuthorizedTouristHandler authorizedTouristHandler = new AuthorizedTouristHandler(authorizedTourist);

        System.out.println("Seleziona l'azione da eseguire: " + "\n" + "1. Salva un contenuto" + "\n" + "2. Rimuovi un contenuto" + "\n" + "Digita il numero corrispondente e premi invio per selezionare l'azione da eseguire --> ");
        String action = scanner.nextLine();
        switch (action) {
            case "1":
                authorizedTouristHandler.saveInfo();
                break;
            case "2":
                authorizedTouristHandler.removeInfo();
                break;
            default:
                System.out.println("Hai inserito un valore non valido devi inserire o 1 o 2, hai inserito: " + action + " riprova");
                showAuthorizedTouristOptions();
        }
    }

    private void showTouristOptions() { // Sistemare -> ci ho provato
        Tourist tourist = (Tourist) this.currentUser;
        TouristHandler touristHandler = new TouristHandler(tourist);

        System.out.print("Seleziona l'azione da eseguire: " + "\n" + "1. Segnala un contenuto" + "\n" +
                "Digita il numero corrispondente e premi invio per selezionare l'azione da eseguire --> ");
        String action = scanner.nextLine();
        if (action.equals("1")) {
            touristHandler.reportContent();
        } else {
            System.out.println("Hai inserito un valore non valido devi inserire 1, hai inserito: " + action + " riprova");
            showTouristOptions();
        }
    }
}