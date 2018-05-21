package com.benczykuadama.personmongo.repository;

import com.benczykuadama.personmongo.model.Friend;
import com.benczykuadama.personmongo.model.Invitation;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public interface FriendRepository extends Neo4jRepository<Friend, Long> {

    Friend findByName(String name);

    @Query("MATCH (me:Friend {name:{0}})-[:FRIENDS_WITH]-(friend:Friend) RETURN friend")
    List<Friend> findAllFriends(String name);

    @Query("MATCH (me:Friend {name:{0}})-[:FRIENDS_WITH*]-(friend:Friend) RETURN friend")
    List<Friend> findNetwork(String name);

    @Query("MATCH (u:Friend {name:{1}}), (f:Friend {name:{0}}) CREATE (u)-[:IS_INVITED {sendOn: {2}}]->(f)")
    void invite(String name, String name2, Date date);

    @Query("MATCH (u:Friend {name:{0}})-[r]-(f:Friend {name:{1}}) WHERE type(r)='IS_INVITED' CREATE (u)-[:FRIENDS_WITH {since: {2}}]->(f) DELETE r")
    void makeFriend(String name, String name2, Date date);

    @Query("MATCH (u:Friend {name:{0}})-[rel:IS_INVITED]-(f:Friend) RETURN rel")
    List<Invitation> getInvitations(String name);

    @Query("MATCH (u:Friend {name:{0}})-[r]-(f:Friend {name:{1}}) WHERE type(r)='FRIENDS_WITH' DELETE r")
    void unfriend(String user, String friend);

    @Query("MATCH (me:Friend { name: {0}}),(f:Friend { name: {1}}), p = shortestPath((me)-[*]-(f))\n" +
            "WHERE NONE (r IN relationships(p) WHERE type(r)= 'IS_INVITED') RETURN p")
    ArrayList<Friend> pathTo(String user, String friend);

    @Query("MATCH (me:Friend { name: {0}}),(f:Friend { name: {1}}), p = shortestPath((me)-[*]-(f))\n" +
            "WHERE NONE (r IN relationships(p) WHERE type(r)= 'IS_INVITED') RETURN length(p)")
    Integer distanceBetween(String user, String friend);
}