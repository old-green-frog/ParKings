package com.king.parking.person;


public class Person {

    private Long id;
    private String name;

    public Person() {}
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
