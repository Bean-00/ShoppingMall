package com.model2.mvc.common.util;

import java.sql.*;
import java.util.*;
import java.util.function.Function;


public class DBUtil {

    private final static String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    private final static String JDBC_URL = "jdbc:oracle:thin:scott/tiger@129.154.220.177:1521:xe";

    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(JDBC_URL);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void executeUpdate(String sql, Object... args) {
        try (Connection conn = getConnection();
			 PreparedStatement psmt = getPreparedStatement(conn, sql, args)) {
			psmt.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> List<T> executeQuery(String sql, Function<ResultSet, T> mapperFn, Object... args) {
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement psmt = getPreparedStatement(conn, sql, args)) {
            List<T> list = new ArrayList<>();
            ResultSet rs = psmt.executeQuery();

            while (rs.next()) {
                T result = mapperFn.apply(rs);
                list.add(result);
            }

            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    private static PreparedStatement getPreparedStatement(Connection conn, String sql, Object... args) throws SQLException {
        PreparedStatement psmt = conn.prepareStatement(sql);
        int index = 1;
        if (Objects.nonNull(args)) {
            for (Object arg : args) {
                psmt.setObject(index++, arg);
            }
        }

        return psmt;
    }
}
