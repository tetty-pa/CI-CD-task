package com.epam.esm.model.entity.util;



import java.util.List;


public class QueryParameters {

    private String partOfName;
    private String partOfDescription;
    private List<String> tagNames;
    private SortType sortNameParam;
    private SortType sortDateParam;


    public QueryParameters(String partOfName, String partOfDescription, List
            <String> tagNames, SortType sortNameParam, SortType sortDateParam) {
        this.partOfName = partOfName;
        this.partOfDescription = partOfDescription;
        if (!tagNames.isEmpty()) {
            this.tagNames = tagNames;
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

    public List<String> getTagNames() {
        return tagNames;
    }

    public void setTagNames(List<String> tagNames) {
        this.tagNames = tagNames;
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

}