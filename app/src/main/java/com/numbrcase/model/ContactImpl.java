package com.numbrcase.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ContactImpl implements Contact, Serializable {

    private String name;
    private String phone;
    private String email;

    private String requestPlace;

    // status that indicate added or requested
    private int status;

    private List<SocialMedia> socialMedias = new ArrayList<>();

    public ContactImpl() {

    }

    public ContactImpl(String name) {
        this.name = name;
    }

    public ContactImpl(String name, String requestPlace) {
        this.name = name;
        this.requestPlace = requestPlace;
    }

    public ContactImpl(String name, int status) {
        this.name = name;
        this.status = status;
    }

    public ContactImpl(String name, String requestPlace, int status) {
        this.name = name;
        this.requestPlace = requestPlace;
        this.status = status;
    }

    public ContactImpl(String name, String requestPlace, int status, List<SocialMedia> socialMedias) {
        this.name = name;
        this.requestPlace = requestPlace;
        this.status = status;
        this.socialMedias = socialMedias;
    }

    public ContactImpl(String name, String requestPlace, int status, List<SocialMedia> socialMedias, String phone) {
        this.name = name;
        this.requestPlace = requestPlace;
        this.status = status;
        this.socialMedias = socialMedias;
        this.phone = phone;
    }


    @Override
    public int getID() {
        return 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setPhone(String phone) {

    }

    @Override
    public void setEmail(String email) {

    }

    public String getRequestPlace() {
        return requestPlace;
    }

    @Override
    public int getStatus() {
        return status;
    }

    @Override
    public void setID(int ID) {

    }

    public void setRequestPlace(String requestPlace) {
        this.requestPlace = requestPlace;
    }

    @Override
    public void setStatus(int status) {

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

    @Override
    public String getEmail() {
        return null;
    }
}
