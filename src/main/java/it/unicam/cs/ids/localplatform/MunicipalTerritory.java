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
    private List<Contest> contests;

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

        // Check if a POI with the same coordinates already exists
        if (this.POIs.containsKey(coordinates))
            throw new IllegalArgumentException("A POI with the same coordinates already exists.");

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

        // Check if the itinerary already exists
        if (this.itineraries.contains(itinerary)) throw new IllegalArgumentException("This itinerary already exists.");

        this.itineraries.add(itinerary);
    }

    /**
     * This method adds a general content to the municipal territory.
     *
     * @param content The content to add.
     */
    public void addGeneralContent(Content content) {
        // Check if the content already exists
        if (this.generalContents.contains(content)) throw new IllegalArgumentException("This content already exists.");

        this.generalContents.add(content);
    }

    /**
     * This method deletes a POI from the municipal territory.
     *
     * @param coordinates The coordinates of the POI to be deleted.
     */
    public void deletePOI(Coordinates coordinates) {
        // Check if the POI exists
        if (!this.POIs.containsKey(coordinates)) throw new IllegalArgumentException("The POI does not exist.");

        this.POIs.remove(coordinates);
    }

    /**
     * this method deletes a general content from the municipal territory.
     *
     * @param content The content to be deleted.
     */
    public void deleteGeneralContent(Content content) {
        // Check if the content exists
        if (!this.generalContents.contains(content)) throw new IllegalArgumentException("The Content does not exist.");

        this.generalContents.remove(content);
    }

    /**
     * This method deletes an itinerary from the municipal territory.
     *
     * @param itinerary The itinerary to be deleted.
     */
    public void deleteItinerary(Itinerary itinerary) {
        // Check if the itinerary exists
        if (!this.itineraries.contains(itinerary)) throw new IllegalArgumentException("The itinerary does not exist.");

        this.itineraries.remove(itinerary);
    }

    /**
     * This method adds a contest to the municipal territory.
     *
     * @param contest The contest to be added.
     */
    public void addContest(Contest contest) {
        removeExpiredContests();

        // Check if the contest already exists
        if (this.contests.contains(contest)) throw new IllegalArgumentException("This contest already exists.");

        this.contests.add(contest);
    }

    private void removeExpiredContests() {
        Date now = new Date();
        this.contests.removeIf(contest -> contest.getEndDate().before(now));
    }
}
