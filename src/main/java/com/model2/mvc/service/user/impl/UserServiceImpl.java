package com.model2.mvc.service.user.impl;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.user.UserService;
import com.model2.mvc.service.user.dao.UserDAO;
import com.model2.mvc.service.domain.User;

import java.util.List;


public class UserServiceImpl implements UserService{
	
	private UserDAO userDAO;
	
	public UserServiceImpl() {
		userDAO=new UserDAO();
	}

	public void addUser(User userVO) throws Exception {
		userDAO.insertUser(userVO);
	}

	public User loginUser(User userVO) throws Exception {
			User dbUser=userDAO.findUser(userVO.getUserId());

			if(! dbUser.getPassword().equals(userVO.getPassword()))
				throw new Exception("로그인에 실패했습니다.");
			
			return dbUser;
	}

	public User getUser(String userId) throws Exception {
		return userDAO.findUser(userId);
	}

	public List<User> getUserList(Search search) {
		return userDAO.getUserList(search);
	}

	public void updateUser(User userVO) {
		userDAO.updateUser(userVO);
	}

	public boolean checkDuplication(String userId) {
		boolean result=true;
		User userVO=userDAO.findUser(userId);
		if(userVO != null) {
			result=false;
		}
		return result;
	}

	@Override
	public int getUserTotalCount(Search search) {
		return UserDAO.getAllUserCount(search);
	}
}