package it.unicam.cs.ids.localplatform;

import it.unicam.cs.ids.localplatform.model.*;

import java.util.*;

/**
 * This class represents a municipal territory.
 */
public class MunicipalTerritory {
    private String municipalName;
    private Map<Coordinates, POI> POIs;
    private List<Content> generalContents;
    private List<Itinerary> itineraries;
    private List<Contest> contests;
    private List<User> users;

    public MunicipalTerritory(String municipalName) {
        this.municipalName = municipalName;
        this.POIs = new HashMap<>();
        this.generalContents = new ArrayList<>();
        this.itineraries = new ArrayList<>();
        this.contests = new ArrayList<>();
        this.users = new ArrayList<>();
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

    public String getMunicipalName() {
        return municipalName;
    }

    /**
     * This method adds a POI to the municipal territory.
     *
     * @param poi The POI to be added.
     */
    public void addPOI(POI poi) {
        // Check if a POI with the same coordinates already exists
        if (this.POIs.containsKey(poi.getCoordinates()))
            throw new IllegalArgumentException("A POI with the same coordinates already exists.");

        this.POIs.put(poi.getCoordinates(), poi);
    }

    /**
     * This method adds an itinerary to the municipal territory.
     *
     * @param itinerary The itinerary to be added.
     */
    public void addItinerary(Itinerary itinerary) {
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
        // contest.contents --> municipality.contents
        this.contests.removeIf(contest -> contest.getEndDate().before(now));
    }

    public List<Contest> getContests() {
        return contests;
    }

    public List<User> getUsers() {
        return this.users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MunicipalTerritory that = (MunicipalTerritory) o;
        return this.municipalName.equals(that.municipalName);
    }

    public void setMunicipalName(String municipalName) {
        this.municipalName = municipalName;
    }

    public void setPOIs(Map<Coordinates, POI> POIs) {
        this.POIs = POIs;
    }

    public void setGeneralContents(List<Content> generalContents) {
        this.generalContents = generalContents;
    }

    public void setItineraries(List<Itinerary> itineraries) {
        this.itineraries = itineraries;
    }

    public void setContests(List<Contest> contests) {
        this.contests = contests;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }


}
