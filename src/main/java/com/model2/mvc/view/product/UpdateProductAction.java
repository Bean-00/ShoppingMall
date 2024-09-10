package com.model2.mvc.view.product;

import java.sql.Date;
import java.time.LocalDate;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.domain.Product;

/**
 * Servlet implementation class UpdateProductAction
 */
@WebServlet("/UpdateProductAction")
public class UpdateProductAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int productNo = Integer.parseInt(request.getParameter("prodNo"));

        Product productVO = new Product();

        productVO.setProdNo(productNo);
        productVO.setProdName(request.getParameter("prodName"));
        productVO.setProdDetail(request.getParameter("prodDetail"));
        productVO.setRegDate(Date.valueOf(LocalDate.now()));
        productVO.setPrice(Integer.parseInt(request.getParameter("price")));
        productVO.setManuDate(request.getParameter("manuDate"));
        productVO.setFileName(request.getParameter("fileName"));

        ProductService service = getBean("productServiceImpl", ProductService.class);
        service.updateProduct(productVO);
        request.setAttribute("prodNo", productVO.getProdNo());

        return "redirect:/getProduct.do?prodNo=" + productNo;
    }

}
