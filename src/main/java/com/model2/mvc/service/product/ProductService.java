package com.model2.mvc.service.product;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.ProductStatus;
import com.model2.mvc.service.domain.Product;

import java.util.List;


public interface ProductService {
	void addProduct (Product productVO);

	void updateProduct (Product productVO);

	void deleteProductByName (String prodName);

	Product getProductByProdNo(int productNo);
	
	List<ProductStatus> getProductWithStatusList (Search search);

	int getAllProductCount (Search search);
}