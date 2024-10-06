package com.king.parking.slotstatus;

import com.king.parking.BaseRepository;
import com.king.parking.person.Person;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@Repository
public class SlotStatusRepository extends BaseRepository<SlotStatus> {

    @Override
    public void save(SlotStatus obj, boolean isUpdate) {
        String query;
        Map<String, Object> params = new HashMap<>();
        params.put("status", obj.getStatus_string());
        params.put("status_rus", obj.getStatus_string_rus());

        if (isUpdate) {
            params.put("id", obj.getId());
            query = "UPDATE slot_status SET status_string = :status, status_string_rus = :status_rus WHERE id = :id";
        } else {
            params.put("id", jdbcTemplate.queryForObject("SELECT next_val FROM slot_status_seq", Long.class));
            query = "INSERT INTO slot_status(id, status_string, status_string_rus) VALUES (:id, :status, :status_rus)";
        }

        SqlParameterSource namedParameters = new MapSqlParameterSource().addValues(params);
        namedJdbcTemplate.update(query, namedParameters);
    }

    @Override
    public Iterable<SlotStatus> findAll() {
        String query = "SELECT * FROM slot_status";
        return jdbcTemplate.query(query, baseMapper);
    }

    @Override
    public Iterable<SlotStatus> findAll(int limit, int page) {
        String query = "SELECT * FROM slot_status LIMIT ? OFFSET ?";
        return jdbcTemplate.query(query, baseMapper, limit, (limit * (page - 1)));
    }

    @Override
    public Optional<SlotStatus> findById(Integer id) {
        String query = "SELECT * FROM slot_status WHERE id = ?";
        return Optional.ofNullable(jdbcTemplate.queryForObject(query, baseMapper, id));
    }

    @Override
    public void deleteById(Integer id) {
        String query = "DELETE FROM slot_status WHERE id = ?";
        jdbcTemplate.update(query, id);
    }
}
