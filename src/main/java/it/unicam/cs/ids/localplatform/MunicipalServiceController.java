package it.unicam.cs.ids.localplatform;

import it.unicam.cs.ids.localplatform.model.*;
import it.unicam.cs.ids.localplatform.web.UserListRepository;
import it.unicam.cs.ids.localplatform.web.UserTable;
import javafx.scene.control.Alert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * This class is the controller of the Municipal Service.
 */
@CrossOrigin(origins = "http://localhost:63342")
@RestController
public class MunicipalServiceController {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private final MunicipalTerritory municipalTerritory = new MunicipalTerritory("Camerino");

    private final UserListRepository userRepository;

    private User currentUser;

    @Autowired
    public MunicipalServiceController(UserListRepository userRepository, FileService fileService) {
        this.userRepository = userRepository;
        this.fileService = fileService;
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

    public User getCurrentUser() {
        return currentUser;
    }

    /**
     * This method is used to get the login information.
     *
     * @param array The information of the user.
     * @return Result of the operation.
     */
    @PostMapping("/Register")
    public ResponseEntity<Object> getLoginInfo(@RequestBody String[] array) {
        UserTable userTable = new UserTable();
        userTable.setName(array[0]);
        userTable.setSurname(array[1]);
        userTable.setEmail(array[2]);
        userTable.setPassword(array[3]);
        userTable.setResidence(array[4]);
        userTable.setCf(array[5]);
        if (userTable.getResidence().equals("Camerino"))
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
    }

    /**
     * This method is used to access the platform.
     *
     * @param array The credentials of the user.
     * @return Result of the operation.
     */
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

    /**
     * This method is used to get the role of the user.
     *
     * @return The role of the user.
     */
    @RequestMapping("/Role")
    public ResponseEntity<Object> getRole() {
        String email = this.currentUser.getEmail();
        UserTable userTable = userRepository.findById(email).orElse(null);
        assert userTable != null;
        return new ResponseEntity<>(userTable.getRole(), HttpStatus.OK);
    }

    /**
     * This method is used to get the list of the pending users.
     *
     * @return The list of the pending users.
     */
    @RequestMapping("/PendingUserList")
    public ResponseEntity<Object> getPendingUserList() {
        List<UserTable> allUsers = new ArrayList<>();
        allUsers = (ArrayList<UserTable>) this.userRepository.findAll();
        List<UserTable> pendingUsers = new ArrayList<>();
        for (UserTable u : allUsers)
            if (u.isPending())
                pendingUsers.add(u);
        String[] pendingUsersArray = new String[pendingUsers.size()];
        for (UserTable u : pendingUsers)
            pendingUsersArray[pendingUsers.indexOf(u)] = u.toString();
        return new ResponseEntity<>(pendingUsersArray, HttpStatus.OK);
    }

    @RequestMapping("/PendingPOI")
    public ResponseEntity<Object> getPendingPOI() {
        List<POI> allPOI = new ArrayList<>();
        allPOI = (ArrayList<POI>) this.municipalTerritory.getPOIs().values();
        List<POI> pendingPOI = new ArrayList<>();
        for (POI p : allPOI)
            if (p.isPending())
                pendingPOI.add(p);
        String[] pendingPOIArray = new String[pendingPOI.size()];
        for (POI p : pendingPOI)
            pendingPOIArray[pendingPOI.indexOf(p)] = p.toString();
        return new ResponseEntity<>(pendingPOIArray, HttpStatus.OK);
    }



    /**
     * This method is used to get the list of the contributors and the tourists.
     *
     * @return The list of the contributors and the tourists.
     */
    @RequestMapping("/ContributorsAndTouristsList")
    public ResponseEntity<Object> getContributorsAndTouristsList() {
        List<UserTable> allUsers = new ArrayList<>();
        allUsers = (ArrayList<UserTable>) this.userRepository.findAll();
        List<UserTable> contributorsAndTouristsUsers = new ArrayList<>();
        for (UserTable u : allUsers)
            if (u.getRole().equals("Contributor") || u.getRole().equals("Turista"))
                contributorsAndTouristsUsers.add(u);
        String[] contributorsAndTouristsUsersArray = new String[contributorsAndTouristsUsers.size()];
        for (UserTable u : contributorsAndTouristsUsers)
            contributorsAndTouristsUsersArray[contributorsAndTouristsUsers.indexOf(u)] = u.toString();
        return new ResponseEntity<>(contributorsAndTouristsUsersArray, HttpStatus.OK);
    }

    /**
     * This method is used to authorize the user.
     *
     * @param array The email of the user.
     * @return Result of the operation.
     */
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

    /**
     * This method is used to authorize the contributor or the tourist.
     *
     * @param array The email of the user.
     * @return Result of the operation.
     */
    @PostMapping("/autorized")
    public ResponseEntity<Object> autorized(@RequestBody String[] array) {
        UserTable userTable = userRepository.findById(array[0]).orElse(null);
        if (userTable != null) {
            if (userTable.getRole().equals("Contributor"))
                userTable.setRole("Authorized_Contributor");
            if (userTable.getRole().equals("Turista"))
                userTable.setRole("Authorized_Tourist");
            userRepository.save(userTable);
            return new ResponseEntity<>("User authorized", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User not found", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * This method is used to deny the user.
     *
     * @param array The email of the user.
     * @return Result of the operation.
     */
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

    /**
     * This method is used to get the list of the POIs.
     *
     * @return The list of the POIs.
     */
    @RequestMapping("/POIs")
    public ResponseEntity<Object> getPOIs() {
        // Recupera la mappa dei POI da MunicipalTerritory
        if(municipalTerritory.getPOIs() == null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("No POI present");
            return new ResponseEntity<>("Nessun POI presente", HttpStatus.OK);
        }

        Map<Coordinates, POI> poiMap = municipalTerritory.getPOIs();

        // Crea una lista di POI dalla mappa
        List<POI> poiList = new ArrayList<>(poiMap.values());
        String[] poiListStringArray = new String[poiList.size()];
        for (POI poi : poiList) {
            poiListStringArray[poiList.indexOf(poi)] = poi.toString();
        }

        System.out.println(Arrays.toString(poiListStringArray));
        // Restituisce la lista di POI
        return new ResponseEntity<>(poiListStringArray, HttpStatus.OK);
    }

    /**
     * This method is used to get the list of the itineraries.
     *
     * @return The list of the itineraries.
     */
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

    /**
     * This method is used to get the list of the general contents.
     *
     * @return The list of the general contents.
     */
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

    /**
     * This method is used to get the list of the contests.
     *
     * @return The list of the contests.
     */
    @RequestMapping("/Contests")
    public ResponseEntity<Object> getContests() {
        List<Contest> contests = municipalTerritory.getContests();
        Contest[] contestsArray = contests.toArray(new Contest[0]);
        String[] contestsStringArray = new String[contestsArray.length];
        for (int i = 0; i < contestsArray.length; i++) {
            contestsStringArray[i] = contestsArray[i].toString();
        }

        return new ResponseEntity<>(contestsStringArray, HttpStatus.OK);
    }

    /**
     * This method is used to add a POI.
     *
     * @param array The information of the POI.
     * @return Result of the operation.
     */
    @PostMapping("/AggiungiPOI")
    public ResponseEntity<String> addPOI(@RequestBody String[] array) {
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

    /**
     * This method is used to add a POI to the temporary list.
     *
     * @param array The coordinates of the POI.
     * @return Result of the operation.
     */
    @PostMapping("/AggiungiPOIAListaItinerario")
    public ResponseEntity<Object> addPOIToTempList(@RequestBody String[] array) {
        Coordinates coordinates = new Coordinates(Double.parseDouble(array[0]), Double.parseDouble(array[1]));
        tempPOIList.add(coordinates);
        return new ResponseEntity<>("Punto d'Interesse aggiunto correttamente", HttpStatus.OK);
    }

    /**
     * This method is used to add an itinerary.
     *
     * @param array The information of the itinerary.
     * @return Result of the operation.
     */
    @PostMapping("/AggiungiItinerario")
    public ResponseEntity<String> addItinerary(@RequestBody String[] array) {
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

    /**
     * This method is used to add a general content.
     *
     * @param text The text of the content.
     * @return Result of the operation.
     */
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

    /**
     * This method is used to add a contest.
     *
     * @param array The information of the contest.
     * @return Result of the operation.
     * @throws ParseException If the date is not valid.
     */
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

    /**
     * This method is used to modify the information of a POI.
     *
     * @param array The information of the POI.
     * @return Result of the operation.
     */
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

    /**
     * This method is used to modify the information of an itinerary.
     *
     * @param array The information of the itinerary.
     * @return Result of the operation.
     */
    @PostMapping("/ModificaItinerario")
    public ResponseEntity<Object> changeItinerary(@RequestBody String[] array) {
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

    /**
     * This method is used to modify the information of a general content.
     *
     * @param array The information of the general content.
     * @return Result of the operation.
     */
    @PostMapping("/ModificaContenutoGenerale")
    public ResponseEntity<Object> changeGeneralContent(@RequestBody String[] array) {
        Content content = new Content(new Date(), currentUser, array[0]);
        if (!this.municipalTerritory.getGeneralContents().contains(content))
            return new ResponseEntity<>(Collections.singletonMap("message", "Il contenuto generale non esiste"), HttpStatus.BAD_REQUEST);
        else {
            this.municipalTerritory.getGeneralContents().get(this.municipalTerritory.getGeneralContents().indexOf(content)).setText(array[1]);
            return new ResponseEntity<>(Collections.singletonMap("message", "Contenuto generale aggiornato"), HttpStatus.OK);
        }
    }

    /**
     * This method is used to delete a POI.
     *
     * @param array The coordinates of the POI.
     * @return Result of the operation.
     */
    @DeleteMapping("/CancellaPOI")
    public ResponseEntity<Object> deletePOI(@RequestBody String[] array) {
        Coordinates coordinates = new Coordinates(Double.parseDouble(array[0]), Double.parseDouble(array[1]));

        if (this.municipalTerritory.getPOIs().containsKey(coordinates)) {
            this.municipalTerritory.getPOIs().remove(coordinates);
            return new ResponseEntity<>("Il Punto d'interesse è stato cancellato correttamente", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Il Punto d'interesse non esiste", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * This method is used to delete an itinerary.
     *
     * @param title The title of the itinerary.
     * @return Result of the operation.
     */
    @DeleteMapping("/CancellaItinerario")
    public ResponseEntity<Object> deleteItinerary(@RequestBody String[] title) {
        POI poi1 = new POI("", new Date(), currentUser, new Coordinates(1, 1), "");
        POI poi2 = new POI("", new Date(), currentUser, new Coordinates(2, 2), "");
        List<POI> pois = new ArrayList<>();
        pois.add(poi1);
        pois.add(poi2);
        Itinerary itinerary = new Itinerary(title[0], new Date(), currentUser, pois, "");
        if (!this.municipalTerritory.getItineraries().contains(itinerary))
            return new ResponseEntity<>(Collections.singletonMap("message", "L'itinerario non esiste"), HttpStatus.BAD_REQUEST);
        else {
            this.municipalTerritory.getItineraries().remove(itinerary);
            return new ResponseEntity<>(Collections.singletonMap("message", "Itinerario cancellato"), HttpStatus.OK);
        }
    }

    /**
     * This method is used to delete a general content.
     *
     * @param text The text of the content.
     * @return Result of the operation.
     */
    @DeleteMapping("/CancellaContenutoGenerale")
    public ResponseEntity<Object> deleteGeneralContent(@RequestBody String[] text) {
        Content content = new Content(new Date(), currentUser, text[0]);
        if (!this.municipalTerritory.getGeneralContents().contains(content))
            return new ResponseEntity<>(Collections.singletonMap("message", "Il contenuto generale non esiste"), HttpStatus.BAD_REQUEST);
        else {
            this.municipalTerritory.getGeneralContents().remove(content);
            return new ResponseEntity<>(Collections.singletonMap("message", "Contenuto generale cancellato"), HttpStatus.OK);
        }
    }

    @GetMapping("/download")
    public ResponseEntity<Resource> downloadFile(@RequestParam("filename") String filename) {
        try {
            Path filePath = Paths.get("src/main/resources/file", filename);
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists() && resource.isReadable()) {
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace(); // Handle exception properly
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    private final FileService fileService;
    @PostMapping(value = "/createOrUpdatePOI", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createOrUpdatePOI(@RequestBody POI poi) {
        fileService.saveToFile(poi);
    }



}