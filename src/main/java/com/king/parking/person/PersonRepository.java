package com.king.parking.person;

import com.king.parking.BaseRepository;
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

        if (isUpdate) {
            params.put("id", obj.getId());
            query = "UPDATE person SET name = :name WHERE id = :id";
        } else {
            params.put("id", jdbcTemplate.queryForObject("SELECT next_val FROM person_seq", Long.class));
            query = "INSERT INTO person(id, name) VALUES (:id, :name)";
        }

        SqlParameterSource namedParameters = new MapSqlParameterSource().addValues(params);
        jdbcTemplate.update(query, namedParameters);
    }

    @Override
    public Iterable<Person> findAll() {
        String query = "SELECT * FROM person";
        return jdbcTemplate.query(query, baseMapper);
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
}
