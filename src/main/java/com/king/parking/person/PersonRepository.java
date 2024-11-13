package com.king.parking.person;

import com.king.parking.BaseRepository;
import com.king.parking.parkingslot.ParkingSlot;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

// This will be AUTO IMPLEMENTED by Spring into a Bean called personRepository
// CRUD refers Create, Read, Update, Delete

@Repository
public class PersonRepository extends BaseRepository<Person> {

    @Override
    public void save(Person obj, boolean isUpdate) {
        String query;
        Map<String, Object> params = new HashMap<>();
        params.put("name", obj.getName());
        params.put("surname", obj.getSurname());
        params.put("middlename", obj.getMiddlename());

        if (isUpdate) {
            params.put("id", obj.getId());
            query = "UPDATE person SET name = :name, surname = :surname, middlename = :middlename WHERE id = :id";
        } else {
            jdbcTemplate.update("UPDATE person_seq SET next_val = next_val + 1");
            params.put("id", jdbcTemplate.queryForObject("SELECT next_val FROM person_seq", Long.class));
            query = "INSERT INTO person(id, name, surname, middlename) VALUES (:id, :name, :surname, :middlename)";
        }

        SqlParameterSource namedParameters = new MapSqlParameterSource().addValues(params);
        namedJdbcTemplate.update(query, namedParameters);

        if (!isUpdate) {
            obj.setId((Long) params.get("id"));
        }
    }

    @Override
    public Iterable<Person> findAll(int limit, int page) {
        String query = "SELECT * FROM person LIMIT ? OFFSET ?";
        return jdbcTemplate.query(query, baseMapper, limit, (limit * (page - 1)));
    }

    @Override
    public Optional<Person> findById(Integer id) {
        String query = "SELECT * FROM person WHERE id = ?";
        return Optional.ofNullable(jdbcTemplate.queryForObject(query, baseMapper, id));
    }

    @Override
    public void deleteById(Integer id) {
        String query = "DELETE FROM person WHERE id = ?";
        jdbcTemplate.update(query, id);
    }

    @Override
    public Integer getObjectsCount() {
        String query = "SELECT COUNT(*) FROM person";
        return jdbcTemplate.queryForObject(query, Integer.class);
    }
}
