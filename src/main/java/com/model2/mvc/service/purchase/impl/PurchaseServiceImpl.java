package com.model2.mvc.service.purchase.impl;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.dao.PurchaseDAO;
import com.model2.mvc.service.domain.PurchaseBuyer;
import com.model2.mvc.service.domain.Purchase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service("purchaseServiceImpl")
public class PurchaseServiceImpl implements PurchaseService {

    @Autowired
    @Qualifier("purchaseDaoImpl")
    private PurchaseDAO purchaseDAO;

    @Override
    public List<PurchaseBuyer> getPurchaseList(Search search, String buyerId) {
        return purchaseDAO.getPurchaseList(search, buyerId);
    }

    @Override
    public HashMap<String, Object> getSaleList(Search search) {
        return null;
    }

    @Override
    public Purchase updatePurchase(Purchase purchaseVO) {
        return null;
    }

    @Override
    public void TranCode(Purchase purchaseVO) {

    }

    @Override
    public int getAllPurchaseCount(String buyerId) {
        return purchaseDAO.getPurchaseTotalCount(buyerId);
    }
}
