package com.numbrcase;

public class SocialMedia {

    private int socialMediaID;

    private String link;
    private String userID;


    public SocialMedia(int socialMediaID) {
        this.socialMediaID = socialMediaID;
    }

    public SocialMedia(int socialMediaID, String userID) {
        this.socialMediaID = socialMediaID;
        this.userID = userID;
    }

    public int getMediaID() {
        return this.socialMediaID;
    }

    public void setSocialMediaID(int socialMediaID) {
        this.socialMediaID = socialMediaID;
    }
}
