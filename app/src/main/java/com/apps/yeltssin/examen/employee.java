package com.apps.yeltssin.examen;

import com.apps.yeltssin.examen.location;

public class employee {


    String id;
    String name;
    location location;
    String mail;

    public employee() {
    }

    public employee(String id, String name, location location,
                    String mail) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.mail = mail;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public com.apps.yeltssin.examen.location getLocation() {
        return location;
    }

    public void setLocation(com.apps.yeltssin.examen.location location) {
        this.location = location;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
