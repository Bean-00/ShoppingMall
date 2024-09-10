package com.model2.mvc.service.product.dao;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.ProductStatus;

import java.util.List;

public interface ProductDao {

    int getTotalProductCount(Search search);

    void insertProduct(Product product);

    Product getProduct(int prodNo);

    List<ProductStatus> getProductWithStatusList (Search search);

    Product getProductByProdNo(int prodNo);

    void updateProduct(Product product);
    
    void deleteProduct(String prodName);

}