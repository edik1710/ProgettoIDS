package it.unicam.cs.ids.localplatform.model;

import java.util.ArrayList;
import java.util.List;

public class ContributorData {
    private List<Content> uploadedContents;
    private List<POI> createdPOIs;
    private List<Itinerary> createdItineraries;

    public ContributorData() {
        // Quando viene creato un ContributorData, le liste di contenuti caricati, POI creati e itinerari creati sono vuote
        this.uploadedContents = new ArrayList<>();
        this.createdPOIs = new ArrayList<>();
        this.createdItineraries = new ArrayList<>();
    }
}
