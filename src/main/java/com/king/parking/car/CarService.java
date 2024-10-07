package com.king.parking.car;


import com.king.parking.person.Person;
import com.king.parking.person.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class CarService {

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private CarRepository carRepository;

    public void saveCar(Car car) {
        carRepository.save(car, false);
    }

    public void updateCar(Car car, Integer id) {
        car.setId(Integer.toUnsignedLong(id));
        carRepository.save(car, true);
    }

    public void deleteCar(Integer id) {
        Optional<Car> cr = carRepository.findById(id);
        if (cr.isPresent()) {
            carRepository.deleteById(id);
        }
    }

    public Integer getCarsPagesCount(int limit) {
        return (int) Math.ceil((double) carRepository.getObjectsCount() / limit);
    }

    public void populateModelData(Model model) {
        Map<Long, Person> personCarRelate = new HashMap<>();
        Iterable<Car> cars = carRepository.findAll();
        Iterable<Person> persons = personRepository.findAll();
        for (Car car : cars) {
            Optional<Person> person = personRepository.findById(car.getPerson_id().intValue());
            person.ifPresent(value -> personCarRelate.put(car.getId(), value));
        }
        model.addAttribute("persons", persons);
        model.addAttribute("cars", cars);
        model.addAttribute("person_relate", personCarRelate);
        model.addAttribute("car", new Car());
    }
}
