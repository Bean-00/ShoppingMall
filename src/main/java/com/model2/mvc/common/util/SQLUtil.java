package com.model2.mvc.common.util;

import com.model2.mvc.common.Search;

public class SQLUtil {
    public static String makeLikeClause(String columnName, String searchString) {
        return makeLikeClause(columnName, searchString, "where");
    }

    public static String makeLikeClause(String columnName, String searchString, String prefix) {
        StringBuilder sb = new StringBuilder();
        sb.append(prefix);
        sb.append(" ");
        sb.append(columnName);
        sb.append(" ");
        sb.append(" LIKE '%");
        sb.append(searchString);
        sb.append("%'");

        return sb.toString();
    }

    public static String makeSearchClause(Search search, String... columnNames) {
        int index = 0;
        for (String columnName : columnNames) {
            if (String.valueOf(index++).equals(search.getSearchCondition())) {
                return makeLikeClause(columnName, search.getSearchKeyword());
            }
        }
        return null;
    }
}
