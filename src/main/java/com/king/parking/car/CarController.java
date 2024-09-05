package com.king.parking.car;

import com.king.parking.person.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(path="/car")
public class CarController {

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private CarRepository carRepository;

    @PostMapping(path="/create")
    public String createCar(@ModelAttribute Car car, Model model) {
        carRepository.save(car);
        return "redirect:/car/all";
    }

    @PostMapping(path="/{id}/update")
    public String updateCar(@PathVariable Integer id, @ModelAttribute Car car, Model model) {
        Optional<Car> cr = carRepository.findById(id);
        if (cr.isPresent()) {
            Car new_car = cr.get();
            new_car.setNumber(car.getNumber());
            new_car.setPerson(car.getPerson());
            carRepository.save(new_car);
        }
        return "redirect:/car/all";
    }

    @PostMapping(path="/{id}/delete")
    public String deleteCar(@PathVariable Integer id, Model model) {
        Optional<Car> cr = carRepository.findById(id);
        if (cr.isPresent()) {
            carRepository.deleteById(id);
        }
        return "redirect:/car/all";
    }

    @GetMapping(path="/all")
    public String getAllPersons(Model model) {
        model.addAttribute("persons", personRepository.findAll());
        model.addAttribute("cars", carRepository.findAll());
        model.addAttribute("car", new Car());
        return "car/index";
    }
}
