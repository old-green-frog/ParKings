package com.king.parking.person;

import com.king.parking.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// This will be AUTO IMPLEMENTED by Spring into a Bean called personRepository
// CRUD refers Create, Read, Update, Delete

@Repository
public class PersonRepository extends BaseRepository<Person> {

    @Override
    public void save(Person obj) {

        Long id = obj.getId();
        if (id == null) {
            id = jdbcTemplate.queryForObject("SELECT next_val FROM person_seq", Long.class);
        }

        String query =
                String.format(
                        "INSERT INTO person(id, name) " +
                                "VALUES ('%d', '%s') ON DUPLICATE KEY UPDATE " +
                                "name = '%s'",
                        id, obj.getName(), obj.getName()
                );

        jdbcTemplate.execute(query);
    }

    @Override
    public Iterable<Person> findAll() {
        String query = "SELECT * FROM person";
        return jdbcTemplate.query(query, baseMapper);
    }

    @Override
    public Optional<Person> findById(Integer id) {
        String query = "SELECT * FROM person WHERE id = " + id;
        return Optional.ofNullable(jdbcTemplate.queryForObject(query, baseMapper));
    }

    @Override
    public void deleteById(Integer id) {
        String query = "DELETE FROM person WHERE id = " + id;
        jdbcTemplate.execute(query);
    }
}
