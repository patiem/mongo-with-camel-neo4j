package com.benczykuadama.personmongo.model;

import java.util.*;

public class PostWall {
    
    private List<UserPostView> wall = new ArrayList<>();

    public PostWall() {
    }

    public List<UserPostView> sortPostsByDate() {
        wall.sort(new Comparator<UserPostView>() {
            @Override
            public int compare(UserPostView o1, UserPostView o2) {
                return (o1.provideDate()).compareTo(o2.provideDate());
            }
        });

        return wall;
    }


    public void addPostView(UserPostView userPostView) {
        wall.add(userPostView);
    }
}
