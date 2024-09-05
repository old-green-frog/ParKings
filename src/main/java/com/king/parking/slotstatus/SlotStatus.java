package com.king.parking.slotstatus;

import jakarta.persistence.*;

@Entity
public class SlotStatus {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String status_string;
    private String status_string_rus;

    protected SlotStatus() {}

    public SlotStatus(String status_string) {
        Status status = Status.valueOfEn(status_string);
        this.status_string = status.en;
        this.status_string_rus = status.ru;
    }
    public SlotStatus(Status status) {
        this.status_string = status.en;
        this.status_string_rus = status.ru;
    }

    @Override
    public String toString() {
        return String.format(
                "Status['%s']", status_string
        );
    }

    public Long getId() {
        return id;
    }

    public String getStatus_string() {
        return status_string;
    }

    public void setStatus_string(String status_string) {
        this.status_string = status_string;
    }

    public String getStatus_string_rus() {
        return status_string_rus;
    }

    public void setStatus_string_rus(String status_string_rus) {
        this.status_string_rus = status_string_rus;
    }
}
