package com.benczykuadama.personmongo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;
import org.neo4j.ogm.annotation.typeconversion.DateLong;

import java.util.Date;

@RelationshipEntity(type = "IS_INVITED")
public class Invitation {

    @GraphId
    private Long id;

    @StartNode
    @JsonSerialize(using = CustomSerializer.class)
    private Friend toUser;

    @EndNode
    @JsonSerialize(using = CustomSerializer.class)
    private Friend fromUser;

    @DateLong
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss a z")
    private Date sendOn;

    public Invitation() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Friend getFromUser() {
        return fromUser;
    }

    public void setFromUser(Friend fromUser) {
        this.fromUser = fromUser;
    }

    public Date getSendOn() {
        return sendOn;
    }

    public void setSendOn(Date sendOn) {
        this.sendOn = sendOn;
    }

    public Friend getToUser() {
        return toUser;
    }

    public void setToUser(Friend toUser) {
        this.toUser = toUser;
    }
}
