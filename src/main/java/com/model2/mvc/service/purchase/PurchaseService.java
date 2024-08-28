package com.model2.mvc.service.purchase;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.service.product.vo.ProductStatusVO;
import com.model2.mvc.service.purchase.vo.PurchaseBuyerVO;
import com.model2.mvc.service.purchase.vo.PurchaseVO;

import java.util.HashMap;
import java.util.List;

public interface PurchaseService {
    PurchaseVO addPurchase(PurchaseVO purchaseVO);

    PurchaseVO getPurchase(int purchaseId);

    List<PurchaseBuyerVO> getPurchaseList(SearchVO searchVO);

    HashMap<String, Object> getSaleList(SearchVO searchVO);

    PurchaseVO updatePurchase(PurchaseVO purchaseVO);

    void TranCode(PurchaseVO purchaseVO);

    int getAllPurchaseCount();
}
