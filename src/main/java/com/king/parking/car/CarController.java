package com.king.parking.car;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/car")
public class CarController {

    @Autowired
    private CarService service;

    @PostMapping(path="/create")
    public String createCar(@ModelAttribute Car car, Model model) {
        service.saveCar(car);
        return "redirect:/car/all";
    }

    @PostMapping(path="/{id}/update")
    public String updateCar(@PathVariable Integer id, @ModelAttribute Car car, Model model) {
        service.updateCar(car, id);
        return "redirect:/car/all";
    }

    @PostMapping(path="/{id}/delete")
    public String deleteCar(@PathVariable Integer id, Model model) {
        service.deleteCar(id);
        return "redirect:/car/all";
    }

    @GetMapping(path="/all")
    public String getAllPersons(Model model) {
        service.populateModelData(model);
        return "car/index";
    }

    @GetMapping(path="/pages")
    @ResponseBody
    public Integer getPageCount(@RequestParam(defaultValue = "25") int limit) {
        return service.getCarsPagesCount(limit);
    }
}
