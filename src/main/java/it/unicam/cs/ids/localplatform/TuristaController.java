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
    private Tourist turist = new Tourist(currentUser.getName(), currentUser.getSurname(), currentUser.getEmail(), currentUser.getPassword(), currentUser.getResidence(), currentUser.getCf();
    public User currentUser;
    @PostMapping("/Login")
    public ResponseEntity<Object> getLoginInfo(@RequestBody String[] array) {
        Tourist mt = new Tourist(array[4]);
        this.currentUser = new User(array[0], array[1], array[2], array[3], mt, array[5]);

        //System.out.println("Mannaggia a quelli della prima fila" + currentUser);
        return new ResponseEntity<>("User saved", HttpStatus.OK);
    }
}
