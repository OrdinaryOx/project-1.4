package com.example.project14;

public class Chat {
    private String username;
    private String test;
    private int age;

    public Chat(String username, int age, String test){
        this.username =username;
        this.age = age;
        this.test = test;
    }


    public int getAge() {
        return age;
    }

    public void setAge(int age){
        this.age = age;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }
}
