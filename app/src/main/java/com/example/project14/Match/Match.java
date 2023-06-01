package com.example.project14.Match;

public class Match {
    private String name;
    private int age;
    private String imageURL;
    private String city;


    public Match(String name, int age, String city, String imageURL) {
        this.name = name;
        this.age = age;
        this.city = city;
        this.imageURL = imageURL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
