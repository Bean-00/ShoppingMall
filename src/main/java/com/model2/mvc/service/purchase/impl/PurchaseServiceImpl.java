package com.model2.mvc.service.purchase.impl;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.service.product.vo.ProductStatusVO;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.dao.PurchaseDAO;
import com.model2.mvc.service.purchase.vo.PurchaseBuyerVO;
import com.model2.mvc.service.purchase.vo.PurchaseVO;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;

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
    public List<PurchaseBuyerVO> getPurchaseList(SearchVO searchVO) {
        return purchaseDAO.getPurchaseList(searchVO);
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
    public int getAllPurchaseCount() {
        return purchaseDAO.getPurchaseTotalCount();
    }
}
