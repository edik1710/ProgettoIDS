package it.unicam.cs.ids.localplatform;

import it.unicam.cs.ids.localplatform.model.Coordinates;
import it.unicam.cs.ids.localplatform.model.POI;
import it.unicam.cs.ids.localplatform.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:63342")
@RestController
public class POIController {
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    private static final Map<String, POI> pois = new HashMap<>();
    static {
        POI p1 = new POI("piazza del comune", null, null, null, null);
        p1.setTitle("Piazza del Comune");
        try {
            p1.setPublicationDate(dateFormat.parse("01-02-2024"));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        p1.setAuthor(new User("Mario", "Rossi", "Mario.Rossi@email.com", "123456", new MunicipalTerritory("Camerino"), "codiceFiscale"));
        p1.setContents(new ArrayList<>());
        p1.setCoordinates(new Coordinates(43.133333, 13.066667));
        p1.setDescription("Piazza del Comune di Camerino");
        pois.put(p1.getTitle(), p1);
    }
    

    @RequestMapping("/POIs")
    public ResponseEntity<Object> getPOIs() {
        return new ResponseEntity<>(pois, HttpStatus.OK);
    }

    @GetMapping("/POI/{name}")
    public ResponseEntity<Object> getPOI (@PathVariable("name") String name){
        return new ResponseEntity<>(pois.get(name), HttpStatus.OK);
    }
    @PostMapping("/AggiungiPOI")
    public ResponseEntity<Object> addPOI(@RequestBody POI POI){
        if(!pois.containsKey(POI.getTitle())){
            pois.put(POI.getTitle(), POI);
            return new ResponseEntity<>("Punto d'Interesse aggiunto correttamente", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Punto d'Interesse già Esistente", HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/POI/{name}")
    public ResponseEntity<Object> deletePOI (@PathVariable("name")String name){
        if(pois.containsKey(name)){
            pois.remove(name);
            return new ResponseEntity<>("Il Punto d'interesse è stato cancellatto correttamente", HttpStatus.OK);
        }else {
            return  new ResponseEntity<>("Il Punto d'interesse non esiste", HttpStatus.BAD_REQUEST);
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
