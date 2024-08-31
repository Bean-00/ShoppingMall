package com.model2.mvc.service.user;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.User;

import java.util.List;


public interface UserService {
	
	void addUser(User userVO) throws Exception;

	User loginUser(User userVO) throws Exception;
	
	User getUser(String userId) throws Exception;
	
	List<User> getUserList(Search search) throws Exception;
	
	void updateUser(User userVO) throws Exception;
	
	boolean checkDuplication(String userId) throws Exception;

	int getUserTotalCount(Search search);
}