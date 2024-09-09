package com.model2.mvc.view.user;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.user.UserService;
import com.model2.mvc.service.domain.User;

public class LoginAction extends Action {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String inputId = request.getParameter("userId");
		String inputPw = request.getParameter("password");

			User loginInfo = new User(inputId, inputPw);

			UserService userService = getBean("userServiceImpl", UserService.class);
			User user = userService.loginUser(loginInfo);

			if (Objects.isNull(user)) {
				return "forward:/user/fail-loginView.jsp";
			}

			HttpSession session = request.getSession();
			session.setAttribute("user", user);

		return "redirect:/index.jsp";
	}
}