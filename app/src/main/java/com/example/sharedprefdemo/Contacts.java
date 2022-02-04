package com.example.sharedprefdemo;

import java.io.Serializable;

public class Contacts implements Serializable {
    private String name;
    //private String occu;
    private int age;

    public Contacts(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Contacts{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
