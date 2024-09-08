package com.model2.mvc.view.user;

import java.util.Objects;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.user.UserService;
import com.model2.mvc.service.user.dao.UserDao;
import com.model2.mvc.service.user.impl.UserDaoImpl;
import com.model2.mvc.service.user.impl.UserServiceImpl;
import com.model2.mvc.service.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.context.support.XmlWebApplicationContext;

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