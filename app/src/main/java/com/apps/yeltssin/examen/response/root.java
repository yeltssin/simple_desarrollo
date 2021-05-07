package com.apps.yeltssin.examen.response;

public class root {
    public data data;

    public root() {
    }

    public root(com.apps.yeltssin.examen.response.data data) {
        this.data = data;
    }

    public com.apps.yeltssin.examen.response.data getData() {
        return data;
    }

    public void setData(com.apps.yeltssin.examen.response.data data) {
        this.data = data;
    }
}
