package com.king.parking.slotstatus;

import java.util.HashMap;
import java.util.Map;

public enum Status {
    OCCUPIED("occupied", "Занято"),
    AVAILABLE("available", "Свободно"),
    RESERVED("reserved", "Зарезервировано"),
    PAYMENT_AWAIT("payment_await", "В ожидании оплаты");

    private static final Map<String, Status> BY_EN = new HashMap<>();

    static {
        for (Status status : values()) {
            BY_EN.put(status.en, status);
        }
    }

    public final String en;
    public final String ru;

    Status(String en, String ru) {
        this.en = en;
        this.ru = ru;
    }

    public static Status valueOfEn(String en) {
        return BY_EN.get(en);
    }
}
