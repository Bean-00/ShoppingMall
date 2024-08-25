package com.model2.mvc.service.product;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.service.product.vo.ProductVO;

import java.util.Map;


public interface ProductService {
	void addProduct (ProductVO productVO);

	void updateProduct (ProductVO productVO);

	void deleteProduct (ProductVO productVO);

	Map<String, Object> getAllProduct (SearchVO searchVO);

	ProductVO getProduct (String productNo);
	
	Map<String, Object> getProductList (SearchVO searchVO);

}