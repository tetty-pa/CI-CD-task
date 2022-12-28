package com.epam.esm.dao;

import com.epam.esm.entity.GiftCertificate;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Repository
public class GiftCertificateDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/rest-api";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static final String SQL = "SELECT * FROM gift_certificate";


    private static Connection connection;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<GiftCertificate> getAll() {
        List<GiftCertificate> giftCertifications = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL);

            while (resultSet.next()) {

                GiftCertificate giftCertificate = new GiftCertificate(resultSet.getInt("id"));

                giftCertificate.setName(resultSet.getString("name"));
                giftCertificate.setDescription(resultSet.getString("description"));
                giftCertificate.setPrice(resultSet.getBigDecimal("price"));
                giftCertificate.setDuration(resultSet.getInt("duration"));
               // giftCertificate.setCreateDate(resultSet.getTimestamp("created_date").toLocalDateTime().atZone(ZoneId.of("GMT+3")));
                //giftCertificate.setLastUpdateDate(resultSet.getTimestamp("last_updated_date").toLocalDateTime().atZone(ZoneId.of("GMT+3")));
                giftCertifications.add(giftCertificate);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return giftCertifications;
    }
}
