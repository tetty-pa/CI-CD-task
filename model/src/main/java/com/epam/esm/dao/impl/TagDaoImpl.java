package com.epam.esm.dao.impl;

import com.epam.esm.dao.AbstractDao;
import com.epam.esm.dao.TagDao;
import com.epam.esm.entity.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;


@Repository
public class TagDaoImpl extends AbstractDao<Tag> implements TagDao {
    private final JdbcTemplate jdbcTemplate;
    private static final String SQL_CREATE_TAG = "INSERT INTO tag(name) VALUE(?)";
    private static final String SQL_GET_ALL_TAGS_BY_GIFT_CERTIFICATE_ID =
    "SELECT * FROM tag"+
        " INNER JOIN gift_certificate_has_tag gcht on tag.id = gcht.tag_id"+
        " WHERE gift_certificate_id = ?";


    @Autowired
    public TagDaoImpl(DataSource dataSource) {
        super(new BeanPropertyRowMapper<>(Tag.class), new JdbcTemplate(dataSource), "tag");
        jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public Optional<Tag> create(Tag tag) {
        String name = tag.getName();
        jdbcTemplate.update(SQL_CREATE_TAG, name);
        return getByColumn("name", name);
    }

    @Override
    public List<Tag> getAllByGiftCertificateId(long id) {
       return jdbcTemplate.query(SQL_GET_ALL_TAGS_BY_GIFT_CERTIFICATE_ID
               ,new BeanPropertyRowMapper<>(Tag.class), id);
    }

}
