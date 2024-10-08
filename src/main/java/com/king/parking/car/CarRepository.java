package com.king.parking.car;


import com.king.parking.BaseRepository;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@Repository
public class CarRepository extends BaseRepository<Car> {

    @Override
    public void save(Car obj, boolean isUpdate) {
        String query;
        Map<String, Object> params = new HashMap<>();
        params.put("number", obj.getNumber());
        params.put("person_id", obj.getPerson_id());

        if (isUpdate) {
            params.put("id", obj.getId());
            query = "UPDATE car SET number = :number, person_id = :person_id WHERE id = :id";
        } else {
            jdbcTemplate.update("UPDATE car_seq SET next_val = next_val + 1");
            params.put("id", jdbcTemplate.queryForObject("SELECT next_val FROM car_seq", Long.class));
            query = "INSERT INTO car(id, number, person_id) VALUES (:id, :number, :person_id)";
        }

        SqlParameterSource namedParameters = new MapSqlParameterSource().addValues(params);
        namedJdbcTemplate.update(query, namedParameters);

        if (!isUpdate) {
            obj.setId((Long) params.get("id"));
        }
    }

    @Override
    public Iterable<Car> findAll(int limit, int page) {
        String query = "SELECT * FROM car LIMIT ? OFFSET ?";
        return jdbcTemplate.query(query, baseMapper, limit, (limit * (page - 1)));
    }

    @Override
    public Optional<Car> findById(Integer id) {
        String query = "SELECT * FROM car WHERE id = ?";
        return Optional.ofNullable(jdbcTemplate.queryForObject(query, baseMapper, id));
    }

    @Override
    public void deleteById(Integer id) {
        String query = "DELETE FROM car WHERE id = ?";
        jdbcTemplate.update(query, id);
    }

    @Override
    public Integer getObjectsCount() {
        String query = "SELECT COUNT(*) FROM car";
        return jdbcTemplate.queryForObject(query, Integer.class);
    }
}
