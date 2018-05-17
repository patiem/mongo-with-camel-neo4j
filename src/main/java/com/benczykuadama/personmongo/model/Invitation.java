package com.benczykuadama.personmongo.model;

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

    private Boolean accepted = false;
    private Boolean declined = false;

    @DateLong
    private Date since;

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

    public Boolean getAccepted() {
        return accepted;
    }

    public void setAccepted(Boolean accepted) {
        this.accepted = accepted;
    }

    public Boolean getDeclined() {
        return declined;
    }

    public void setDeclined(Boolean declined) {
        this.declined = declined;
    }

    public Date getSince() {
        return since;
    }

    public void setSince(Date since) {
        this.since = since;
    }

    public Friend getToUser() {
        return toUser;
    }

    public void setToUser(Friend toUser) {
        this.toUser = toUser;
    }
}
