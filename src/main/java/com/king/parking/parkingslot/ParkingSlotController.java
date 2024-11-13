package com.king.parking.parkingslot;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping(path="/slots")
public class ParkingSlotController {

    @Autowired
    private ParkingSlotService service;

    @PostMapping(
            path = "/create/",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ParkingSlot> createSlot(@RequestBody ParkingSlot slot) {
        service.saveParkingSlot(slot);
        return new ResponseEntity<>(slot, HttpStatus.CREATED);
    }

    @PutMapping(
            path = "/{id}/",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ParkingSlot> updateSlot(@PathVariable Integer id, @RequestBody ParkingSlot slot) {
        service.updateParkingSlot(slot, id);
        return new ResponseEntity<>(slot, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}/")
    public ResponseEntity deleteSlot(@PathVariable Integer id) {
        service.deleteParkingSlot(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/")
    public Iterable<ParkingSlot> getAllSlots(
            @RequestParam(defaultValue = "25") int limit,
            @RequestParam(defaultValue = "1") int page
    ) {
        return service.findAll(limit, page);
    }

    @GetMapping(path="/pages/")
    public Map<String, Object> getPageCount(@RequestParam(defaultValue = "25") int limit) {
        return Map.of("pages_count", service.getSlotPagesCount(limit));
    }
}
