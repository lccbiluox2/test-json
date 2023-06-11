package com.fastjson.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Apple implements Fruit {
    private BigDecimal price;
    //省略 setter/getter、toString等
}