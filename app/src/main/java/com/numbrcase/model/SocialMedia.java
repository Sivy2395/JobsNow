package com.numbrcase.model;

import com.numbrcase.common.SocialMediaIDs;

import java.io.Serializable;

public class SocialMedia implements Serializable {

    private int socialMediaID;

    private String link;
    private String userID;


    public SocialMedia(int socialMediaID) {
        this.socialMediaID = socialMediaID;
    }

    public SocialMedia(int socialMediaID, String userID) {
        this.socialMediaID = socialMediaID;
        this.userID = userID;
        this.link = SocialMediaIDs.getLink(socialMediaID, userID);
    }

    public int getMediaID() {
        return socialMediaID;
    }

    public String getUserID() {
        return userID;
    }

    public String getLink() {
        return link;
    }

    public void setSocialMediaID(int socialMediaID) {
        this.socialMediaID = socialMediaID;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}
