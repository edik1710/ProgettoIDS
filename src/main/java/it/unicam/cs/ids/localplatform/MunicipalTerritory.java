package it.unicam.cs.ids.localplatform;

import it.unicam.cs.ids.localplatform.model.Content;
import it.unicam.cs.ids.localplatform.model.Coordinates;
import it.unicam.cs.ids.localplatform.model.Itinerary;
import it.unicam.cs.ids.localplatform.model.POI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
}
