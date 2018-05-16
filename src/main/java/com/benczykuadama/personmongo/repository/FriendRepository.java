package com.benczykuadama.personmongo.repository;

import com.benczykuadama.personmongo.model.Friend;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;


import java.util.Map;

@Repository
public interface FriendRepository extends Neo4jRepository<Friend, Long> {

    Friend findByName(String name);

//    @Query("match p=(f:Friend {name:{0}})-[r:FRIENDS_WITH*0..3]-(p2) return p;")
//    Iterable<Map<String, Object>> getFriendsPaths(String friendName);

}