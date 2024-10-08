package com.king.parking.slotstatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SlotStatusService {
    @Autowired
    private SlotStatusRepository statusRepository;

    public void saveSlotStatus(SlotStatus slotStatus) {
        statusRepository.save(slotStatus, false);
    }

    public void updateSlotStatus(SlotStatus slotStatus, Integer id) {
        slotStatus.setId(Integer.toUnsignedLong(id));
        statusRepository.save(slotStatus, true);
    }

    public void deleteSlotStatus(Integer id) {
        Optional<SlotStatus> p = statusRepository.findById(id);
        if (p.isPresent()) {
            statusRepository.deleteById(id);
        }
    }

    public Integer getStatusPagesCount(int limit) {
        return (int) Math.ceil((double) statusRepository.getObjectsCount() / limit);
    }

    public Iterable<SlotStatus> findAll(int limit, int page) {
        return statusRepository.findAll(limit, page);
    }
}
