package com.model2.mvc.view.purchase;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.constant.PurchaseStatus;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.time.LocalDate;


public class AddPurchaseAction extends Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        ProductService productService = getBean("productServiceImpl", ProductService.class);
        PurchaseService purchaseService = getBean("purchaseServiceImpl", PurchaseService.class);

        Purchase purchase = new Purchase();
        purchase.setBuyer((User) request.getSession().getAttribute("user"));
        purchase.setDlvyAddr(request.getParameter("receiverAddr"));
        purchase.setDlvyDate(Date.valueOf(request.getParameter("receiverDate")));
        purchase.setDlvyRequest(request.getParameter("receiverRequest"));
        purchase.setOrderDate(Date.valueOf(LocalDate.now()));
        purchase.setPaymentOption(request.getParameter("paymentOption"));
        purchase.setPurchaseProd(productService.getProductByProdNo(Integer.parseInt(request.getParameter("prodNo"))));
        purchase.setReceiverName(request.getParameter("receiverName"));
        purchase.setReceiverPhone(request.getParameter("receiverPhone"));
        purchase.setStatus(PurchaseStatus.PURCHASED.getCode());

        purchaseService.addPurchase(purchase);

        request.setAttribute("purchase", purchase);


        return "forward:/purchase/addPurchase.jsp";
    }
}
