package com.apps.yeltssin.examen;

public class location {

       String lat;
       String log;

    public location() {
    }

    public location(String lat, String log) {
        this.lat = lat;
        this.log = log;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }
}
