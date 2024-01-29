package it.unicam.cs.ids.localplatform;

import it.unicam.cs.ids.localplatform.model.Coordinates;
import it.unicam.cs.ids.localplatform.model.POI;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class MunicipalServiceController {
    private static Map<String, Comune> municipalities = new HashMap<>();

    static {
        Comune Camerino = new Comune();
        Camerino.setName("Camerino");
        Camerino.setProvince("MC");
        municipalities.put(Camerino.getName(), Camerino);
    }

    @RequestMapping("/AggiungiPOI")
    public ResponseEntity<Object> getPOIs() {
        return new ResponseEntity<>(municipalities, HttpStatus.OK);
    }

    @GetMapping("/POI/{name}")
    public ResponseEntity<Object> getPOI (@PathVariable("name") String name){
        return new ResponseEntity<>(municipalities.get(name), HttpStatus.OK);
    }
    @PostMapping("/POI")
    public ResponseEntity<Object> addPOI(@RequestBody Comune comune){
        if(!municipalities.containsKey(comune.getName())){
            municipalities.put(comune.getName(), comune);
            return new ResponseEntity<>("Punto d'Interesse aggiunto correttamente", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Punto d'Interesse già Esistente", HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/POI/{name}")
    public ResponseEntity<> deletePOI (@PathVariable("name")String name){
        if(municipalities.containsKey(name)){
            municipalities.remove(name);
            return new ResponseEntity("Il Punto d'interesse è stato cancellatto correttamente", HttpStatus.OK);
        }else {
            return  new ResponseEntity("Il Punto d'interesse non esiste", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping (value = "/POI{name}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updatePOI(@PathVariable("name")String name, @RequestBody Comune comune){
        if(municipalities.containsKey(name)){
            municipalities.get(name).setName(comune.getName());
            return new ResponseEntity<>("Punto d'Interesse Aggiornato correttamente", HttpStatus.OK);
        }else {
            throw new MunicipalTerritoryExecption (); //<- Da Implemetare
        }
    }

}
