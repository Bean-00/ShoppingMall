package com.model2.mvc.view.purchase;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.dao.ProductDAO;
import com.model2.mvc.service.domain.Product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AddPurchaseViewAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProductDAO productDAO = new ProductDAO();
        Product productVO = productDAO.getProductByProdNo(request.getParameter("prodNo"));

        request.setAttribute("productVO", productVO);

        return "forward:/purchase/addPurchaseView.jsp";
    }
}
