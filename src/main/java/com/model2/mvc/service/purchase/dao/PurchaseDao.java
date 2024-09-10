package com.model2.mvc.service.purchase.dao;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.PurchaseBuyer;
import com.model2.mvc.service.domain.Purchase;

import java.util.List;


public interface PurchaseDao {
    void insertPurchase(Purchase purchaseVO);

    List<PurchaseBuyer> getPurchaseList(Search search);

    int getPurchaseTotalCount(String buyerId);

    void updateTransCode(int prodNo);

    void deletePurchase(int prodNo);
}
