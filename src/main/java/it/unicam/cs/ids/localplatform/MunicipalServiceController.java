package it.unicam.cs.ids.localplatform;

import it.unicam.cs.ids.localplatform.model.Content;
import it.unicam.cs.ids.localplatform.model.Coordinates;
import it.unicam.cs.ids.localplatform.model.Itinerary;
import it.unicam.cs.ids.localplatform.model.POI;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:63342")
@RestController
public class MunicipalServiceController {
    //private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    // Da sostituire con il database <-- l'ha scritto Copilot
    // Da tentare di rimuovere
    private static final Map<String, POI> pois = new HashMap<>();

    private MunicipalTerritory municipalTerritory = new MunicipalTerritory("Camerino");

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

    @GetMapping("/POI/{name}")
    public ResponseEntity<Object> getPOI(@PathVariable("name") String name) {
        return new ResponseEntity<>(pois.get(name), HttpStatus.OK);
    }

    @PostMapping("/AggiungiPOI")
    public ResponseEntity<String> addPOI(@RequestBody POI POI) {
        if (!this.municipalTerritory.getPOIs().containsKey(POI.getCoordinates())) {
            this.municipalTerritory.addPOI(POI);
            return new ResponseEntity<>("Punto d'Interesse aggiunto correttamente", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Punto d'Interesse già Esistente", HttpStatus.BAD_REQUEST);
        }

        /*
        if (!pois.containsKey(POI.getTitle())) {
            pois.put(POI.getTitle(), POI);
            return new ResponseEntity<>("Punto d'Interesse aggiunto correttamente", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Punto d'Interesse già Esistente", HttpStatus.BAD_REQUEST);
        }
        */
    }

    @DeleteMapping("/POI/{name}")
    public ResponseEntity<Object> deletePOI(@PathVariable("name") String name) {
        if (pois.containsKey(name)) {
            pois.remove(name);
            return new ResponseEntity<>("Il Punto d'interesse è stato cancellatto correttamente", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Il Punto d'interesse non esiste", HttpStatus.BAD_REQUEST);
        }
    }

   /* @RequestMapping (value = "/POI{name}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updatePOI(@PathVariable("name")String name, @RequestBody POI POI){
        if(pois.containsKey(name)){
            pois.get(name).setName(POI.getMunicipalName());
            return new ResponseEntity<>("Punto d'Interesse Aggiornato correttamente", HttpStatus.OK);
        }else {
            throw new POIExecption (); //<- Da Implemetare
        }
    }
*/
}
