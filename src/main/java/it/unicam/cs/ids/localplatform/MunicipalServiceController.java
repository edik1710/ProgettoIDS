package it.unicam.cs.ids.localplatform;

import it.unicam.cs.ids.localplatform.model.*;
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

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    private User currentUser;

    public User getCurrentUser() {
        return currentUser;
    }

    @PostMapping("/Login")
    public ResponseEntity<Object> getLoginInfo(@RequestBody String[] array) {
        MunicipalTerritory mt = new MunicipalTerritory(array[4]);
        this.currentUser = new User(array[0], array[1], array[2], array[3], mt, array[5]);

        //System.out.println("Mannaggia a quelli della prima fila" + currentUser);
        return new ResponseEntity<>("User saved", HttpStatus.OK);
    }

    @RequestMapping("/POIs")
    public ResponseEntity<Object> getPOIs() {
        /*
        Coordinates coordinates = new Coordinates(43.133333, 13.066667);
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

        /*
        Contributor contributor = new Contributor("Mario", "Rossi", "email", "password", municipalTerritory, "codiceFiscale");
        Coordinates coordinates = new Coordinates(43.133333, 13.066667);
        POI p1 = new POI("piazza del comune", new Date(), contributor, coordinates, "Piazza del Comune di Camerino");
        POI p2 = new POI("piazza del comune", new Date(), contributor, coordinates, "Piazza del Comune di Camerino");
        List<POI> pois = new ArrayList<>();
        pois.add(p1);
        pois.add(p2);
        Itinerary i1 = new Itinerary("Itinerario 1", new Date(), contributor, pois, "Itinerario 1");
        this.municipalTerritory.getItineraries().add(i1);

        Itinerary i2 = new Itinerary("Itinerario 2", new Date(), contributor, pois, "Itinerario 2");
        this.municipalTerritory.getItineraries().add(i2);
        */

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

        /*
        Contributor contributor = new Contributor("Mario", "Rossi", "email", "password", municipalTerritory, "codiceFiscale");
        Content c1 = new Content(new Date(), contributor, "Contenuto 1");
        Content c2 = new Content(new Date(), contributor, "Contenuto 2");
        this.municipalTerritory.getGeneralContents().add(c1);
        this.municipalTerritory.getGeneralContents().add(c2);
        */

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

        /* mammamia
        List<POI> pois = new ArrayList<>();
        // TODO : Lista placeholder
        POI poi1 = new POI("piazza del comune", new Date(), currentUser, new Coordinates(43.133333, 13.066667), "Piazza del Comune di Camerino");
        POI poi2 = new POI("Pievetorina", new Date(), currentUser, new Coordinates(43, 13), "Pievetorina");
        pois.add(poi1);
        pois.add(poi2);
        */

        List<POI> POIs = new ArrayList<>();
        for(Coordinates c : tempPOIList)
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
    public ResponseEntity<String> addGeneralContent(@RequestBody String text) {
        Content content = new Content(new Date(), currentUser, text);

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
        if(!this.municipalTerritory.getPOIs().containsKey(coordinates))
            return new ResponseEntity<>("Il POI non esiste", HttpStatus.BAD_REQUEST);
        else {
            this.municipalTerritory.getPOIs().get(coordinates).setTitle(array[2]);
            return new ResponseEntity<>("POI aggiornato", HttpStatus.OK);
        }
    }

    // TODO : changeItinerary

    @PostMapping("/ModificaContenutoGenerale")
    public ResponseEntity<Object> changeGeneralContent(@RequestBody String[] array) {
        String currentText = '"' + array[0] + '"';
        Content content = new Content(new Date(), currentUser, currentText);
        if(!this.municipalTerritory.getGeneralContents().contains(content))
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

            return new ResponseEntity<>("Il Punto d'interesse è stato cancellatto correttamente", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Il Punto d'interesse non esiste", HttpStatus.BAD_REQUEST);
        }
    }

    // TODO : deleteItinerary

    // TODO : deleteGeneralContent
}