package com.king.parking.car;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CarService {
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

    public Iterable<Car> findAll(int limit, int page) {
        return carRepository.findAll(limit, page);
    }
}
