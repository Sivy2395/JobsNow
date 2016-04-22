package com.numbrcase.model;

public interface Contact {

    // Contact Status
    public int ADDED     = 1;
    public int REQUESTED = 2;

    /* Get Methods */
    public int getID();

    public String getName();
    public String getPhone();
    public String getEmail();

    public String getRequestPlace();

    public int getStatus();

    /* Set Methods */
    public void setID(int ID);

    public void setName(String name);
    public void setPhone(String phone);
    public void setEmail(String email);

    public void setRequestPlace(String requestPlace);

    public void setStatus(int status);
}
