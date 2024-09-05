package com.model2.mvc.service.user.dao;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

import static com.model2.mvc.common.util.DBUtil.executeQuery;
import static com.model2.mvc.common.util.DBUtil.executeUpdate;
import static com.model2.mvc.common.util.SQLUtil.makeSearchClause;


@Repository("userDAO")
public class UserDAO {
    @Autowired
    @Qualifier("sqlSessionTemplate")
    private SqlSession sqlSession;

    private final Function<ResultSet, User> mapperFn = (rs) -> {
        try {
            User userVO = new User();
            userVO.setUserId(rs.getString("USER_ID"));
            userVO.setUserName(rs.getString("USER_NAME"));
            userVO.setPassword(rs.getString("PASSWORD"));
            userVO.setRole(rs.getString("ROLE"));
            userVO.setSsn(rs.getString("SSN"));
            userVO.setPhone(rs.getString("CELL_PHONE"));
            userVO.setAddr(rs.getString("ADDR"));
            userVO.setEmail(rs.getString("EMAIL"));
            userVO.setRegDate(rs.getDate("REG_DATE"));

            return userVO;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    };

    public UserDAO() {
    }

    public static int getAllUserCount(Search search) {
        Function<ResultSet, Integer> mapperFn = (rs) -> {
                try {
                    int totalCount = rs.getInt("totalCount");
                    return totalCount;
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
        };

        StringBuilder sql = new StringBuilder("SELECT\n" +
                "    COUNT(user_id) AS \"totalCount\"\n" +
                "FROM USERS\n");
        if (Objects.nonNull(search.getSearchCondition())) {
            sql.append(makeSearchClause(search, "USER_ID", "USER_NAME"));
        }
        return executeQuery(sql.toString(),mapperFn).get(0);
    }

    public void insertUser(User userVO) {
        String sql = "insert into USERS values (?,?,?,'user',?,?,?,?,sysdate)";
        executeUpdate(sql, userVO.getUserId(), userVO.getUserName(), userVO.getPassword(), userVO.getSsn(), userVO.getPhone(), userVO.getAddr(), userVO.getEmail());
    }

    public User findUser(String userId) {
        String sql = "select * from USERS where USER_ID=?";
        List<User> userList = executeQuery(sql, mapperFn, userId);

        return userList.size() > 0 ? userList.get(0) : null;
    }

    public User findUser(String userId, String userPw) {
        String sql = "select * from USERS where USER_ID=? AND PASSWORD=?";
        List<User> userList = executeQuery(sql, mapperFn, userId, userPw);

        return userList.size() > 0 ? userList.get(0) : null;
    }


    public List<User> getUserList(Search search) {
        final Function<ResultSet, User> mapperFn = (rs) -> {
            try {
                User userVO = new User();
                userVO.setUserId(rs.getString("USER_ID"));
                userVO.setUserName(rs.getString("USER_NAME"));
                userVO.setEmail(rs.getString("EMAIL"));
                userVO.setRowNum(rs.getInt("ROW_NUM"));

                return userVO;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        };
        StringBuilder sql = new StringBuilder("SELECT ROW_NUM, user_id, user_name, email" +
                " FROM (SELECT ROW_NUMBER() over (ORDER BY USER_ID) AS ROW_NUM,\n" +
                "             user_id,\n" +
                "             user_name,\n" +
                "             email\n" +
                "      FROM USERS\n");

        if (Objects.nonNull(search.getSearchCondition())) {
            sql.append(makeSearchClause(search, "USER_ID", "USER_NAME"));
        }

        sql.append(") U\n");
        sql.append("WHERE ROW_NUM BETWEEN ? AND ?");
        sql.append(" order by USER_ID");

        List<User> userList = executeQuery(sql.toString(), mapperFn, search.getStartIndex(), search.getEndIndex());

        return userList;
    }

    public void updateUser(User userVO) {
        String sql = "update USERS set USER_NAME=?,CELL_PHONE=?,ADDR=?,EMAIL=? where USER_ID=?";
        executeUpdate(sql, userVO.getUserName(), userVO.getPhone(), userVO.getAddr(), userVO.getEmail(), userVO.getUserId());
    }

    public void deleteUser(String userId) {
        sqlSession.delete("UserMapper.deleteUser", userId);
    }
}