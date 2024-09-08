package com.model2.mvc.service.user.dao;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.User;

import java.util.List;

public interface UserDao {

    int getTotalUserCount(Search search);

    void insertUser(User user);

    User getUserById(String userId);

    User loginUser(User user);

    List<User> getUserList(Search search);

    void updateUser(User user);

    void deleteUser(String userId);
}