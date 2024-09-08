package com.model2.mvc.service.user.impl;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.user.UserService;
import com.model2.mvc.service.user.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userServiceImpl")
public class UserServiceImpl implements UserService {

    @Autowired
    @Qualifier("userDaoImpl")
    private UserDao userDao;

    public void addUser(User userVO) {
        userDao.insertUser(userVO);
    }

    public User loginUser(User userVO) {
        User dbUser = userDao.loginUser(userVO);

        return dbUser;
    }

    public User getUserByUserId(String userId) {
        return userDao.getUserById(userId);
    }

    public List<User> getUserList(Search search) {
        return userDao.getUserList(search);
    }

    public void updateUser(User userVO) {
        userDao.updateUser(userVO);
    }

    public boolean checkDuplication(String userId) {
        boolean result = true;
        User userVO = userDao.getUserById(userId);
        if (userVO != null) {
            result = false;
        }
        return result;
    }

    @Override
    public int getTotalUserCount(Search search) {
        return userDao.getTotalUserCount(search);
    }

    @Override
    public void deleteUser(String userId) {
        userDao.deleteUser(userId);
    }
}