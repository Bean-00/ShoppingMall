package com.model2.mvc.service.purchase.impl;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.dao.PurchaseDao;
import com.model2.mvc.service.domain.PurchaseBuyer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("purchaseServiceImpl")
public class PurchaseServiceImpl implements PurchaseService {

    @Autowired
    @Qualifier("purchaseDaoImpl")
    private PurchaseDao purchaseDao;

    @Override
    public List<Purchase> getPurchaseList(Search search) {
        return purchaseDao.getPurchaseList(search);
    }

    @Override
    public int getAllPurchaseCount(String buyerId) {
        return purchaseDao.getPurchaseTotalCount(buyerId);
    }

    @Override
    public int checkPurchaseLog(String prodNo) {
        return purchaseDao.checkPurchaseLog(prodNo);
    }

    @Override
    public void deletePurchase(int tranNo) {
        purchaseDao.deletePurchase(tranNo);
    }

    @Override
    public void addPurchase(Purchase purchase) {
        purchaseDao.insertPurchase(purchase);
    }

    @Override
    public void updateTransCode(int prodNo) {
        purchaseDao.updateTransCode(prodNo);
    }

}
