package com.king.parking.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public void savePerson(Person person) {
        personRepository.save(person, false);
    }

    public void updatePerson(Person person, Integer id) {
        person.setId(Integer.toUnsignedLong(id));
        personRepository.save(person, true);
    }

    public void deletePerson(Integer id) {
        Optional<Person> p = personRepository.findById(id);
        if (p.isPresent()) {
            personRepository.deleteById(id);
        }
    }

    public Integer getPersonPagesCount(int limit) {
        return (int) Math.ceil((double) personRepository.getObjectsCount() / limit);
    }

    public Iterable<Person> findAll(int limit, int page) {
        return personRepository.findAll(limit, page);
    }
}