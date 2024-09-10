package com.model2.mvc.service.product.test;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.ProductStatus;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.user.UserService;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:config/commonservice.xml"})
public class ProductServiceTest {

    //==>@RunWith,@ContextConfiguration 이용 Wiring, Test 할 instance DI
    @Autowired
    @Qualifier("productServiceImpl")
    private ProductService productService;

//    @Before
    public void setUp() {
        productService.deleteProductByName("testName");
    }

    @Test
//    @Ignore
    public void testAddProduct() throws Exception {

        Product product = new Product();
        ProductStatus productStatus = new ProductStatus();


        product.setFileName("123");
        product.setPrice(1000);
        product.setRegDate(Date.valueOf("20000122"));
        product.setManuDate("99-01-22");
        product.setProdDetail("detail");
        product.setProdName("testName");


        productService.addProduct(product);

        Search search = new Search();
        search.setSearchCondition("1");
        search.setSearchKeyword("testName");


        productStatus = productService.getProductWithStatusList(search).get(0);


        //==> API 확인
        assertEquals("testName", productStatus.getProductName());
        assertEquals(1000, productStatus.getPrice());
    }

    @Test
    public void testGetProduct() throws Exception {
        ProductStatus productStatus = new ProductStatus();

        Search search = new Search();
        search.setSearchCondition("1");
        search.setSearchKeyword("testName");

        productStatus = productService.getProductWithStatusList(search).get(0);


        //==> API 확인
        assertEquals("testName", productStatus.getProductName());
        assertEquals(1000, productStatus.getPrice());
    }

    @Test
    @Ignore
    public void testUpdateProduct() throws Exception{

        ProductStatus productStatus = new ProductStatus();

        Search search = new Search();
        search.setSearchCondition("1");
        search.setSearchKeyword("testName");

        productStatus = productService.getProductWithStatusList(search).get(0);

        Product product = productService.getProductByProdNo(productStatus.getProdNo());
        Assert.assertNotNull(product);

        assertEquals("testName", product.getProdName());
        assertEquals(1000, product.getPrice());


        product.setPrice(100);

        productService.updateProduct(product);

        //==> API 확인
        product = productService.getProductByProdNo(productStatus.getProdNo());
        assertEquals(100, product.getPrice());
    }


}