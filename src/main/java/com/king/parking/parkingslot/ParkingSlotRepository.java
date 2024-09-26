package com.king.parking.parkingslot;

import com.king.parking.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Locale;
import java.util.Optional;


@Repository
public class ParkingSlotRepository extends BaseRepository<ParkingSlot> {

    @Override
    public void save(ParkingSlot obj) {

        Long id = obj.getId();
        if (id == null) {
            id = jdbcTemplate.queryForObject("SELECT next_val FROM parking_slot_seq", Long.class);
        }

        String query = String.format(
                Locale.US,
                "INSERT INTO parking_slot(id, number, cost, status_id, car_id) " +
                        "VALUES ('%d', '%s', '%.2f', '%d', '%d') ON DUPLICATE KEY UPDATE " +
                        "number = '%s', cost = '%.2f', status_id = '%d', car_id = '%d'",
                id, obj.getNumber(), obj.getCost(), obj.getStatus_id(), obj.getCar_id(),
                obj.getNumber(), obj.getCost(), obj.getStatus_id(), obj.getStatus_id()
        );

        jdbcTemplate.execute(query);
    }

    @Override
    public Iterable<ParkingSlot> findAll() {
        String query = "SELECT * FROM parking_slot";
        return jdbcTemplate.query(query, baseMapper);
    }

    @Override
    public Optional<ParkingSlot> findById(Integer id) {
        String query = "SELECT * FROM parking_slot WHERE id = " + id;
        return Optional.ofNullable(jdbcTemplate.queryForObject(query, baseMapper));
    }

    @Override
    public void deleteById(Integer id) {
        String query = "DELETE FROM parking_slot WHERE id = " + id;
        jdbcTemplate.execute(query);
    }
}
