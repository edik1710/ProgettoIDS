package it.unicam.cs.ids.localplatform.model;

import java.util.ArrayList;
import java.util.List;

public class Itinerary implements Info {
    private InfoData infoData;
    private List<POI> POIs;

    public Itinerary(POI POI1, POI POI2) {
        this.infoData = null; // Da completare
        this.POIs = new ArrayList<>();
        // La creazione di un itinerario richiede almeno due POI
        this.POIs.add(POI1);
        this.POIs.add(POI2);
    }

    public InfoData getInfoData() {
        return infoData;
    }

    public List<POI> getPOIs() {
        return POIs;
    }
}
