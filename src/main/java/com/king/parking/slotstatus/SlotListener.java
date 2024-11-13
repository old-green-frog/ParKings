package com.king.parking.slotstatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class SlotListener {

    @Autowired
    private SlotStatusRepository statusRepository;

    @EventListener
    public void handleContextRefresh(ContextRefreshedEvent event) {
        int status_counter = 0;
        for (Object status: statusRepository.findAll(25, 1)) {
            status_counter++;
        }
        if (status_counter <= 0) {
            for (Status status: Status.values()) {
                statusRepository.save(new SlotStatus(status), false);
            }
        }
    }
}
