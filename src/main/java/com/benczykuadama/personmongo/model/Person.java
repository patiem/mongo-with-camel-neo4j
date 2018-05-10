package com.benczykuadama.personmongo.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Document(collection = "person")
public class Person {

    @Id
    private String id;

    @Indexed(unique = true)
    private String name;

    private String city;

    private Date birthDate;

    public Person() {
    }

    public Person(String name, String city, String birthDate) {
        this.name = name;
        this.city = city;
        this.birthDate = createDate(birthDate);
    }

    private Date createDate(String birthDate) {
        try {
            Date date = new SimpleDateFormat("dd-MM-yyyy").parse(birthDate);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return new Date();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", birthDate=" + birthDate.toString() +
                '}';
    }
}
