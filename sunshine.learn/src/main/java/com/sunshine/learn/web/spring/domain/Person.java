package com.sunshine.learn.web.spring.domain;

import lombok.Data;

@Data
public class Person {

    private String name;
    private Integer age;

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}
