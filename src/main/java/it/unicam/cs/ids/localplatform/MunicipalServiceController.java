package it.unicam.cs.ids.localplatform;

import it.unicam.cs.ids.localplatform.model.*;
import it.unicam.cs.ids.localplatform.web.POITable;
import it.unicam.cs.ids.localplatform.web.POITableRepoitory;
import it.unicam.cs.ids.localplatform.web.UserListRepository;
import it.unicam.cs.ids.localplatform.web.UserTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * This class is the controller of the Municipal Service.
 */
@CrossOrigin(origins = "http://localhost:63342")
@RestController
public class MunicipalServiceController {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private final MunicipalTerritory municipalTerritory = new MunicipalTerritory("Camerino");

    private final UserListRepository userRepository;
    private final POITableRepoitory poiRepository;

    private User currentUser;

    @Autowired
    public MunicipalServiceController(UserListRepository userRepository, POITableRepoitory poiRepository, FileService fileService) {
        this.userRepository = userRepository;
        this.poiRepository = poiRepository;
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

        // per testing
        UserTable userTable3 = new UserTable();
        userTable3.setName("Giovanni");
        userTable3.setSurname("Bianchi");
        userTable3.setEmail("g@g");
        userTable3.setPassword("g");
        userTable3.setResidence("Camerino");
        userTable3.setCf("ggggggggggggggg");
        userTable3.setRole("Authorized_Contributor");
        userTable3.setPending(false);
        this.userRepository.save(userTable3);
        // per testing
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    // Register method

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
            userTable.setRole("Tourist");
        userTable.setPending(true);
        if (!userRepository.existsById(userTable.getEmail())) {
            userRepository.save(userTable);
            return new ResponseEntity<>("User saved", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User already exists", HttpStatus.BAD_REQUEST);
        }
    }

    // Login methods

    /**
     * This method is used to access the platform.
     *
     * @param array The credentials of the user.
     * @return Result of the operation.
     */
    @PostMapping("/Login")
    public ResponseEntity<String> accessPlatform(@RequestBody String[] array) {
        return userRepository.findById(array[0])
                .map(userTable -> {
                    if (userTable.getPassword().equals(array[1])) {
                        this.currentUser = new User(userTable.getName(), userTable.getSurname(), userTable.getEmail(),
                                userTable.getPassword(), new MunicipalTerritory(userTable.getResidence()), userTable.getCf());
                        return new ResponseEntity<>("User logged in", HttpStatus.OK);
                    } else {
                        return new ResponseEntity<>("Invalid password", HttpStatus.BAD_REQUEST);
                    }
                })
                .orElse(new ResponseEntity<>("User not found", HttpStatus.BAD_REQUEST));
    }

    /**
     * This method is used to get the role of the user.
     *
     * @return The role of the user.
     */
    @RequestMapping("/Role")
    public ResponseEntity<String> getRole() {
        String email = this.currentUser.getEmail();
        Optional<UserTable> userTableOptional = userRepository.findById(email);
        return userTableOptional
                .map(userTable -> new ResponseEntity<>(userTable.getRole(), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>("User not found", HttpStatus.BAD_REQUEST));
    }

    // Getter methods for the lists of the POIs, the itineraries, the general contents and the contests

    /**
     * This method is used to get the list of the POIs.
     *
     * @return The list of the POIs.
     */
    @RequestMapping("/POIs")
    public ResponseEntity<Object> getPOIs() {
        // Recupera la mappa dei POI da MunicipalTerritory
        Map<Coordinates, POI> poiMap = municipalTerritory.getPOIs();

        // Converti la lista di POI in un array di stringhe
        String[] poiListStringArray = poiMap.values().stream()
                .map(POI::toString)
                .toArray(String[]::new);

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

        // Converti la lista di itinerari in un array di stringhe
        String[] itinerariesStringArray = itineraries.stream()
                .map(Itinerary::toString)
                .toArray(String[]::new);

        // Restituisci l'array di stringhe
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

        // Converti la lista di contenuti generali in un array di stringhe
        String[] generalContentsStringArray = generalContents.stream()
                .map(Content::toString)
                .toArray(String[]::new);

        // Restituisci l'array di stringhe
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
        String[] contestsStringArray = contests.stream()
                .map(Contest::toString)
                .toArray(String[]::new);
        return new ResponseEntity<>(contestsStringArray, HttpStatus.OK);
    }

    // Animator method

    /**
     * This method is used to add a contest.
     *
     * @param array The information of the contest.
     * @return Result of the operation.
     */
    @PostMapping("/AggiungiContest")
    public ResponseEntity<String> addContest(@RequestBody String[] array) {
        try {
            Date startDate = dateFormat.parse(array[2]);
            Date endDate = dateFormat.parse(array[3]);
            Contest contest = new Contest(array[0], array[1], startDate, endDate);

            if (this.municipalTerritory.getContests().stream().noneMatch(c -> c.equals(contest))) {
                this.municipalTerritory.addContest(contest);
                return new ResponseEntity<>("Concorso aggiunto correttamente", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Concorso già Esistente", HttpStatus.BAD_REQUEST);
            }
        } catch (ParseException e) {
            return new ResponseEntity<>("Data non valida", HttpStatus.BAD_REQUEST);
        }
    }

    // Authorized Contributor methods

    /**
     * This method is used to add a POI.
     *
     * @param array The information of the POI.
     * @return Result of the operation.
     */
    @PostMapping("/AggiungiPOI")
    public ResponseEntity<String> addPOI(@RequestBody String[] array) {
        Coordinates coordinates = new Coordinates(Double.parseDouble(array[2]), Double.parseDouble(array[3]));

        if (!this.municipalTerritory.getPOIs().containsKey(coordinates)) {
            POI POI = new POI(array[0], new Date(), currentUser, coordinates, array[1]);
            this.municipalTerritory.addPOI(POI);
            return new ResponseEntity<>("Punto d'Interesse aggiunto correttamente", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Punto d'Interesse già Esistente", HttpStatus.BAD_REQUEST);
        }
    }

    /*
    Temporary list of POIs for the creation of an itinerary
     */
    private static final List<Coordinates> tempPOIList = new ArrayList<>();

    /**
     * This method is used to add a POI to the temporary list for the creation of an itinerary.
     *
     * @param array The coordinates of the POI.
     * @return Result of the operation.
     */
    @PostMapping("/AggiungiPOIAListaItinerario")
    public ResponseEntity<Object> addPOIToTempList(@RequestBody String[] array) {
        Coordinates coordinates = new Coordinates(Double.parseDouble(array[0]), Double.parseDouble(array[1]));
        tempPOIList.add(coordinates);

        return new ResponseEntity<>("Punto d'Interesse aggiunto correttamente alla lista temporanea", HttpStatus.OK);
    }

    /**
     * This method is used to add an itinerary.
     *
     * @param array The information of the itinerary.
     * @return Result of the operation.
     */
    @PostMapping("/AggiungiItinerario")
    public ResponseEntity<String> addItinerary(@RequestBody String[] array) {
        // Converti la lista temporanea di coordinate in una lista di POI
        List<POI> POIs = tempPOIList.stream()
                .map(c -> this.municipalTerritory.getPOIs().get(c))
                .collect(Collectors.toList());

        Itinerary itinerary = new Itinerary(array[0], new Date(), currentUser, POIs, array[1]);

        // Verifica se l'itinerario esiste già
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
    public ResponseEntity<Map<String, String>> changeItinerary(@RequestBody String[] array) {
        String title = array[0];
        String newTitle = array[1];

        return this.municipalTerritory.getItineraries().stream()
                .filter(itinerary -> itinerary.getTitle().equals(title))
                .findFirst()
                .map(itinerary -> {
                    itinerary.setTitle(newTitle);
                    return new ResponseEntity<>(Collections.singletonMap("message", "Itinerario aggiornato"), HttpStatus.OK);
                })
                .orElseGet(() -> new ResponseEntity<>(Collections.singletonMap("message", "L'itinerario non esiste"), HttpStatus.BAD_REQUEST));
    }

    /**
     * This method is used to modify the information of a general content.
     *
     * @param array The information of the general content.
     * @return Result of the operation.
     */
    @PostMapping("/ModificaContenutoGenerale")
    public ResponseEntity<Map<String, String>> changeGeneralContent(@RequestBody String[] array) {
        Content content = new Content(new Date(), currentUser, array[0]);

        return this.municipalTerritory.getGeneralContents().stream()
                .filter(c -> c.equals(content))
                .findFirst()
                .map(c -> {
                    c.setText(array[1]);
                    return new ResponseEntity<>(Collections.singletonMap("message", "Contenuto generale aggiornato"), HttpStatus.OK);
                })
                .orElseGet(() -> new ResponseEntity<>(Collections.singletonMap("message", "Il contenuto generale non esiste"), HttpStatus.BAD_REQUEST));
    }

    /**
     * This method is used to delete a POI.
     *
     * @param array The coordinates of the POI.
     * @return Result of the operation.
     */
    @DeleteMapping("/CancellaPOI")
    public ResponseEntity<String> deletePOI(@RequestBody String[] array) {
        Coordinates coordinates = new Coordinates(Double.parseDouble(array[0]), Double.parseDouble(array[1]));

        if (this.municipalTerritory.getPOIs().remove(coordinates) != null) {
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
        boolean removed = this.municipalTerritory.getItineraries().removeIf(itinerary -> itinerary.getTitle().equals(title[0]));

        if (removed) {
            return new ResponseEntity<>(Collections.singletonMap("message", "Itinerario cancellato"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(Collections.singletonMap("message", "L'itinerario non esiste"), HttpStatus.BAD_REQUEST);
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
        String contentText = text[0];
        boolean removed = this.municipalTerritory.getGeneralContents().removeIf(content -> content.getText().equals(contentText));

        if (removed) {
            return new ResponseEntity<>(Collections.singletonMap("message", "Contenuto generale cancellato"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(Collections.singletonMap("message", "Il contenuto generale non esiste"), HttpStatus.BAD_REQUEST);
        }
    }

    //

    @RequestMapping("/PendingPOIList")
    public ResponseEntity<Object> getPendingPOI() {
        List<POITable> allPoi = new ArrayList<>();
        allPoi = (ArrayList<POITable>) this.poiRepository.findAll();
        List<POITable> pendingPOI = new ArrayList<>();
        for (POITable p : allPoi)
            if (p.isPending())
                pendingPOI.add(p);
        String[] pendingPOIArray = new String[pendingPOI.size()];
        for (POITable p : pendingPOI)
            pendingPOIArray[pendingPOI.indexOf(p)] = p.toString();
        return new ResponseEntity<>(pendingPOIArray, HttpStatus.OK);

    }

    @PostMapping("/AcceptPoi")
    public ResponseEntity<Object> acceptPoi(@RequestBody String[] array) {
        POITable poiTable = poiRepository.findById(array[0]).orElse(null);
        assert poiTable != null;
        poiTable.setPending(false);
        poiRepository.save(poiTable);
        return new ResponseEntity<>("POI authorized", HttpStatus.OK);
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

    // Platform Manager methods

    /**
     * This method is used to get the list of the pending users.
     *
     * @return The list of the pending users.
     */
    @RequestMapping("/PendingUserList")
    public ResponseEntity<Object> getPendingUserList() {
        List<UserTable> allUsers = (ArrayList<UserTable>) this.userRepository.findAll();
        String[] pendingUsersArray = allUsers.stream()
                .filter(UserTable::isPending)
                .map(UserTable::toString)
                .toArray(String[]::new);
        return new ResponseEntity<>(pendingUsersArray, HttpStatus.OK);
    }

    /**
     * This method is used to authorize the user.
     *
     * @param array The email of the user.
     * @return Result of the operation.
     */
    @PostMapping("/AcceptUser")
    public ResponseEntity<String> authorizeUser(@RequestBody String[] array) {
        return userRepository.findById(array[0])
                .map(userTable -> {
                    userTable.setPending(false);
                    userRepository.save(userTable);
                    return new ResponseEntity<>("User accepted", HttpStatus.OK);
                })
                .orElseGet(() -> new ResponseEntity<>("User not found", HttpStatus.BAD_REQUEST));
    }

    /**
     * This method is used to deny the user.
     *
     * @param array The email of the user.
     * @return Result of the operation.
     */
    @PostMapping("/DeleteUser")
    public ResponseEntity<String> deleteUser(@RequestBody String[] array) {
        return userRepository.findById(array[0])
                .map(userTable -> {
                    userRepository.delete(userTable);
                    return new ResponseEntity<>("User deleted", HttpStatus.OK);
                })
                .orElseGet(() -> new ResponseEntity<>("User not found", HttpStatus.BAD_REQUEST));
    }

    /**
     * This method is used to get the list of the contributors and the tourists.
     *
     * @return The list of the contributors and the tourists.
     */
    @RequestMapping("/ContributorsAndTouristsList")
    public ResponseEntity<Object> getContributorsAndTouristsList() {
        List<UserTable> allUsers = (ArrayList<UserTable>) this.userRepository.findAll();
        String[] contributorsAndTouristsUsersArray = allUsers.stream()
                .filter(u -> u.getRole().equals("Contributor") || u.getRole().equals("Tourist"))
                .map(UserTable::toString)
                .toArray(String[]::new);
        return new ResponseEntity<>(contributorsAndTouristsUsersArray, HttpStatus.OK);
    }

    /**
     * This method is used to authorize the contributor or the tourist.
     *
     * @param array The email of the user.
     * @return Result of the operation.
     */
    @PostMapping("/authorize")
    public ResponseEntity<String> authorize(@RequestBody String[] array) {
        return userRepository.findById(array[0])
                .map(userTable -> {
                    if (userTable.getRole().equals("Contributor")) {
                        userTable.setRole("Authorized_Contributor");
                    } else if (userTable.getRole().equals("Tourist")) {
                        userTable.setRole("Authorized_Tourist");
                    }
                    userRepository.save(userTable);
                    return new ResponseEntity<>("User authorized", HttpStatus.OK);
                })
                .orElseGet(() -> new ResponseEntity<>("User not found", HttpStatus.BAD_REQUEST));
    }
}