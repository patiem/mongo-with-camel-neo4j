package com.benczykuadama.personmongo.model;

import java.util.Date;

public class UserPostView {

    private String userName;
    private UserPost post;

    public UserPostView(String userName, UserPost post) {
        this.userName = userName;
        this.post = post;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public UserPost getPost() {
        return post;
    }

    public void setPost(UserPost post) {
        this.post = post;
    }

    public Date provideDate() {
        return post.getDate();
    }
}
