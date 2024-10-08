package com.king.parking.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(path="/persons") // This means URL's start with /person (after Application path)
public class PersonController {

    @Autowired
    private PersonService service;

    @PostMapping(
            path = "/create/",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Person> createPerson(@RequestBody Person person) {
        service.savePerson(person);
        return new ResponseEntity<>(person, HttpStatus.CREATED);
    }

    @PutMapping(
            path = "/{id}/",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Person> updatePerson(@PathVariable Integer id, @RequestBody Person person) {
        service.updatePerson(person, id);
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}/")
    public ResponseEntity deletePerson(@PathVariable Integer id) {
        service.deletePerson(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/")
    public Iterable<Person> getAllPersons(
            @RequestParam(defaultValue = "25") int limit,
            @RequestParam(defaultValue = "1") int page
    ) {
        return service.findAll(limit, page);
    }

    @GetMapping(path="/pages/")
    public Map<String, Object> getPageCount(@RequestParam(defaultValue = "25") int limit) {
        return Map.of("pages_count", service.getPersonPagesCount(limit));
    }
}
