package com.model2.mvc.view.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.dao.ProductDAO;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.domain.Product;

/**
 * Servlet implementation class UpdateProductViewAction
 */

public class UpdateProductViewAction extends Action {
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int productNo = Integer.parseInt(request.getParameter("prodNo"));

		ProductService productService = new ProductServiceImpl();
		ProductDAO productDAO = new ProductDAO();
		Product pvo = productDAO.getProductByProdNo(String.valueOf(productNo));
		productService.updateProduct(pvo);

		request.setAttribute("product", pvo);

		return "forward:/product/updateProductView.jsp";
	}

}
