package com.king.parking.slotstatus;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

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

    public void populateModelData(Model model) {
        model.addAttribute("statuses", statusRepository.findAll());
        model.addAttribute("status", new SlotStatus());
    }
}
