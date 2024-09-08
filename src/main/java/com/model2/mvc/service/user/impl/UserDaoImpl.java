package com.model2.mvc.service.user.impl;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.user.dao.UserDao;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository("userDaoImpl")
public class UserDaoImpl implements UserDao {

    @Autowired
    @Qualifier("sqlSessionTemplate")
    private SqlSession sqlSession;

    @Override
    public int getTotalUserCount(Search search) {
        return sqlSession.selectOne("UserMapper.getTotalUserCount", search);
    }

    @Override
    public void insertUser(User user) {
        sqlSession.insert("UserMapper.addUser", user);
    }

    @Override
    public User getUserById(String userId) {
        return sqlSession.selectOne("UserMapper.getUserById", userId);
    }


    @Override
    public User loginUser(User user) {
        return sqlSession.selectOne("UserMapper.loginUser", user);
    }

    @Override
    public List<User> getUserList(Search search) {
        return sqlSession.selectList("UserMapper.getUserList", search);
    }

    @Override
    public void updateUser(User user) {
        sqlSession.update("UserMapper.updateUser", user);
    }

    @Override
    public void deleteUser(String userId) {
        sqlSession.delete("UserMapper.deleteUser", userId);
    }
}
