package com.model2.mvc.common.util;

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
}
