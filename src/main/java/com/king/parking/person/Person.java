package com.king.parking.person;

import jakarta.persistence.*;


@Entity
public class Person {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String name;

    protected Person() {}
    public Person(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format(
            "Person['%s']", name
        );
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
