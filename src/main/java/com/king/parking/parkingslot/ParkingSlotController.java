package com.king.parking.parkingslot;

import com.king.parking.car.CarRepository;
import com.king.parking.slotstatus.SlotStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(path="/slot")
public class ParkingSlotController {

    @Autowired
    private ParkingSlotService service;


    @PostMapping(path="/create")
    public String createSlot(@ModelAttribute ParkingSlot slot, Model model) {
        service.saveParkingSlot(slot);
        return "redirect:/slot/all";
    }

    @PostMapping(path="/{id}/update")
    public String updateSlot(@PathVariable Integer id, @ModelAttribute ParkingSlot slot, Model model) {
        service.updateParkingSlot(slot, id);
        return "redirect:/slot/all";
    }

    @PostMapping(path="/{id}/delete")
    public String deleteSlot(@PathVariable Integer id, Model model) {
        service.deleteParkingSlot(id);
        return "redirect:/slot/all";
    }

    @GetMapping(path="/all")
    public String getAllSlots(Model model) {
        service.populateModelData(model);
        return "slot/index";
    }

    @GetMapping(path="/pages")
    @ResponseBody
    public Integer getPageCount(@RequestParam(defaultValue = "25") int limit) {
        return service.getSlotPagesCount(limit);
    }
}
