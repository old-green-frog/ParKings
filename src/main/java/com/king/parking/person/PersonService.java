package com.king.parking.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public void savePerson(Person person) {
        personRepository.save(person, false);
    }

    public void updatePerson(Person person, Integer id) {
        Optional<Person> p = personRepository.findById(id);
        if (p.isPresent()) {
            Person pers = p.get();
            pers.setName(person.getName());
            personRepository.save(pers, true);
        }
    }

    public void deletePerson(Integer id) {
        Optional<Person> p = personRepository.findById(id);
        if (p.isPresent()) {
            personRepository.deleteById(id);
        }
    }

    public void populateModelData(Model model) {
        model.addAttribute("persons", personRepository.findAll());
        model.addAttribute("person", new Person());
    }
}