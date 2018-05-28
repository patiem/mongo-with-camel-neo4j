package com.benczykuadama.personmongo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;
import org.neo4j.ogm.annotation.typeconversion.DateLong;

import java.util.Date;
import java.util.Objects;

@RelationshipEntity(type = "FRIENDS_WITH")
public class Friendship {

    @GraphId
    private Long id;

    @StartNode
    @JsonSerialize(using = CustomSerializer.class)
    private Friend first;

    @EndNode
    @JsonSerialize(using = CustomSerializer.class)
    private Friend second;

    @DateLong
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss a z")
    private Date since;

    public Friendship() {
    }

    public Friendship(Friend first, Friend second) {

        this.first = first;
        this.second = second;
        since = new Date();
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

    public Date getSince() {
        return since;
    }

    public void setSince(Date since) {
        this.since = since;
    }

}
