package com.model2.mvc.service.purchase.impl;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.dao.PurchaseDAO;
import com.model2.mvc.service.purchase.vo.PurchaseVO;

import java.util.HashMap;

public class PurchaseServiceImpl implements PurchaseService {

    private PurchaseDAO purchaseDAO;

    public PurchaseServiceImpl() {
        purchaseDAO = new PurchaseDAO();
    }

    @Override
    public PurchaseVO addPurchase(PurchaseVO purchaseVO) {
        purchaseDAO.insertPurchase(purchaseVO);
        return null;
    }

    @Override
    public PurchaseVO getPurchase(int purchaseId) {
        return null;
    }

    @Override
    public HashMap<String, Object> getPurchaseList() {
        return null;
    }

    @Override
    public HashMap<String, Object> getSaleList(SearchVO searchVO) {
        return null;
    }

    @Override
    public PurchaseVO updatePurchase(PurchaseVO purchaseVO) {
        return null;
    }

    @Override
    public void TranCode(PurchaseVO purchaseVO) {

    }
}
