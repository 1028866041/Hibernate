package com.myself.model;

import com.oracle.webservices.internal.api.message.PropertySet;

/**
 * Created by Administrator on 2017/6/16 0016.
 */
public class User {

    private int id;
    private String name;
    private int age;

    public void setId(int id){
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setAge(int age){
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
