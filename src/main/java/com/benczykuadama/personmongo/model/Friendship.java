package com.benczykuadama.personmongo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;
import org.neo4j.ogm.annotation.typeconversion.DateLong;

import java.util.Date;

@RelationshipEntity(type = "FRIENDS_WITH")
public class Friendship {

    @GraphId
    private Long id;

    @StartNode
    @JsonBackReference
    private Friend first;

    @EndNode
    @JsonBackReference
    private Friend second;

    @DateLong
    private Date dateAdded;

    public Friendship() {
    }

    public Friendship(Friend first, Friend second) {

        this.first = first;
        this.second = second;
        dateAdded = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Friend getFirst() {
        return first;
    }

    public void setFirst(Friend first) {
        this.first = first;
    }

    public Friend getSecond() {
        return second;
    }

    public void setSecond(Friend second) {
        this.second = second;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }
}
