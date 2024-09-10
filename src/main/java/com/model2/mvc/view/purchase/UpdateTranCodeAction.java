package com.model2.mvc.view.purchase;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.purchase.PurchaseService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

public class UpdateTranCodeAction extends Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String prodNo = request.getParameter("prodNo");
        String tranCode = request.getParameter("tranCode");
        int page = request.getParameter("page") == null ? 1 : Integer.parseInt(request.getParameter("page"));

        PurchaseService purchaseService = getBean("purchaseServiceImpl", PurchaseService.class);
        purchaseService.updateTransCode(Integer.parseInt(prodNo));
        if (Objects.isNull(request.getParameter("role"))) {
            return "/listProduct.do?menu=manage&page=" + page;
        }
        if (request.getParameter("role").equals("Buyer")){
            StringBuilder path = new StringBuilder("forward:/listPurchase.do?buyerId=");
            path.append(request.getParameter("buyerId"));
            path.append("&page=");
            path.append(page);
            return path.toString();
        }
        return "/listProduct.do?menu=manage&page=" + page;
    }
}
