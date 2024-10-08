package com.king.parking.car;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(path="/cars")
public class CarController {

    @Autowired
    private CarService service;

    @PostMapping(
            path = "/create/",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Car> createCar(@RequestBody Car car) {
        service.saveCar(car);
        return new ResponseEntity<>(car, HttpStatus.CREATED);
    }

    @PutMapping(
            path = "/{id}/",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Car> updateCar(@PathVariable Integer id, @RequestBody Car car) {
        service.updateCar(car, id);
        return new ResponseEntity<>(car, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}/")
    public ResponseEntity deleteCar(@PathVariable Integer id) {
        service.deleteCar(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/")
    public Iterable<Car> getAllCars(
            @RequestParam(required = false, defaultValue = "25") int limit,
            @RequestParam(required = false, defaultValue = "1") int page
    ) {
        return service.findAll(limit, page);
    }

    @GetMapping(path="/pages/")
    public Map<String, Object> getPageCount(@RequestParam(defaultValue = "25") int limit) {
        return Map.of("pages_count", service.getCarsPagesCount(limit));

    }
}
