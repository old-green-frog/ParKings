package com.king.parking.parkingslot;

import com.king.parking.BaseRepository;
import com.king.parking.car.Car;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;


@Repository
public class ParkingSlotRepository extends BaseRepository<ParkingSlot> {

    @Override
    public void save(ParkingSlot obj, boolean isUpdate) {
        String query;
        Map<String, Object> params = new HashMap<>();
        params.put("number", obj.getNumber());
        params.put("cost", obj.getCost());
        params.put("status_id", obj.getStatus_id());
        params.put("car_id", obj.getCar_id());

        if (isUpdate) {
            params.put("id", obj.getId());
            query = "UPDATE parking_slot SET " +
                    "number = :number, cost = :cost, status_id = :status_id, car_id = :car_id WHERE id = :id";
        } else {
            params.put("id", jdbcTemplate.queryForObject("SELECT next_val FROM parking_slot_seq", Long.class));
            query = "INSERT INTO parking_slot(id, number, cost, status_id, car_id) " +
                    "VALUES (:id, :number, :cost, :status_id, :car_id)";
        }

        SqlParameterSource namedParameters = new MapSqlParameterSource().addValues(params);
        namedJdbcTemplate.update(query, namedParameters);
    }

    @Override
    public Iterable<ParkingSlot> findAll() {
        String query = "SELECT * FROM parking_slot";
        return jdbcTemplate.query(query, baseMapper);
    }

    @Override
    public Iterable<ParkingSlot> findAll(int limit, int page) {
        String query = "SELECT * FROM parking_slot LIMIT ? OFFSET ?";
        return jdbcTemplate.query(query, baseMapper, limit, (limit * (page - 1)));
    }

    @Override
    public Optional<ParkingSlot> findById(Integer id) {
        String query = "SELECT * FROM parking_slot WHERE id = ?";
        return Optional.ofNullable(jdbcTemplate.queryForObject(query, baseMapper, id));
    }

    @Override
    public void deleteById(Integer id) {
        String query = "DELETE FROM parking_slot WHERE id = ?";
        jdbcTemplate.update(query, id);
    }

    @Override
    public Integer getObjectsCount() {
        String query = "SELECT COUNT(*) FROM parking_slot";
        return jdbcTemplate.queryForObject(query, Integer.class);
    }
}
