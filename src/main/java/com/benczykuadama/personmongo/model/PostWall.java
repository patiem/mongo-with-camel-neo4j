package com.benczykuadama.personmongo.model;

import java.util.*;

public class PostWall {

    private PostWallType title;
    private List<UserPostView> wall = new ArrayList<>();

    public PostWall() {}



    public PostWall sortedWall() {
        wall.sort(Comparator.comparing(o -> (o.provideDate())));
        return this;
    }

    public void addPostView(UserPostView userPostView) {
        wall.add(userPostView);
    }

    public PostWallType getTitle() {
        return title;
    }

    public void setTitle(PostWallType title) {
        this.title = title;
    }

    public List<UserPostView> getWall() {
        return wall;
    }

    public void setWall(List<UserPostView> wall) {
        this.wall = wall;
    }
}
