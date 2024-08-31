package com.model2.mvc.view.user;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.user.UserService;
import com.model2.mvc.service.user.dao.UserDAO;
import com.model2.mvc.service.user.impl.UserServiceImpl;
import com.model2.mvc.service.domain.User;

public class LoginAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String inputId = request.getParameter("userId");
		String inputPw = request.getParameter("password");
		
		UserDAO userDAO = new UserDAO();
//		UserVO userVO = new UserVO();
		
		User userVO = userDAO.findUser(inputId, inputPw);
		
		if (Objects.isNull(userVO)) {
			return "forward:/user/fail-loginView.jsp";
		}

		userVO.setUserId(inputId);
		userVO.setPassword(inputPw);

		UserService service = new UserServiceImpl();
		User dbVO = service.loginUser(userVO);

		HttpSession session = request.getSession();
		session.setAttribute("user", dbVO);

		return "redirect:/index.jsp";
	}
}