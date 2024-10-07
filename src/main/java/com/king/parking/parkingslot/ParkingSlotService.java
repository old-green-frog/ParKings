package com.king.parking.parkingslot;

import com.king.parking.car.Car;
import com.king.parking.car.CarRepository;
import com.king.parking.person.Person;
import com.king.parking.person.PersonRepository;
import com.king.parking.slotstatus.SlotStatus;
import com.king.parking.slotstatus.SlotStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class ParkingSlotService {
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private SlotStatusRepository statusRepository;
    @Autowired
    private ParkingSlotRepository slotRepository;
    @Autowired
    private PersonRepository personRepository;

    public void saveParkingSlot(ParkingSlot slot) {
        slotRepository.save(slot, false);
    }

    public void updateParkingSlot(ParkingSlot slot, Integer id) {
        slot.setId(Integer.toUnsignedLong(id));
        slotRepository.save(slot, true);
    }

    public void deleteParkingSlot(Integer id) {
        Optional<ParkingSlot> sl = slotRepository.findById(id);
        if (sl.isPresent()) {
            slotRepository.deleteById(id);
        }
    }

    public Integer getSlotPagesCount(int limit) {
        return (int) Math.ceil((double) slotRepository.getObjectsCount() / limit);
    }

    public void populateModelData(Model model) {
        Iterable<Car> cars = carRepository.findAll();
        Iterable<SlotStatus> statuses = statusRepository.findAll();
        Iterable<ParkingSlot> slots = slotRepository.findAll();
        Map<Long, Car> slotCarRelate = new HashMap<>();
        Map<Long, SlotStatus> slotStatusRelate = new HashMap<>();

        for (ParkingSlot slot : slots) {
            Optional<Car> car = carRepository.findById(slot.getCar_id().intValue());
            car.ifPresent(value -> slotCarRelate.put(slot.getId(), value));

            Optional<SlotStatus> status = statusRepository.findById(slot.getStatus_id().intValue());
            status.ifPresent(value -> slotStatusRelate.put(slot.getId(), value));
        }

        Iterable<Person> persons = personRepository.findAll();
        Map<Long, Person> personCarRelate = new HashMap<>();
        for (Car car : cars) {
            Optional<Person> person = personRepository.findById(car.getPerson_id().intValue());
            person.ifPresent(value -> personCarRelate.put(car.getId(), value));
        }

        model.addAttribute("cars", carRepository.findAll());
        model.addAttribute("statuses", statusRepository.findAll());
        model.addAttribute("slots", slotRepository.findAll());
        model.addAttribute("car_relate", slotCarRelate);
        model.addAttribute("status_relate", slotStatusRelate);
        model.addAttribute("person_relate", personCarRelate);
        model.addAttribute("slot", new ParkingSlot());
    }
}
