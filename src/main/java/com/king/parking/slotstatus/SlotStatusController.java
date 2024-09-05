package com.king.parking.slotstatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(path="/status") // This means URL's start with /person (after Application path)
public class SlotStatusController {

    @Autowired
    private SlotStatusRepository statusRepository;

    @PostMapping(path="/create")
    public String createStatus(@ModelAttribute SlotStatus slotStatus, Model model) {
        statusRepository.save(slotStatus);
        return "redirect:/status/all";
    }

    @PostMapping(path="/{id}/update")
    public String updateStatus(@PathVariable Integer id, @ModelAttribute SlotStatus slotStatus, Model model) {
        Optional<SlotStatus> ss = statusRepository.findById(id);
        if (ss.isPresent()) {
            SlotStatus status = ss.get();
            status.setStatus_string(slotStatus.getStatus_string());
            status.setStatus_string_rus(slotStatus.getStatus_string_rus());
            statusRepository.save(status);
        }
        return "redirect:/status/all";
    }

    @PostMapping(path="/{id}/delete")
    public String deleteStatus(@PathVariable Integer id, Model model) {
        Optional<SlotStatus> p = statusRepository.findById(id);
        if (p.isPresent()) {
            statusRepository.deleteById(id);
        }
        return "redirect:/status/all";
    }

    @GetMapping(path="/all")
    public String getAllStatuses(Model model) {
        model.addAttribute("statuses", statusRepository.findAll());
        model.addAttribute("status", new SlotStatus());
        return "status/index";
    }
}
