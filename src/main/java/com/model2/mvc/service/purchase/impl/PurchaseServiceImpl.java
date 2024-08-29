package com.model2.mvc.service.purchase.impl;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.dao.PurchaseDAO;
import com.model2.mvc.service.purchase.vo.PurchaseBuyerVO;
import com.model2.mvc.service.purchase.vo.PurchaseVO;

import java.util.HashMap;
import java.util.List;

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
    public List<PurchaseBuyerVO> getPurchaseList(SearchVO searchVO, String buyerId) {
        return purchaseDAO.getPurchaseList(searchVO, buyerId);
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

    @Override
    public int getAllPurchaseCount(String buyerId) {
        return purchaseDAO.getPurchaseTotalCount(buyerId);
    }
}
