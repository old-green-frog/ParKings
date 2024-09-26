package com.king.parking.parkingslot;


public class ParkingSlot {

    private Long id;
    private String number;
    private Double cost;
    private Long status_id;
    private Long car_id;

    public ParkingSlot() {}

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

    public Long getStatus_id() {
        return status_id;
    }

    public Long getCar_id() {
        return car_id;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setCar_id(Long car_id) {
        this.car_id = car_id;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public void setStatus_id(Long status_id) {
        this.status_id = status_id;
    }
}