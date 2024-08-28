package com.model2.mvc.service.user.impl;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.service.user.UserService;
import com.model2.mvc.service.user.dao.UserDAO;
import com.model2.mvc.service.user.vo.UserVO;

import java.util.List;
import java.util.Map;


public class UserServiceImpl implements UserService{
	
	private UserDAO userDAO;
	
	public UserServiceImpl() {
		userDAO=new UserDAO();
	}

	public void addUser(UserVO userVO) throws Exception {
		userDAO.insertUser(userVO);
	}

	public UserVO loginUser(UserVO userVO) throws Exception {
			UserVO dbUser=userDAO.findUser(userVO.getUserId());

			if(! dbUser.getPassword().equals(userVO.getPassword()))
				throw new Exception("로그인에 실패했습니다.");
			
			return dbUser;
	}

	public UserVO getUser(String userId) throws Exception {
		return userDAO.findUser(userId);
	}

	public List<UserVO> getUserList(SearchVO searchVO) {
		return userDAO.getUserList(searchVO);
	}

	public void updateUser(UserVO userVO) {
		userDAO.updateUser(userVO);
	}

	public boolean checkDuplication(String userId) {
		boolean result=true;
		UserVO userVO=userDAO.findUser(userId);
		if(userVO != null) {
			result=false;
		}
		return result;
	}

	@Override
	public int getUserTotalCount(SearchVO searchVO) {
		return UserDAO.getAllUserCount(searchVO);
	}
}