package com.model2.mvc.view.product;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.dao.ProductDAO;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.user.UserService;
import com.model2.mvc.service.user.impl.UserServiceImpl;
import com.model2.mvc.service.user.vo.UserVO;

/**
 * Servlet implementation class UpdateProductViewAction
 */

public class UpdateProductViewAction extends Action {
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int productNo = Integer.parseInt(request.getParameter("prodNo"));

		ProductService productService = new ProductServiceImpl();
		ProductDAO productDAO = new ProductDAO();
		ProductVO pvo = productDAO.getProductByProdNo(String.valueOf(productNo));
		productService.updateProduct(pvo);

		request.setAttribute("productVO", pvo);

		return "forward:/product/updateProductView.jsp";
	}

}
