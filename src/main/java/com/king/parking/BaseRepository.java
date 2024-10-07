package com.king.parking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Optional;


@Repository
public abstract class BaseRepository<T> {
    @Autowired
    public JdbcTemplate jdbcTemplate;
    @Autowired
    public NamedParameterJdbcTemplate namedJdbcTemplate;
    private Class<T> classFactory;

    public BaseRepository() {
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        classFactory = (Class) pt.getActualTypeArguments()[0];
    }

    public RowMapper<T> baseMapper = (rs, rowNum) -> {
        T obj = null;
        try {
            obj = classFactory.getDeclaredConstructor().newInstance();
            Field[] fields = obj.getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                field.set(obj, rs.getObject(field.getName()));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return obj;
    };

    public abstract void save(T obj, boolean isUpdate);
    public abstract Iterable<T> findAll();
    public abstract Iterable<T> findAll(int limit, int page);
    public abstract Optional<T> findById(Integer id);
    public abstract void deleteById(Integer id);
    public abstract Integer getObjectsCount();
}
