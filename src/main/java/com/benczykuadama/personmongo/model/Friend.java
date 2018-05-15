package com.benczykuadama.personmongo.model;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Set;

@NodeEntity
public class Friend {

    @GraphId
    private Long id;

    private String mongoId;
    private String name;

    @Relationship(type = "FRIENDS_WITH", direction = Relationship.UNDIRECTED)
    private Set<Frienship> pairs = new HashSet<>();

//    @Relationship(type = "HAS_INVITEd", direction = "OUTGOING")
//    private Set<Invitation> invitations = new HashSet<>();

    public Friend() {
    }

    public Friend(String mongoId, String name) {
        this.mongoId = mongoId;
        this.name = name;
    }

    public void addFrienship(Frienship frienship) {
        frienship.getFirst().getPairs().add(frienship);
        frienship.getSecond().getPairs().add(frienship);

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMongoId() {
        return mongoId;
    }

    public void setMongoId(String mongoId) {
        this.mongoId = mongoId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Frienship> getPairs() {
        return pairs;
    }

    public void setPairs(Set<Frienship> pairs) {
        this.pairs = pairs;
    }

//    public Set<Invitation> getInvitations() {
//        return invitations;
//    }
//
//    public void setInvitations(Set<Invitation> invitations) {
//        this.invitations = invitations;
//    }
}
