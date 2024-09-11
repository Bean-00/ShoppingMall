package com.model2.mvc.service.purchase.test;

import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.constant.PurchaseStatus;
import com.model2.mvc.service.user.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Date;
import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
//==> Meta-Data 를 다양하게 Wiring 하자...
//@ContextConfiguration(locations = { "classpath:config/context-*.xml" })
@ContextConfiguration	(locations = {	"classpath:config/context-common.xml",
        "classpath:config/context-aspect.xml",
        "classpath:config/context-mybatis.xml",
        "classpath:config/context-transaction.xml" })

public class PurchaseServiceTest {

    //==>@RunWith,@ContextConfiguration 이용 Wiring, Test 할 instance DI
    @Autowired
    @Qualifier("purchaseServiceImpl")
    private PurchaseService purchaseService;

    @Autowired
    @Qualifier("productServiceImpl")
    private ProductService productService;

    @Autowired
    @Qualifier("userServiceImpl")
    private UserService userService;


//    @Before
//    public void setUp() {
//        purchaseService.deletePurchase();
//    }

    @Test
//    @Ignore
    public void testAddPurchase() throws Exception {


        Purchase purchase = new Purchase();
        purchase.setBuyer(userService.getUserByUserId("user02"));
        purchase.setDlvyAddr("test");
        purchase.setDlvyDate(Date.valueOf("2000-01-22"));
        purchase.setDlvyRequest("sss");
        purchase.setOrderDate(Date.valueOf(LocalDate.now()));
        purchase.setPaymentOption("0");
        purchase.setPurchaseProd(productService.getProductByProdNo(10025));
        purchase.setReceiverName("name");
        purchase.setReceiverPhone("01000000000");
        purchase.setStatus(PurchaseStatus.PURCHASED.getCode());

        purchaseService.addPurchase(purchase);


        //==> API 확인
        assertEquals(1, purchaseService.getAllPurchaseCount("user02"));

    }

    @Test
    public void testGetPurchaseList() throws Exception {
        assertEquals(1, purchaseService.getAllPurchaseCount("user02"));
    }


}