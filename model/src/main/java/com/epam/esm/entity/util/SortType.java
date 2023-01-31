package com.epam.esm.entity.util;

import com.epam.esm.exception.InvalidParameterException;

import java.util.Locale;

public enum SortType {
    ASC, DESC;

    public static SortType findSortType(String sortType) {
        SortType sortType1;
        try {
            sortType1 = SortType.valueOf(sortType.toUpperCase(Locale.ROOT));
        } catch (Exception e) {
            throw new InvalidParameterException("invalid-sortparameter");
        }
        return sortType1;
    }

}
