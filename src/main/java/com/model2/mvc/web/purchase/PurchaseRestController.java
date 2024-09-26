package com.model2.mvc.web.purchase;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.purchase.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api/purchases/*")
public class PurchaseRestController {
    @Autowired
    @Qualifier("purchaseServiceImpl")
    PurchaseService purchaseService;

    @Autowired
    @Qualifier("productServiceImpl")
    ProductService productService;

    @Value("#{commonProperties['displayCount']}")
    //@Value("#{commonProperties['pageUnit'] ?: 3}")
    int displayCount;

    @Value("#{commonProperties['pageNumSize']}")
    //@Value("#{commonProperties['pageSize'] ?: 2}")
    int pageNumSize;

    @PostMapping({"/", ""})
    public ResponseEntity<Void> addPurchase(@RequestBody Purchase purchase) {

        purchaseService.addPurchase(purchase);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/{buyerId}")
    public ResponseEntity<Map<String, Object>> listPurchase(@PathVariable String buyerId) {

        Search search = new Search();
        search.setCurrentPage(0);
        search.setDisplayCount(this.displayCount);
        search.setPageNumSize(this.pageNumSize);
        search.setBuyerId(buyerId);

        int totalCount = purchaseService.getAllPurchaseCount(buyerId);
        List<Purchase> purchaseList = purchaseService.getPurchaseList(search);

        Map<String, Object> response = new HashMap<>();

        response.put("totalCount", totalCount);
        response.put("purchaseList", purchaseList);

        return ResponseEntity.ok(response);
    }

    @RequestMapping("/{prodNo}")
    public ResponseEntity<Void> updateTranCode(@PathVariable int prodNo) {

        purchaseService.updateTransCode(prodNo);

        return ResponseEntity.ok().build();
    }


}
