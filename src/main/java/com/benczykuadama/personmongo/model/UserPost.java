package com.benczykuadama.personmongo.model;

import java.util.Date;

public class UserPost {

    String message;
    Date date;

    public UserPost() {}

    public UserPost(String message) {
        this.message = message;
        this.date = new Date();
    }

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
