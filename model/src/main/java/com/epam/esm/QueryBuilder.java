package com.epam.esm;

import com.epam.esm.entity.util.QueryParameters;
import com.epam.esm.entity.util.SortType;
import io.micrometer.common.util.StringUtils;

import java.util.Set;

public class QueryBuilder {

    public static String buildUpdateColumnsQuery(Set<String> columns) {
        StringBuilder query = new StringBuilder();
        boolean isFirstElement = true;
        for (String column : columns) {
            if (!isFirstElement) {
                query.append(", ");
            } else {
                isFirstElement = false;
            }
            query.append(column);
            query.append("=?");
        }
        return query.toString();
    }

    public static String buildGetAllByParametersQuery(QueryParameters queryParameters) {
        StringBuilder getAllByParametersQuery = new StringBuilder("SELECT * FROM gift_certificate");
        String tagName = queryParameters.getTagName();
        if (!StringUtils.isEmpty(tagName)) {
            getAllByParametersQuery.append(" INNER JOIN gift_certificate_has_tag gcht on gift_certificate.id = gcht.gift_certificate_id " +
                    "INNER JOIN tag t on gcht.tag_id = t.id ");
            addParameter(getAllByParametersQuery, tagName);
        }
        String partOfName = queryParameters.getPartOfName();
        if (!StringUtils.isEmpty(partOfName)) {
            addPartParameter(getAllByParametersQuery, "gift_certificate.name", partOfName);
        }
        String partOfDescription = queryParameters.getPartOfDescription();
        if (!StringUtils.isEmpty(partOfDescription)) {
            addPartParameter(getAllByParametersQuery, "description", partOfDescription);
        }
        SortType sortNameParam = queryParameters.getSortNameParam();
        if (sortNameParam != null) {
            addSortParameter(getAllByParametersQuery, "gift_certificate.name", sortNameParam);
        }
        SortType sortDateParam = queryParameters.getSortDateParam();
        if (sortDateParam != null) {
            addSortParameter(getAllByParametersQuery, "create_date", sortDateParam);
        }

        return getAllByParametersQuery.toString();
    }

    private static void addParameter(StringBuilder query, String value) {
        if (query.toString().contains("WHERE")) query.append(" AND ");
        else query.append(" WHERE ");
        query.append("t.name").append("='").append(value).append('\'');
    }

    private static void addPartParameter(StringBuilder query, String partParameter, String value) {
        if (query.toString().contains("WHERE")) query.append(" AND ");
        else query.append(" WHERE ");
        query.append(partParameter).append(" LIKE '%").append(value).append("%'");
    }

    private static void addSortParameter(StringBuilder query, String sortParameter, SortType value) {
        if (query.toString().contains("ORDER BY")) query.append(", ");
        else query.append(" ORDER BY ");
        query.append(sortParameter).append(" ").append(value.name());
    }

}
