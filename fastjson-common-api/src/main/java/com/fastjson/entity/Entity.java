package com.fastjson.entity;

public class Entity {
    private Integer id;
    private String name;
    private Object value;

    public Entity() {
    }

    public Entity(Integer id, Object value) {
        this.id = id;
        this.value = value;
    }

    public Entity(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Entity(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public Object getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}