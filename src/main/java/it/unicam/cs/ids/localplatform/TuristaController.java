package it.unicam.cs.ids.localplatform;

import it.unicam.cs.ids.localplatform.model.Tourist;
import it.unicam.cs.ids.localplatform.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//TODO implementare il controller per il turista
@CrossOrigin(origins = "http://localhost:63343")
@RestController
public class TuristaController {
    private Tourist turist = new Tourist("Mario", "Rossi", "Mario.Rossi@Tursita.t", "MarioRossi", new MunicipalTerritory("Camerino"), "MRRSMR00A00A000A");
    public User currentUser;
    @PostMapping("/LoginTurista")
    public ResponseEntity<Object> getLoginInfo(@RequestBody String[] array) {
        if (array.length == 2) {
            if (array[0].equals("turista")) {
                if (turist.getEmail().equals(array[1])) {
                    return new ResponseEntity<>(turist, HttpStatus.OK);
                }
            }
        }
        return new ResponseEntity<>("Errore", HttpStatus.BAD_REQUEST);
    }
}
