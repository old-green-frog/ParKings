package com.king.parking.car;


import com.king.parking.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public class CarRepository extends BaseRepository<Car> {

    @Override
    public void save(Car obj) {

        Long id = obj.getId();
        if (id == null) {
            id = jdbcTemplate.queryForObject("SELECT next_val FROM car_seq", Long.class);
        }

        String query =
                String.format(
                        "INSERT INTO car(id, number, person_id) " +
                        "VALUES ('%d', '%s', '%d') ON DUPLICATE KEY UPDATE " +
                                "number = '%s', person_id = '%d'",
                        id, obj.getNumber(), obj.getPerson_id(),
                        obj.getNumber(), obj.getPerson_id()
                );

        jdbcTemplate.execute(query);
    }

    @Override
    public Iterable<Car> findAll() {
        String query = "SELECT * FROM car";
        return jdbcTemplate.query(query, baseMapper);
    }

    @Override
    public Optional<Car> findById(Integer id) {
        String query = "SELECT * FROM car WHERE id = " + id;
        return Optional.ofNullable(jdbcTemplate.queryForObject(query, baseMapper));
    }

    @Override
    public void deleteById(Integer id) {
        String query = "DELETE FROM car WHERE id = " + id;
        jdbcTemplate.execute(query);
    }
}
