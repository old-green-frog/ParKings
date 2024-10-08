package com.king.parking.slotstatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping(path="/statuses") // This means URL's start with /person (after Application path)
public class SlotStatusController {

    @Autowired
    private SlotStatusService service;

    @PostMapping(
            path = "/create/",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<SlotStatus> createStatus(@RequestBody SlotStatus status) {
        service.saveSlotStatus(status);
        return new ResponseEntity<>(status, HttpStatus.CREATED);
    }

    @PutMapping(
            path = "/{id}/",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<SlotStatus> updateStatus(@PathVariable Integer id, @RequestBody SlotStatus status) {
        service.updateSlotStatus(status, id);
        return new ResponseEntity<>(status, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}/")
    public ResponseEntity deleteStatus(@PathVariable Integer id) {
        service.deleteSlotStatus(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/")
    public Iterable<SlotStatus> getAllStatuses(
            @RequestParam(defaultValue = "25") int limit,
            @RequestParam(defaultValue = "1") int page
    ) {
        return service.findAll(limit, page);
    }

    @GetMapping(path="/pages/")
    public Map<String, Object> getPageCount(@RequestParam(defaultValue = "25") int limit) {
        return Map.of("pages_count", service.getStatusPagesCount(limit));
    }
}
