package com.model2.mvc.view.product;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.common.util.SessionUtil;
import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.domain.Product;

import java.util.StringJoiner;

public class GetProductAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String prodNo = request.getParameter("prodNo");
        String cookieValue = SessionUtil.getCookieValue(request.getCookies(), SessionUtil.HISTORY_NAME)
                .map(str -> {
                    StringJoiner sj = new StringJoiner("|");
                    sj.add(str);
                    sj.add(prodNo);
                    return sj.toString();
                })
                .orElse(prodNo);

        Cookie cookie = SessionUtil.createCookie(SessionUtil.HISTORY_NAME, cookieValue);

        response.addCookie(cookie);

        int productNo = Integer.parseInt(request.getParameter("prodNo"));

        ProductService productService = new ProductServiceImpl();
        Product pvo = productService.getProductByProdNo(productNo);

        User user = (User) request.getSession().getAttribute("user");

        request.setAttribute("product", pvo);
        request.setAttribute("user", user);

        return "forward:/product/getProduct.jsp";
    }

}