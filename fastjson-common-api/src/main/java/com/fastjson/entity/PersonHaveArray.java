package com.fastjson.entity;

import lombok.Data;

import java.util.List;

@Data
public class PersonHaveArray {
    private Integer id;
    private String name;
    private List<String> list;
    private PersonHaveArray[] personHaveArrays;
    private List<PersonHaveArray> personHaveArrayList;
    private String chName;
}
