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
    private CarRepository carRepository;
    @Autowired
    private SlotStatusRepository statusRepository;
    @Autowired
    private ParkingSlotRepository slotRepository;


    @PostMapping(path="/create")
    public String createSlot(@ModelAttribute ParkingSlot slot, Model model) {
        slotRepository.save(slot);
        return "redirect:/slot/all";
    }

    @PostMapping(path="/{id}/update")
    public String updateSlot(@PathVariable Integer id, @ModelAttribute ParkingSlot slot, Model model) {
        Optional<ParkingSlot> sl = slotRepository.findById(id);
        if (sl.isPresent()) {
            ParkingSlot new_slot = sl.get();
            new_slot.setNumber(slot.getNumber());
            new_slot.setCost(slot.getCost());
            new_slot.setCar(slot.getCar());
            new_slot.setStatus(slot.getStatus());
            slotRepository.save(new_slot);
        }
        return "redirect:/slot/all";
    }

    @PostMapping(path="/{id}/delete")
    public String deleteSlot(@PathVariable Integer id, Model model) {
        Optional<ParkingSlot> sl = slotRepository.findById(id);
        if (sl.isPresent()) {
            slotRepository.deleteById(id);
        }
        return "redirect:/slot/all";
    }

    @GetMapping(path="/all")
    public String getAllSlots(Model model) {
        model.addAttribute("cars", carRepository.findAll());
        model.addAttribute("statuses", statusRepository.findAll());
        model.addAttribute("slots", slotRepository.findAll());
        model.addAttribute("slot", new ParkingSlot());
        return "slot/index";
    }
}
