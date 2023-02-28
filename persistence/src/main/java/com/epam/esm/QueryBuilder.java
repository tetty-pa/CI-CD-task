package com.epam.esm;

/*

public class QueryBuilder {


    public static CriteriaQuery<GiftCertificate> buildGetAllByParametersQuery(QueryParameters queryParameters, CriteriaBuilder criteriaBuilder*/
/*, CriteriaQuery<GiftCertificate> criteriaQuery*//*
*/
/*, Root<GiftCertificate> root*//*
) {
        CriteriaQuery<GiftCertificate> criteriaQuery = criteriaBuilder.createQuery(GiftCertificate.class);
        Root<GiftCertificate> root = criteriaQuery.from(GiftCertificate.class);

        List<Predicate> restrictions = getPredicates(queryParameters, criteriaBuilder, root);
        if (!restrictions.isEmpty()) {
            criteriaQuery.select(root).where(restrictions.toArray(new Predicate[]{}));
        }
        getSorting(queryParameters, criteriaBuilder, criteriaQuery, root);
        return criteriaQuery;
    }

    private static void getSorting(QueryParameters queryParameters, CriteriaBuilder criteriaBuilder, CriteriaQuery<GiftCertificate> criteriaQuery, Root<GiftCertificate> root) {
        SortType sortNameParam = queryParameters.getSortNameParam();
        if (sortNameParam != null) {
            if (Objects.equals(sortNameParam, SortType.DESC)) {
                criteriaBuilder.desc(root.get("name"));
            }
            if (Objects.equals(sortNameParam, SortType.ASC)) {
                criteriaBuilder.asc(root.get("name"));
            }
        }
        SortType sortDateParam = queryParameters.getSortDateParam();
        if (sortDateParam != null) {
            if (Objects.equals(sortDateParam, SortType.DESC)) {
                criteriaQuery.orderBy(criteriaBuilder.desc(root.get("createDate")));
            }
            if (Objects.equals(sortDateParam, SortType.ASC)) {
                criteriaQuery.orderBy(criteriaBuilder.asc(root.get("createDate")));
            }
        }
    }

    private static List<Predicate> getPredicates(QueryParameters queryParameters, CriteriaBuilder criteriaBuilder, Root<GiftCertificate> root) {
        List<Predicate> restrictions = new ArrayList<>();

        List<String> tagNames = queryParameters.getTagNames();
        if (tagNames != null && !tagNames.isEmpty()) {
            restrictions = Arrays.stream(tagNames.toArray())
                    .map(tagName -> criteriaBuilder.equal(root.join("tagList").get("name"), tagName))
                    .collect(Collectors.toList());
        }
        String partOfName = queryParameters.getPartOfName();
        if (!StringUtils.isEmpty(partOfName)) {
            restrictions.add(criteriaBuilder.like(root.get("name"), "%" + partOfName + "%"));
        }
        String partOfDescription = queryParameters.getPartOfDescription();
        if (!StringUtils.isEmpty(partOfDescription)) {
            restrictions.add(criteriaBuilder.like(root.get("description"), "%" + partOfDescription + "%"));
        }
        return restrictions;
    }


}
*/
