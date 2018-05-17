package com.benczykuadama.personmongo.repository;

import com.benczykuadama.personmongo.model.Friend;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;


import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public interface FriendRepository extends Neo4jRepository<Friend, Long> {

    Friend findByName(String name);

    @Query("MATCH (me:Friend {name:{0}})-[:FRIENDS_WITH]-(friend:Friend) RETURN friend")
    List<Friend> findAllFriends(String name);

    @Query("MATCH (me:Friend {name:{0}})-[:FRIENDS_WITH*]-(friend:Friend) RETURN friend")
    List<Friend> findNetwork(String name);

    @Query("MATCH (u:Friend {name:{0}}), (f:Friend {name:{1}}) CREATE (u)-[:FRIENDS_WITH {dateAdded:{2}}]->(f)")
    void invite(String name, String name2, Date date);
}


//    @Query("MATCH p=(f:Friend {name:{0}})-[r:FRIENDS_WITH*0..3]-(p2) return p;")
//    Iterable<Map<String, Friend>> getFriendsPaths(String friendName);

//    MATCH (a:LabelA {id: '123abc'), (res:Resource {id: '321cba'})
//        WHERE NOT(()-[:has_resource]->res)
//        CREATE a-[:has_resource]->res