package com.model2.mvc.view.product;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.service.product.ProductService;
import com.model2.service.product.impl.ProductServiceImpl;

public class GetProductAction extends Action {
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String userId = request.getParameter("userId");

//		ProductService service = new ProductServiceImpl();
//      ProductVO pvo = service.getProductList();
//
//
//		request.setAttribute("pvo", pvo);

		return "forward:/product/readUser.jsp";
	}

}
