package com.king.parking.car;

import com.king.parking.person.Person;
import jakarta.persistence.*;

@Entity
public class Car {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String number;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    protected Car() {}
    public Car(String number, Person person) {
        this.number = number;
        this.person = person;
    }

    @Override
    public String toString() {
        return String.format(
                "Car['%s']", number
        );
    }

    public Long getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public Person getPerson() {
        return person;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}