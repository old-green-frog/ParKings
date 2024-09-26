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
        statusRepository.save(slotStatus);
    }

    public void updateSlotStatus(SlotStatus slotStatus, Integer id) {
        Optional<SlotStatus> ss = statusRepository.findById(id);
        if (ss.isPresent()) {
            SlotStatus status = ss.get();
            status.setStatus_string(slotStatus.getStatus_string());
            status.setStatus_string_rus(slotStatus.getStatus_string_rus());
            statusRepository.save(status);
        }
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
