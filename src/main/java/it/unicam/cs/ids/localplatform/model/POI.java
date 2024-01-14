package it.unicam.cs.ids.localplatform.model;

public class POI implements Info {
    private InfoData infoData;
    private Coordinates coordinates;

    public POI(Coordinates coordinates) {
        this.infoData = null; // Da completare
        this.coordinates = coordinates;
    }

    public InfoData getInfoData() {
        return infoData;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }
}
