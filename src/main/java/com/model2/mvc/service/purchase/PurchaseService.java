package com.model2.mvc.service.purchase;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.PurchaseBuyer;

import java.util.List;

public interface PurchaseService {

    List<Purchase> getPurchaseList(Search search);

    int getAllPurchaseCount(String buyerId);

    int checkPurchaseLog(String prodNo);

    void deletePurchase(int tranNo);

    void addPurchase(Purchase purchase);

    void updateTransCode(int prodNo);
}
