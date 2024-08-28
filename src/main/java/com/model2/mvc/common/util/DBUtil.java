package com.model2.mvc.common.util;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.service.user.vo.UserVO;

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
			 PreparedStatement psmt = getPreparedStatement(conn, sql, false, args)) {
			psmt.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> List<T> executeQuery(String sql, Function<ResultSet, T> mapperFn, Object... args) {
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement psmt = getPreparedStatement(conn, sql, false, args)) {
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

    public static <T> Map<String, Object> executePagingQuery(String sql, Function<ResultSet, T> mapperFn, SearchVO searchVO, Object... args) {
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement psmt = getPreparedStatement(conn, sql, true, args)) {
            Map<String, Object> map = new HashMap<>();

            ResultSet rs = psmt.executeQuery();
            rs.last();
            int total = rs.getRow();

            map.put("count", total);

            rs.absolute(searchVO.getPage() * searchVO.getPageUnit() - searchVO.getPageUnit() + 1);
            System.out.println("searchVO.getPage():" + searchVO.getPage());
            System.out.println("searchVO.getPageUnit():" + searchVO.getPageUnit());

            List<T> list = new ArrayList<>();
            if (total > 0) {
                for (int i = 0; i < searchVO.getPageUnit(); i++) {
                    T vo = mapperFn.apply(rs);

                    list.add(vo);
                    if (!rs.next())
                        break;
                }
            }

            System.out.println("list.size() : " + list.size());
            map.put("list", list);
            System.out.println("map().size() : " + map.size());


            return map;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static PreparedStatement getPreparedStatement(Connection conn, String sql, boolean isSearch, Object... args) throws SQLException {
        PreparedStatement psmt = isSearch ? conn.prepareStatement(sql.toString(),
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE) : conn.prepareStatement(sql);
        int index = 1;
        if (Objects.nonNull(args)) {
            for (Object arg : args) {
                psmt.setObject(index++, arg);
            }
        }

        return psmt;
    }
}
