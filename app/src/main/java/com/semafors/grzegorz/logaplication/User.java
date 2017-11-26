package com.semafors.grzegorz.logaplication;

import java.io.Serializable;

/**
 * Created by grzegorz on 23.11.17.
 */

public class User implements Serializable{

    Long id;
    String name;
    String sName;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    @Override
    public String toString() {
        return name + " " + sName;
    }
}
