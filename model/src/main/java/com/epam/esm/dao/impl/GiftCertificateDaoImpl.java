package com.epam.esm.dao.impl;

import com.epam.esm.QueryBuilder;
import com.epam.esm.dao.AbstractDao;
import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.util.QueryParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.*;

@Repository
public class GiftCertificateDaoImpl extends AbstractDao<GiftCertificate> implements GiftCertificateDao {
    private final JdbcTemplate jdbcTemplate;

    private static final String SQL_CREATE_GIFT_CERTIFICATE =
            "INSERT INTO gift_certificate(name, description, price, duration, create_date, last_updated_date) VALUE(?, ?, ?, ?, NOW(), NOW() )";

    private static final String SQL_ADD_TAG_TO_GIFT_CERTIFICATE =
            "INSERT INTO gift_certificate_has_tag(gift_certificate_id, tag_id) VALUES (?, ?)";


    private static final String SQL_UPDATE_GIFT_CERTIFICATE =
            "UPDATE gift_certificate SET last_updated_date = NOW(), ";

    @Autowired
    public GiftCertificateDaoImpl(DataSource dataSource) {
        super(new BeanPropertyRowMapper<>(GiftCertificate.class), new JdbcTemplate(dataSource), "gift_certificate");

        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Optional<GiftCertificate> create(GiftCertificate giftCertificate) {
        jdbcTemplate.update(SQL_CREATE_GIFT_CERTIFICATE, giftCertificate.getName(), giftCertificate.getDescription(), giftCertificate.getPrice(), giftCertificate.getDuration());
        return getById(giftCertificate.getId());
    }

    @Override
    public boolean update(long id, Map<String, Object> giftCertificateUpdateInfo) {
        if (!giftCertificateUpdateInfo.isEmpty()) {
            StringBuilder updateQueryBuilder = new StringBuilder(SQL_UPDATE_GIFT_CERTIFICATE);
            String updateColumnsQuery = QueryBuilder.buildUpdateColumnsQuery(giftCertificateUpdateInfo.keySet());
            updateQueryBuilder.append(updateColumnsQuery);
            updateQueryBuilder.append(" WHERE id=?");
            List<Object> values = new ArrayList<>(giftCertificateUpdateInfo.values());
            values.add(id);
            return jdbcTemplate.update(updateQueryBuilder.toString(), values.toArray()) > 0;
        }
        return false;
    }


    @Override
    public boolean addTagToGiftCertificate(long giftCertificateId, long tagId) {
        return jdbcTemplate.update(SQL_ADD_TAG_TO_GIFT_CERTIFICATE, giftCertificateId, tagId) > 0;
    }



    @Override
    public List<GiftCertificate> getGiftCertificatesByParameters(
            QueryParameters queryParameters) {
        String query = QueryBuilder.buildGetAllByParametersQuery(queryParameters);
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(GiftCertificate.class));
    }
}
