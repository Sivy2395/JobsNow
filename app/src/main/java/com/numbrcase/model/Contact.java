package com.numbrcase.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Contact implements Serializable {

    private String name;
    private String phone;
    private String email;

    private String requestPlace;

    // status that indicate added or requested
    private boolean added;

    private List<SocialMedia> socialMedias = new ArrayList<>();

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

    public Contact(String name, String requestPlace, boolean added, List<SocialMedia> socialMedias) {
        this.name = name;
        this.requestPlace = requestPlace;
        this.added = added;
        this.socialMedias = socialMedias;
    }

    public Contact(String name, String requestPlace, boolean added, List<SocialMedia> socialMedias, String phone) {
        this.name = name;
        this.requestPlace = requestPlace;
        this.added = added;
        this.socialMedias = socialMedias;
        this.phone = phone;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRequestPlace() {
        return requestPlace;
    }

    public void setRequestPlace(String requestPlace) {
        this.requestPlace = requestPlace;
    }

    public List<SocialMedia> getSocialMedias() {
        return this.socialMedias;
    }

    public void addSocialMedia(SocialMedia socialMedia) {
        this.socialMedias.add(socialMedia);
    }

    public String getPhone() {
        return phone;
    }
}
