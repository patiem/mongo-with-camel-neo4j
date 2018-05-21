package com.benczykuadama.personmongo.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class UserPost {

    String message;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss a z")
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
