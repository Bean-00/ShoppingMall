package com.model2.mvc.service.purchase.impl;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.PurchaseBuyer;
import com.model2.mvc.service.purchase.dao.PurchaseDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

public class PurchaseDaoImpl implements PurchaseDAO {

    private PurchaseDAO purchaseDao;

    @Override
    public void insertPurchase(Purchase purchaseVO) {
        purchaseDao.insertPurchase(purchaseVO);
    }

    @Override
    public List<PurchaseBuyer> getPurchaseList(Search search, String buyerId) {
        return purchaseDao.getPurchaseList(search, buyerId);
    }

    @Override
    public int getPurchaseTotalCount(String buyerId) {
        return purchaseDao.getPurchaseTotalCount(buyerId);
    }

    @Override
    public void updateTransCode(String prodNo) {
        purchaseDao.updateTransCode(prodNo);
    }
}
