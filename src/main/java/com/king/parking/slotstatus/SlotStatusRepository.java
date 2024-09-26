package com.king.parking.slotstatus;

import com.king.parking.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public class SlotStatusRepository extends BaseRepository<SlotStatus> {

    @Override
    public void save(SlotStatus obj) {

        Long id = obj.getId();
        if (id == null) {
            id = jdbcTemplate.queryForObject("SELECT next_val FROM slot_status_seq", Long.class);
        }


        String query =
                String.format(
                        "INSERT INTO slot_status(id, status_string, status_string_rus) " +
                                "VALUES ('%d', '%s', '%s') ON DUPLICATE KEY UPDATE " +
                                "status_string = '%s', status_string_rus = '%s'",
                        id, obj.getStatus_string(), obj.getStatus_string_rus(),
                        obj.getStatus_string(), obj.getStatus_string_rus()
                );

        jdbcTemplate.execute(query);
    }

    @Override
    public Iterable<SlotStatus> findAll() {
        String query = "SELECT * FROM slot_status";
        return jdbcTemplate.query(query, baseMapper);
    }

    @Override
    public Optional<SlotStatus> findById(Integer id) {
        String query = "SELECT * FROM slot_status WHERE id = " + id;
        return Optional.ofNullable(jdbcTemplate.queryForObject(query, baseMapper));
    }

    @Override
    public void deleteById(Integer id) {
        String query = "DELETE FROM slot_status WHERE id = " + id;
        jdbcTemplate.execute(query);
    }
}
