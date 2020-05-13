package com.benameur.carretriever.model;

import java.io.Serializable;

/**
 * Created by Paul VINOT et Théo TURQUIN
 */
public class Car implements Serializable {
    private double lat;
    private double lng;

    public Car(double la, double ln) {
        this.setLat(la);
        this.setLng(ln);
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }
}
