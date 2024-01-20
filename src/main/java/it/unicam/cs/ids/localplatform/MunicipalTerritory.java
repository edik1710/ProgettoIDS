package it.unicam.cs.ids.localplatform;

import it.unicam.cs.ids.localplatform.model.*;

import java.util.*;

/**
 * This class represents a municipal territory.
 */
public class MunicipalTerritory {
    private Map<Coordinates, POI> POIs;
    private List<Content> generalContents;
    private List<Itinerary> itineraries;

    public MunicipalTerritory() {
        this.POIs = new HashMap<>();
        this.generalContents = new ArrayList<>();
        this.itineraries = new ArrayList<>();
    }

    public Map<Coordinates, POI> getPOIs() {
        return POIs;
    }

    public List<Content> getGeneralContents() {
        return generalContents;
    }

    public List<Itinerary> getItineraries() {
        return itineraries;
    }

    /**
     * This method adds a POI to the municipal territory.
     *
     * @param title           The title of the POI.
     * @param publicationDate The publication date of the POI.
     * @param author          The author of the POI.
     * @param coordinates     The coordinates of the POI.
     */
    public void addPOI(String title, Date publicationDate, User author, Coordinates coordinates) {
        POI poi = new POI(title, publicationDate, author, coordinates);
        this.POIs.put(coordinates, poi);
    }

    /**
     * This method adds an itinerary to the municipal territory.
     *
     * @param title           The title of the itinerary.
     * @param publicationDate The publication date of the itinerary.
     * @param author          The author of the itinerary.
     * @param POIs            The POIs that make up the itinerary.
     */
    public void addItinerary(String title, Date publicationDate, User author, List<POI> POIs) {
        Itinerary itinerary = new Itinerary(title, publicationDate, author, POIs);
        this.itineraries.add(itinerary);
    }

    /**
     * This method adds a general content to the municipal territory.
     *
     * @param content The content to add.
     */
    public void addGeneralContent(Content content) {
        this.generalContents.add(content);
    }

    /**
     * This method deletes a POI from the municipal territory.
     *
     * @param coordinates The coordinates of the POI to be deleted.
     */
    public void deletePOI(Coordinates coordinates) {
        this.POIs.remove(coordinates);
    }

    // Il metodo deleteGeneralContent è già presente nel file vpp
    /*
    public void deleteGeneralContent(Content content) {

    }
*/

    /**
     * This method deletes an itinerary from the municipal territory.
     *
     * @param itinerary The itinerary to be deleted.
     */
    public void deleteItinerary(Itinerary itinerary) {
        this.itineraries.remove(itinerary);
    }
}
