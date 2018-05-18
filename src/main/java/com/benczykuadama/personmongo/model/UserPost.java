package com.benczykuadama.personmongo.model;

import java.util.Date;

public class UserPost {

    String message;
    Date date;

    public UserPost() {}

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
