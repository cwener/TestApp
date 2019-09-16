package com.example.testapp;

import java.io.Serializable;

/**
 * Created by chengwen on 2019-07-06
 */
public class CloudMeta implements Serializable {
    private long id;
    private String name;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
