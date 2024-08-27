package com.model2.mvc.view.purchase;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
import com.model2.mvc.service.purchase.vo.PurchaseVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AddPurchaseViewAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        PurchaseService purchaseService = new PurchaseServiceImpl();
        ProductVO productVO = new ProductVO();
        PurchaseVO purchaseVO = new PurchaseVO();


        return "forward:/purchase/addPurchaseView.jsp";
    }
}
