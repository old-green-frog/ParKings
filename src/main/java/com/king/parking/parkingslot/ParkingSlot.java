package com.king.parking.parkingslot;

import com.king.parking.car.Car;
import com.king.parking.person.Person;
import com.king.parking.slotstatus.SlotStatus;
import jakarta.persistence.*;

@Entity
public class ParkingSlot {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String number;
    private Double cost;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private SlotStatus status;

    @OneToOne
    @JoinColumn(name = "car_id")
    private Car car;

    protected ParkingSlot() {}

    @Override
    public String toString() {
        return String.format(
                "ParkingSlot['%s']", number
        );
    }

    public Long getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public Double getCost() {
        return cost;
    }

    public SlotStatus getStatus() {
        return status;
    }

    public Car getCar() {
        return car;
    }

    public Person getPerson() {
        return car.getPerson();
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public void setStatus(SlotStatus status) {
        this.status = status;
    }
}