package it.unicam.cs.ids.localplatform;

import it.unicam.cs.ids.localplatform.model.*;
import it.unicam.cs.ids.localplatform.web.UserListRepository;
import it.unicam.cs.ids.localplatform.web.UserTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@CrossOrigin(origins = "http://localhost:63342")
@RestController
public class MunicipalServiceController {
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private MunicipalTerritory municipalTerritory = new MunicipalTerritory("Camerino");

    private final UserListRepository userRepository;

    @Autowired
    public MunicipalServiceController(UserListRepository userRepository) {
        this.userRepository = userRepository;
        UserTable userTable1 = new UserTable();
        userTable1.setName("Edoardo");
        userTable1.setSurname("Dottori");
        userTable1.setEmail("edoardo.dottori@progetto.ids");
        userTable1.setPassword("tottigol");
        userTable1.setResidence("Camerino");
        userTable1.setCf("cfcfcfcf456cf4f");
        userTable1.setRole("Platform_Manager");
        userTable1.setPending(false);
        this.userRepository.save(userTable1);

        UserTable userTable2 = new UserTable();
        userTable2.setName("Elia");
        userTable2.setSurname("Toma");
        userTable2.setEmail("elia.toma@progetto.ids");
        userTable2.setPassword("brodoCovid");
        userTable2.setResidence("Camerino");
        userTable2.setCf("tomatoma002t2tte");
        userTable2.setRole("Platform_Manager");
        userTable2.setPending(false);
        this.userRepository.save(userTable2);
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    private User currentUser;

    public User getCurrentUser() {
        return currentUser;
    }

    @PostMapping("/Register")
    public ResponseEntity<Object> getLoginInfo(@RequestBody String[] array) {
        // Le due righe seguenti si dovrebbero togliere
        //MunicipalTerritory mt = new MunicipalTerritory(array[4]);
        //this.currentUser = new User(array[0], array[1], array[2], array[3], mt, array[5]);

        UserTable userTable = new UserTable();
        userTable.setName(array[0]);
        userTable.setSurname(array[1]);
        userTable.setEmail(array[2]);
        userTable.setPassword(array[3]);
        userTable.setResidence(array[4]);
        userTable.setCf(array[5]);
        if(!userTable.getResidence().equals("Camerino"))
            userTable.setRole(array[6]);
        else
            userTable.setRole("Turista");
        userTable.setPending(true);
        if (!userRepository.existsById(userTable.getEmail())) {
            userRepository.save(userTable);
            return new ResponseEntity<>("User saved", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User already exists", HttpStatus.BAD_REQUEST);
        }

        //System.out.println("Mannaggia a quelli della prima fila" + currentUser);
        //return new ResponseEntity<>("User saved", HttpStatus.OK);
    }

    @PostMapping("/Login")
    public ResponseEntity<Object> accessPlatform(@RequestBody String[] array) {
        UserTable userTable = userRepository.findById(array[0]).orElse(null);
        if (userTable != null && userTable.getPassword().equals(array[1])) {
            this.currentUser = new User(userTable.getName(), userTable.getSurname(), userTable.getEmail(), userTable.getPassword(), new MunicipalTerritory(userTable.getResidence()), userTable.getCf());
            return new ResponseEntity<>("User logged in", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User not found", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping("/Role")
    public ResponseEntity<Object> getRole() {
        String email = this.currentUser.getEmail();
        UserTable userTable = userRepository.findById(email).orElse(null);
        return new ResponseEntity<>(userTable.getRole(), HttpStatus.OK);
    }

    @RequestMapping("/PendingUserList")
    public ResponseEntity<Object> getPendingUserList() {
        List<UserTable> allUsers = new ArrayList<>();
        allUsers = (ArrayList<UserTable>) this.userRepository.findAll();
        List<UserTable> pendingUsers = new ArrayList<>();
        for(UserTable u : allUsers)
            if(u.isPending())
                pendingUsers.add(u);
        String[] pendingUsersArray = new String[pendingUsers.size()];
        for(UserTable u : pendingUsers)
            pendingUsersArray[pendingUsers.indexOf(u)] = u.toString();
        return new ResponseEntity<>(pendingUsersArray, HttpStatus.OK);
    }

    @RequestMapping("/ContributorsAndTouristsList")
    public ResponseEntity<Object> getContributorsAndTouristsList() {
        List<UserTable> allUsers = new ArrayList<>();
        allUsers = (ArrayList<UserTable>) this.userRepository.findAll();
        List<UserTable> contributorsAndTouristsUsers = new ArrayList<>();
        for(UserTable u : allUsers)
            if(u.getRole().equals("Contributor") || u.getRole().equals("Turista"))
                contributorsAndTouristsUsers.add(u);
        String[] contributorsAndTouristsUsersArray = new String[contributorsAndTouristsUsers.size()];
        for(UserTable u : contributorsAndTouristsUsers)
            contributorsAndTouristsUsersArray[contributorsAndTouristsUsers.indexOf(u)] = u.toString();
        return new ResponseEntity<>(contributorsAndTouristsUsersArray, HttpStatus.OK);
    }

    @PostMapping("/AcceptUser")
    public ResponseEntity<Object> authorizeUser(@RequestBody String[] array) {
        UserTable userTable = userRepository.findById(array[0]).orElse(null);
        if (userTable != null) {
            userTable.setPending(false);
            userRepository.save(userTable);
            return new ResponseEntity<>("User authorized", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User not found", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping ("/autorized")
    public ResponseEntity<Object> autorized(@RequestBody String[] array){
        UserTable userTable = userRepository.findById(array[0]).orElse(null);
        if (userTable != null) {
            if(userTable.getRole().equals("Contributor"))
                userTable.setRole("Authorized_Contributor");
            if(userTable.getRole().equals("Turista"))
                userTable.setRole("Authorized_Tourist");
            userRepository.save(userTable);
            return new ResponseEntity<>("User authorized", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User not found", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/DeleteUser")
    public ResponseEntity<Object> deleteUser(@RequestBody String[] array) {
        UserTable userTable = userRepository.findById(array[0]).orElse(null);
        if (userTable != null) {
            userRepository.delete(userTable);
            return new ResponseEntity<>("User deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User not found", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping("/POIs")
    public ResponseEntity<Object> getPOIs() {
        /*
        Coordinates coordinates = new Coordinates(43, 13);
        Contributor contributor = new Contributor("Mario", "Rossi", "email", "password", municipalTerritory, "codiceFiscale");
        POI p1 = new POI("piazza del comune", new Date(), contributor, coordinates, "Piazza del Comune di Camerino");
        this.municipalTerritory.getPOIs().put(coordinates, p1);

        Coordinates coordinates1 = new Coordinates(52, 75);
        POI p2 = new POI("piazza del comune", new Date(), contributor, coordinates1, "Piazza del Comune di Camerino");
        this.municipalTerritory.getPOIs().put(coordinates1, p2);
        */

        // Recupera la mappa dei POI da MunicipalTerritory
        Map<Coordinates, POI> poiMap = municipalTerritory.getPOIs();

        // Crea una lista di POI dalla mappa
        List<POI> poiList = new ArrayList<>(poiMap.values());
        String[] poiListStringArray = new String[poiList.size()];
        for (POI poi : poiList) {
            poiListStringArray[poiList.indexOf(poi)] = poi.toString();
        }

        //System.out.println(currentUser);

        // Restituisce la lista di POI
        return new ResponseEntity<>(poiListStringArray, HttpStatus.OK);
    }

    @RequestMapping("/Itineraries")
    public ResponseEntity<Object> getItineraries() {
        List<Itinerary> itineraries = municipalTerritory.getItineraries();

        Itinerary[] itinerariesArray = itineraries.toArray(new Itinerary[0]);

        String[] itinerariesStringArray = new String[itinerariesArray.length];
        for (int i = 0; i < itinerariesArray.length; i++) {
            itinerariesStringArray[i] = itinerariesArray[i].toString();
        }

        return new ResponseEntity<>(itinerariesStringArray, HttpStatus.OK);
    }

    @RequestMapping("/GeneralContents")
    public ResponseEntity<Object> getGeneralContents() {
        List<Content> generalContents = municipalTerritory.getGeneralContents();
        Content[] generalContentsArray = generalContents.toArray(new Content[0]);
        String[] generalContentsStringArray = new String[generalContentsArray.length];
        for (int i = 0; i < generalContentsArray.length; i++) {
            generalContentsStringArray[i] = generalContentsArray[i].toString();
        }

        return new ResponseEntity<>(generalContentsStringArray, HttpStatus.OK);
    }

    @RequestMapping("/Contests")
    public ResponseEntity<Object> getContests() {

        /*
        Contributor contributor = new Contributor("Mario", "Rossi", "email", "password", municipalTerritory, "codiceFiscale");
        Date startDate = new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24); // 1 day from now
        Date endDate = new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 48); // 2 days from now
        Contest c1 = new Contest("Objective", "Title", startDate, endDate);
        this.municipalTerritory.getContests().add(c1);
        */

        List<Contest> contests = municipalTerritory.getContests();
        Contest[] contestsArray = contests.toArray(new Contest[0]);
        String[] contestsStringArray = new String[contestsArray.length];
        for (int i = 0; i < contestsArray.length; i++) {
            contestsStringArray[i] = contestsArray[i].toString();
        }

        return new ResponseEntity<>(contestsStringArray, HttpStatus.OK);
    }

    // FUNZIONA
    @PostMapping("/AggiungiPOI")
    public ResponseEntity<String> addPOI(@RequestBody String[] array) {

        //Contributor contributor = new Contributor("Eracleonte", "Villa", "email", "password", municipalTerritory, "codiceFiscale");
        //Coordinates c = new Coordinates(43, 13);

        Coordinates coordinates = new Coordinates(Double.parseDouble(array[2]), Double.parseDouble(array[3]));

        POI POI = new POI(array[0], new Date(), currentUser, coordinates, array[1]);

        if (!this.municipalTerritory.getPOIs().containsKey(POI.getCoordinates())) {
            this.municipalTerritory.addPOI(POI);
            return new ResponseEntity<>("Punto d'Interesse aggiunto correttamente", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Punto d'Interesse già Esistente", HttpStatus.BAD_REQUEST);
        }
    }

    private static List<Coordinates> tempPOIList = new ArrayList<>();

    @PostMapping("/AggiungiPOIAListaItinerario")
    public ResponseEntity<Object> addPOIToTempList(@RequestBody String[] array) {
        Coordinates coordinates = new Coordinates(Double.parseDouble(array[0]), Double.parseDouble(array[1]));
        tempPOIList.add(coordinates);
        return new ResponseEntity<>("Punto d'Interesse aggiunto correttamente", HttpStatus.OK);
    }

    // FUNZIONA
    @PostMapping("/AggiungiItinerario")
    public ResponseEntity<String> addItinerary(@RequestBody String[] array) {

        /* mammamia<
        List<POI> pois = new ArrayList<>();
        POI poi1 = new POI("piazza del comune", new Date(), currentUser, new Coordinates(43.133333, 13.066667), "Piazza del Comune di Camerino");
        POI poi2 = new POI("Pievetorina", new Date(), currentUser, new Coordinates(43, 13), "Pievetorina");
        pois.add(poi1);
        pois.add(poi2);
        */

        List<POI> POIs = new ArrayList<>();
        for (Coordinates c : tempPOIList)
            POIs.add(this.municipalTerritory.getPOIs().get(c));

        Itinerary itinerary = new Itinerary(array[0], new Date(), currentUser, POIs, array[1]);

        if (!this.municipalTerritory.getItineraries().contains(itinerary)) {
            this.municipalTerritory.addItinerary(itinerary);
            tempPOIList.clear();
            return new ResponseEntity<>("Itinerario aggiunto correttamente", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Itinerario già Esistente", HttpStatus.BAD_REQUEST);
        }
    }

    // FUNZIONA
    @PostMapping("/AggiungiContenutoGenerale")
    public ResponseEntity<Object> addGeneralContent(@RequestBody String[] text) {
        Content content = new Content(new Date(), currentUser, text[0]);

        if (!this.municipalTerritory.getGeneralContents().contains(content)) {
            this.municipalTerritory.addGeneralContent(content);
            return new ResponseEntity<>("Contenuto aggiunto correttamente", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Contenuto già Esistente", HttpStatus.BAD_REQUEST);
        }
    }

    // Ho aggiunto throws ParseException per non dover gestire l'eccezione
    // e perché si legge meglio l'errore nella console
    @PostMapping("/AggiungiContest")
    public ResponseEntity<Object> addContest(@RequestBody String[] array) throws ParseException {
        Date startDate = dateFormat.parse(array[2]);
        Date endDate = dateFormat.parse(array[3]);
        Contest contest = new Contest(array[0], array[1], startDate, endDate);

        if (!this.municipalTerritory.getContests().contains(contest)) {
            this.municipalTerritory.addContest(contest);
            return new ResponseEntity<>("Concorso aggiunto correttamente", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Concorso già Esistente", HttpStatus.BAD_REQUEST);
        }
    }

    // FUNZIONA
    @PostMapping("/ModificaPOI")
    public ResponseEntity<Object> changePOI(@RequestBody String[] array) {
        Coordinates coordinates = new Coordinates(Double.parseDouble(array[0]), Double.parseDouble(array[1]));
        if (!this.municipalTerritory.getPOIs().containsKey(coordinates))
            return new ResponseEntity<>("Il POI non esiste", HttpStatus.BAD_REQUEST);
        else {
            this.municipalTerritory.getPOIs().get(coordinates).setTitle(array[2]);
            return new ResponseEntity<>("POI aggiornato", HttpStatus.OK);
        }
    }

    // FUNZIONA
    @PostMapping("/ModificaItinerario")
    public ResponseEntity<Object> changeItinerary(@RequestBody String[] array) {
        //String currentTitle = '"' + array[0] + '"';
        POI poi1 = new POI("", new Date(), currentUser, new Coordinates(1, 1), "");
        POI poi2 = new POI("", new Date(), currentUser, new Coordinates(2, 2), "");
        List<POI> pois = new ArrayList<>();
        pois.add(poi1);
        pois.add(poi2);
        Itinerary itinerary = new Itinerary(array[0], new Date(), currentUser, pois, "");
        if (!this.municipalTerritory.getItineraries().contains(itinerary))
            return new ResponseEntity<>(Collections.singletonMap("message", "L'itinerario non esiste"), HttpStatus.BAD_REQUEST);
        else {
            this.municipalTerritory.getItineraries().get(this.municipalTerritory.getItineraries().indexOf(itinerary)).setTitle(array[1]);
            return new ResponseEntity<>(Collections.singletonMap("message", "Itinerario aggiornato"), HttpStatus.OK);
        }
    }

    // FUNZIONA
    @PostMapping("/ModificaContenutoGenerale")
    public ResponseEntity<Object> changeGeneralContent(@RequestBody String[] array) {
        //String currentText = '"' + array[0] + '"';
        Content content = new Content(new Date(), currentUser, array[0]);
        if (!this.municipalTerritory.getGeneralContents().contains(content))
            return new ResponseEntity<>(Collections.singletonMap("message", "Il contenuto generale non esiste"), HttpStatus.BAD_REQUEST);
        else {
            this.municipalTerritory.getGeneralContents().get(this.municipalTerritory.getGeneralContents().indexOf(content)).setText(array[1]);
            return new ResponseEntity<>(Collections.singletonMap("message", "Contenuto generale aggiornato"), HttpStatus.OK);
        }
    }

    // FUNZIONA
    @DeleteMapping("/CancellaPOI")
    public ResponseEntity<Object> deletePOI(@RequestBody String[] array) {
        Coordinates coordinates = new Coordinates(Double.parseDouble(array[0]), Double.parseDouble(array[1]));

        if (this.municipalTerritory.getPOIs().containsKey(coordinates)) {
            this.municipalTerritory.getPOIs().remove(coordinates);

            System.out.println(this.municipalTerritory.getPOIs().size());

            return new ResponseEntity<>("Il Punto d'interesse è stato cancellato correttamente", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Il Punto d'interesse non esiste", HttpStatus.BAD_REQUEST);
        }
    }

    // FUNZIONA
    @DeleteMapping("/CancellaItinerario")
    public ResponseEntity<Object> deleteItinerary(@RequestBody String[] title) {
        POI poi1 = new POI("", new Date(), currentUser, new Coordinates(1, 1), "");
        POI poi2 = new POI("", new Date(), currentUser, new Coordinates(2, 2), "");
        List<POI> pois = new ArrayList<>();
        pois.add(poi1);
        pois.add(poi2);

        //System.out.println(title[0]);

        Itinerary itinerary = new Itinerary(title[0], new Date(), currentUser, pois, "");
        if (!this.municipalTerritory.getItineraries().contains(itinerary))
            return new ResponseEntity<>(Collections.singletonMap("message", "L'itinerario non esiste"), HttpStatus.BAD_REQUEST);
        else {
            this.municipalTerritory.getItineraries().remove(itinerary);

            //System.out.println(this.municipalTerritory.getItineraries().size());

            return new ResponseEntity<>(Collections.singletonMap("message", "Itinerario cancellato"), HttpStatus.OK);
        }
    }

    // FUNZIONA
    @DeleteMapping("/CancellaContenutoGenerale")
    public ResponseEntity<Object> deleteGeneralContent(@RequestBody String[] text) {
        //String currentText = '"' + text[0] + '"';
        Content content = new Content(new Date(), currentUser, text[0]);
        if (!this.municipalTerritory.getGeneralContents().contains(content))
            return new ResponseEntity<>(Collections.singletonMap("message", "Il contenuto generale non esiste"), HttpStatus.BAD_REQUEST);
        else {
            this.municipalTerritory.getGeneralContents().remove(content);
            return new ResponseEntity<>(Collections.singletonMap("message", "Contenuto generale cancellato"), HttpStatus.OK);
        }
    }
}