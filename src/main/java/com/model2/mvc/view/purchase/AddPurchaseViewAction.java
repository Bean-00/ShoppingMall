package com.model2.mvc.view.purchase;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.dao.ProductDAO;
import com.model2.mvc.service.domain.Product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AddPurchaseViewAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        ProductService productService = getBean("productServiceImpl", ProductService.class);
        Product productVO = productService.getProductByProdNo(Integer.parseInt(request.getParameter("prodNo")));
        User user = (User)request.getSession().getAttribute("user");

        request.setAttribute("product", productVO);
        request.setAttribute("user", user);

        return "forward:/purchase/addPurchaseView.jsp";
    }
}
