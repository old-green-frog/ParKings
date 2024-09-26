package com.king.parking.car;


public class Car {

    private Long id;
    private String number;
    private Long person_id;

    public Car() {}
    public Car(String number, Long person_id) {
        this.number = number;
        this.person_id = person_id;
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

    public Long getPerson_id() {
        return person_id;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setPerson_id(Long person_id) {
        this.person_id = person_id;
    }
}