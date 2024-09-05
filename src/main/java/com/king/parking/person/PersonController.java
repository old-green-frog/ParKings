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
    private PersonRepository personRepository;

    @PostMapping(path="/create")
    public String createPerson(@ModelAttribute Person person, Model model) {
        personRepository.save(person);
        return "redirect:/person/all";
    }

    @PostMapping(path="/{id}/update")
    public String updatePerson(@PathVariable Integer id, @ModelAttribute Person person, Model model) {
        Optional<Person> p = personRepository.findById(id);
        if (p.isPresent()) {
            Person pers = p.get();
            pers.setName(person.getName());
            personRepository.save(pers);
        }
        return "redirect:/person/all";
    }

    @PostMapping(path="/{id}/delete")
    public String deletePerson(@PathVariable Integer id, Model model) {
        Optional<Person> p = personRepository.findById(id);
        if (p.isPresent()) {
            personRepository.deleteById(id);
        }
        return "redirect:/person/all";
    }

    @GetMapping(path="/all")
    public String getAllPersons(Model model) {
        model.addAttribute("persons", personRepository.findAll());
        model.addAttribute("person", new Person());
        return "person/index";
    }
}
