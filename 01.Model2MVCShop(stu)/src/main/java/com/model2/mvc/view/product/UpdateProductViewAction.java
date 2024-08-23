package com.model2.mvc.view.product;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.user.UserService;
import com.model2.mvc.service.user.impl.UserServiceImpl;
import com.model2.mvc.service.user.vo.UserVO;

/**
 * Servlet implementation class UpdateProductViewAction
 */
@WebServlet("/UpdateProductViewAction")
public class UpdateProductViewAction extends Action {
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String userId = request.getParameter("userId");

		UserService service = new UserServiceImpl();
		UserVO vo = service.getUser(userId);

		request.setAttribute("vo", vo);

		return "forward:/user/readUser.jsp";
	}

}
