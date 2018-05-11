package com.benczykuadama.personmongo.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@Document(collection = "users")
public class User {

    @Id
    private String id;

    @Indexed(unique = true)
    private String name;

    private String city;

    private Date birthDate;

    public User() {
    }

    public User(String name, String city, String birthDate) {
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

    public void setBirthDate(String birthDate) {
        this.birthDate = createDate(birthDate);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", birthDate=" + birthDate.toString() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(name, user.name) &&
                Objects.equals(city, user.city) &&
                Objects.equals(birthDate, user.birthDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, city, birthDate);
    }
}
