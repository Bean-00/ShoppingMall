package com.model2.mvc.web.purchase;

import com.model2.mvc.service.purchase.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

@Controller
public class PurchaseController {
    @Autowired
    @Qualifier("purchaseServiceImpl")
    PurchaseService purchaseService;


}
