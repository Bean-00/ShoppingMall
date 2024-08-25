package com.model2.mvc.service.user;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.service.user.vo.UserVO;

import java.util.Map;


public interface UserService {
	
	void addUser(UserVO userVO) throws Exception;

	UserVO loginUser(UserVO userVO) throws Exception;
	
	UserVO getUser(String userId) throws Exception;
	
	Map<String, Object> getUserList(SearchVO searchVO) throws Exception;
	
	void updateUser(UserVO userVO) throws Exception;
	
	boolean checkDuplication(String userId) throws Exception;

}