package com.numbrcase;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Contact implements Serializable {

    private String name;
    private String requestPlace;

    private boolean added;

    private List<SocialMedia> sMedias = new ArrayList<>();

    //TODO: add social networks

    public Contact(String name) {
        this.name = name;
    }

    public Contact(String name, String requestPlace) {
        this.name = name;
        this.requestPlace = requestPlace;
    }

    public Contact(String name, boolean added) {
        this.name = name;
        this.added = added;
    }

    public Contact(String name, String requestPlace, boolean added) {
        this.name = name;
        this.requestPlace = requestPlace;
        this.added = added;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAdded() {
        return added;
    }

    public void setAdded(boolean added) {
        this.added = added;
    }

    public String getRequestPlace() {
        return requestPlace;
    }

    public void setRequestPlace(String requestPlace) {
        this.requestPlace = requestPlace;
    }
}
