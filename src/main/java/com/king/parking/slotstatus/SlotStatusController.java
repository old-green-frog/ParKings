package com.king.parking.slotstatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping(path="/status") // This means URL's start with /person (after Application path)
public class SlotStatusController {

    @Autowired
    private SlotStatusService service;

    @PostMapping(path="/create")
    public String createStatus(@ModelAttribute SlotStatus slotStatus, Model model) {
        service.saveSlotStatus(slotStatus);
        return "redirect:/status/all";
    }

    @PostMapping(path="/{id}/update")
    public String updateStatus(@PathVariable Integer id, @ModelAttribute SlotStatus slotStatus, Model model) {
        service.updateSlotStatus(slotStatus, id);
        return "redirect:/status/all";
    }

    @PostMapping(path="/{id}/delete")
    public String deleteStatus(@PathVariable Integer id, Model model) {
        service.deleteSlotStatus(id);
        return "redirect:/status/all";
    }

    @GetMapping(path="/all")
    public String getAllStatuses(Model model) {
        service.populateModelData(model);
        return "status/index";
    }
}
