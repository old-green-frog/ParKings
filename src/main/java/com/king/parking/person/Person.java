package com.king.parking.person;


public class Person {

    private Long id;
    private String name;
    private String surname;
    private String middlename;


    public Person() {}
    public Person(String name, String surname, String middlename) {
        this.name = name;
        this.surname = surname;
        this.middlename = middlename;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
