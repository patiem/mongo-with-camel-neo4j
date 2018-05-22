#### Dependencies

    spring-boot = "1.5.14"
    camel = "2.21.0"
    activeMQ = "5.14.0"
    springfox-swagger = "2.8.0"
    de.flapdoodle.embed.mongo = "2.0.3"

#### System requirements

* Java 8
* Maven
* MongoDB
* Neo4j

#### Environment Setup


1.Start MongoDB and Neo4j as services

2.Neo4j requires login: "neo4j" and password "password".
  You can change them in application-properties to correct ones:
    spring.data.neo4j.username=neo4j
    spring.data.neo4j.password=password
    
#### Running

```bash
$ mvn clean spring-boot:run
``` 

Rest Api can be found on [SWAGGER UI](http://localhost:8080/swagger-ui.html) - it doesnt need any external resources, it's part of application. 

You can see Neo4j's nodes and relation through [NEO4J UI](http://localhost:7474/browser/)

#### Tests

There are no working test :( Shame on me!


