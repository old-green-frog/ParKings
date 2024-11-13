package com.king.parking.parkingslot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ParkingSlotService {
    @Autowired
    private ParkingSlotRepository slotRepository;

    public void saveParkingSlot(ParkingSlot slot) {
        slotRepository.save(slot, false);
    }

    public void updateParkingSlot(ParkingSlot slot, Integer id) {
        slot.setId(Integer.toUnsignedLong(id));
        slotRepository.save(slot, true);
    }

    public void deleteParkingSlot(Integer id) {
        Optional<ParkingSlot> sl = slotRepository.findById(id);
        if (sl.isPresent()) {
            slotRepository.deleteById(id);
        }
    }

    public Integer getSlotPagesCount(int limit) {
        return (int) Math.ceil((double) slotRepository.getObjectsCount() / limit);
    }

    public Iterable<ParkingSlot> findAll(int limit, int page) {
        return slotRepository.findAll(limit, page);
    }
}
