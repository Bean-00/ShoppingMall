package com.model2.mvc.view.product;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.product.vo.ProductVO;

public class GetProductAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String productNo = request.getParameter("prodNo");

		ProductService productService = new ProductServiceImpl();
		ProductVO pvo = productService.getProduct(productNo);

		request.setAttribute("pvo", pvo);

		return "forward:/product/getProduct.jsp";
	}

}