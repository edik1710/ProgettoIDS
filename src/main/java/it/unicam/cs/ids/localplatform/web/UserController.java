package it.unicam.cs.ids.localplatform.web;

import it.unicam.cs.ids.localplatform.MunicipalTerritory;
import it.unicam.cs.ids.localplatform.model.User;
import jakarta.servlet.annotation.WebServlet;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Creo una classe controller che gestisce le richieste HTTP
@RestController
public class UserController {
    // Creo un metodo che gestisce la richiesta POST dalla pagina di login
    @PostMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response) {

        // Recupero i dati dal form HTML usando il metodo getParameter()
        String name = request.getParameter("uname");
        String surname = request.getParameter("Cf").substring(0, 6); // Assumo che il codice fiscale contenga il cognome nei primi 6 caratteri
        String email = request.getParameter("email");
        String password = request.getParameter("psw");
        MunicipalTerritory residence = new MunicipalTerritory(request.getParameter("Residence")); // Assumo che esista una classe MunicipalTerritory che accetta il nome della residenza come parametro
        String cf = request.getParameter("Cf");
        String role = request.getParameter("Roles");

        // Creo un oggetto di tipo User usando i dati
        User user = new User(name, surname, email, password, residence, cf);

        // Salvo l'oggetto user in un'altra classe che hai già definito
        // Qui devi inserire il codice per salvare l'oggetto user nella classe desiderata
        // Per esempio, se hai una classe UserService che ha un metodo saveUser(), puoi scrivere:
        // UserService userService = new UserService();
        // userService.saveUser(user);

        // Ritorno una stringa di conferma
        return "Utente registrato con successo.";
    }
}