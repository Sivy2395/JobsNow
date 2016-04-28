package com.numbrcase.model;

import com.numbrcase.common.SocialMediaIDs;

import java.util.ArrayList;
import java.util.List;

public class ContactImpl implements Contact {

    private int contactID;

    private String name;
    private String phone;
    private String email;

    private String requestPlace;

    // status that indicate added or requested
    private int status;

    private List<SocialMedia> socialMedias = new ArrayList<>();

    /*
     * Default values for a contact
     */
    public ContactImpl() {
        this.name = "User";
        this.phone = "000 000 0000";
        this.email = "numbrcase@email.com";
        this.status = Contact.ADDED;

        // delete me later
        SocialMedia sm1 = new SocialMediaImpl(SocialMediaIDs.INSTAGRAM, "instaID");
        SocialMedia sm2 = new SocialMediaImpl(SocialMediaIDs.FACEBOOK, "faceID");
        sm1.setContactID(1);
        sm2.setContactID(1);
        this.socialMedias.add(sm1);
        this.socialMedias.add(sm2);
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
        return contactID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
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
        this.contactID = ID;
    }

    public void setRequestPlace(String requestPlace) {
        this.requestPlace = requestPlace;
    }

    @Override
    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public void setSocialMedias(List<SocialMedia> socialMedias) {
        this.socialMedias = socialMedias;
    }

    public List<SocialMedia> getSocialMedias() {
        return this.socialMedias;
    }

    public void addSocialMedia(SocialMediaImpl socialMedia) {
        this.socialMedias.add(socialMedia);
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public String getEmail() {
        return email;
    }
}
