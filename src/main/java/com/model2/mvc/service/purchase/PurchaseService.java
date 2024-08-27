package com.model2.mvc.service.purchase;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.service.purchase.vo.PurchaseVO;

import java.util.HashMap;
import java.util.List;

public interface PurchaseService {
    public PurchaseVO addPurchase(PurchaseVO purchaseVO);

    public PurchaseVO getPurchase(int purchaseId);

    public HashMap<String, Object> getPurchaseList();

    public HashMap<String, Object> getSaleList(SearchVO searchVO);

    public PurchaseVO updatePurchase(PurchaseVO purchaseVO);

    public void TranCode(PurchaseVO purchaseVO);
}
