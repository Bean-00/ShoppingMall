package com.model2.mvc.service.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.function.Function;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.common.util.DBUtil;
import com.model2.mvc.common.util.SQLUtil;
import com.model2.mvc.service.user.vo.UserVO;

import javax.swing.tree.RowMapper;

import static com.model2.mvc.common.util.DBUtil.*;


public class UserDAO {
    private final Function<ResultSet, UserVO> mapperFn = (rs) -> {
        try {
            UserVO userVO = new UserVO();
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

    public void insertUser(UserVO userVO) {
        String sql = "insert into USERS values (?,?,?,'user',?,?,?,?,sysdate)";
        executeUpdate(sql, userVO.getUserId(), userVO.getUserName(), userVO.getPassword(), userVO.getSsn(), userVO.getPhone(), userVO.getAddr(), userVO.getEmail());
    }

    public UserVO findUser(String userId) {
        String sql = "select * from USERS where USER_ID=?";
        List<UserVO> userList = executeQuery(sql, mapperFn, userId);

        return userList.size() > 0 ? userList.get(0) : null;
    }

    public UserVO findUser(String userId, String userPw) {
        String sql = "select * from USERS where USER_ID=? AND PASSWORD=?";
        List<UserVO> userList = executeQuery(sql, mapperFn, userId, userPw);

        return userList.size() > 0 ? userList.get(0) : null;
    }


    public Map<String, Object> getUserList(SearchVO searchVO) {
        StringBuilder sql = new StringBuilder("select * from USERS ");
        if (searchVO.getSearchCondition() != null) {
            if (searchVO.getSearchCondition().equals("0")) {
                sql.append(SQLUtil.makeLikeClause("USER_ID", searchVO.getSearchKeyword()));
            } else if (searchVO.getSearchCondition().equals("1")) {
                sql.append(SQLUtil.makeLikeClause("USER_NAME", searchVO.getSearchKeyword()));
            }
        }
        sql.append(" order by USER_ID");

        Map<String, Object> result = executePagingQuery(sql.toString(), mapperFn, searchVO);

        return result;
    }

    public void updateUser(UserVO userVO) throws Exception {
        String sql = "update USERS set USER_NAME=?,CELL_PHONE=?,ADDR=?,EMAIL=? where USER_ID=?";
        executeUpdate(sql, userVO.getUserName(), userVO.getPhone(), userVO.getAddr(), userVO.getEmail(), userVO.getUserId());
    }
}