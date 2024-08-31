package com.model2.mvc.view.purchase;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.dao.ProductDAO;
import com.model2.mvc.service.purchase.constant.PurchaseStatus;
import com.model2.mvc.service.purchase.dao.PurchaseDAO;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.time.LocalDate;


public class AddPurchaseAction extends Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        ProductDAO productDAO = new ProductDAO();

        Purchase purchaseVO = new Purchase();
        purchaseVO.setBuyer((User) request.getSession().getAttribute("user"));
        purchaseVO.setDivyAddr(request.getParameter("receiverAddr"));
        purchaseVO.setDivyDate(Date.valueOf(request.getParameter("receiverDate")));
        purchaseVO.setDivyRequest(request.getParameter("receiverRequest"));
        purchaseVO.setOrderDate(Date.valueOf(LocalDate.now()));
        purchaseVO.setPaymentOption(request.getParameter("paymentOption"));
        purchaseVO.setPurchaseProd(productDAO.getProductByProdNo(request.getParameter("prodNo")));
        purchaseVO.setReceiverName(request.getParameter("receiverName"));
        purchaseVO.setReceiverPhone(request.getParameter("receiverPhone"));
        purchaseVO.setStatus(PurchaseStatus.PURCHASED);

        PurchaseDAO purchaseDAO = new PurchaseDAO();
        purchaseDAO.insertPurchase(purchaseVO);

        request.setAttribute("purchase", purchaseVO);


        return "forward:/purchase/addPurchase.jsp";
    }
}
