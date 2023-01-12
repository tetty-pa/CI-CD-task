package com.epam.esm.entity.util;


import io.micrometer.common.util.StringUtils;


public class QueryParameters {

    private String partOfName;
    private String partOfDescription;
    private String tagName;
    private SortType sortNameParam;
    private SortType sortDateParam;


    public QueryParameters(String partOfName, String partOfDescription, String tagName, SortType sortNameParam, SortType sortDateParam) {
        if (!StringUtils.isEmpty(partOfName)) {
            this.partOfName = partOfName;
        }
        if (!StringUtils.isEmpty(partOfDescription)) {
            this.partOfDescription = partOfDescription;
        }
        if (!StringUtils.isEmpty(tagName)) {
            this.tagName = tagName;
        }
        if (sortNameParam != null) {
            this.sortNameParam = sortNameParam;
        }

        if (sortDateParam != null) {
            this.sortDateParam = sortDateParam;
        }
    }

    public String getPartOfName() {
        return partOfName;
    }

    public void setPartOfName(String partOfName) {
        this.partOfName = partOfName;
    }

    public String getPartOfDescription() {
        return partOfDescription;
    }

    public void setPartOfDescription(String partOfDescription) {
        this.partOfDescription = partOfDescription;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public SortType getSortNameParam() {
        return sortNameParam;
    }

    public void setSortNameParam(SortType sortNameParam) {
        this.sortNameParam = sortNameParam;
    }

    public SortType getSortDateParam() {
        return sortDateParam;
    }

    public void setSortDateParam(SortType sortDateParam) {
        this.sortDateParam = sortDateParam;
    }

    public boolean isEmpty() {
        return partOfName == null && partOfDescription == null && tagName == null && sortNameParam == null && sortDateParam == null ;
    }
}