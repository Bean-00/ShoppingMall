package com.model2.mvc.service.purchase;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.PurchaseBuyer;
import com.model2.mvc.service.domain.Purchase;

import java.util.HashMap;
import java.util.List;

public interface PurchaseService {
    Purchase addPurchase(Purchase purchaseVO);

    Purchase getPurchase(int purchaseId);

    List<PurchaseBuyer> getPurchaseList(Search search, String buyerId);

    HashMap<String, Object> getSaleList(Search search);

    Purchase updatePurchase(Purchase purchaseVO);

    void TranCode(Purchase purchaseVO);

    int getAllPurchaseCount(String buyerId);
}
