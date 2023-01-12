package com.epam.esm.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;
import java.util.Optional;

public abstract class AbstractDao<T> {

    private final RowMapper<T> rowMapper;
    private final JdbcTemplate jdbcTemplate;

    private final String getByIdQuery;
    private final String getByColumnQuery;
    private final String deleteByIdQuery;
    private final String getAllQuery;

    public AbstractDao(RowMapper<T> rowMapper, JdbcTemplate jdbcTemplate, String tableName) {
        this.rowMapper = rowMapper;
        this.jdbcTemplate = jdbcTemplate;
        getByIdQuery = "SELECT * FROM " + tableName + " WHERE id =?";
        getByColumnQuery = "SELECT * FROM " + tableName + " WHERE %s=?";
        deleteByIdQuery = "DELETE FROM " + tableName + " WHERE id=?";
        getAllQuery = "SELECT * FROM " + tableName;

    }

    public List<T> getAll() {
        return jdbcTemplate.query(getAllQuery, rowMapper);
    }

    public Optional<T> getByColumn(String columnName, String value) {
        String query = String.format(getByColumnQuery, columnName);
        return jdbcTemplate.query(query, rowMapper, value)
                .stream().findAny();
    }

    public Optional<T> getById(long id) {
        return jdbcTemplate.query(getByIdQuery, rowMapper, id)
                .stream().findAny();
    }

    public boolean deleteById(long id) {
        return jdbcTemplate.update(deleteByIdQuery, id) > 0;
    }


}
