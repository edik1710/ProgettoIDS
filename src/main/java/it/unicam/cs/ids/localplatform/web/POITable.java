package it.unicam.cs.ids.localplatform.web;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class POITable {
    @Id
    private String title;
    private String POILatitude;
    private String POILongitude;
    private boolean pending;


    public boolean isPending() {
        return pending;
    }

    public void setPending(boolean pending) {
        this.pending = pending;
    }

    public String getPOILatitude() {
        return POILatitude;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPOILatitude(String POILatitude) {
        this.POILatitude = POILatitude;
    }

    public String getPOILongitude() {
        return POILongitude;
    }

    public void setPOILongitude(String POILongitude) {
        this.POILongitude = POILongitude;
    }

    public String toString() {
        return "title=" + title +
                ", POILatitude=" + POILatitude +
                ", POILongitude=" + POILongitude +
                ", pending=" + pending;
    }

}
