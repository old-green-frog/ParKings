package com.king.parking.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(path="/person") // This means URL's start with /person (after Application path)
public class PersonController {

    @Autowired
    private PersonService service;

    @PostMapping(path="/create")
    public String createPerson(@ModelAttribute Person person, Model model) {
        service.savePerson(person);
        return "redirect:/person/all";
    }

    @PostMapping(path="/{id}/update")
    public String updatePerson(@PathVariable Integer id, @ModelAttribute Person person, Model model) {
        service.updatePerson(person, id);
        return "redirect:/person/all";
    }

    @PostMapping(path="/{id}/delete")
    public String deletePerson(@PathVariable Integer id, Model model) {
        service.deletePerson(id);
        return "redirect:/person/all";
    }

    @GetMapping(path="/all")
    public String getAllPersons(Model model) {
        service.populateModelData(model);
        return "person/index";
    }
}
